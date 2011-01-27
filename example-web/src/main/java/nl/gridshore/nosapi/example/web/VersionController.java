package nl.gridshore.nosapi.example.web;

import nl.gridshore.nosapi.DataProvider;
import nl.gridshore.nosapi.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Jettro Coenradie
 */
@Controller
@RequestMapping("/version")
public class VersionController {
    private DataProvider dataProvider;

    @Autowired
    public VersionController(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showVersion(ModelMap modelMap) {
        Version version = dataProvider.obtainVersion();

        modelMap.addAttribute("version", version);
        return "version";
    }
}
