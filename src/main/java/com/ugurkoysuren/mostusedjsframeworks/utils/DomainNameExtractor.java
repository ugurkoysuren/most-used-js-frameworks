package com.ugurkoysuren.mostusedjsframeworks.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class DomainNameExtractor {

    private static Matcher domain_matcher;
    private static final String DOMAIN_NAME_PATTERN = "([a-zA-Z0-9]([a-zA-Z0-9\\-]{0,61}[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,15}";
    private static Pattern domain_pattern = Pattern.compile(DOMAIN_NAME_PATTERN);



    private DomainNameExtractor() {}
    public static String getDomainName(String url) {

        String domainName = "";
        domain_matcher = domain_pattern.matcher(url);

        if (domain_matcher.find()) {
            domainName = domain_matcher.group(0).toLowerCase().trim();
        }

        return domainName;
    }
}