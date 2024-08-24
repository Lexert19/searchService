package com.searchEngine.searchEngine.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.component.SearchService;

@Service
public class ScrapperService {

    public String scrapeDisplayedText(String url) throws IOException {
        Document document = Jsoup.connect(url).get();

        StringBuilder displayedText = new StringBuilder();

        Elements textElements = document.select("p, h1, h2, h3, h4, h5, h6, li, span, div");
        for (Element element : textElements) {
            String text = element.text().trim();
            if (!text.isEmpty()) {
                displayedText.append(text).append("\n");
            }
        }

        return displayedText.toString();
    }

    public List<String> scrapeLinksWithDomain(String url, String domain) throws IOException {
        Document document = Jsoup.connect(url).get();

        Elements links = document.select("a[href]");
        List<String> filteredLinks = new LinkedList<>();

        for(Element link : links){
            String href = link.attr("abs:href");

            if(isSameDomain(url, domain))
                filteredLinks.add(href);
        }

        return filteredLinks;
    }


    public List<String> srapeLinksFromDomain(String domain, boolean https) throws IOException{
        String protocol = https ? "https://" : "http://";
        String url = protocol+domain;
        HashSet<String> uniqueLinks = new HashSet<>();
        uniqueLinks.add(url);

        Queue<String> linksToDownload = new LinkedList<>();
        linksToDownload.add(url);

        while(!linksToDownload.isEmpty()){
            String linkToDownload = linksToDownload.poll();
            List<String> links = this.scrapeLinksWithDomain(linkToDownload, domain);
            for(String link : links){
                if(!uniqueLinks.contains(link)){
                    linksToDownload.add(link);
                    uniqueLinks.add(link);
                }
            }
        }

        return new LinkedList<>(uniqueLinks);
    }

    public List<String> srapeLinksFromDomain(String domain) throws IOException{
        return this.srapeLinksFromDomain(domain, true);
    }

    public void indexLinks(List<String> links, String domain) throws IOException{
        SearchService searchService = new SearchService(domain);

        for(String link : links){
            String text = this.scrapeDisplayedText(link);
            searchService.indexDocument(link, text);
        }
    }

    private boolean isSameDomain(String url, String domain){
        if(url.startsWith("/"))
            return true;

        return false;
    }
}
