package com.lookie.toy1_back.tome.request;

import lombok.Data;

@Data
public class QuestionCreateRequest {
    private String content;
    private Long u_num;
}