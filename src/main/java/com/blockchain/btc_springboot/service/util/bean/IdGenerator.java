package com.blockchain.btc_springboot.service.util.bean;

import com.blockchain.btc_springboot.Constants;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

    private AtomicLong atomicLong;

    private Long currentWorker;

    private Long workers;

    public IdGenerator(Long currentWorker, Long workers) {
        Long currentTime = System.currentTimeMillis();
        this.atomicLong = new AtomicLong(0);
        this.currentWorker = currentWorker;
        this.workers = workers;
        while (currentTime % workers != 0) {
            currentTime++;
        }
        currentTime += currentWorker;
        this.atomicLong.addAndGet(currentTime);
    }

    public IdGenerator() {
        this(Constants.Concurrent.CURRENT_WORKER, Constants.Concurrent.WORKERS);
    }

    public Long generateId() {
        return this.atomicLong.addAndGet(this.workers);
    }

}
