package de.robinmucha.ecobackend.model.controller.user;

import de.robinmucha.ecobackend.model.assembler.UserModelAssembler;
import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.model.repository.UserRepository;
import de.robinmucha.ecobackend.security.utils.UserAuthenticationHelper;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserControllerImpl implements UserController {

    private final UserRepository userRepository;
    private final UserModelAssembler assembler;

    public UserControllerImpl(UserRepository repository, UserModelAssembler assembler) {
        this.userRepository = repository;
        this.assembler = assembler;
    }

    @Override
    @PostMapping("/users")
    public EntityModel<User> newUser(@RequestBody User newUser) {

        userRepository.isUsernameUniqueElseThrowUsernameAlreadyExistsEx(newUser);

        return assembler.toModel(userRepository.save(newUser));
    }

    @Override
    @GetMapping("/users/{requestedUserId}")
    public EntityModel<User> user(Authentication loggedInUser, @PathVariable int requestedUserId) {
        User requestedUser = userRepository.findUserByIdElseThrowUserNotFoundEx(requestedUserId);
        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), requestedUser);

        return assembler.toModel(requestedUser);
    }

    @Override
    @DeleteMapping("/users/{userToDeleteId}")
    public void deleteUser(Authentication loggedInUser, @PathVariable int userToDeleteId) {
        User userToDelete = userRepository.findUserByIdElseThrowUserNotFoundEx(userToDeleteId);

        UserAuthenticationHelper.isSameUserElseThrowUserAccessForbiddenEx(loggedInUser.getName(), userToDelete);
        userRepository.delete(userToDelete);
    }
}
