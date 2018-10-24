package io.github.omn0mn0m.util;

/**
 * @author Petteri Salonurmi
 * public class TextPrinter
 *
 * A utility class for printing system messages without having to rely on the contructed Game object.
 * Using the utility class removes a dependency from the rest of the classes to the main Game-object, as
 * it may not have been initialized properly.
 *
 */
public class TextPrinter {

    public static void print(String string) {
        System.out.println(string);
    }

}
