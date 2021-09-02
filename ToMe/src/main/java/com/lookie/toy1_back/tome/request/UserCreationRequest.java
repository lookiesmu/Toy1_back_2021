package com.lookie.toy1_back.tome.request;

import lombok.Data;

@Data
public class UserCreationRequest {
    private String name;
    private String email;
    private String password;
}
