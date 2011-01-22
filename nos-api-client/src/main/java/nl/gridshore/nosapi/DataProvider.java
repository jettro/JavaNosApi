package nl.gridshore.nosapi;

import java.util.List;

/**
 * <p>Provider for the NOS api, returns our own objects to be able to handle different api versions without to much
 * problems for clients of this provider.</p>
 * <p>All methods throw ClientExceptions.</p>
 *
 * @author Jettro Coenradie
 */
public interface DataProvider {

    /**
     * Returns information about the version of the NOS api used. Might in the future be used for returned data
     * about the version of our own api as well.
     * {@link "http://open.nos.nl/documentatie/v1/algemeen/versie"}
     *
     * @return Version object that contains the version data
     */
    Version obtainVersion();

    /**
     * Returns a List of articles that are obtained using the latest news api call of the nos api
     * {@link "http://open.nos.nl/documentatie/v1/overzicht/laatste10artikelen/"}
     *
     * @return List of news Articles
     */
    List<Article> obtainLatestNewsArticles();

    /**
     * Returns a List of sports articles that are obtained using the latest sports news api call of the nos api
     * {@link "http://open.nos.nl/documentatie/v1/overzicht/laatste10artikelen/"}
     *
     * @return List of news Articles
     */
    List<Article> obtainLatestSportsNewsArticles();

    /**
     * Returns a List of videos that are obtained using the latest news api call of the nos api
     * {@link "http://open.nos.nl/documentatie/v1/overzicht/laatste10video/"}
     *
     * @return List of news Articles
     */
    List<Article> obtainLatestNewsVideos();

    /**
     * Returns a List of sports videos that are obtained using the latest sports news api call of the nos api
     * {@link "http://open.nos.nl/documentatie/v1/overzicht/laatste10video/"}
     *
     * @return List of news Articles
     */
    List<Article> obtainLatestSportsNewsVideos();

    /**
     * Returns a List of audio that are obtained using the latest news api call of the nos api
     * {@link "http://open.nos.nl/documentatie/v1/overzicht/laatste10audio/"}
     *
     * @return List of news Articles
     */
    List<Article> obtainLatestNewsAudio();

    /**
     * Returns a List of sports audio that are obtained using the latest sports news api call of the nos api
     * {@link "http://open.nos.nl/documentatie/v1/overzicht/laatste10audio/"}
     *
     * @return List of news Articles
     */
    List<Article> obtainLatestSportsNewsAudio();

    /**
     * The search functionality is limited to one keyword or two keywords combined with AND or OR
     * {@link "http://open.nos.nl/documentatie/v1/zoeken/query"}
     *
     * @param queryString String containing the queryString
     * @return SearchResults objects found with the query string containing the documents and the keywords
     */
    SearchResults searchForDocuments(String queryString);
}
