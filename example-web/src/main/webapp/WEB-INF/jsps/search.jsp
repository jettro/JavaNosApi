<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<form method="GET" action="${ctx}/search">
    <label for="q">Search for</label>
    <input id="q" name="q" /><br/>
    <input type="submit" value="Search"/>
</form>

<c:if test="${not empty results}">
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
