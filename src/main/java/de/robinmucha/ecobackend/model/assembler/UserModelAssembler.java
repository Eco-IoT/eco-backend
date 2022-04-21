package de.robinmucha.ecobackend.model.assembler;

import de.robinmucha.ecobackend.model.controller.user.UserController;
import de.robinmucha.ecobackend.model.entity.User;
import de.robinmucha.ecobackend.security.utils.AuthenticatedUser;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User user) {
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class)
                        .user(AuthenticatedUser.getCurrentlyAuthenticatedUser(), user.getId())).withSelfRel());
    }
}
