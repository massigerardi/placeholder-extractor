package net.ambulando.placeholder.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * The definitionn of type to be used to extract real values.
 * <p>
 * Any class T defining the method <code>public T valueOf(String value)</code> can be used as type's class.
 * <p>
 * <p>
 * Created by mgerardi on 14.08.17.
 */
@Getter
@ToString
@Slf4j
public class PlaceholderType {

  private String regex;

  private String type;

  private Class cls;

  private Method method;

  @JsonCreator
  public PlaceholderType(@JsonProperty("regex") String regex,
                         @JsonProperty("type") String type) throws ClassNotFoundException, NoSuchMethodException {
    this.regex = regex;
    this.type = type;
    this.cls = Class.forName(type);
    if (!String.class.isAssignableFrom(cls)) {
      this.method = cls.getMethod("valueOf", String.class);
    }
  }

  /**
   * returns the value of a given string that satisfies the regex expression that defines this placeholder.
   *
   * @param value the string values
   * @return an instance of {@link Placeholder#type}
   * @throws ClassNotFoundException
   */
  public Object getValue(String value) throws InvocationTargetException, IllegalAccessException {
    if (String.class.isAssignableFrom(cls)) {
      return value;
    }
    return method.invoke(cls, value);
  }

}
