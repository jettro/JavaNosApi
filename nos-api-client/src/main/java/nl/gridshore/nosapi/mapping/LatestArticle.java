package nl.gridshore.nosapi.mapping;

import nl.gridshore.nosapi.mapping.Article;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

/**
 * @author Jettro Coenradie
 */
public class LatestArticle implements LatestItem {
    @JsonProperty("latest_article")
    private ArrayList<ArrayList<Article>> items = new ArrayList<ArrayList<Article>>();

    @Override
    public ArrayList<ArrayList<Article>> getItems() {
        return items;
    }

    @Override
    public void setItems(ArrayList<ArrayList<Article>> items) {
        this.items = items;
    }
}
