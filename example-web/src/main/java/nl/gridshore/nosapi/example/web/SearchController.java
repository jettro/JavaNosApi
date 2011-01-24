package nl.gridshore.nosapi.example.web;

import nl.gridshore.nosapi.DataProvider;
import nl.gridshore.nosapi.SearchResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Jettro Coenradie
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    private DataProvider dataProvider;

    @Autowired
    public SearchController(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String search(@RequestParam(value = "q",required = false) String queryString, ModelMap modelMap) {
        SearchResults searchResults = null;
        if (StringUtils.hasText(queryString)) {
            searchResults = dataProvider.searchForDocuments(queryString);
        }

        modelMap.addAttribute("results", searchResults);
        return "search";
    }
}
