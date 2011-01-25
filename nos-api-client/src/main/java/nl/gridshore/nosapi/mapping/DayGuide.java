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

package nl.gridshore.nosapi.mapping;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.joda.time.LocalDate;

import java.util.ArrayList;

/**
 * @author Jettro Coenradie
 */
public class DayGuide {
    private LocalDate date;
    private ArrayList<GuideItem> guide = new ArrayList<GuideItem>();

    public LocalDate getDate() {
        return date;
    }

    @JsonDeserialize(using = JsonDateDeserializer.class)
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public ArrayList<GuideItem> getGuide() {
        return guide;
    }

    public void setGuide(ArrayList<GuideItem> guide) {
        this.guide = guide;
    }
}
