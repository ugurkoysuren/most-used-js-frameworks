package com.ugurkoysuren.mostusedjsframeworks.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Service
public class ResultService {

    private GoogleParserService googleParserService;
    private LibraryExtractor libraryExtractor;

    @Autowired
    public ResultService(GoogleParserService googleParserService, LibraryExtractor libraryExtractor) {
        this.googleParserService = googleParserService;
        this.libraryExtractor=libraryExtractor;
    }


    public Map<String,Integer> getResults(String keyword, int numberOfResults){
        Set<String> result = null;
        Map<String, Integer> jsLibraryResultMap = null;
        try {
            result = googleParserService.getGoogleResults(keyword,numberOfResults);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            jsLibraryResultMap = libraryExtractor.getJsLibsAsMap(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return jsLibraryResultMap;
    }
    public Set<String> getWebsites(String keyword, int numberOfResults){
        Set<String> result = null;
        try {
            result = googleParserService.getGoogleResults(keyword,numberOfResults);
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }

}
