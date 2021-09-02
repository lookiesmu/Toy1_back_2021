package com.lookie.toy1_back.tome.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String content;

    @OneToOne(fetch =  FetchType.EAGER)
    @JoinColumn(columnDefinition = "user_id")
    private User user;

    @OneToOne(fetch =  FetchType.EAGER)
    @JoinColumn(columnDefinition = "question_id")
    private Question question;
}