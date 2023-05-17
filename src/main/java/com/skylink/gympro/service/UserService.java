package com.skylink.gympro.service;

import java.util.List;
import com.skylink.gympro.model.User;
import com.skylink.gympro.model.dto.UserDTO;

public interface UserService {
    List<UserDTO> getAllUser();

    UserDTO createUser(UserDTO user);
    UserDTO getUserById(long userId);
    UserDTO updateUser(UserDTO userDTO);
    void deleteUser(long userId);

}
