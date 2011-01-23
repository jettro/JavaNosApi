<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%--
  ~ Copyright (c) 2010. Gridshore
  ~ Licensed under the Apache License, Version 2.0 (the "License");
  ~ you may not use this file except in compliance with the License.
  ~ You may obtain a copy of the License at
  ~
  ~     http://www.apache.org/licenses/LICENSE-2.0
  ~
  ~ Unless required by applicable law or agreed to in writing, software
  ~ distributed under the License is distributed on an "AS IS" BASIS,
  ~ WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  ~ See the License for the specific language governing permissions and
  ~ limitations under the License.
  --%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
    <title><decorator:title/></title>
    <link rel="stylesheet" href="${ctx}/style/main.css"/>
</head>
<body>
<div id="navigation">
    <div class="heading">
        <h2>News</h2>
        <a href="${ctx}/news/articles">Articles</a><br/>
        <a href="${ctx}/news/videos">Videos</a><br/>
        <a href="${ctx}/news/audios">Audio</a><br/>
    </div>
    <div class="heading">
        <h2>Sport news</h2>
        <a href="${ctx}/news/sport/articles">Articles</a><br/>
        <a href="${ctx}/news/sport/videos">Videos</a><br/>
        <a href="${ctx}/news/sport/audios">Audio</a><br/>
    </div>
    <br clear="all"/>
</div>

<div id="main">
    <decorator:body/>
</div>

</body>
</html>
