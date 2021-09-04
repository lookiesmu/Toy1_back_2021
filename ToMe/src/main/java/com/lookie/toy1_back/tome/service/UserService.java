package com.lookie.toy1_back.tome.service;

import com.lookie.toy1_back.tome.domain.Question;
import com.lookie.toy1_back.tome.domain.User;
import com.lookie.toy1_back.tome.repository.AnswerRepository;
import com.lookie.toy1_back.tome.repository.QuestionRepository;
import com.lookie.toy1_back.tome.repository.UserRepository;
import com.lookie.toy1_back.tome.request.QuestionCreateRequest;
import com.lookie.toy1_back.tome.request.UserCreateRequest;
import com.lookie.toy1_back.tome.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;


@Service
@RequiredArgsConstructor
public class UserService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public Long joinUser(User user) {
        Long userId = userRepository.save(
                User.builder()
                        .username(user.getUsername())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .role(UserRole.USER)
                        .phone(user.getPhone())
                        .name(user.getName())
                        .build())
                .getU_num();
        return userId;
    }

    public Long joinAdmin(User user) {
        Long userId = userRepository.save(
                User.builder()
                        .username(user.getUsername())
                        .password(passwordEncoder.encode(user.getPassword()))
                        .role(UserRole.ADMIN)
                        .phone(user.getPhone())
                        .name(user.getName())
                        .build())
                .getU_num();
        return userId;
    }

    public User findUser(User user) {
        User member = userRepository.findByUsername(user.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("아이디 혹은 비밀번호가 잘못되었습니다."));
        return member;
    }

    public boolean checkPassword(User member, User user) {
        return passwordEncoder.matches(user.getPassword(), member.getPassword());
    }
}
