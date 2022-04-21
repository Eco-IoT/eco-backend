package de.robinmucha.ecobackend.model.repository;

import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.exception.user.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    default User findUserByIdElseThrowUserNotFoundEx(int userId) {
        return findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

}