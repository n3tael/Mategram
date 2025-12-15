#!/usr/bin/env bash
set -euo pipefail

# build_vpx.sh
# Usage:
#   ./build_vpx.sh [--ndk PATH] [--api N] [--abis "armeabi-v7a arm64-v8a x86 x86_64"] [--jobs N] [--vpx-root PATH]
#
# Defaults:
#   NDK default is taken from ANDROID_NDK_HOME or '/usr/lib/android-sdk/ndk/21.4.7075529'
#   API default: 21
#   ABIS default: "armeabi-v7a arm64-v8a x86 x86_64"
#   JOBS default: number of processors
#   VPX_ROOT default: tries to find third_party/libvpx relative to this script directory or current dir

NDK=${ANDROID_NDK_HOME:-/usr/lib/android-sdk/ndk/21.4.7075529}
API=21
ABIS="armeabi-v7a arm64-v8a x86 x86_64"
JOBS=$(nproc 2>/dev/null || echo 4)
VPX_ROOT=""
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"

print_usage() {
  cat <<EOF
Usage: $0 [options]
Options:
  --ndk PATH        Android NDK root (default: \$ANDROID_NDK_HOME or ${NDK})
  --api N           Android API level (default: ${API})
  --abis "list"     Space-separated ABI list (default: "${ABIS}")
  --jobs N          Parallel jobs for make (default: ${JOBS})
  --vpx-root PATH   Path to libvpx source (default: autodetect)
  -h|--help         Show this help
EOF
}

# parse args
while [[ $# -gt 0 ]]; do
  case "$1" in
    --ndk) NDK="$2"; shift 2;;
    --api) API="$2"; shift 2;;
    --abis) ABIS="$2"; shift 2;;
    --jobs) JOBS="$2"; shift 2;;
    --vpx-root) VPX_ROOT="$2"; shift 2;;
    -h|--help) print_usage; exit 0;;
    *) echo "Unknown arg: $1"; print_usage; exit 1;;
  esac
done

# autodetect VPX_ROOT if not provided
if [[ -z "$VPX_ROOT" ]]; then
  # try common locations relative to script dir and current dir
  if [[ -d "${SCRIPT_DIR}/third_party/libvpx" ]]; then
    VPX_ROOT="${SCRIPT_DIR}/third_party/libvpx"
  elif [[ -d "${SCRIPT_DIR}/../third_party/libvpx" ]]; then
    VPX_ROOT="${SCRIPT_DIR}/../third_party/libvpx"
  elif [[ -d "./third_party/libvpx" ]]; then
    VPX_ROOT="$(pwd)/third_party/libvpx"
  else
    echo "libvpx source not found automatically. Please pass --vpx-root PATH"
    exit 1
  fi
fi

echo "NDK: ${NDK}"
echo "API: ${API}"
echo "ABIs: ${ABIS}"
echo "JOBS: ${JOBS}"
echo "libvpx root: ${VPX_ROOT}"

if [[ ! -d "${NDK}" ]]; then
  echo "ERROR: NDK not found at ${NDK}"
  exit 1
fi

# toolchain base
TOOLCHAIN="${NDK}/toolchains/llvm/prebuilt/linux-x86_64"
if [[ ! -d "${TOOLCHAIN}" ]]; then
  # try another common prebuilt name on some systems
  TOOLCHAIN="${NDK}/toolchains/llvm/prebuilt"
  if [[ ! -d "${TOOLCHAIN}" ]]; then
    echo "ERROR: NDK toolchain directory not found under ${NDK}"
    exit 1
  fi
fi

# check yasm presence (recommended)
if command -v yasm >/dev/null 2>&1; then
  echo "yasm: found"
else
  echo "WARNING: 'yasm' not found in PATH. Recommended for x86 optimizations. Install yasm if you want optimal builds."
fi

