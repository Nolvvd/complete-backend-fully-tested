package com.cims.computerinventorymanagmentsystem.service.user;

import com.cims.computerinventorymanagmentsystem.model.Role;
import com.cims.computerinventorymanagmentsystem.model.User;

import java.util.List;

public interface IUserService {
    User getUserByUserName(String userName);
    void deleteUserByUserName(String userName);
    User addUser(Role role, String username, String password, String firstName, String lastName);
    List<User> getAllUsers();
}
