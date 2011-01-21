package nl.gridshore.nosapi.mapping;

import java.util.ArrayList;

/**
 * @author Jettro Coenradie
 */
public interface LatestItem {
    ArrayList<ArrayList<Article>> getItems();
    void setItems(ArrayList<ArrayList<Article>> items);
}
