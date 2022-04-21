package de.robinmucha.ecobackend.model.controller.user;

import de.robinmucha.ecobackend.model.entity.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

public interface UserController {
    @PostMapping("/users")
    EntityModel<User> newUser(@RequestBody User newUser);

    @GetMapping("/users/{requestedUserId}")
    EntityModel<User> user(Authentication loggedInUser, @PathVariable int requestedUserId);

    @DeleteMapping("/users/{userToDeleteId}")
    void deleteUser(Authentication loggedInUser, @PathVariable int userToDeleteId);
}