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
 * <p>Exception used to indicate something unexpected and unanticipated has happened.</p>
 * @author Jettro Coenradie
 */
public class UnknownClientException extends RuntimeException {
    public UnknownClientException(String s) {
        super(s);
    }

    public UnknownClientException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
