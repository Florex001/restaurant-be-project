package hu.balogh.restaurantbe.auth.controller.dto;

import hu.balogh.restaurantbe.enums.UserRole;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private Long userId;
    private String username;
    private UserRole userRole;
    private String jwt;
}
