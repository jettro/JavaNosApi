<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<c:forEach var="article" items="${articles}" >
    <c:out value="${article.description}"/><br/>
</c:forEach>