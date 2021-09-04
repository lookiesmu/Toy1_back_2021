package com.lookie.toy1_back.tome.controller;

import com.lookie.toy1_back.tome.assembler.UserModelAssembler;
import com.lookie.toy1_back.tome.config.JwtTokenProvider;
import com.lookie.toy1_back.tome.domain.User;
import com.lookie.toy1_back.tome.exception.UserNotFoundException;
import com.lookie.toy1_back.tome.repository.UserRepository;
import com.lookie.toy1_back.tome.request.UserCreateRequest;
import com.lookie.toy1_back.tome.request.UserUpdateRequest;
import com.lookie.toy1_back.tome.role.UserRole;
import com.lookie.toy1_back.tome.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
@RestController
public class UserController {
    private final UserRepository repository;
    private final UserModelAssembler assembler;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    UserController(UserRepository repository, UserModelAssembler assembler, JwtTokenProvider jwtTokenProvider, UserService userService, PasswordEncoder passwordEncoder){
        this.repository = repository;
        this.assembler = assembler;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<User>> all(){
        List<EntityModel<User>> users = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping("/signup/user")
    ResponseEntity<?> newUser(@Valid @RequestBody User newUser){
        checkUserNameDuplicate(newUser.getUsername());
        newUser.setRole(UserRole.ROLE_USER);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


    @PostMapping("/signup/admin")
    ResponseEntity<?> newAdmin(@Valid @RequestBody User newUser){
        checkUserNameDuplicate(newUser.getUsername());
        newUser.setRole(UserRole.ROLE_ADMIN);
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        EntityModel<User> entityModel = assembler.toModel(repository.save(newUser));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }


    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
        HttpSession session = request.getSession();

        // 유저 존재 확인
        User member = userService.findUser(user);
        boolean checkResult = userService.checkPassword(member, user);
        // 비밀번호 체크
        if(!checkResult) {
            throw new IllegalArgumentException("아이디 혹은 비밀번호가 잘못되었습니다.");
        }
        // 토큰 생성 및 응답
        String token = jwtTokenProvider.createToken(member.getUsername(), member.getRole());
        response.setHeader("authorization", "bearer " + token);

        return jwtTokenProvider.createToken(member.getUsername(), member.getRole());

    }


    @PostMapping("/logout")
    public static void logout(HttpServletRequest request) {
        request.getSession().invalidate();
        request.getSession(true);
    }


    @GetMapping("/user/{id}")
    public EntityModel<User> one(@PathVariable Long id){
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return assembler.toModel(user);
    }

    @PutMapping("/user/{id}")
    ResponseEntity<?> updateUser(@RequestBody UserUpdateRequest userUpdateRequest, @PathVariable Long id){
        userService.updateUser(id, userUpdateRequest);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/user/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public void checkUserNameDuplicate(String username) {
        List<User> user = repository.findAll();
        for (User findUser: user) {
            if ( findUser.getUsername().equals(username)) {
                throw new IllegalStateException("이미 존재하는 ID입니다.");
            }
        }
    }
}