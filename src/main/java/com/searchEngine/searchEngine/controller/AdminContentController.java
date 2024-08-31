package com.searchEngine.searchEngine.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.searchEngine.searchEngine.service.TranslationService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/admin/content")
public class AdminContentController {
    @Autowired
    private TranslationService translationService;
    
    @GetMapping("/list/{language}/{file}")
    public String getFieldsList(
        @PathVariable String locale,
        @PathVariable String file,
        Model model
    ) throws FileNotFoundException, IOException{
        Properties properties = translationService.loadProperties(file, locale);
        model.addAttribute("fields", properties);

        return "admin/content-fields";
    }

    @PostMapping("/list/{language}/{file}")
    public String editFields(
        @ModelAttribute Map<String, String> fields,
        Model model
    ) {
        
        return "admin/content-fields";
    }
    
}
