package com.huan.distributed.main;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

public class MapSample {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        SortedMap<String, String> sortedMap = new TreeMap<>();
        Map<String, String> currentMap = new ConcurrentHashMap<>();
    }
}
