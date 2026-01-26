#ifndef MATEGRAM_NATIVEBUFFERPOOL_H
#define MATEGRAM_NATIVEBUFFERPOOL_H

#include <android/hardware_buffer.h>
#include <android/hardware_buffer_jni.h>
#include <mutex>
#include <vector>
#include <atomic>

struct NativeBuffer {
    AHardwareBuffer* hwBuffer = nullptr;
    void* mapping = nullptr;
    int width = 0;
    int height = 0;
    int stride = 0;
    uint64_t id = 0;

    int lock() {
        int result = -1;
        if (hwBuffer && !mapping) {
            result = AHardwareBuffer_lock(hwBuffer, AHARDWAREBUFFER_USAGE_CPU_WRITE_OFTEN, -1, nullptr, &mapping);
            AHardwareBuffer_Desc desc;
            AHardwareBuffer_describe(hwBuffer, &desc);
            stride = desc.stride * 4; // RGBA_8888 = 4 bytes per pixel
        }
        return result;
    }

    void unlock() {
        if (hwBuffer && mapping) {
            AHardwareBuffer_unlock(hwBuffer, nullptr);
            mapping = nullptr;
        }
    }
};

class NativeBufferPool {
private:
    std::mutex mtx;
    std::vector<NativeBuffer*> available;
    std::atomic<uint64_t> nextId{1};

public:
    NativeBuffer* acquire(int w, int h) {
        std::lock_guard<std::mutex> lock(mtx);
        for (auto it = available.begin(); it != available.end(); ++it) {
            if ((*it)->width == w && (*it)->height == h) {
                NativeBuffer* b = *it;
                available.erase(it);
                return b;
            }
        }

        NativeBuffer* nb = new NativeBuffer();
        AHardwareBuffer_Desc desc = {};
        desc.width = w;
        desc.height = h;
        desc.layers = 1;
        desc.format = AHARDWAREBUFFER_FORMAT_R8G8B8A8_UNORM;
        desc.usage = AHARDWAREBUFFER_USAGE_CPU_WRITE_OFTEN | AHARDWAREBUFFER_USAGE_GPU_SAMPLED_IMAGE;

        if (AHardwareBuffer_allocate(&desc, &nb->hwBuffer) != 0) {
            delete nb;
            return nullptr;
        }

        nb->width = w;
        nb->height = h;
        nb->id = nextId.fetch_add(1);
        return nb;
    }

    void release(NativeBuffer* buffer) {
        if (!buffer) return;
        std::lock_guard<std::mutex> lock(mtx);
        available.push_back(buffer);
    }
};

static NativeBufferPool gNativePool;

#endif