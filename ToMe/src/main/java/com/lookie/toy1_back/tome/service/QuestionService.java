package com.lookie.toy1_back.tome.service;

import com.lookie.toy1_back.tome.domain.Answer;
import com.lookie.toy1_back.tome.domain.Question;
import com.lookie.toy1_back.tome.domain.User;
import com.lookie.toy1_back.tome.repository.AnswerRepository;
import com.lookie.toy1_back.tome.repository.QuestionRepository;
import com.lookie.toy1_back.tome.repository.UserRepository;
import com.lookie.toy1_back.tome.request.QuestionCreateRequest;
import com.lookie.toy1_back.tome.role.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static jdk.nashorn.internal.objects.Global.print;


@Service
@RequiredArgsConstructor
public class QuestionService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public Question createQuestion(QuestionCreateRequest question) {

        Optional<User> userForId = userRepository.findById(question.getUserId());

        if (!userForId.isPresent()) {
            throw new EntityNotFoundException("해당하는 유저가 없습니다.");
        }

        User user = userForId.get();

        if (user.getRole() != UserRole.ADMIN) {
            throw new RuntimeException("어드민 계정만 접근 가능합니다.");
        }

        Question questionToCreate = new Question();
        BeanUtils.copyProperties(question, questionToCreate);

        questionToCreate.setUser(userForId.get());

        return questionRepository.save(questionToCreate);
    }

    public void deleteQuestion(Long id) {
        questionRepository.deleteById(id);
    }

    public Question updateQuestion (Long id, QuestionCreateRequest request) {
        Optional<Question> optionalQuestion = questionRepository.findById(id);
        if (!optionalQuestion.isPresent()) {
            throw new EntityNotFoundException(
                    "데이터 베이스에 해당하는 질문이 없습니다.");
        }

        Question question = optionalQuestion.get();

        question.setContent(request.getContent());

        return questionRepository.save(question);
    }


}