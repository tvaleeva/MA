package ru.amfitel.task.util;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Bublik on 27.03.2016.
 */
public class NoCacheFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        filterChain.doFilter(servletRequest, servletResponse);
        ((HttpServletResponse) servletResponse).setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    }

    public void destroy() {

    }
}