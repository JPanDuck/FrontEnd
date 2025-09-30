package com.example.realrealfinal.controller.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/error")
public class ErrorLoggerController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ErrorLoggerController.class);

    public String handleError(HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if(status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if(statusCode == HttpStatus.UNAUTHORIZED.value()) {         // 401
                logger.warn("Authentication Error: Status 401, Access Denied");
            } else if(statusCode == HttpStatus.FORBIDDEN.value()) {     // 403
                logger.warn("Authentication Error: Status 403 Forbidden");
            } else if (statusCode == HttpStatus.NOT_FOUND.value()) {    // 404
                logger.warn("Authentication Error: Status 404 Not Found");
            } else if (statusCode >= 500) {                             // 5xx
                logger.error("Server Error: Status " + statusCode);
            }
        }
        return "redirect:/log/log-monitor";
    }
}
