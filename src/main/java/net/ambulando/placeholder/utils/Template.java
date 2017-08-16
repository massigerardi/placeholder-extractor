package net.ambulando.placeholder.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents a template to extract parameter from.
 * A template has the form:
 * <p>
 * "with <li>placeholder1</li> do <li>placeholder2</li> so <li>placeholder3</li>
 * <p>
 * where <li>placeholder</li> is  described in {@link Placeholder}.
 * <p>
 * <p>
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

  /**
   * Factory method for the template.
   * <p>
   * if a template exists with the same input, it does return the existing one.
   *
   * @param input the template description.
   * @return a new template
   */
  public static Template getTemplate(String input) {
    if (!templates.containsKey(input)) {
      Map<String, Placeholder> args = prepareMap(input);
      String pattern = preparePattern(input, args);
      Template template = Template.builder().pattern(pattern).args(args).build();
      templates.put(input, template);
    }
    return templates.get(input);


  }

  private static Map<String, Placeholder> prepareMap(String template) {
    Matcher matcher = Pattern.compile(regex).matcher(template);
    Map<String, Placeholder> args = new LinkedHashMap<>();
    while (matcher.find()) {
      args.put(matcher.group(2), Placeholder.builder()
          .name(matcher.group(2)).expression(matcher.group(1))
          .type(PlaceholderTypes.INSTANCE.get(matcher.group(3))).build());
    }
    return args;

  }

  private static String preparePattern(String template, Map<String, Placeholder> vars) {
    String pattern = template;
    pattern = pattern.replaceAll("([?.])", "\\\\$1");
    for (Placeholder var : vars.values()) {
      pattern = pattern.replaceAll("\\{" + var.getExpression() + "\\}", var.getType().getRegex());
    }
    return pattern;
  }


}
