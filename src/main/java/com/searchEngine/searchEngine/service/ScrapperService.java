package com.searchEngine.searchEngine.service;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jsoup.Jsoup;
import org.jsoup.UnsupportedMimeTypeException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.searchEngine.searchEngine.component.SearchService;

@Service
public class ScrapperService {
    public static int LIMIT = 100;

    public String scrapeDisplayedText(Document document) throws IOException {
        StringBuilder displayedText = new StringBuilder();

        Elements textElements = document
                .select("main p, main h1, main h2, main h3, main h4, main h5, main h6, main li, main span");
        for (Element element : textElements) {
            String text = element.text().trim();
            if (!text.isEmpty() && text.split("\\s+").length > 2) {
                displayedText.append(text).append("\n");
            }
        }

        return displayedText.toString();
    }

    public String scrapePage(String url, String domain) throws IOException {
        SearchService searchService = new SearchService(domain);
        Document document;
        try {
            document = Jsoup.connect(url).get();
        } catch (Exception exception) {
            exception.printStackTrace();
            return "";
        }
        String displayedText = this.scrapeDisplayedText(document);
        searchService.indexDocument(url, displayedText);
        return displayedText;
    }

    public List<String> scrapeLinksFromHtml(Document document, String domain, String url) throws IOException {
        List<String> filteredLinks = new LinkedList<>();

        Elements links = document.select("a[href]");

        for (Element link : links) {
            String href = link.attr("abs:href");

            if (isSameDomain(url, domain))
                filteredLinks.add(href);
        }

        return filteredLinks;

    }

    public List<String> scrapeDomain(String domain, boolean https) throws IOException {
        SearchService searchService = new SearchService(domain);
        String protocol = https ? "https://" : "http://";
        String url = protocol + domain;
        HashSet<String> uniqueLinks = new HashSet<>();
        uniqueLinks.add(url);

        Queue<String> linksToDownload = new LinkedList<>();
        linksToDownload.add(url);

        int counter = 0;
        while (!linksToDownload.isEmpty()) {
            if (counter > LIMIT)
                break;
            String linkToDownload = linksToDownload.poll();
            Document document;
            try {
                document = Jsoup.connect(linkToDownload).get();
            } catch (UnsupportedMimeTypeException exception) {
                exception.printStackTrace();
                continue;
            }
            List<String> links = this.scrapeLinksFromHtml(document, domain, linkToDownload);
            String text = this.scrapeDisplayedText(document);
            searchService.indexDocument(linkToDownload, text);

            for (String link : links) {
                if (!uniqueLinks.contains(link)) {
                    linksToDownload.add(link);
                    uniqueLinks.add(link);
                }
            }

            counter++;
        }

        return new LinkedList<>(uniqueLinks);
    }

    public List<String> srapeLinksFromDomain(String domain) throws IOException {
        return this.scrapeDomain(domain, true);
    }

    private boolean isSameDomain(String url, String domain) {
        if (url.startsWith("/"))
            return true;
        if (url.contains("://" + domain))
            return true;

        return false;
    }
}
