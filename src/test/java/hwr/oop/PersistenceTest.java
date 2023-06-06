package hwr.oop;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


import java.util.List;

class PersistenceTest {
    @Test
    void save_and_load() {
        IOAdapter mioa = new MockIOAdapter();
        List<ScorePrimitive> data = List.of(new ScorePrimitive("Jason", 50), new ScorePrimitive("BongoCat", 9001));
        PersistenceAdapter persistenceAdapter = new JSONPersistence(mioa);
        persistenceAdapter.save(new ScorePrimitiveList(data));
        assertThat(mioa.pollOutput()).isEqualTo("Data saved successfully!");
        ScorePrimitiveList loadedData = persistenceAdapter.load();
        assertThat(loadedData.getScorePrimitiveList().get(1).getName()).isEqualTo("BongoCat");
        assertThat(loadedData.getScorePrimitiveList().get(0).getScore()).isEqualTo(50);
    }

    @Test
    void testExceptions() {
        IOAdapter mioa = new MockIOAdapter();
        PersistenceAdapter persistenceAdapter = new JSONPersistence(mioa);
        ScorePrimitiveList spl = new ScorePrimitiveList(List.of(new ScorePrimitive("Jason", 1)));

        assertDoesNotThrow(()->persistenceAdapter.save(spl, "/../"));

        assertThat(mioa.pollOutput()).isEqualTo("There was an error writing the file /../.json to persistent storage.");
        assertThat(mioa.pollOutput()).isEqualTo("Copy the following data instead:");
        assertThat(mioa.pollOutput()).isEqualTo(
                "{\n" +
                "  \"scorePrimitives\": [\n" +
                "    {\n" +
                "      \"name\": \"Jason\",\n" +
                "      \"score\": 1\n" +
                "    }\n" +
                "  ]\n" +
                "}");

        mioa.queueInput("{\"scorePrimitives\":[{\"name\":\"Jason\",\"score\":50},{\"name\":\"BongoCat\",\"score\":9001}]}");
        assertThat(persistenceAdapter.load("/../").getScorePrimitiveList().get(0).getScore()).isEqualTo(50);
        assertThat(mioa.lastOutput()).isEqualTo("There was an error reading the file /../.json. Please paste your JSON data into this prompt:");

    }
}
