package nl.gridshore.nosapi.mapping;

import java.util.ArrayList;

/**
 * @author Jettro Coenradie
 */
public class Search {
    private ArrayList<Document> documents = new ArrayList<Document>();
    private ArrayList<Keyword> keywords = new ArrayList<Keyword>();

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(ArrayList<Document> documents) {
        this.documents = documents;
    }

    public ArrayList<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<Keyword> keywords) {
        this.keywords = keywords;
    }
}
