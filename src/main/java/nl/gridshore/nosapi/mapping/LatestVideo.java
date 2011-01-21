package nl.gridshore.nosapi.mapping;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

/**
 * @author Jettro Coenradie
 */
public class LatestVideo  implements LatestItem {
    @JsonProperty("latest_video")
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
