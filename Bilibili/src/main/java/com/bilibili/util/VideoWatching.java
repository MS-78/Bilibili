package com.bilibili.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class VideoWatching {
    public static ConcurrentMap<String,String> Watching = new ConcurrentHashMap<>();
}
