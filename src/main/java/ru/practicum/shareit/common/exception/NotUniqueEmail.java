package ru.practicum.shareit.common.exception;

public class NotUniqueEmail extends RuntimeException {
    public NotUniqueEmail(String message) {
        super(message);
    }
}
