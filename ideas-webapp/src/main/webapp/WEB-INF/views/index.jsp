<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page session="false" %>
<html>

<head>
    <title>Ideas API Documentation</title>
    <link href='http://fonts.googleapis.com/css?family=Droid+Sans:400,700' rel='stylesheet' type='text/css'/>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />

    <link href='<c:url value="/resources/css/reset.css" />' media='screen'
          rel='stylesheet' type='text/css'/>
    <link href='<c:url value='/resources/css/screen.css' />' media='screen' rel='stylesheet'
          type='text/css'/>
    <link href='<c:url value='/resources/css/reset.css' />' media='print'
          rel='stylesheet' type='text/css'/>
    <link href='<c:url value='/resources/css/screen.css' />' media='print' rel='stylesheet'
          type='text/css'/>
    <script type="text/javascript" src="<c:url value='/resources/lib/shred.bundle.js'/>"></script>
    <script src='<c:url value='/resources/lib/jquery-1.8.0.min.js' />' type='text/javascript'></script>
    <script src='<c:url value='/resources/lib/jquery.slideto.min.js' />'
            type='text/javascript'></script>
    <script src='<c:url value='/resources/lib/jquery.wiggle.min.js' />'
            type='text/javascript'></script>
    <script src='<c:url value='/resources/lib/jquery.ba-bbq.min.js' />'
            type='text/javascript'></script>
    <script src='<c:url value='/resources/lib/handlebars-1.0.0.js' />'
            type='text/javascript'></script>
    <script src='<c:url value='/resources/lib/underscore-min.js' />' type='text/javascript'></script>
    <script src='<c:url value='/resources/lib/backbone-min.js' />' type='text/javascript'></script>
    <script src='<c:url value='/resources/lib/swagger.js' />' type='text/javascript'></script>
    <script src='<c:url value='/resources/lib/swagger-client.js' />' type='text/javascript'></script>
    <script src='<c:url value='/resources/swagger-ui.js' />' type='text/javascript'></script>
    <script src='<c:url value='/resources/lib/highlight.7.3.pack.js' />'
            type='text/javascript'></script>
    <script src='<c:url value='/resources/lib/swagger-oauth.js' />' type='text/javascript'></script>

    <script type="text/javascript" th:inline="javascript">
        $(document).ready(function () {

            displaySwaggerDocuments();

            function displaySwaggerDocuments() {
                var resourceUrl = '<c:url value="/api-docs?group=v1"/>';
                window.swaggerUi = new SwaggerUi({
                    url: resourceUrl,
                    dom_id: "swagger-ui-container",
                    supportedSubmitMethods: ['get', 'post', 'put', 'delete'],
                    onComplete: function (swaggerApi, swaggerUi) {
                        if (console) {
                            console.log("Loaded SwaggerUI")
                            console.log(swaggerApi);
                            console.log(swaggerUi);
                        }
                        $('pre code').each(function (i, e) {
                            hljs.highlightBlock(e)
                        });
                        if(typeof initOAuth == "function") {
                            /*
                             initOAuth({
                             clientId: "your-client-id",
                             realm: "your-realms",
                             appName: "your-app-name"
                             });
                             */
                        }
                    },
                    onFailure: function (data) {
                        if (console) {
                            console.log("Unable to Load SwaggerUI");
                            console.log(data);
                        }
                    },
                    docExpansion: "none",
                    sorter : "alpha"
                });

                $('#input_apiKey').change(function() {
                    var key = $('#input_apiKey')[0].value;
                    log("key: " + key);
                    if(key && key.trim() != "") {
                        log("added key " + key);
                        window.authorizations.add("key", new ApiKeyAuthorization("api_key", key, "query"));
                    }
                });
                window.swaggerUi.load();
            }
        });
    </script>
</head>

<body class="swagger-section">
<div id='header'>
    <div class="swagger-ui-wrap">
        <a id="logo" href="https://github.com/darogina/ideas-web">Ideas API</a>
        <form id='api_selector'>
            <!--<div class='input icon-btn'>-->
            <!--<img id="show-pet-store-icon" src="images/pet_store_api.png" title="Show Swagger Petstore Example Apis">-->
            <!--</div>-->
            <!--<div class='input icon-btn'>-->
            <!--<img id="show-wordnik-dev-icon" src="images/wordnik_api.png" title="Show Wordnik Developer Apis">-->
            <!--</div>-->
            <div class='input'><input placeholder="http://example.com/api-docs?group={version}" id="input_baseUrl" name="baseUrl" type="text"/></div>
            <!--<div class='input'><input placeholder="api_key" id="input_apiKey" name="apiKey" type="text"/></div>-->
            <div class='input'><a id="explore" href="#">Explore</a></div>
        </form>
    </div>
</div>

<div id="message-bar" class="swagger-ui-wrap">&nbsp;</div>
<div id="swagger-ui-container" class="swagger-ui-wrap"></div>
</body>

</html>