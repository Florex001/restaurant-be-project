package hu.balogh.restaurantbe.auth.service.jwt;

import hu.balogh.restaurantbe.repository.UserReporitory;
import hu.balogh.restaurantbe.repository.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserReporitory userReporitory;

    public UserDetailsServiceImpl(UserReporitory userReporitory) {
        this.userReporitory = userReporitory;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> optionalUser = userReporitory.findFirstByEmail(email);

        if (optionalUser.isEmpty()) throw new UsernameNotFoundException("User not found", null);

        return new org.springframework.security.core.userdetails.User(optionalUser.get().getEmail(), optionalUser.get().getPassword(), new ArrayList<>());
    }
}
