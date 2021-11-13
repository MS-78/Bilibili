package com.bilibili.pojo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int UUID;
    private String UserPhone;
    private String UserName;
    private String UserPwd;
}
