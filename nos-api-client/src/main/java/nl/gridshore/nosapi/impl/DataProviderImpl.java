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

package nl.gridshore.nosapi.impl;

import nl.gridshore.nosapi.*;
import nl.gridshore.nosapi.mapping.*;
import nl.gridshore.nosapi.mapping.DayGuide;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>The default and for now only implementation of the DataProvider interface. This implementation is based on the
 * RESTTemplate from the spring framework. You can provide your own instance of the RESTTemplate. The default
 * RESTTemplate makes use of a specific ErrorHandler <code>NosApiResponseErrorHandler</code>.</p>
 * <p>This class is based on the NOS api version v1</p>
 *
 * @author Jettro Coenradie
 */
public class DataProviderImpl implements DataProvider {
    private final static Logger logger = LoggerFactory.getLogger(DataProviderImpl.class);
    private final static DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");

    private String serverBaseUrl = "http://open.nos.nl/v2/";

    private final String apiKey;
    private final RestTemplate restTemplate;

    public DataProviderImpl(String apiKey) {
        this(apiKey, new RestTemplate());
        restTemplate.setErrorHandler(new NosApiResponseErrorHandler());
    }

    public DataProviderImpl(String apiKey, RestTemplate restTemplate) {
        Assert.hasText(apiKey);
        Assert.notNull(restTemplate);
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
    }

    @Override
    public nl.gridshore.nosapi.Version obtainVersion() {
        VersionWrapper versionWrapper = restTemplate.getForObject(
                serverBaseUrl + "index/version/key/{apikey}/output/json", VersionWrapper.class, apiKey);
        return new nl.gridshore.nosapi.Version(
                versionWrapper.getVersions().get(0).getBuild(),
                versionWrapper.getVersions().get(0).getVersion());
    }

    @Override
    public List<nl.gridshore.nosapi.Article> obtainLatestNewsArticles() {
        return obtainLatestItemPerCategory("nieuws/", "article", LatestArticle.class);
    }

    @Override
    public List<nl.gridshore.nosapi.Article> obtainLatestSportsNewsArticles() {
        return obtainLatestItemPerCategory("sport/", "article", LatestArticle.class);
    }

    @Override
    public List<nl.gridshore.nosapi.Article> obtainLatestNewsVideos() {
        return obtainLatestItemPerCategory("nieuws/", "video", LatestVideo.class);
    }

    @Override
    public List<nl.gridshore.nosapi.Article> obtainLatestSportsNewsVideos() {
        return obtainLatestItemPerCategory("sport/", "video", LatestVideo.class);
    }

    @Override
    public List<nl.gridshore.nosapi.Article> obtainLatestNewsAudio() {
        return obtainLatestItemPerCategory("nieuws/", "audio", LatestAudio.class);
    }

    @Override
    public List<nl.gridshore.nosapi.Article> obtainLatestSportsNewsAudio() {
        return obtainLatestItemPerCategory("sport/", "audio", LatestAudio.class);
    }

    @Override
    public SearchResults searchForDocuments(String queryString) {
        return this.searchForDocuments(queryString,null);
    }

    @Override
    public SearchResults searchForDocuments(String queryString, SearchSort sort) {
        Assert.notNull(queryString);
        String searchSort;
        if (sort == null) {
            searchSort = SearchSort.SCORE.toString().toLowerCase();
        } else {
            searchSort = sort.toString().toLowerCase();
        }
        String url = serverBaseUrl + "search/query/key/{apikey}/output/json/q/{search}/sort/{sort}";
        SearchWrapper searchResults;
        try {
            searchResults = restTemplate.getForObject(url, SearchWrapper.class, apiKey, queryString,searchSort);
        } catch (HttpMessageNotReadableException e) {
            logger.error("There might be a problem on the server while searching for documents. Usually this exception " +
                    "is thrown if the json returned has a wrong format. The used query is {}",
                    queryString, e.getMostSpecificCause());
            throw new UnknownClientException("Most probably an incorrect response was received from the nos data api",
                    e.getMostSpecificCause());
        }
        ArrayList<nl.gridshore.nosapi.mapping.Document> documents = searchResults.getSearch().get(0).getDocuments();
        List<nl.gridshore.nosapi.Document> foundDocuments = new ArrayList<nl.gridshore.nosapi.Document>();
        for (nl.gridshore.nosapi.mapping.Document document : documents) {
            foundDocuments.add(new nl.gridshore.nosapi.Document(
                    document.getId(),
                    document.getType(),
                    document.getTitle(),
                    document.getDescription(),
                    document.getCategory(),
                    document.getSubCategory(),
                    document.getPublished(),
                    document.getLastUpdate(),
                    document.getLink(),
                    document.getThumbnail(),
                    document.getScore(),
                    document.getKeywords()
            ));
        }

        ArrayList<nl.gridshore.nosapi.mapping.Keyword> keywords = searchResults.getSearch().get(0).getKeywords();
        List<nl.gridshore.nosapi.Keyword> foundKeywords = new ArrayList<nl.gridshore.nosapi.Keyword>();
        for (nl.gridshore.nosapi.mapping.Keyword keyword : keywords) {
            foundKeywords.add(new nl.gridshore.nosapi.Keyword(keyword.getTag(), keyword.getCount()));
        }

        return new SearchResults(foundDocuments, foundKeywords);
    }

