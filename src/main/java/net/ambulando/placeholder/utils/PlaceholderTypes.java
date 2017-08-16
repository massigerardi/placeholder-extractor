package net.ambulando.placeholder.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A collections of {@link PlaceholderType}.
 *
 *
 * Created by mgerardi on 14.08.17.
 */
@Slf4j
@Getter
public class PlaceholderTypes extends HashMap<String, PlaceholderType> {

  public final static String DEFAULT_TYPES_FILE = "default-types.yml";

  public final static String CUSTOM_TYPES_FILE = "custom-types.yml";

  static {
    try {
      INSTANCE = load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static PlaceholderTypes INSTANCE;


  protected static PlaceholderTypes load() throws IOException {
    String defaultTypes = Resources.toString(Resources.getResource(DEFAULT_TYPES_FILE), Charsets.UTF_8);
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
    PlaceholderTypes types = mapper.readValue(defaultTypes, PlaceholderTypes.class);
    Map<String, PlaceholderType> externalTypes = loadExternalTypes(mapper);
    if (externalTypes != null) {
      types.putAll(externalTypes);
    }
    return types;
  }

  private static Map<String, PlaceholderType> loadExternalTypes(ObjectMapper mapper) {
    try {
      String defaultTypes = Resources.toString(Resources.getResource(CUSTOM_TYPES_FILE), Charsets.UTF_8);
      PlaceholderTypes types = mapper.readValue(defaultTypes, PlaceholderTypes.class);
      return types;
    } catch (IOException e) {
      return null;
    }
  }

}
