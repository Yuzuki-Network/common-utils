package dev.yuzuki.utils.text;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isMatch(String str, String regex) {
        return str.matches(regex);
    }

    public static List<String> getAllMatches(String str, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(str);
        List<String> matchList = new ArrayList<>();

        while (matcher.find()) {
            matchList.add(matcher.group());
        }

        return matchList;
    }
}
