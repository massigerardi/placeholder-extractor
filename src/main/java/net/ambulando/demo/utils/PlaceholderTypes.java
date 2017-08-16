package net.ambulando.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * Created by mgerardi on 14.08.17.
 */
@Slf4j
@Getter
public class PlaceholderTypes {

    static {
        try {
            INSTANCE = load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, PlaceholderType> types;

    public static PlaceholderTypes INSTANCE;


    protected static PlaceholderTypes load() throws IOException {
        File file = new File("regex.yml");
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        PlaceholderTypes types = mapper.readValue(file, PlaceholderTypes.class);
        return types;
    }

    public PlaceholderType getType(String name) {
        return types.get(name);
    }

}
