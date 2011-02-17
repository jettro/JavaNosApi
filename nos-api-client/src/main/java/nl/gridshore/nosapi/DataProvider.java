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
     * {@link "http://open.nos.nl/documentatie/v2/algemeen/versie"}
     *
     * @return Version object that contains the version data
     */
    Version obtainVersion();

    /**
     * Returns a List of articles that are obtained using the latest news api call of the nos api
     * {@link "http://open.nos.nl/documentatie/v2/overzicht/laatste10artikelen/"}
     *
     * @return List of news Articles
     */
    List<Article> obtainLatestNewsArticles();

    /**
     * Returns a List of sports articles that are obtained using the latest sports news api call of the nos api
     * {@link "http://open.nos.nl/documentatie/v2/overzicht/laatste10artikelen/"}
     *
     * @return List of news Articles
     */
    List<Article> obtainLatestSportsNewsArticles();

    /**
     * Returns a List of videos that are obtained using the latest news api call of the nos api
     * {@link "http://open.nos.nl/documentatie/v2/overzicht/laatste10video/"}
     *
     * @return List of news Articles
     */
    List<Article> obtainLatestNewsVideos();

    /**
     * Returns a List of sports videos that are obtained using the latest sports news api call of the nos api
     * {@link "http://open.nos.nl/documentatie/v2/overzicht/laatste10video/"}
     *
     * @return List of news Articles
     */
    List<Article> obtainLatestSportsNewsVideos();

    /**
     * Returns a List of audio that are obtained using the latest news api call of the nos api
     * {@link "http://open.nos.nl/documentatie/v2/overzicht/laatste10audio/"}
     *
     * @return List of news Articles
     */
    List<Article> obtainLatestNewsAudio();

    /**
     * Returns a List of sports audio that are obtained using the latest sports news api call of the nos api
     * {@link "http://open.nos.nl/documentatie/v2/overzicht/laatste10audio/"}
     *
     * @return List of news Articles
     */
    List<Article> obtainLatestSportsNewsAudio();

    /**
     * The search functionality is limited to one keyword or two keywords combined with AND or OR
     * {@link "http://open.nos.nl/documentatie/v2/zoeken/query"}
     *
     * @param queryString String containing the queryString
     * @return SearchResults objects found with the query string containing the documents and the keywords
     */
    SearchResults searchForDocuments(String queryString);


    /**
     * The search functionality is limited to one keyword or two keywords combined with AND or OR. You can also
     * provide a sort order. At the moment two options are available using the enumeration SearchSort. You can sort
     * by Score (which is the default) and by date.
     * {@link "http://open.nos.nl/documentatie/v2/zoeken/query"}
     *
     * @param queryString String containing the queryString
     * @param sort Enumeration of values that can be used for the sort. The default is sort by Score
     * @return SearchResults objects found with the query string containing the documents and the keywords
     */
    SearchResults searchForDocuments(String queryString, SearchSort sort);


    /**
     * Returns the tv guide for yesterday, today and tomorrow as specified for the default in the NOS api.
     * {@link "http://open.nos.nl/documentatie/v2/gids/tv/"}
     *
     * @return Guide containing the tv programs for the mentioned three days
     */
    List<DayGuide> obtainTVGuide();

    /**
     * Returns the radio guide for yesterday, today and tomorrow as specified for the default in the NOS api.
     * {@link "http://open.nos.nl/documentatie/v2/gids/radio/"}
     *
     * @return Guide containing the tv programs for the mentioned three days
     */
    List<DayGuide> obtainRadioGuide();

    /**
     * Returns the list of days with a maximum of 14 with all tv shows on those days for specified channel. The channel
     * and the start/end dat are not mandatory to provide. If providing dates, you always have to provide both dates.
     * The end date must be after the start date. In case of exceptions, IllegalArgumentException objects are thrown.
     *
     * Date's must have format yyyy-mm-dd.
     *
     * @param channel TVChannel to filter on
     * @param startDate String containing a date in format yyyy-mm-dd as a startDate
     * @param endDate String containing a date in format yyyy-mm-dd as a endDate
     * @return List of DayGuide objects that each contain programs
     */
    List<DayGuide> obtainTVGuide(TVChannel channel, String startDate, String endDate);

    /**
     * Returns the list of days with a maximum of 14 with all radio shows on those days for specified channel. The channel
     * and the start/end dat are not mandatory to provide. If providing dates, you always have to provide both dates.
     * The end date must be after the start date. In case of exceptions, IllegalArgumentException objects are thrown.
     *
     * Date's must have format yyyy-mm-dd.
     *
     * @param channel RadioChannel to filter on
     * @param startDate String containing a date in format yyyy-mm-dd as a startDate
     * @param endDate String containing a date in format yyyy-mm-dd as a endDate
     * @return List of DayGuide objects that each contain programs
     */
    List<DayGuide> obtainRadioGuide(RadioChannel channel, String startDate, String endDate);

}
