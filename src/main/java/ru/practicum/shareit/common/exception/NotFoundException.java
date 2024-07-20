package ru.practicum.shareit.common.exception;

import lombok.ToString;

@ToString
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
