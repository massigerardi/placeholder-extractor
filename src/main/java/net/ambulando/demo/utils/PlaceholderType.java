package net.ambulando.demo.utils;

import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Getter;
import lombok.ToString;

/**
 * Created by mgerardi on 14.08.17.
 */
@Getter
@ToString
public class PlaceholderType {

    private String name;

    @JsonRawValue
    private String regex;

    private String type;

    public Class getClassType() throws ClassNotFoundException {
        return Class.forName(type);
    }
}
