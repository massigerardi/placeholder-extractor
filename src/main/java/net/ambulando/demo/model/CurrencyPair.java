package net.ambulando.demo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mgerardi on 14.08.17.
 */
@Builder
@Getter
public class CurrencyPair {

    private String base;

    private String counter;
    private final static String REGEX = "([A-Z]{3})[/|@]*([A-Z]{3})";

    public static CurrencyPair valueOf(String pair) {
        Matcher m = Pattern.compile(REGEX).matcher(pair);
        if (m.matches()) {
            return CurrencyPair.builder().base(m.group(1)).counter(m.group(2)).build();
        }
        throw new IllegalArgumentException(pair);
    }

    public String toString() {
        return base+counter;
    }
}
