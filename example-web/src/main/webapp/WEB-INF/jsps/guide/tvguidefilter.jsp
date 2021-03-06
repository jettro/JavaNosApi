<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
<h2>TV Guide</h2>
<form method="GET" action="${ctx}/tvguide/filter">
    <label for="channel">TV Channel</label>
    <select id="channel" name="channel">
        <c:forEach items="${tvchannels}" var="item">
            <option value="${item}">${item}</option>
        </c:forEach>
    </select><br/>
    <label for="startdate">Start date</label>
    <input id="startdate" name="startdate"/><br/>
    <label for="enddate">End date</label>
    <input id="enddate" name="enddate"/><br/>
    <input type="submit" value="Search"/>
</form>
<jsp:include page="guide.jsp" />
