package com.lookie.toy1_back.tome.request;

import lombok.Data;

@Data
public class UserCreateRequest {
    private String name;
    private String phone;
    private String username;
    private String password;
}
