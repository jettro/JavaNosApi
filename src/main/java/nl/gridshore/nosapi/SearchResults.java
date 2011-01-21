package nl.gridshore.nosapi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jettro Coenradie
 */
public class SearchResults {
    List<Document> documents = new ArrayList<Document>();
    List<Keyword> keywords = new ArrayList<Keyword>();

    public SearchResults(List<Document> documents, List<Keyword> keywords) {
        this.documents = Collections.unmodifiableList(documents);
        this.keywords = Collections.unmodifiableList(keywords);
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }
}
