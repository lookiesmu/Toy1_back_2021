package com.lookie.toy1_back.tome.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class AnswerCreateRequest {
    private String content;
    private Long u_num;
}
