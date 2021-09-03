package com.lookie.toy1_back.tome.service;

import com.lookie.toy1_back.tome.domain.Question;
import com.lookie.toy1_back.tome.domain.User;
import com.lookie.toy1_back.tome.repository.AnswerRepository;
import com.lookie.toy1_back.tome.repository.QuestionRepository;
import com.lookie.toy1_back.tome.repository.UserRepository;
import com.lookie.toy1_back.tome.request.QuestionCreateRequest;
import com.lookie.toy1_back.tome.request.UserCreateRequest;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.Store;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;


@Service
@RequiredArgsConstructor
public class UserService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

/*    public User createUser(UserCreateRequest user) {

        User userToCreate = new User();
        BeanUtils.copyProperties(user, userToCreate);

        return userRepository.save(userToCreate);
    }*/
}
