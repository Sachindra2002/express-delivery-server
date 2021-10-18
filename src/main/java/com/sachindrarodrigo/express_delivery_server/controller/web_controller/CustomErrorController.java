package com.sachindrarodrigo.express_delivery_server.controller.web_controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    /* Custom error controller to handle common Http errors */
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        String url = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        System.out.println(url);

        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());

            System.out.println(statusCode);

            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "errors/error_404.jsp";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "errors/error_500.jsp";
            }
            else if(statusCode == HttpStatus.METHOD_NOT_ALLOWED.value() || statusCode == HttpStatus.FORBIDDEN.value()) {
                return "redirect:/";
            }
        }
        return "error";
    }

}
