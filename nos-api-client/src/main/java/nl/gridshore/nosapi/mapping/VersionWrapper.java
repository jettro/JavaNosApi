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

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;

/**
 * @author Jettro Coenradie
 */
public class VersionWrapper {
    @JsonProperty("version")
    private ArrayList<Version> versions = new ArrayList<Version>();

    public ArrayList<Version> getVersions() {
        return versions;
    }

    public void setVersions(ArrayList<Version> versions) {
        this.versions = versions;
    }
}
