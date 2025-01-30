package com.cims.computerinventorymanagmentsystem.service.user;

import com.cims.computerinventorymanagmentsystem.exceptions.UserNotFoundException;
import com.cims.computerinventorymanagmentsystem.model.Role;
import com.cims.computerinventorymanagmentsystem.model.User;
import com.cims.computerinventorymanagmentsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUsername(userName)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
    }

    @Override
    public void deleteUserByUserName(String userName) {
        userRepository.findByUsername(userName).ifPresentOrElse(userRepository::delete,
                () ->{throw new UserNotFoundException("User not found!");});
    }

    @Override
    public User addUser(Role role, String username, String password, String firstName, String lastName) {
        User user = new User(); // Create a User directly
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setRole(role); // Set the role

        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
