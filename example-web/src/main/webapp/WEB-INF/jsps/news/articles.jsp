<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<%--
  ~ Copyright (c) 2011. Gridshore
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~ http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<c:forEach var="article" items="${articles}">
    <div class="article">
            <c:if test="${not empty article.thumbnailS}">
                <img src=" ${article.thumbnailS}" alt="Thumbnail"/>
            </c:if>
            <div class="title"><a href="${article.link}">${article.title}</a></div>
        <div class="description">${article.description}</div>
        <div class="footer">
            <span class="published">${article.published}</span>
            <span class="tags">
                <c:forEach items="${article.keywords}" var="keyword">
                    "${keyword}"
                </c:forEach>
            </span>
        </div>
    </div>
</c:forEach>