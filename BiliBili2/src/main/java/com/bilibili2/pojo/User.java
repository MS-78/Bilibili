package com.bilibili2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int UUID;
    private String UserPhone;
    private String UserName;
    private String UserPwd;
}
