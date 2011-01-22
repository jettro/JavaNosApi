package nl.gridshore.nosapi.impl;

import nl.gridshore.nosapi.DataProvider;
import nl.gridshore.nosapi.SearchResults;
import nl.gridshore.nosapi.mapping.*;
import org.springframework.util.Assert;
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
    private String serverBaseUrl = "http://open.nos.nl/v1/";

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
        Assert.notNull(queryString);
        String url = serverBaseUrl + "search/query/key/{apikey}/output/json/q/{search}";
        SearchWrapper searchResults = restTemplate.getForObject(url, SearchWrapper.class, apiKey, queryString);
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
                    article.getThumbnail(),
                    article.getEmbedCode()
            ));
        }
        return articles;
    }
}
