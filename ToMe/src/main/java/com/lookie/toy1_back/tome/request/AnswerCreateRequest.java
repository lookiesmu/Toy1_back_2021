package com.lookie.toy1_back.tome.request;

import lombok.Data;

@Data
public class AnswerCreateRequest {
    private String content;
    private Long userId;
}
