package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler{
    private final MessageHandler target;
    private final int maxSize;
    private final List<String> buffer = new ArrayList<>();

    public MemoryHandler(MessageHandler target, int maxSize) {
        this.target = target;
        this.maxSize = maxSize;
    }

    @Override
    public void handle(String message) {
        buffer.add(message);
        if (buffer.size() >= maxSize) {
            flush();
        }
    }

    public void flush() {
        for (String msg : buffer) {
            target.handle(msg);
        }
        buffer.clear();
    }
}
