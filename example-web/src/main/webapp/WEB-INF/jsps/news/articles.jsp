<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

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