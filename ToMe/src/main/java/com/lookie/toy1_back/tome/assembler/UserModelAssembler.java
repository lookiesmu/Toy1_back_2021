package com.lookie.toy1_back.tome.assembler;

import com.lookie.toy1_back.tome.controller.UserController;
import com.lookie.toy1_back.tome.domain.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User user){
        return EntityModel.of(user,
                linkTo(methodOn(UserController.class).one(user.getU_num())).withSelfRel(),
                linkTo(methodOn(UserController.class).all()).withRel("users"));
    }
}