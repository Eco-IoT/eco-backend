package de.robinmucha.ecobackend.model.repository;

import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.exception.user.UserNotFoundException;
import de.robinmucha.ecobackend.model.exception.user.UsernameAlreadyExistsException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    default User findUserByIdElseThrowUserNotFoundEx(int userId) {
        return findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    default void isUsernameUniqueElseThrowUsernameAlreadyExistsEx(User newUser) {
        if (!isUsernameUnique(newUser.getName())) {
            throw new UsernameAlreadyExistsException(newUser.getName());
        }
    }

    private boolean isUsernameUnique(String username) {
        for (User user : findAll()) {
            if (user.getName().equals(username)) {
                return false;
            }
        }
        return true;
    }

}