/*
 * Copyright (c) 2011. Gridshore
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
    private String thumbnailXS;
    private String thumbnailS;
    private String thumbnailM;
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
                   String thumbnailM,
                   String thumbnailS,
                   String thumbnailXS) {
        this(id,type,title,description,link,keywords,lastUpdate,published, thumbnailM, thumbnailS, thumbnailXS,null);
    }

    public Article(String id,
                   String type,
                   String title,
                   String description,
                   String link,
                   List<String> keywords,
                   DateTime lastUpdate,
                   DateTime published,
                   String thumbnailM,
                   String thumbnailS,
                   String thumbnailXS,
                   String embedCode) {
        this.id = id;
        this.type = type;
        this.title = title;
        this.description = description;
        this.link = link;
        this.keywords = keywords;
        this.lastUpdate = lastUpdate;
        this.published = published;
        this.thumbnailM = thumbnailM;
        this.thumbnailS = thumbnailS;
        this.thumbnailXS = thumbnailXS;
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

    public String getThumbnailM() {
        return thumbnailM;
    }

    public String getThumbnailS() {
        return thumbnailS;
    }

    public String getThumbnailXS() {
        return thumbnailXS;
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

}
