package hu.balogh.restaurantbe.auth.service.Impl;

import hu.balogh.restaurantbe.auth.controller.dto.SignupRequest;
import hu.balogh.restaurantbe.auth.controller.dto.UserDto;
import hu.balogh.restaurantbe.enums.UserRole;
import hu.balogh.restaurantbe.mapper.UserMapper;
import hu.balogh.restaurantbe.repository.UserReporitory;
import hu.balogh.restaurantbe.repository.entity.User;
import hu.balogh.restaurantbe.auth.AuthService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserReporitory userReporitory;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserReporitory userReporitory, PasswordEncoder passwordEncoder) {
        this.userReporitory = userReporitory;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void createAdminAccount(){
        User adminUser = userReporitory.findByUserRole(UserRole.ADMIN);
        if(adminUser == null){
            User user = new User();
            user.setName("admin");
            user.setEmail("admin@admin.hu");
            user.setUserRole(UserRole.ADMIN);
            user.setPassword(passwordEncoder.encode("admin"));
            userReporitory.save(user);
        }
    }

    @Override
    public UserDto createUser(SignupRequest signupRequest) {
        User user = UserMapper.INSTANCE.toEntityBySignup(signupRequest);
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.setUserRole(UserRole.CUSTOMER);
        userReporitory.save(user);
        return UserMapper.INSTANCE.toDto(user);
    }


}
