package com.rhea.common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author xubulong
 * @version V1.0
 */
public class SessionUtil {

    public static <T> T getAttribute(final String key, HttpServletRequest request) {
        return (T) request.getSession().getAttribute(key);
    }

    public static void removeAttribute(final String key, HttpServletRequest request) {
        request.getSession().removeAttribute(key);
    }

    public static void setAttribute(final String key, final Object value, HttpServletRequest request) {
        request.getSession().setAttribute(key, value);
    }

    public static void removeAttributes(HttpSession session) {
        Enumeration<String> atts = session.getAttributeNames();
        while (atts.hasMoreElements()) {
            String name = atts.nextElement();
            session.removeAttribute(name);
            System.err.println(name);
        }
    }
}
