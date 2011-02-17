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

package nl.gridshore.nosapi.mapping;

import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.joda.time.DateTime;

import java.util.ArrayList;

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
    private String embedCode;
    private ArrayList<String> keywords = new ArrayList<String>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public DateTime getPublished() {
        return published;
    }

    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    public void setPublished(DateTime published) {
        this.published = published;
    }

    public DateTime getLastUpdate() {
        return lastUpdate;
    }

    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    @JsonProperty("last_update")
    public void setLastUpdate(DateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public ArrayList<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(ArrayList<String> keywords) {
        this.keywords = keywords;
    }

    public String getThumbnail_m() {
        return thumbnail_m;
    }

    public void setThumbnail_m(String thumbnail_m) {
        this.thumbnail_m = thumbnail_m;
    }

    public String getThumbnail_s() {
        return thumbnail_s;
    }

    public void setThumbnail_s(String thumbnail_s) {
        this.thumbnail_s = thumbnail_s;
    }

    public String getThumbnail_xs() {
        return thumbnail_xs;
    }

    public void setThumbnail_xs(String thumbnail_xs) {
        this.thumbnail_xs = thumbnail_xs;
    }

    public String getEmbedCode() {
        return embedCode;
    }

    @JsonProperty("embedcode")
    public void setEmbedCode(String embedCode) {
        this.embedCode = embedCode;
    }

}
