package com.lookie.toy1_back.tome.service;

import com.lookie.toy1_back.tome.domain.Answer;
import com.lookie.toy1_back.tome.domain.Question;
import com.lookie.toy1_back.tome.domain.User;
import com.lookie.toy1_back.tome.repository.AnswerRepository;
import com.lookie.toy1_back.tome.repository.QuestionRepository;
import com.lookie.toy1_back.tome.repository.UserRepository;
import com.lookie.toy1_back.tome.request.AnswerCreateRequest;
import com.lookie.toy1_back.tome.request.QuestionCreateRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    private final QuestionService questionService;

    public Answer createAnswer(Long questionId,AnswerCreateRequest answer) {
        Optional<User> userForId = userRepository.findById(answer.getUserId());
        Optional<Question> questionForId = questionRepository.findById(questionId);

        Answer answerToCreate = new Answer();
        BeanUtils.copyProperties(answer, answerToCreate);

        answerToCreate.setUser(userForId.get());
        answerToCreate.setQuestion(questionForId.get());

        return answerRepository.save(answerToCreate);
    }

    public void deleteAnswer(Long questionId, Long answerId) {

        Optional<Question> questionForId = questionRepository.findById(questionId);

        Long getId = questionForId.get().getAnswerList().get(Math.toIntExact(answerId)).getId();
        questionForId.get().getAnswerList().removeIf( n -> n.equals(getId) );

        questionRepository.save(questionForId.get());
    }

    public Answer updateAnswer (Long questionId, Long answerid, AnswerCreateRequest request) {

        Optional<Question> questionForId = questionRepository.findById(questionId);
        Answer findAnswer = questionForId.get().getAnswerList().get(Math.toIntExact(answerid));

        if ( findAnswer == null ) {
            throw new EntityNotFoundException(
                    "데이터 베이스에 해당하는 답변이 없습니다.");
        }

        findAnswer.setContent(request.getContent());

        return answerRepository.save(findAnswer);
    }
}
