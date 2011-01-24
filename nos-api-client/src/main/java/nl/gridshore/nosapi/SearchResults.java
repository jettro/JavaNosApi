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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jettro Coenradie
 */
public class SearchResults {
    List<Document> documents = new ArrayList<Document>();
    List<Keyword> keywords = new ArrayList<Keyword>();

    public SearchResults(List<Document> documents, List<Keyword> keywords) {
        this.documents = Collections.unmodifiableList(documents);
        this.keywords = Collections.unmodifiableList(keywords);
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }
}
