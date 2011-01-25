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

import nl.gridshore.nosapi.impl.DataProviderImpl;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author Jettro Coenradie
 */
public class DataProviderImplIntegrationTest {
    private static String key;
    private DataProvider dataProvider;

    @BeforeClass
    public static void loadProperties() throws IOException {
        Properties props = new Properties();
        InputStream inputStream = DataProviderImplIntegrationTest.class.getClassLoader()
                .getResourceAsStream("key.properties");
        props.load(inputStream);
        key = props.getProperty("key");
    }

    @Before
    public void createInstanceProperties() {
        dataProvider = new DataProviderImpl(key);
    }

    @Test
    public void testObtainVersion() throws Exception {
        Version version = dataProvider.obtainVersion();
        assertEquals("v1", version.getVersion());
        assertEquals("0.0.1", version.getBuild());
    }

    @Test
    public void testObtainLatestNewsArticles() throws Exception {
        List<Article> articles = dataProvider.obtainLatestNewsArticles();
        assertEquals(10, articles.size());
    }

    @Test
    public void testObtainLatestSportNewsArticles() throws Exception {
        List<Article> articles = dataProvider.obtainLatestSportsNewsArticles();
        assertEquals(10, articles.size());
    }

    @Test
    public void testObtainLatestNewsVideo() throws Exception {
        List<Article> articles = dataProvider.obtainLatestNewsVideos();
        assertEquals(10, articles.size());
    }

    @Test
    public void testObtainLatestSportNewsVideo() throws Exception {
        List<Article> articles = dataProvider.obtainLatestSportsNewsVideos();
        assertEquals(10, articles.size());
    }

    @Test
    public void testObtainLatestNewsAudio() throws Exception {
        List<Article> articles = dataProvider.obtainLatestNewsAudio();
        assertEquals(10, articles.size());
    }

    @Test
    public void testObtainLatestSportNewsAudio() throws Exception {
        List<Article> articles = dataProvider.obtainLatestSportsNewsAudio();
        assertEquals(10, articles.size());
    }

    @Test
    public void testSearchForDocuments() throws Exception {
        SearchResults voetbal = dataProvider.searchForDocuments("voetbal");
        assertEquals(25, voetbal.getDocuments().size());
    }

    @Test
    public void testTvGuide() throws Exception {
        List<DayGuide> dayGuides = dataProvider.obtainTVGuide();
        assertNotNull(dayGuides);
        assertEquals("We should have 3 days; yesterday, today and tomorrow.", 3, dayGuides.size());
        LocalDate now = new LocalDate();
        assertEquals(now.dayOfMonth(), dayGuides.get(1).getDay().dayOfMonth());
    }

    @Test
    public void testRadioGuide() throws Exception {
        List<DayGuide> dayGuides = dataProvider.obtainRadioGuide();
        assertNotNull(dayGuides);
        assertEquals("We should have 3 days; yesterday, today and tomorrow.", 3, dayGuides.size());
        LocalDate now = new LocalDate();
        assertEquals(now.dayOfMonth(), dayGuides.get(1).getDay().dayOfMonth());
    }
}
