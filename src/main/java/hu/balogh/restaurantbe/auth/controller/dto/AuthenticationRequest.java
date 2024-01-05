package hu.balogh.restaurantbe.auth.controller.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {

    private String email;
    private String password;

}
