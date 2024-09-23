package com.searchEngine.searchEngine;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.searchEngine.searchEngine.service.ScrapperService;

@SpringBootTest
public class ScrapperServiceTest {
    @Autowired
    private ScrapperService scrapperService;

    @Test
    public void testAddPage() throws IOException{
        String displayedText = scrapperService.scrapePage("https://www.apache.org/", "apache.org");
        System.out.println(displayedText.length());
    }

}
