package com.bilibili2.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chat {
    private int UUID;
    private int To_videoID;
    private int From_userID;
    private String content;
    private int video_time;

}
