package hu.balogh.restaurantbe.auth;

import hu.balogh.restaurantbe.auth.controller.dto.SignupRequest;
import hu.balogh.restaurantbe.auth.controller.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
}
