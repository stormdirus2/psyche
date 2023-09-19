package net.scirave.psyche.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.scirave.psyche.Psyche;

import java.io.FileNotFoundException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public final class PsycheConfig {
    public static final Gson GSON;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder = gsonBuilder.setPrettyPrinting();
        GSON = gsonBuilder.create();
    }

    public float TRAUMA_LOW_HEALTH = 0.1F;
    public boolean TRAUMA_SEVERE_WOUND = true;

    public static PsycheConfig readConfig(Path path) {
        try {
            Reader reader = Files.newBufferedReader(path);
            PsycheConfig config = GSON.fromJson(reader, PsycheConfig.class);

            reader.close();
            return config;
        } catch (Exception ex) {
            if (!(ex instanceof FileNotFoundException)) // File isn't missing, but it still errored!
                Psyche.LOGGER.error("Failed to read config from path '" + path.toString() +"' Error: \n" + ex.getMessage());
            return new PsycheConfig();
        }
    }

    public static void writeConfig(Path path, PsycheConfig config) {
        try {
            Writer writer = Files.newBufferedWriter(path, Charset.defaultCharset());
            String json = GSON.toJson(config, PsycheConfig.class);

            writer.write(json);
            writer.close();
        } catch (Exception ex) {
            Psyche.LOGGER.error("Failed to write config to path '" + path.toString() +"' Error: \n" + ex.getMessage());
        }
    }
}
