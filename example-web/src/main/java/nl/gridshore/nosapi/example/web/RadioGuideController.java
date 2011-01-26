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

import nl.gridshore.nosapi.DataProvider;
import nl.gridshore.nosapi.DayGuide;
import nl.gridshore.nosapi.RadioChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author Jettro Coenradie
 */
@Controller
@RequestMapping("/radioguide")
public class RadioGuideController {
    private final static Logger logger = LoggerFactory.getLogger(RadioGuideController.class);

    private DataProvider dataProvider;

    @Autowired
    public RadioGuideController(DataProvider dataProvider) {
        this.dataProvider = dataProvider;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showGuide(ModelMap modelMap) {
        logger.debug("Received request for radio guide");

        List<DayGuide> dayGuides = dataProvider.obtainRadioGuide();
        modelMap.addAttribute("days", dayGuides);
        return "guide/radioguide";
    }

    @RequestMapping("/filter")
    public String showGuide(@RequestParam(value = "channel", required = false) RadioChannel channel,
                            @RequestParam(value = "startdate", required = false) String startDate,
                            @RequestParam(value = "enddate", required = false) String endDate,
                            ModelMap modelMap) {
        logger.debug("Received request for filtered radio guide");
        if (channel != null || startDate != null || endDate != null) {
            List<DayGuide> dayGuides = dataProvider.obtainRadioGuide(channel, startDate, endDate);
            modelMap.addAttribute("days", dayGuides);
        }
        modelMap.addAttribute("radiochannels", RadioChannel.values());
        return "guide/radioguidefilter";
    }

}
