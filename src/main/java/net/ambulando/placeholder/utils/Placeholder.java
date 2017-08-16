package net.ambulando.placeholder.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * A placeholder is described as:
 * <p>
 * <li>{name:type}</li>
 * <p>
 * where type can be one of the following:
 * <ul>
 * <li>string</li>
 * <li>integer</li>
 * <li>double</li>
 * </ul>
 * <p>
 * or a custom type class, as described in {@link PlaceholderTypes}
 * <p>
 * Created by mgerardi on 14.08.17.
 */
@Builder(toBuilder = true)
@Getter
@ToString
@Slf4j
public class Placeholder {

  private String name;

  private String expression;

  private PlaceholderType type;

}
