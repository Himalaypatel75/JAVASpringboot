package com.skylink.gympro.service.impl;
import com.skylink.gympro.exception.UserNotFound;
import com.skylink.gympro.service.UserService;
import com.skylink.gympro.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import com.skylink.gympro.repository.UserRepository;
import org.springframework.stereotype.Service;
import com.skylink.gympro.exception.ResourceNotFound;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAllUser() {
        return this.userRepository.findByDeleted(false);
    }

    @Override
    public User createUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User getUserById(long userId) {
        Optional<User> user = this.userRepository.findById(userId);
        if(user.isPresent()) {
            return user.get();
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
    public User updateUser(User updatedUser) {
        User user = userRepository.findById(updatedUser.getUserId())
                .orElseThrow(() -> new UserNotFound("User not found with id : " + updatedUser.getUserId()));

        if (!user.isDeleted()) {
            // update fields that are allowed to be updated
            if (updatedUser.getFirstName() != null) {
                user.setFirstName(updatedUser.getFirstName());
            }
            if (updatedUser.getLastName() != null) {
                user.setLastName(updatedUser.getLastName());
            }
            if (updatedUser.getEmail() != null) {
                user.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getPassword() != null) {
                user.setPassword(updatedUser.getPassword());
            }
            if (updatedUser.getPhoneNo() != null) {
                user.setPhoneNo(updatedUser.getPhoneNo());
            }
            if (updatedUser.getType() != null) {
                user.setType(updatedUser.getType());
            }

            return userRepository.save(user);
        } else {
            throw new UserNotFound("User not found with id : " + updatedUser.getUserId());
        }
    }

}
