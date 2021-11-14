package com.bilibili2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    private int UUID;
    private String Name;
    private String ImgName;
    private int PlayCount;
}
