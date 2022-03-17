package de.drolpi.dailyreward.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileController {

    private static final String LOGGER = "CONFIG";
    private static final Gson GSON = new Gson();

    //TODO: Remove

    public void copyFileFromJar(ClassLoader loader, String internalPath, Path externalPath) {
        if(loader != null && !Files.exists(externalPath)) {
            try {
                Files.copy(Objects.requireNonNull(loader.getResourceAsStream(internalPath)), externalPath);
            } catch(IOException e) {
                logError(e, "copy", internalPath, externalPath);
            }
        }
    }

    
    public void copyFileFromDisk(Path source, Path destination) {
        if(!Files.exists(destination)) {
            try {
                Files.copy(source, destination);
            } catch(IOException e) {
                logError(e, "copy", source.toString(), destination);
            }
        }
    }

    
    public <T> T loadFromJson(Path sourcePath, Class<T> objectClass) {
        try(BufferedReader reader = new BufferedReader(new FileReader(sourcePath.toFile()))) {
            return GSON.fromJson(reader, objectClass);
        } catch(IOException e) {
            Logger.getLogger(LOGGER).log(Level.SEVERE, e,
                    () -> MessageFormat.format("Could not read JSON file {0}", sourcePath));
            return null;
        }
    }

    
    public void saveToJson(Object object, Path externalPath) {
        try(FileWriter writer = new FileWriter(externalPath.toFile())) {
            writer.write(GSON.toJson(object));
        } catch(IOException e) {
            logError(e, "save", object.getClass().getName(), externalPath);
            return;
        }
        Logger.getLogger(LOGGER).log(Level.INFO, "Saved: {0} -> {1}",
                new Object[]{object.getClass().getName(), externalPath});
    }

    
    public void createDirectory(File path) {
        if(!path.exists()) {
            Logger.getLogger(LOGGER).log(Level.INFO, "Creating path {0}", path.getName());
            boolean bool = path.mkdirs();
            Logger.getLogger(LOGGER).log(Level.INFO, "Directory creation {0}", bool ? "successful" : "failed");
        }
    }

    private void logError(Throwable t, String action, String source, Path destination) {
        Logger.getLogger(LOGGER).log(Level.SEVERE, t, () -> MessageFormat.format("Failed to {0}: {1} -> {2}",
                action, source, destination));
    }

}