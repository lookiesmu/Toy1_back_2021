package com.lookie.toy1_back.tome.request;

import lombok.Data;

@Data
public class UserUpdateRequest {
    private String name;

    private String phone;

    private String password;
}
