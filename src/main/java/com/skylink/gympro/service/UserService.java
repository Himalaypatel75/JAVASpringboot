package com.skylink.gympro.service;

import java.util.List;
import com.skylink.gympro.model.User;

public interface UserService {
    List<User> getAllUser();

    User createUser(User user);
    User getUserById(long userId);
    User updateUser(User user);
    void deleteUser(long userId);

}
