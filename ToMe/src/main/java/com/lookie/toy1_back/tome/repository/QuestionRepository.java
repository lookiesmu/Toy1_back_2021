package com.lookie.toy1_back.tome.repository;

import com.lookie.toy1_back.tome.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
