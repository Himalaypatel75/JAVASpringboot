package com.skylink.gympro.model.dto;
import lombok.*;
import com.skylink.gympro.model.UserType;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNo;
    private UserType type;

}
