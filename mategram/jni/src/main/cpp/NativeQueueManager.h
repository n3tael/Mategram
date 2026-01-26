#ifndef MATEGRAM_NATIVEQUEUEMANAGER_H
#define MATEGRAM_NATIVEQUEUEMANAGER_H

#include <thread>

enum class StickerPriority { EMOJI, STICKER };

struct RenderTask {
    std::function<void()> work;
    StickerPriority priority;
};

class NativeQueueManager {
private:
    std::mutex mtx;
    std::condition_variable cv;
    std::deque<RenderTask> emojiQueue;
    std::deque<RenderTask> stickerQueue;
    bool stop = false;
    std::thread emojiThread;
    std::thread stickerThread;

    void worker(std::deque<RenderTask>& queue) {
        while (true) {
            RenderTask task;
            {
                std::unique_lock<std::mutex> lock(mtx);
                cv.wait(lock, [&] { return stop || !queue.empty(); });
                if (stop && queue.empty()) return;
                task = std::move(queue.front());
                queue.pop_front();
            }
            task.work();
        }
    }

public:
    NativeQueueManager() {
        emojiThread = std::thread(&NativeQueueManager::worker, this, std::ref(emojiQueue));
        stickerThread = std::thread(&NativeQueueManager::worker, this, std::ref(stickerQueue));
    }

    void enqueue(StickerPriority p, std::function<void()> f) {
        {
            std::lock_guard<std::mutex> lock(mtx);
            if (p == StickerPriority::EMOJI) emojiQueue.push_back({f, p});
            else stickerQueue.push_back({f, p});
        }
        cv.notify_all();
    }

    ~NativeQueueManager() {
        {
            std::lock_guard<std::mutex> lock(mtx);
            stop = true;
        }
        cv.notify_all();
        if (emojiThread.joinable()) emojiThread.join();
        if (stickerThread.joinable()) stickerThread.join();
    }
};

static NativeQueueManager gQueueManager;

#endif //MATEGRAM_NATIVEQUEUEMANAGER_H
