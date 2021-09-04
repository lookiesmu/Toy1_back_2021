package com.lookie.toy1_back.tome.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Answer extends BaseTime implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long a_num;

    @NotBlank(message = "답변을 입력해주세요")
    @Column
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;
}