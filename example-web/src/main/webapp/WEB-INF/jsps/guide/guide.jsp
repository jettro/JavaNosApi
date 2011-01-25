<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>

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
<c:forEach var="day" items="${days}">
    <h3>${day.day}</h3>
    <table>
        <c:forEach var="program" items="${day.programs}">
            <tr>
                <td><img src="${program.channel.channelIcon}" alt="Channel Icon"/></td>
                <td><joda:format value="${program.startTime}" pattern="HH:mm"/> -
                    <joda:format value="${program.endTime}" pattern="HH:mm" /></td>
                <td>${program.title}</td>
                <td>${program.genre}</td>
            </tr>
        </c:forEach>
    </table>
</c:forEach>