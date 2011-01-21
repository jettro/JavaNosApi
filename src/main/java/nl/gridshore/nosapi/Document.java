package nl.gridshore.nosapi;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jettro Coenradie
 */
public class Document {
    private String id;
    private int score;
    private String type;
    private String title;
    private String description;
    private DateTime published;
    private DateTime lastUpdate;
    private String thumbnail;
    private String category;
    private String subCategory;
    private String link;
    private List<String> keywords = new ArrayList<String>();

    public Document(String id,
                    String type,
                    String title,
                    String description,
                    String category,
                    String subCategory,
                    DateTime published,
                    DateTime lastUpdate,
                    String link,
                    String thumbnail,
                    int score,
                    ArrayList<String> keywords) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.category = category;
        this.subCategory = subCategory;
        this.published = published;
        this.lastUpdate = lastUpdate;
        this.link = link;
        this.thumbnail = thumbnail;
        this.score = score;
        this.keywords = keywords;
    }

    public String getCategory() {
        return category;
    }

    public String getId() {
        return id;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public DateTime getLastUpdate() {
        return lastUpdate;
    }

    public String getLink() {
        return link;
    }

    public DateTime getPublished() {
        return published;
    }

    public int getScore() {
        return score;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
