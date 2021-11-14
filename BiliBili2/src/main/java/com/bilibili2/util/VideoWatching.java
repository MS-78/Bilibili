package com.bilibili2.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class VideoWatching {
    /**
     * 当前key人正在看value视频
     * key -- uid
     * value -- 视频的UUID
     */
    public static ConcurrentMap<String,Integer> Watching = new ConcurrentHashMap<>();
}
