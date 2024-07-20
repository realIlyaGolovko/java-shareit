package ru.practicum.shareit.user.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.dto.UserCreateRequestDto;
import ru.practicum.shareit.user.dto.UserUpdateRequestDto;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
@Slf4j
@Validated
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody @Valid final UserCreateRequestDto userCreateRequestDto) {
        log.info("request POST /users body = {}", userCreateRequestDto);
        final User user = userService.create(userCreateRequestDto);
        log.info("response POST /users body = {}", user);
        return user;
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User update(@RequestBody @Valid final UserUpdateRequestDto userUpdateRequestDto,
                       @PathVariable("userId") final long userId) {
        log.info("request PATH /users/{} body = {}", userId, userUpdateRequestDto);
        final User user = userService.update(userUpdateRequestDto, userId);
        log.info("response PATH /users/{} body = {}", userId, user);
        return user;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public User get(@PathVariable("userId") final long userId) {
        log.info("request GET /users/{}", userId);
        final User user = userService.get(userId);
        log.info("response GET /users/{} body = {}", userId, user);
        return user;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAll() {
        log.info("request GET /users");
        final List<User> users = userService.getAll();
        log.info("response GET /users body = {}", users);
        return users;
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("userId") final long userId) {
        log.info("request DELETE /users/{}", userId);
        userService.delete(userId);
        log.info("response DELETE /users/{}", userId);
    }
}
