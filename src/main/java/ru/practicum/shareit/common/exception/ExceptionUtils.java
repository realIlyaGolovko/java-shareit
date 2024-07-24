package ru.practicum.shareit.common.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ExceptionUtils {
    public NotFoundException createNotFoundException(final long id) {
        return new NotFoundException(String.format("Entity with id = %s not found", id));
    }
}