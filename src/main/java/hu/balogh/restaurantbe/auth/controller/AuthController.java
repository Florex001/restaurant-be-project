package hu.balogh.restaurantbe.auth.controller;

import hu.balogh.restaurantbe.auth.controller.dto.AuthenticationRequest;
import hu.balogh.restaurantbe.auth.controller.dto.AuthenticationResponse;
import hu.balogh.restaurantbe.auth.controller.dto.SignupRequest;
import hu.balogh.restaurantbe.auth.controller.dto.UserDto;
import hu.balogh.restaurantbe.repository.UserReporitory;
import hu.balogh.restaurantbe.repository.entity.User;
import hu.balogh.restaurantbe.auth.AuthService;
import hu.balogh.restaurantbe.auth.service.jwt.UserDetailsServiceImpl;
import hu.balogh.restaurantbe.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtUtil jwtUtil;
    private final UserReporitory userReporitory;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, UserDetailsServiceImpl userDetailsService, JwtUtil jwtUtil, UserReporitory userReporitory) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
        this.userReporitory = userReporitory;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest){
        UserDto userDto = authService.createUser(signupRequest);

        if (userDto == null){
            return new ResponseEntity<>("User not created.", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws IOException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password;");
        }catch (DisabledException e){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "User not active");
            return null;
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        //TODO: szépíteni a kódot, átvinni a service-be
        Optional<User> optionalUser = userReporitory.findFirstByEmail(userDetails.getUsername());
        AuthenticationResponse authenticationResponse = new AuthenticationResponse();
        if (optionalUser.isPresent()){
            authenticationResponse.setJwt(jwt);
            authenticationResponse.setUsername(optionalUser.get().getName());
            authenticationResponse.setUserRole(optionalUser.get().getUserRole());
            authenticationResponse.setUserId(optionalUser.get().getId());
        }
        return authenticationResponse;
    }

}
