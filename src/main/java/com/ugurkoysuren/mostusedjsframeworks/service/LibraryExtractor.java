package com.ugurkoysuren.mostusedjsframeworks.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class LibraryExtractor {
    private static final List<String> jslibs = Arrays.asList("jquery", "bootstrap", "angular", "vue", "backbone", "node", "anime", "require", "javelin", "ember", "three", "winjs", "meteor", "aurelia", "ionic", "next", "d3");

    public LibraryExtractor() {
    }

    /**
     * This method takes a hashset of domains and checks containing js libs
     * @param result
     * @return
     * @throws IOException
     *
     */
    public Map<String, Integer> getJsLibsAsMap(Set<String> result) throws IOException {
        Map<String, Integer> jsLibraryResultMap = new HashMap<String, Integer>();

        result.parallelStream().forEach(url -> {
            System.out.println(url);
            System.out.println(Thread.currentThread().getName() + ": " + url);
            Document doc2 = null;
            try {
                doc2 = Jsoup
                        .connect("http://" + url)
                        .userAgent("Jsoup client")
                        .timeout(5000).get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements scriptElements = doc2.getElementsByTag("script");
            for (Element element :scriptElements ){
                // get element src
                String src = element.attr("src");
                if(!src.equals(""))
                {
                    String mLibrary = getMatchingLibraryFromList(src, jslibs);
                    if(!mLibrary.equals("")) {
                        // check if lib name is already in map
                        if (!jsLibraryResultMap.containsKey(mLibrary)) {
                            // if its a new value
                            jsLibraryResultMap.put(mLibrary, 1);
                        } else {
                            // otherwise update the value
                            int value = jsLibraryResultMap.get(mLibrary);
                            value++;
                            jsLibraryResultMap.put(mLibrary, value);
                        }
                    }
                }
            }
        });

        return jsLibraryResultMap;
    }
    /**
     * This method gets an string and checks it if it contains one of the js libs from the list.
     * E.g. :  String : https://cdn.journaldev.com/wp-includes/js/jquery/jquery.js , Contains: jquery
     * @param inputString
     * @param items
     * @return
     */
    public static String getMatchingLibraryFromList(String inputString, List<String> items) {
        String matchingLibrary = "";
        for(int i=0; i<items.size(); i++) {
            if (inputString.toLowerCase().contains(items.get(i)))
            {
                matchingLibrary = items.get(i);
                break;
            }
        }
        return matchingLibrary;
    }
}
