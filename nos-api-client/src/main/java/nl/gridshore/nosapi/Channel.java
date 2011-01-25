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

/**
 * @author Jettro Coenradie
 */
public class Channel {
    private String channelIcon;
    private String channelCode;
    private String channelName;

    public Channel(String channelCode, String channelIcon, String channelName) {
        this.channelCode = channelCode;
        this.channelIcon = channelIcon;
        this.channelName = channelName;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public String getChannelIcon() {
        return channelIcon;
    }

    public String getChannelName() {
        return channelName;
    }
}
