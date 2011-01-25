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

/**
 * @author Jettro Coenradie
 */
public class Program {
    private String id;
    private String type;
    private Channel channel;
    private DateTime startTime;
    private DateTime endTime;
    private String genre;
    private String title;
    private String description;

    public Program(String id,
                   String title,
                   String type,
                   String genre,
                   String description,
                   DateTime startTime,
                   DateTime endTime,
                   Channel channel) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.genre = genre;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.channel = channel;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getDescription() {
        return description;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public String getGenre() {
        return genre;
    }

    public String getId() {
        return id;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }
}
