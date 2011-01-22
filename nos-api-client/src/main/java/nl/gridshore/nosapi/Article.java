package nl.gridshore.nosapi;

import org.joda.time.DateTime;

import java.util.List;

/**
 * @author Jettro Coenradie
 */
public class Article {
    private String id;
    private String type;
    private String title;
    private String description;
    private String link;
    private DateTime published;
    private DateTime lastUpdate;
    private String thumbnail_xs;
    private String thumbnail_s;
    private String thumbnail_m;
    private String thumbnail;
    private List<String> keywords;
    private String embedCode;

    public Article(String id,
                   String type,
                   String title,
                   String description,
                   String link,
                   List<String> keywords,
                   DateTime lastUpdate,
                   DateTime published,
                   String thumbnail_m,
                   String thumbnail_s,
                   String thumbnail_xs,
                   String thumbnail) {
        this(id,type,title,description,link,keywords,lastUpdate,published,thumbnail_m,thumbnail_s,thumbnail_xs,thumbnail,null);
    }

    public Article(String id,
                   String type,
                   String title,
                   String description,
                   String link,
                   List<String> keywords,
                   DateTime lastUpdate,
                   DateTime published,
                   String thumbnail_m,
                   String thumbnail_s,
                   String thumbnail_xs,
                   String thumbnail,
                   String embedCode) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.link = link;
        this.keywords = keywords;
        this.lastUpdate = lastUpdate;
        this.published = published;
        this.thumbnail_m = thumbnail_m;
        this.thumbnail_s = thumbnail_s;
        this.thumbnail_xs = thumbnail_xs;
        this.embedCode = embedCode;
    }

    public String getDescription() {
        return description;
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

    public String getThumbnail_m() {
        return thumbnail_m;
    }

    public String getThumbnail_s() {
        return thumbnail_s;
    }

    public String getThumbnail_xs() {
        return thumbnail_xs;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getEmbedCode() {
        return embedCode;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
