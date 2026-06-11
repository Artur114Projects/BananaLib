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

/**
 * Represents an array element from the mcmod.info file as an object with corresponding fields.
 * If a field is missing in the source file, the corresponding field in this object
 * will contain an empty string or an empty array.'
 *
 * @author Artur114
 * @since 1.0
 */
public class McModInfo {
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final String META_PATH = "assets/%s/mcmod.info";
    private static final JsonParser PARSER = new JsonParser();

    /**
     * Creates a McModInfo instance from a JsonObject that is an element of the core mcmod.info array <br>
     * @param obj json object that is an element of the core array mcmod.info
     * @return a new McModInfo instance representing the passed json object
     * @see #loadMetaJson(String, String)
     */
    public static McModInfo fromJson(JsonObject obj) {
        return new McModInfo(obj);
    }

    /**
     * Loads a file from {@code assets/modId/mcmod.info} as json,
     * looks for a JsonObject with key "modid" equal to {@code modId}
     * and builds a new McModInfo instance.
     * @param modId target modId of the array element that will be returned
     * @return result of {@link #load(String, String)} method
     * @see #load(String, String)
     */
    public static Optional<McModInfo> load(String modId) {
        return load(String.format(META_PATH, modId), modId);
    }

    /**
     * Loads a file from {@code location} as json
     * and assembles a McModInfo instance from a JsonObject
     * that is an element of the core array with the "modid" key equal to {@code modId}
     * @param location path to the resource file
     * @param modId target modId of the array element that will be returned
     * @return a new McModInfo instance representing an array element in the mcmod.info file
     * loaded from {@code location} with the "modid" key equal to {@code modId},
     * returns Optional.empty() if {@link #loadMetaJson(String, String)} returns Optional.empty().
     * @see #loadMetaJson(String, String)
     */
    public static Optional<McModInfo> load(String location, String modId) {
        return loadMetaJson(location, modId).map(McModInfo::fromJson);
    }

    /**
     * Loads a file from {@code assets/modId/mcmod.info} as json and looks for a JsonObject with a "modid" key equal to {@code modId}
     * @param modId target modId of the array element that will be returned
     * @return result of method {@link #loadMetaJson(String, String)}
     * @see #loadMetaJson(String, String)
     */
    public static Optional<JsonObject> loadMetaJson(String modId) {
        return loadMetaJson(String.format(META_PATH, modId), modId);
    }

    /**
     * Loads a file from {@code location} as json and looks for a JsonObject with a "modid" key equal to {@code modId}
     * @param location path to the resource file
     * @param modId target modId of the array element that will be returned
     * @return {@code Optional} with a JsonObject that is an element of the mcmod.info array whose key "modid" is equal to the passed modId,
     * if the resource is not found, the target modId is not in the array, or if an exception occurs while reading the file, then {@code Optional.empty()} is returned
     */
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

    /** An array from the mcmod.info file with the "dependencies" key */
    public final String[] dependencies;
    /** An array from the mcmod.info file with the "screenshots" key */
    public final String[] screenshots;
    /** An array from the mcmod.info file with the "authorList" key */
    public final String[] authorList;
    /** Value of the "description" property from mcmod.info. */
    public final String description;
    /** Value of the "updateUrl" property from mcmod.info. */
    public final String updateUrl;
    /** Value of the "mcversion" property from mcmod.info. */
    public final String mcversion;
    /** Value of the "logoFile" property from mcmod.info. */
    public final String logoFile;
    /** Value of the "credits" property from mcmod.info. */
    public final String credits;
    /** Value of the "version" property from mcmod.info. */
    public final String version;
    /** Value of the "modid" property from mcmod.info. */
    public final String modid;
    /** Value of the "name" property from mcmod.info. */
    public final String name;
    /** Value of the "url" property from mcmod.info. */
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