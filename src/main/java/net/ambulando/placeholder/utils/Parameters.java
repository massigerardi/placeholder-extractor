package net.ambulando.placeholder.utils;

import lombok.AllArgsConstructor;

import java.util.HashMap;

/**
 * A collection of parameters in the form name:value.
 *
 * Created by massi on 16/08/2017.
 */
@AllArgsConstructor
public class Parameters extends HashMap <String, Object> {

  public <T> T get(String name) {
    return (T) super.get(name);
  }
}
