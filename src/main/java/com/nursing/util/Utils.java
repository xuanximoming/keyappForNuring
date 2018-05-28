package com.nursing.util;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Jack on 2015/12/12.
 */
public class Utils {

    public static boolean shouldReturnHtml(HttpServletRequest request) {
        String accept = request.getHeader("Accept");
        return accept != null && accept.contains("text/html");
    }

}
