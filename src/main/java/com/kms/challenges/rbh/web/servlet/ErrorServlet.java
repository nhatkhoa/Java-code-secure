/*
 * Copyright (c) 2015 Kms-technology.com
 */

package com.kms.challenges.rbh.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by khoahoang on 9/1/2015.
 */
@WebServlet(name = "ErrorServlet", urlPatterns = "/error")
public class ErrorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processError(request, response);
    }

    private void processError(HttpServletRequest request,
                              HttpServletResponse response) throws IOException {

        Throwable throwable = (Throwable) request
                .getAttribute("javax.servlet.error.exception");
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        String servletName = (String) request
                .getAttribute("javax.servlet.error.servlet_name");
        if (servletName == null) {
            servletName = "Unknown";
        }
        String requestUri = (String) request
                .getAttribute("javax.servlet.error.request_uri");
        if (requestUri == null) {
            requestUri = "Unknown";
        }

        response.setContentType("text/html");

        try (PrintWriter out = response.getWriter()) {
            out.write("<html><head><title>Error Details</title></head><body>");
            if (statusCode != 500) {
                out.write("<h3>Your request was broken. Please contact to admin!</h3>");
                out.write("<strong>Status Code</strong>:" + statusCode + "<br>");
                out.write("<strong>Requested URI</strong>:" + requestUri);
            } else {
                out.write("<h3>Your request was broken. Please contact to admin!</h3>");
                out.write("<li>Exception Name:" + throwable.getClass().getName() + "</li>");
                out.write("<li>Requested URI:" + requestUri + "</li>");
                out.write("<li>Exception Message:" + throwable.getMessage() + "</li>");
                out.write("</ul>");
            }
        }

    }
}
