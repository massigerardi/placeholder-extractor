package net.ambulando.demo.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mgerardi on 14.08.17.
 */
@Builder
@Getter
@ToString
public class Template {

    private static String regex = "\\{((\\w*?):(\\w*?))\\}";

    private static Map<String, Template> templates = new HashMap<>();

    private String pattern;

    private Map<String, Placeholder> args;

    public static Template getTemplate(String input)  {
        if (!templates.containsKey(input)) {
            Map<String, Placeholder> args = prepareMap(input);
            String pattern = preparePattern(input, args);
            Template template = Template.builder().pattern(pattern).args(args).build();
            templates.put(input, template);
        }
        return templates.get(input);


    }

    private static Map<String, Placeholder> prepareMap(String template)  {
        Matcher matcher = Pattern.compile(regex).matcher(template);
        Map<String, Placeholder> args = new LinkedHashMap<>();
        while (matcher.find()) {
            args.put(matcher.group(2), Placeholder.builder()
                    .name(matcher.group(2)).expression(matcher.group(1))
                    .type(PlaceholderTypes.INSTANCE.getType(matcher.group(3).toUpperCase())).build());
        }
        return args;

    }

    private static String preparePattern(String template, Map<String, Placeholder> vars) {
        String pattern = template;
        pattern = pattern.replaceAll("([?.])", "\\\\$1");
        for (Placeholder var : vars.values()) {
            pattern = pattern.replaceAll("\\{"+var.getExpression()+"\\}", var.getType().getRegex());
        }
        return pattern;
    }


}
