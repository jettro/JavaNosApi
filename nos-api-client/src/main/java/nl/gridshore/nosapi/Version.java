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
 * <p>Has information about the version of the api that is being used</p>
 *
 * @author Jettro Coenradie
 */
public class Version {
    private String version;
    private String build;

    public Version(String build, String version) {
        this.build = build;
        this.version = version;
    }

    public String getBuild() {
        return build;
    }

    public String getVersion() {
        return version;
    }
}
