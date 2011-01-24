package nl.gridshore.nosapi.example.web;

import nl.gridshore.nosapi.UnknownClientException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Jettro Coenradie
 */
@Controller
public class ExceptionController {

    @ExceptionHandler(UnknownClientException.class)
    public String handleUnknownClientException(UnknownClientException exception, HttpServletRequest request) {

        return "error";
    }
}
