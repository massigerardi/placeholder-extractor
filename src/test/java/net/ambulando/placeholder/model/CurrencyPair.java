package net.ambulando.placeholder.model;

import lombok.Builder;
import lombok.Getter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A test class.
 *
 * Created by mgerardi on 14.08.17.
 */
@Builder
@Getter
public class CurrencyPair {

    private String base;

    private String counter;

    private final static Pattern PATTERN = Pattern.compile("([A-Z]{3})[/|@]*([A-Z]{3})");

    public static CurrencyPair valueOf(String pair) {
        Matcher m = PATTERN.matcher(pair);
        if (m.matches()) {
            return CurrencyPair.builder().base(m.group(1)).counter(m.group(2)).build();
        }
        throw new IllegalArgumentException(pair);
    }

    public String toString() {
        return base+counter;
    }
}
