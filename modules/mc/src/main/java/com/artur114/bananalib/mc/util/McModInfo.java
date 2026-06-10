package com.artur114.bananalib.mc.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

public class McModInfo {
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final String META_PATH = "assets/%s/mcmod.info";
    private static final JsonParser PARSER = new JsonParser();

    public static McModInfo fromJson(JsonObject obj) {
        return new McModInfo(obj);
    }

    public static Optional<McModInfo> load(String modId) {
        return load(String.format(META_PATH, modId), modId);
    }

    public static Optional<McModInfo> load(String location, String modId) {
        return loadMetaJson(location, modId).map(McModInfo::fromJson);
    }

    public static Optional<JsonObject> loadMetaJson(String modId) {
        return loadMetaJson(String.format(META_PATH, modId), modId);
    }

    public static Optional<JsonObject> loadMetaJson(String location, String modId) {
        URL url = McModInfo.class.getResource(location);

        if (url == null) {
            return Optional.empty();
        }

        try (InputStreamReader isr = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
            JsonArray mcModInfo = PARSER.parse(new JsonReader(isr)).getAsJsonArray();
            for (int i = 0; i != mcModInfo.size(); i++) {
                JsonObject obj = mcModInfo.get(i).getAsJsonObject();

                if (modId.equals(propOrEmpty(obj, "modid"))) {
                    return Optional.of(obj);
                }
            }
        } catch (IOException ignored) {}

        return Optional.empty();
    }

    private static String propOrEmpty(JsonObject obj, String name) {
        return obj.has(name) ? obj.get(name).getAsString() : "";
    }

    private static String[] propOrEmptyArr(JsonObject obj, String name) {
        if (obj.has(name)) {
            JsonElement element = obj.get(name);

            if (element.isJsonPrimitive()) {
                return new String[] {element.getAsString()};
            }

            if (!element.isJsonArray()) {
                return EMPTY_STRING_ARRAY;
            }

            JsonArray arr = obj.get(name).getAsJsonArray();
            String[] ret = new String[arr.size()];
            for (int i = 0; i != arr.size(); i++) {
                ret[i] = arr.get(i).getAsString();
            }
            return ret;
        } else {
            return EMPTY_STRING_ARRAY;
        }
    }

    public final String[] dependencies;
    public final String[] screenshots;
    public final String[] authorList;
    public final String description;
    public final String updateUrl;
    public final String mcversion;
    public final String logoFile;
    public final String credits;
    public final String version;
    public final String modid;
    public final String name;
    public final String url;

    private McModInfo(JsonObject obj) {
        this.dependencies = propOrEmptyArr(obj, "dependencies");
        this.screenshots = propOrEmptyArr(obj, "screenshots");
        this.authorList = propOrEmptyArr(obj, "authorList");
        this.description = propOrEmpty(obj, "description");
        this.updateUrl = propOrEmpty(obj, "updateUrl");
        this.mcversion = propOrEmpty(obj, "mcversion");
        this.logoFile = propOrEmpty(obj, "logoFile");
        this.credits = propOrEmpty(obj, "credits");
        this.version = propOrEmpty(obj, "version");
        this.modid = propOrEmpty(obj, "modid");
        this.name = propOrEmpty(obj, "name");
        this.url = propOrEmpty(obj, "url");
    }
}