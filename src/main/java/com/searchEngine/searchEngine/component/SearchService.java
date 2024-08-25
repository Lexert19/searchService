package com.searchEngine.searchEngine.component;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexNotFoundException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.MultiTerms;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.queryparser.flexible.core.QueryNodeException;
import org.apache.lucene.queryparser.flexible.standard.StandardQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

import com.searchEngine.searchEngine.model.SearchResult;

public class SearchService {
    private Directory index;
    private final StandardAnalyzer analyzer;
    private final StandardQueryParser queryParser;
    private final HashSet<String> allTitles;

    public SearchService(String domain) throws IOException {
        String directory = domain.replace(".", "_");
        Path path = Paths.get("").toAbsolutePath().resolve("storage").resolve(directory);
        this.index = FSDirectory.open(path);
        this.analyzer = new StandardAnalyzer();
        this.queryParser = new StandardQueryParser(analyzer);
        this.allTitles = getAllTitles();
    }

    public void indexDocument(String title, String content) throws IOException {
        if (allTitles.contains(title))
            return;
        allTitles.add(title);

        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        try (IndexWriter writer = new IndexWriter(index, config)) {
            Document doc = new Document();
            doc.add(new TextField("title", title, Field.Store.YES));
            doc.add(new TextField("content", content, Field.Store.YES));
            writer.addDocument(doc);
        }
    }

    public List<SearchResult> searchDocuments(String queryString) throws QueryNodeException, IOException {
        Query query = queryParser.parse(queryString, "content");

        try (IndexReader reader = DirectoryReader.open(index)) {
            IndexSearcher searcher = new IndexSearcher(reader);
            TopDocs topDocs = searcher.search(query, 10);

            List<SearchResult> results = new ArrayList<>();
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                results.add(new SearchResult(doc.get("title"), doc.get("content"), scoreDoc.score));
            }

            return results;
        }
    }

    public HashSet<String> getAllTitles() throws IOException {
        HashSet<String> titles = new HashSet<>();

        try (IndexReader reader = DirectoryReader.open(index)) {
            Terms terms = MultiTerms.getTerms(reader, "title");

            if (terms != null) {
                TermsEnum termsEnum = terms.iterator();
                BytesRef text;
                while ((text = termsEnum.next()) != null) {
                    titles.add(text.utf8ToString());
                }
            }
        }catch(IndexNotFoundException exception){

        }

        return titles;
    }

}
