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

package nl.gridshore.nosapi.impl;

import nl.gridshore.nosapi.ClientException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestClientException;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * <p>ResponseErrorHandler that knows the error codes that NOS API uses. We obtain the json values from these
 * exceptions and in any other situation throw the default spring Client and server applications.</p>
 *
 * @author Jettro Coenradie
 */
public class NosApiResponseErrorHandler implements ResponseErrorHandler {
    private final static Logger logger = LoggerFactory.getLogger(NosApiResponseErrorHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = response.getStatusCode();
        return (statusCode.series() == HttpStatus.Series.CLIENT_ERROR ||
                statusCode.series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        logger.debug("Handle error '{}' received from the NOS server.", response.getStatusCode().name());
        HttpStatus statusCode = response.getStatusCode();
        MediaType contentType = response.getHeaders().getContentType();
        Charset charset = contentType != null ? contentType.getCharSet() : null;
        byte[] body = FileCopyUtils.copyToByteArray(response.getBody());

        switch (statusCode) {
            case BAD_REQUEST:
            case UNAUTHORIZED:
            case FORBIDDEN:
                throwClientException(charset, body);
            default:
                // do nothing, let the series resolving do it' work
        }

        switch (statusCode.series()) {
            case CLIENT_ERROR:
                throw new HttpClientErrorException(statusCode, response.getStatusText(), body, charset);
            case SERVER_ERROR:
                throw new HttpServerErrorException(statusCode, response.getStatusText(), body, charset);
            default:
                throw new RestClientException("Unknown status code [" + statusCode + "]");
        }
    }

    private void throwClientException(Charset charset, byte[] body) throws IOException {
        String jsonContentString = (charset != null) ? new String(body, charset) : new String(body);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readValue(jsonContentString, JsonNode.class);
        String type = rootNode.getFieldNames().next();

        JsonNode errorNode = rootNode.get("error");
        int code = errorNode.get("code").getIntValue();
        String message = errorNode.get("message").getTextValue();
        throw new ClientException(code, type, message);
    }
}
