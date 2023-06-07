package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.stream.Collectors;

public class JSONPersistence implements PersistenceAdapter {
    IOAdapter globalIO;

    Gson globalGson;

    public JSONPersistence(IOAdapter ioAdapter) {
        globalGson = new GsonBuilder().setPrettyPrinting().create();
        globalIO = ioAdapter;
    }

    @Override
    public void save(ScorePrimitiveList scores, String key) {
        String fileName = key + ".json";
        try(FileWriter writer = new FileWriter((fileName))) {
            writer.write(globalGson.toJson(scores));
            globalIO.putString("Data saved successfully!");
        } catch (IOException e) {
            globalIO.putString("There was an error writing the file " + fileName + " to persistent storage.");
            globalIO.putString("Copy the following data instead:");
            globalIO.putString(globalGson.toJson(scores));
        }
    }

    @Override
    public void save(ScorePrimitiveList scores) {
        save(scores, "defaultPersistence");
    }

    @Override
    public ScorePrimitiveList load(String key) {
        String fileName = key + ".json";
        String jsonData;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))){
            jsonData = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            globalIO.putString("There was an error reading the file " + fileName + ". Please paste your JSON data into this prompt:");
            jsonData = globalIO.getString();
        }
        return globalGson.fromJson(jsonData, ScorePrimitiveList.class);
    }

    @Override
    public ScorePrimitiveList load() {
        return load("defaultPersistence");
    }
}
