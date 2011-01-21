package nl.gridshore.nosapi.impl;

import nl.gridshore.nosapi.ClientException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
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
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        HttpStatus statusCode = response.getStatusCode();
        return (statusCode.series() == HttpStatus.Series.CLIENT_ERROR ||
                statusCode.series() == HttpStatus.Series.SERVER_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
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
        ObjectMapper mapper = new ObjectMapper();
        String jsonConstentString = (charset != null)?new String(body, charset):new String(body);
        JsonNode rootNode = mapper.readValue(jsonConstentString, JsonNode.class);
        String type = rootNode.getFieldNames().next();
        JsonNode errorNode = rootNode.getElements().next().get("error");
        int code = errorNode.get("code").getIntValue();
        String message = errorNode.get("message").getTextValue();
        throw new ClientException(code, type, message);
    }
}
