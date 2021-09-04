package com.lookie.toy1_back.tome;

import com.lookie.toy1_back.tome.domain.Answer;
import com.lookie.toy1_back.tome.domain.Question;
import com.lookie.toy1_back.tome.domain.User;
import com.lookie.toy1_back.tome.repository.AnswerRepository;
import com.lookie.toy1_back.tome.repository.QuestionRepository;
import com.lookie.toy1_back.tome.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootTest(classes = ToMeApplication.class)
class ToMeApplicationTests {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Test
    public void contextLoads() {
        System.out.println("아니 왜 안 돼!!! 나는 개멍청이..?");
        User user = User.builder().name("test").password("test").phone("test").build();
        Question question = Question.builder().content("test 질문").user(user).build();
        Answer answer = Answer.builder().content("대답 테스트").question(question).user(user).build();

        questionRepository.save(question);
        userRepository.save(user);
        answerRepository.save(answer);
    }
}
