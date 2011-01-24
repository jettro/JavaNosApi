<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

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

<form method="GET" action="${ctx}/search">
    <label for="q">Search for</label>
    <input id="q" name="q"/><br/>
    <input type="submit" value="Search"/>
</form>

<c:if test="${not empty results}">
    <h3>Keywords</h3>
    <table class="keyword">
    <c:forEach var="keyword" items="${results.keywords}">
        <tr>
            <td class="tag">${keyword.tag}</td><td class="count">${keyword.count}</td>
        </tr>
    </c:forEach>
    </table>
</c:if>
<c:if test="${not empty results}">
    <h3>Results</h3>
    <c:forEach var="document" items="${results.documents}">
        <div class="article">
            <c:if test="${not empty document.thumbnail}">
                <img src=" ${document.thumbnail}" alt="Thumbnail"/>
            </c:if>
            <div class="title"><a href="${document.link}">${document.title}</a>
                (${document.type} - ${document.category} - ${document.subCategory})
            </div>
            <div class="description">${document.description}</div>
            <div class="footer">
                <span class="published">${document.published}</span>
            <span class="tags">
                <c:forEach items="${document.keywords}" var="keyword">
                    "${keyword}"
                </c:forEach>
            </span>
            </div>
        </div>
    </c:forEach>
</c:if>