    @Override
    public List<nl.gridshore.nosapi.DayGuide> obtainTVGuide() {
        return obtainGuide("tv");
    }

    @Override
    public List<nl.gridshore.nosapi.DayGuide> obtainRadioGuide() {
        return obtainGuide("radio");
    }

    @Override
    public List<nl.gridshore.nosapi.DayGuide> obtainTVGuide(TVChannel channel, String startDate, String endDate) {
        String channelName = (channel != null)?channel.toString():null;
        return obtainGuide(channelName, startDate, endDate, "tv");
    }

    @Override
    public List<nl.gridshore.nosapi.DayGuide> obtainRadioGuide(RadioChannel channel, String startDate, String endDate) {
        String channelName = (channel != null)?channel.toString():null;
        return obtainGuide(channelName, startDate, endDate, "radio");
    }

    private List<nl.gridshore.nosapi.DayGuide> obtainGuide(String channel, String startDate, String endDate, String type) {
        validateStartEndDate(startDate, endDate);

        StringBuilder builder = new StringBuilder();
        builder.append(serverBaseUrl);
        builder.append("guide/{type}/key/{apikey}/output/json");

        List<Comparable> arguments = new ArrayList<Comparable>();
        arguments.add(type);
        arguments.add(apiKey);

        if (channel != null) {
            builder.append("/channel/{channel}");
            arguments.add(channel);
        }
        if (StringUtils.hasText(startDate) && StringUtils.hasText(endDate)) {
            builder.append("/start/{startdate}");
            arguments.add(startDate);

            builder.append("/end/{enddate}");
            arguments.add(endDate);
        }

        Guide guide = restTemplate.getForObject(builder.toString(), Guide.class, arguments.toArray());

        return mapJSONGuideToOurGuide(guide);
    }

    private void validateStartEndDate(String startDate, String endDate) {
        if (StringUtils.hasText(startDate) || StringUtils.hasText(endDate)) {
            if (!StringUtils.hasText(startDate) || !StringUtils.hasText(endDate)) {
                throw new IllegalArgumentException(
                        "When providing a start or end data, the other one needs to be provided as well.");
            }
            DateTime startTime = formatter.parseDateTime(startDate);
            DateTime endTime = formatter.parseDateTime(endDate);
            if (endTime.isBefore(startTime)) {
                throw new IllegalArgumentException("Start date must be before end date");
            }
        }
    }

    private List<nl.gridshore.nosapi.DayGuide> obtainGuide(String type) {
        String url = serverBaseUrl + "guide/{type}/key/{apikey}/output/json";
        Guide guide = restTemplate.getForObject(url, Guide.class, type, apiKey);
        return mapJSONGuideToOurGuide(guide);
    }

    private List<nl.gridshore.nosapi.DayGuide> mapJSONGuideToOurGuide(Guide guide) {
        ArrayList<DayGuide> dayGuides = guide.getGuide().get(0);
        List<nl.gridshore.nosapi.DayGuide> days = new ArrayList<nl.gridshore.nosapi.DayGuide>();
        for (DayGuide dayGuide : dayGuides) {
            List<Program> programs = new ArrayList<Program>();
            for (GuideItem guideItem :dayGuide.getGuide()) {
                programs.add(new Program(guideItem.getId(),
                        guideItem.getTitle(),
                        guideItem.getType(),
                        guideItem.getGenre(),
                        guideItem.getDescription(),
                        guideItem.getStartTime(),
                        guideItem.getEndTime(),
                        new Channel(guideItem.getChannelCode(),guideItem.getChannelIcon(),guideItem.getChannelName())
                        ));
            }
            days.add(new nl.gridshore.nosapi.DayGuide(dayGuide.getDate(),programs));
        }
        return days;
    }

    private List<nl.gridshore.nosapi.Article> obtainLatestItemPerCategory(String category, String type, Class<? extends LatestItem> clazz) {
        String url = serverBaseUrl + "latest/" + type + "/key/{apikey}/output/json/category/" + category;
        LatestItem latestItem = restTemplate.getForObject(url, clazz, apiKey);

        List<nl.gridshore.nosapi.Article> articles = new ArrayList<nl.gridshore.nosapi.Article>();
        for (nl.gridshore.nosapi.mapping.Article article : latestItem.getItems().get(0)) {
            articles.add(new nl.gridshore.nosapi.Article(
                    article.getId(),
                    article.getType(),
                    article.getTitle(),
                    article.getDescription(),
                    article.getLink(),
                    article.getKeywords(),
                    article.getLastUpdate(),
                    article.getPublished(),
                    article.getThumbnail_m(),
                    article.getThumbnail_s(),
                    article.getThumbnail_xs(),
                    article.getEmbedCode()
            ));
        }
        return articles;
    }
}
