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
    public String showLatestNewsArticles(ModelMap modelMap) {
        logger.debug("Retrieved request for the latest news articles.");

        List<Article> articles = dataProvider.obtainLatestNewsArticles();
        modelMap.addAttribute("articles",articles);
        return "news/articles";
    }

    @RequestMapping("/videos")
    public String showLatestNewsVideos(ModelMap modelMap) {
        logger.debug("Retrieved request for the latest news videos.");

        List<Article> videos = dataProvider.obtainLatestNewsVideos();
        modelMap.addAttribute("articles", videos);
        return "news/articles";
    }

    @RequestMapping("/audios")
    public String showLatestNewsAudios(ModelMap modelMap) {
        logger.debug("Retrieved request for the latest news audio.");

        List<Article> audios = dataProvider.obtainLatestNewsAudio();
        modelMap.addAttribute("articles", audios);
        return "news/articles";
    }

    @RequestMapping("/sport/articles")
    public String showLatestSportNewsArticles(ModelMap modelMap) {
        logger.debug("Retrieved request for the latest sport news articles.");

        List<Article> articles = dataProvider.obtainLatestSportsNewsArticles();
        modelMap.addAttribute("articles",articles);
        return "news/articles";
    }

    @RequestMapping("/sport/videos")
    public String showLatestSportNewsVideos(ModelMap modelMap) {
        logger.debug("Retrieved request for the latest sport news videos.");

        List<Article> videos = dataProvider.obtainLatestSportsNewsVideos();
        modelMap.addAttribute("articles", videos);
        return "news/articles";
    }

    @RequestMapping("/sport/audios")
    public String showLatestSportNewsAudios(ModelMap modelMap) {
        logger.debug("Retrieved request for the latest sport news audio.");

        List<Article> audios = dataProvider.obtainLatestSportsNewsAudio();
        modelMap.addAttribute("articles", audios);
        return "news/articles";
    }
}
