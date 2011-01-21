package nl.gridshore.nosapi;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

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
        assertEquals(10,articles.size());
    }

    @Test
    public void testObtainLatestSportNewsArticles() throws Exception {
        List<Article> articles = dataProvider.obtainLatestSportsNewsArticles();
        assertEquals(10,articles.size());
    }

    @Test
    public void testObtainLatestNewsVideo() throws Exception {
        List<Article> articles = dataProvider.obtainLatestNewsVideos();
        assertEquals(10,articles.size());
    }

    @Test
    public void testObtainLatestSportNewsVideo() throws Exception {
        List<Article> articles = dataProvider.obtainLatestSportsNewsVideos();
        assertEquals(10,articles.size());
    }

    @Test
    public void testObtainLatestNewsAudio() throws Exception {
        List<Article> articles = dataProvider.obtainLatestNewsAudio();
        assertEquals(10,articles.size());
    }

    @Test
    public void testObtainLatestSportNewsAudio() throws Exception {
        List<Article> articles = dataProvider.obtainLatestSportsNewsAudio();
        assertEquals(10,articles.size());
    }

    @Test
    public void testSearchForDocuments() throws Exception {
        SearchResults voetbal = dataProvider.searchForDocuments("voetbal");
        assertEquals(25,voetbal.getDocuments().size());
    }
}
