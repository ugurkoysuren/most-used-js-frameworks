package com.ugurkoysuren.mostusedjsframeworks.controller;

import com.ugurkoysuren.mostusedjsframeworks.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class ResultsController {

    ResultService resultService;

    @Autowired
    public ResultsController(ResultService resultService) {
        this.resultService = resultService;
    }

    @GetMapping("/{keyword}/{numberOfResults}")
    public Map<String,Integer> getResults(@PathVariable String keyword, @PathVariable int numberOfResults) {
        return resultService.getResults(keyword,numberOfResults);
    }
    @GetMapping("/websites/{keyword}/{numberOfResults}")
    public Set<String> getWebsites(@PathVariable String keyword, @PathVariable int numberOfResults) {
        return resultService.getWebsites(keyword,numberOfResults);
    }

}
