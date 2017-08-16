package net.ambulando.placeholder.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A class to extract a set of parameters from a template.
 * <p>
 * Created by mgerardi on 14.08.17.
 */
public class PlaceholderExtractor {


  /**
   *
   * Example:
   *
   * input1: tell me how many grams are in 100 kilograms
   * input2: tell me how much grams are in 100 kilograms
   * input3: tell me how many grams are in 100.5 kilograms
   * model: tell me how many {unit1:string} are in {quantity:integer} {unit2:string}
   *
   * result from input1:
   * <ul>
   *   <li>unit1 = "grams"</li>
   *   <li>quantity = 100</li>
   *   <li>unit2 = kilograms</li>
   * </ul>
   * result from input2: null
   * result from input3: null
   *
   * @param input the current string
   * @param model the string representing the template of the parameters
   * @return a set of parameters extracted from the current string according to the temlate or null if no match is found.
   */
  public Parameters getArguments(String input, String model) throws InvocationTargetException, IllegalAccessException {
    Template template = Template.getTemplate(model);
    Matcher matcher = Pattern.compile(template.getPattern()).matcher(input);
    if (!matcher.matches()) {
      return null;
    }
    Parameters args = new Parameters();
    int i = 0;
    for (String k : template.getArgs().keySet()) {
      String value = matcher.group(++i);
      Placeholder placeholder = template.getArgs().get(k);
      args.put(k, placeholder.getType().getValue(value));
    }
    return args;
  }


}
