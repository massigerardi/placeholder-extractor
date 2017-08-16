package net.ambulando.demo.utils;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mgerardi on 14.08.17.
 */
public class PlaceholderExtractor {

    public Map<String, Object> getArguments(String input, String model) throws ClassNotFoundException {
        Template template = Template.getTemplate(model);
        Matcher matcher = Pattern.compile(template.getPattern()).matcher(input);
        if (!matcher.matches()) {
            return Collections.emptyMap();
        }
        Map<String, Object> args = new LinkedHashMap<>();
        args.put("command", input);
        int i = 0;
        for (String k : template.getArgs().keySet()){
            Placeholder placeholder = template.getArgs().get(k);
            args.put(k, placeholder.getValue(matcher.group(++i)));
        }
        return args;
    }


}
