package net.ambulando.demo.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
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

    public Object getValue(String value) throws ClassNotFoundException {
        Class type = getType().getClassType();
        if (String.class.isAssignableFrom(type)) {
            return value;
        }
        try {
            Method method = type.getMethod("valueOf", String.class);
            return method.invoke(type, value);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("!!Error getting value: {}:{}", e.getClass().getSimpleName(), e.getMessage());
        }
        return value;
    }


}
