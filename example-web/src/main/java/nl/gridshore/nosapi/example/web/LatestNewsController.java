package nl.gridshore.nosapi.example.web;

import nl.gridshore.nosapi.Article;
import nl.gridshore.nosapi.DataProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author Jettro Coenradie
 */
@Controller
@RequestMapping("/news")
public class LatestNewsController {
    private final static Logger logger = LoggerFactory.getLogger(LatestNewsController.class);

    private DataProvider dataProvider;

    @Autowired
    public LatestNewsController(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @RequestMapping("/articles")
    public String showLatestNews(ModelMap modelMap) {
        logger.debug("Retrieved request for the latest news articles.");

        List<Article> articles = dataProvider.obtainLatestNewsArticles();
        modelMap.addAttribute("articles",articles);
        return "news/articles";
    }
}
