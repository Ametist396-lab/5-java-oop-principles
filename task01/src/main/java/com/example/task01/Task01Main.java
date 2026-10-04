package com.example.task01;

public class Task01Main {
    public static void main(String[] args) {
        Logger logger = Logger.getLogger("myLogger");
        logger.setLevel(Level.INFO);

        logger.debug("не напечатается — уровень ниже INFO");
        logger.info("обычное сообщение");
        logger.warning("что-то странное: %d", 42);
        logger.error("ошибка: %s", "файл не найден");

        Logger same = Logger.getLogger("myLogger");
        System.out.println(logger == same); // true
    }
}
