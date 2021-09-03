package com.lookie.toy1_back.tome.repository;

import com.lookie.toy1_back.tome.domain.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Long> {
}
