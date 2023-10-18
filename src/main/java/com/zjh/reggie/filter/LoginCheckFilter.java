package com.zjh.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zjh.reggie.utils.Result;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/****************************
 * @project reggie
 * @package com.zjh.reggie.filter
 * @className LoginCheckFilter
 * @author Zjiah
 * @date 2023/10/18 13:36
 * @Description:   *
 ****************************/
@WebFilter(urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        log.info("拦截到请求{}", request.getRequestURI());
        String requestURI = request.getRequestURI();
        if (requestURI.contains("login") || requestURI.contains("logout")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if (!(requestURI.contains("backend") || requestURI.contains("front"))) {


            if (request.getSession().getAttribute("employee") != null) {
                log.info("已经登录");
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            if (request.getSession().getAttribute("employee") == null) {
                log.info("未登录");
                response.getWriter().write(JSON.toJSONString(Result.error("NOTLOGIN")));
                return;
            }
        } else {
            log.info("放行{}", request.getRequestURI());
            filterChain.doFilter(servletRequest, servletResponse);
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
