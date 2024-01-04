package hu.balogh.restaurantbe.controller.auth.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.balogh.restaurantbe.enums.UserRole;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String name;
    private String email;
    @JsonIgnore
    private String password;
    private UserRole userRole;
}
