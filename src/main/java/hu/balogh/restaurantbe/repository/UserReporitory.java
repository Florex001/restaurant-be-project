package hu.balogh.restaurantbe.repository;

import hu.balogh.restaurantbe.enums.UserRole;
import hu.balogh.restaurantbe.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserReporitory extends JpaRepository<User, Long> {

    Optional<User> findFirstByEmail(String email);

    User findByUserRole(UserRole userRole);
}
