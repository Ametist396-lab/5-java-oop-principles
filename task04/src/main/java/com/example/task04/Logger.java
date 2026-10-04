package com.example.task04;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Logger {
    private static final Map<String, Logger> ALL = new HashMap<>();
    private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat("yyyy.MM.dd");
    private static final SimpleDateFormat TIME_FMT = new SimpleDateFormat("HH:mm:ss");

    private final String name;
    private Level level = Level.DEBUG;
    private final List<MessageHandler> handlers = new ArrayList<>();

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        Logger existing = ALL.get(name);
        if (existing == null) {
            existing = new Logger(name);
            ALL.put(name, existing);
        }
        return existing;
    }

    public String getName() { return name; }
    public Level getLevel() { return level; }
    public void setLevel(Level level) { this.level = level; }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    public void removeHandler(MessageHandler handler) {
        handlers.remove(handler);
    }

    private void print(Level msgLevel, String message) {
        if (msgLevel.ordinal() < level.ordinal()) return;

        Date now = new Date();
        String line = "[" + msgLevel + "] "
                + DATE_FMT.format(now) + " "
                + TIME_FMT.format(now) + " "
                + name + " - "
                + message;

        for (MessageHandler handler : handlers) {
            handler.handle(line);
        }
    }

    public void debug(String message) { print(Level.DEBUG, message); }
    public void debug(String format, Object... args) { print(Level.DEBUG, String.format(format, args)); }
    public void info(String message) { print(Level.INFO, message); }
    public void info(String format, Object... args) { print(Level.INFO, String.format(format, args)); }
    public void warning(String message) { print(Level.WARNING, message); }
    public void warning(String format, Object... args) { print(Level.WARNING, String.format(format, args)); }
    public void error(String message) { print(Level.ERROR, message); }
    public void error(String format, Object... args) { print(Level.ERROR, String.format(format, args)); }

    public void log(Level level, String message) { print(level, message); }
    public void log(Level level, String format, Object... args) { print(level, String.format(format, args)); }
}
