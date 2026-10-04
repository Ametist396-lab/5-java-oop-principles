package com.example.task01;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {
    private static Map<String, Logger> allLoggers = new HashMap<>(); //список всех логгеров
    private final String name;
    private Level level = Level.DEBUG;
    private Logger(String name){
        this.name = name;
    }
    public static Logger getLogger(String name){
        Logger instance = allLoggers.get(name);
        if (instance == null){
            instance = new Logger(name);
            allLoggers.put(name, instance);
        }
        return instance;
    }
    public String getName(){
        return name;
    }
    public Level getLevel(){
        return level;
    }
    public void setLevel(Level level){
        this.level = level;
    }

    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    private String Message(Level msgLevel, String message){
        Date now = new Date();
        return "[" + msgLevel + "] " + dateFormat.format(now) + " " + timeFormat.format(now) + " " + name + " - " + message;
    }

    private void print(Level msgLevel, String message) {
        if (msgLevel.ordinal() < level.ordinal()) {
            return;
        }
        System.out.println(Message(msgLevel, message));
    }

    //DEBUG
    public void debug(String message) {
        print(Level.DEBUG, message);
    }

    public void debug(String format, Object... args) {
        print(Level.DEBUG, String.format(format, args));
    }

    //INFO
    public void info(String message) {
        print(Level.INFO, message);
    }

    public void info(String format, Object... args) {
        print(Level.INFO, String.format(format, args));
    }

    //WARNING
    public void warning(String message) {
        print(Level.WARNING, message);
    }

    public void warning(String format, Object... args) {
        print(Level.WARNING, String.format(format, args));
    }

    //ERROR
    public void error(String message) {
        print(Level.ERROR, message);
    }

    public void error(String format, Object... args) {
        print(Level.ERROR, String.format(format, args));
    }

    public void log(Level level, String message) {
        print(level, message);
    }

    public void log(Level level, String format, Object... args) {
        print(level, String.format(format, args));
    }
}
