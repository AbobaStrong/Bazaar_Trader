package com.example.bazaartrader;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class JsonUtils {
    public static String toJson(Object object) {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    public static <T> T fromJson(String json, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(json, type);
    }
}
