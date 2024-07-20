package ru.practicum.shareit.user.service;

import ru.practicum.shareit.user.dto.UserCreateRequestDto;
import ru.practicum.shareit.user.dto.UserUpdateRequestDto;
import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserService {
    User create(final UserCreateRequestDto userCreateRequestDto);

    User update(final UserUpdateRequestDto userUpdateRequestDto, final long userId);

    User get(final long userId);

    List<User> getAll();

    void delete(final long userId);

}
