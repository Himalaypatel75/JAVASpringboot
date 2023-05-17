package com.skylink.gympro.service.impl;
import com.skylink.gympro.exception.UserNotFound;
import com.skylink.gympro.model.dto.UserDTO;
import com.skylink.gympro.service.UserService;
import com.skylink.gympro.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.skylink.gympro.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.skylink.gympro.exception.ResourceNotFound;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<UserDTO> getAllUser() {
        List<User> userList = this.userRepository.findByDeleted(false);
        return userList.stream()
                .map(this::userToUserDTO)
                .collect(Collectors.toList());
    }


    @Override
    public UserDTO createUser(UserDTO userDTO){
        User user = this.userDTOtoUser(userDTO);
        User savedUser = userRepository.save(user);
        return this.userToUserDTO(savedUser);
    }

    @Override
    public UserDTO getUserById(long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if(user.isPresent()) {
            UserDTO userdto = this.userToUserDTO(user.get());
            return userdto;
        }else {
            throw new ResourceNotFound();
        }
    }

    @Override
    public void deleteUser(long userId) {
        Optional<User> userDB = this.userRepository.findById(userId);
        if(userDB.isPresent()) {
            User user = userDB.get();
            user.delete(); // set deleted field to true
            this.userRepository.save(user); // save the updated user
        } else {
            throw new ResourceNotFound();
        }
    }


    @Override
    public UserDTO updateUser(UserDTO updatedUserDTO) {
//        System.out.println(updatedUserDTO + " this is object2");
        User user = userRepository.findById(updatedUserDTO.getUserId())
                .orElseThrow(() -> new UserNotFound("User not found with id: " + updatedUserDTO.getUserId()));

        if (!user.isDeleted()) {
            // Update fields that are allowed to be updated
            if (updatedUserDTO.getFirstName() != null) {
                user.setFirstName(updatedUserDTO.getFirstName());
            }
            if (updatedUserDTO.getLastName() != null) {
                user.setLastName(updatedUserDTO.getLastName());
            }
            if (updatedUserDTO.getEmail() != null) {
                user.setEmail(updatedUserDTO.getEmail());
            }
            if (updatedUserDTO.getPassword() != null) {
                user.setPassword(updatedUserDTO.getPassword());
            }
            if (updatedUserDTO.getPhoneNo() != null) {
                user.setPhoneNo(updatedUserDTO.getPhoneNo());
            }
//            System.out.println(updatedUserDTO.getType() + " this is object3");
//            System.out.println("This is type..");
            if (updatedUserDTO.getType() != null) {
                user.setType(updatedUserDTO.getType());
            }

            User updatedUser = userRepository.save(user);
            return userToUserDTO(updatedUser);
        } else {
            throw new UserNotFound("User not found with id: " + updatedUserDTO.getUserId());
        }
    }






    private User userDTOtoUser(UserDTO userDTO){
        User user = this.modelMapper.map(userDTO , User.class);
        return user;
    }

    private UserDTO userToUserDTO(User user) {
        UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);
        return userDTO;
    }


}
