package com.example.testwork.exception;

public class TaskException extends Exception {

    public TaskException(String message) {
        super(message);
    }

    public enum TaskExceptionType {
        TASK_MISSING_QUEUE(-1, "В очереди не хватает потоков."),
        TASK_FAILED_DATA(-2, "Таблица Tasks пустая."),
        TASK_FAILED_GET_BY_ID(-3, "Поле не найдено по id"),
        ;
        private final int code;

        private final String message;

        TaskExceptionType(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }
}
