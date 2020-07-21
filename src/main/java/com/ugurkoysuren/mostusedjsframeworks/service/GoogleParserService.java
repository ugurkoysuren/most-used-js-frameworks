package com.ugurkoysuren.mostusedjsframeworks.service;

import com.ugurkoysuren.mostusedjsframeworks.utils.DomainNameExtractor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

@Service
public class GoogleParserService {
    public GoogleParserService() {
    }

    public Set<String> getGoogleResults(String query, int numberOfResults) throws IOException {
        query = query.replaceAll(" ", "+").toLowerCase();
        System.out.println("\nSearching for \"" + query + "\"");
        System.out.println("Showing " + numberOfResults + " results:\n----------------------------------");

        String url = "https://www.google.com/search?q=" + query + "&num=" + numberOfResults;
        Document doc = Jsoup
                .connect(url)
                .userAgent("Jsoup client")
                .timeout(5000).get();
        Elements links = doc.select("a[href]");
        Set<String> websitesHashSet = new HashSet<String>();
        for (Element link : links) {
            String attr1 = link.attr("href");
            if (attr1.startsWith("/url?q=")) {
                websitesHashSet.add(DomainNameExtractor.getDomainName(attr1));
            }
        }
        return websitesHashSet;
    }
}