build_one() {
  local ABI="$1"
  local TARGET=""
  local PREFIX_DIR_NAME=""
  local CC=""
  local CXX=""
  local CROSS_PREFIX=""
  local BUILD_SUBDIR=""
  local FFMPEG_ARCH=""
  local EXTRA_CONFIG_FLAGS=""
  local EXTRA_CFLAGS="-D__ANDROID_API__=${API}"
  case "${ABI}" in
    armeabi-v7a)
      TARGET="armv7-android-gcc"
      BUILD_SUBDIR="armv7-a"
      CC="${TOOLCHAIN}/bin/armv7a-linux-androideabi${API}-clang"
      CXX="${TOOLCHAIN}/bin/armv7a-linux-androideabi${API}-clang++"
      # enable NEON only for ARMv7 (32-bit)
      EXTRA_CONFIG_FLAGS="--enable-neon"
      EXTRA_CFLAGS+=" -march=armv7-a -mfpu=neon -mfloat-abi=softfp"
      ;;
    arm64-v8a)
      TARGET="arm64-android-gcc"
      BUILD_SUBDIR="arm64-v8a"
      CC="${TOOLCHAIN}/bin/aarch64-linux-android${API}-clang"
      CXX="${TOOLCHAIN}/bin/aarch64-linux-android${API}-clang++"
      # NEON is standard on AArch64; explicitly enable for clarity
      EXTRA_CONFIG_FLAGS="--enable-neon"
      ;;
    x86)
      TARGET="x86-android-gcc"
      BUILD_SUBDIR="i686"
      CC="${TOOLCHAIN}/bin/i686-linux-android${API}-clang"
      CXX="${TOOLCHAIN}/bin/i686-linux-android${API}-clang++"
      # enable MMX/SSE family for x86
      EXTRA_CONFIG_FLAGS="--enable-mmx --enable-sse2 --enable-sse3 --enable-ssse3"
      EXTRA_CFLAGS+=" -msse3 -msse2"
      ;;
    x86_64)
      TARGET="x86_64-android-gcc"
      BUILD_SUBDIR="x86_64"
      CC="${TOOLCHAIN}/bin/x86_64-linux-android${API}-clang"
      CXX="${TOOLCHAIN}/bin/x86_64-linux-android${API}-clang++"
      # enable MMX/SSE family for x86_64
      EXTRA_CONFIG_FLAGS="--enable-mmx --enable-sse2 --enable-sse3 --enable-ssse3"
      EXTRA_CFLAGS+=" -msse3 -msse2"
      ;;
    *)
      echo "Unsupported ABI: ${ABI}"
      return 1
      ;;
  esac

  # validate compilers
  if [[ ! -x "${CC}" ]]; then
    echo "ERROR: CC not found or not executable: ${CC}"
    return 1
  fi
  if [[ ! -x "${CXX}" ]]; then
    echo "ERROR: CXX not found or not executable: ${CXX}"
    return 1
  fi

  local BUILD_DIR="${VPX_ROOT}/build/${BUILD_SUBDIR}"
  mkdir -p "${BUILD_DIR}"
  pushd "${BUILD_DIR}" >/dev/null

  echo "=== Building libvpx for ${ABI} (target=${TARGET}) in ${BUILD_DIR} ==="
  echo "CC=${CC}"
  echo "CXX=${CXX}"
  echo "Extra configure flags: ${EXTRA_CONFIG_FLAGS}"
  echo "Extra CFLAGS: ${EXTRA_CFLAGS}"

  # common binutils (llvm-ar etc.)
  local AR="${TOOLCHAIN}/bin/llvm-ar"
  local NM="${TOOLCHAIN}/bin/llvm-nm"
  local RANLIB="${TOOLCHAIN}/bin/llvm-ranlib"
  local STRIP="${TOOLCHAIN}/bin/llvm-strip"

  local AS=""
  case "${ABI}" in
    armeabi-v7a|arm64-v8a)
      AS="${CC} -c"
      ;;
    x86|x86_64)
      AS="yasm"
      ;;
  esac

  # run configure with environment variables to avoid shell escaping issues
  CC="${CC}" CXX="${CXX}" AS="${AS}" AR="${AR}" NM="${NM}" RANLIB="${RANLIB}" STRIP="${STRIP}" \
    "${VPX_ROOT}/configure" \
      --target="${TARGET}" \
      --prefix="$(pwd)" \
      --disable-examples \
      --disable-tools \
      --disable-unit-tests \
      --enable-vp8 \
      --enable-vp9 \
      --enable-vp9-highbitdepth \
      --disable-shared \
      --enable-static \
      --enable-pic \
      ${EXTRA_CONFIG_FLAGS} \
      --extra-cflags="${EXTRA_CFLAGS}" || {
        echo "!!! CONFIGURE FAILED for ${ABI} !!!"
        # print last lines of config.log if exists
        if [[ -f ffbuild/config.log ]]; then
          echo "----- ffbuild/config.log (last 100 lines) -----"
          tail -n 100 ffbuild/config.log || true
        fi
        if [[ -f config.log ]]; then
          echo "----- config.log (last 100 lines) -----"
          tail -n 100 config.log || true
        fi
        popd >/dev/null
        return 1
      }

  echo "Configured. Starting make..."
  make -j"${JOBS}" || { echo "make failed for ${ABI}"; popd >/dev/null; return 1; }
  make install || { echo "make install failed for ${ABI}"; popd >/dev/null; return 1; }

  echo "Finished ${ABI}: lib at ${BUILD_DIR}/lib/libvpx.a"
  popd >/dev/null
  return 0
}

# build requested ABIs
for abi in ${ABIS}; do
  build_one "${abi}" || { echo "Build failed for ${abi}"; exit 1; }
done

echo "All requested libvpx builds finished."
