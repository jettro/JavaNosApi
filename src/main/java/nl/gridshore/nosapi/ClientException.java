package nl.gridshore.nosapi;

import org.springframework.web.client.HttpClientErrorException;

/**
 * Contains data related to a Client error
 *
 * @author Jettro Coenradie
 */
public class ClientException extends RuntimeException {
    private int code;
    private String type;
    private String message;

    public ClientException(int code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }
}
