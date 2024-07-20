package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.common.exception.ExceptionUtils;
import ru.practicum.shareit.common.exception.NotUniqueEmail;
import ru.practicum.shareit.user.dto.UserCreateRequestDto;
import ru.practicum.shareit.user.dto.UserUpdateRequestDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    @Override
    public User create(final UserCreateRequestDto userCreateRequestDto) {
        final User newUser = userMapper.toModel(userCreateRequestDto);
        if (!userRepository.isUniqueEmail(newUser.getEmail())) {
            throw new NotUniqueEmail(String.format("Email %s is already exist", newUser.getEmail()));
        }
        userRepository.saveEmail(newUser.getEmail());
        return userRepository.persist(newUser);

    }

    @Override
    public User update(final UserUpdateRequestDto userUpdateRequestDto, final long userId) {
        final User original = userRepository.get(userId)
                .orElseThrow(() -> ExceptionUtils.createNotFoundException(userId));
        final User newUser = userMapper.toModel(userUpdateRequestDto, userId);
        updateEntityIfCorrect(original, newUser);
        return userRepository.update(original);
    }

    private void updateEntityIfCorrect(final User original, final User newUser) {
        updateEmailIfCorrect(original, newUser);
        updateNameIfCorrect(original, newUser);
    }

    private void updateEmailIfCorrect(final User original, final User newUser) {
        if (!Objects.isNull(newUser.getEmail()) && !newUser.getEmail().equals(original.getEmail())) {
            if (!userRepository.isUniqueEmail(newUser.getEmail())) {
                throw new NotUniqueEmail(String.format("Email %s is already exist", newUser.getEmail()));
            }
            userRepository.removeEmail(original.getEmail());
            userRepository.saveEmail(newUser.getEmail());
            original.setEmail(newUser.getEmail());
        }
    }

    private void updateNameIfCorrect(final User original, final User newUser) {
        if (!Objects.isNull(newUser.getName()) && !newUser.getName().equals(original.getName())) {
            original.setName(newUser.getName());
        }
    }

    @Override
    public User get(final long userId) {
        return userRepository.get(userId)
                .orElseThrow(() -> ExceptionUtils.createNotFoundException(userId));
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public void delete(final long userId) {
        final User original = userRepository.get(userId).orElseThrow(
                () -> ExceptionUtils.createNotFoundException(userId)
        );
        userRepository.delete(userId);
        userRepository.removeEmail(original.getEmail());
    }
}
