package com.kushi.niobium.modrinth;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class ModrinthAPI {
    public static final OkHttpClient CLIENT = new OkHttpClient();

    public static String fetchMods(String query) throws IOException {
        String url = "https://api.modrinth.com/v2/search?query=" + query;
        Request request = new Request.Builder().url(url).build();
        try (Response response = CLIENT.newCall(request).execute()) {
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                throw new IOException("Failed to fetch mods: " + response.message());
            }
        }
    }
}

