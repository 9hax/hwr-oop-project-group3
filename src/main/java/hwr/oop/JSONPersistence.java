package hwr.oop;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class JSONPersistence implements PersistenceAdapter{
    IOAdapter globalIO;

    Gson globalGson;

    public JSONPersistence(IOAdapter ioAdapter) {
        globalGson = new GsonBuilder().setPrettyPrinting().create();
        globalIO = ioAdapter;
    }

    @Override
    public void save(Object persistentObject, String key) {
        FileWriter writer = null;
        String fileName = key+".json";
        try {
            writer = new FileWriter(fileName);
            writer.write(globalGson.toJson(persistentObject));
            writer.close();
            globalIO.putString("Data saved successfully!");
        } catch (IOException e) {
            globalIO.putString("There was an error writing the file "+fileName+" to persistent storage.");
            globalIO.putString("Copy the following data instead:");
            globalIO.putString(globalGson.toJson(persistentObject));
        }
    }

    @Override
    public void save(Object persistentObject) {
        save(persistentObject, "defaultPersistence");
    }

    @Override
    public ScorePrimitiveList load(String key) {
        String fileName = key+".json";
        String jsonData;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));
            jsonData = bufferedReader.lines().collect(Collectors.joining(System.lineSeparator()));
        } catch (FileNotFoundException e) {
            globalIO.putString("There was an error reading the file "+fileName+". Please paste your JSON data into this prompt:");
            jsonData = globalIO.getString();
        }
        return globalGson.fromJson(jsonData, ScorePrimitiveList.class);
    }

    @Override
    public ScorePrimitiveList load() {
        return load("defaultPersistence");
    }
}
