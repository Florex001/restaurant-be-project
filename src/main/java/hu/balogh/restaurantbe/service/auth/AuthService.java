package hu.balogh.restaurantbe.service.auth;

import hu.balogh.restaurantbe.controller.auth.dto.SignupRequest;
import hu.balogh.restaurantbe.controller.auth.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
