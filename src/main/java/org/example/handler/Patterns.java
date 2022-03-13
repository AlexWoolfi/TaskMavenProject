package org.example.handler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patterns {
    static String cleanWorldForFirstArgs(String s) {
        s = s.replaceAll("[^a-zA-Z]", "");
        return s;
    }

    public static String cleanWorldArgs(String s) {
        Pattern pattern = Pattern.compile("'.*'");
        Matcher matcher = pattern.matcher(s);
        if (s.contains("-fn")) {
            while (matcher.find()) {
                s = s.substring(matcher.start()+1, matcher.end()-1);
                return s;
            }
        }
        if (s.contains("-ln")) {
            while (matcher.find()) {
                s = s.substring(matcher.start()+1, matcher.end()-1);
                return s;
            }
        }
        if (s.contains("-un")) {
            while (matcher.find()) {
                s = s.substring(matcher.start()+1, matcher.end()-1);
                return s;
            }

        }
        if (s.contains("-tt")) {
            while (matcher.find()) {
                s = s.substring(matcher.start()+1, matcher.end()-1);
                return s;
            }

        }
        if (s.contains("-td")) {
            while (matcher.find()) {
                s = s.substring(matcher.start()+1, matcher.end()-1);
                return s;
            }

        }

        return s;
    }
}
