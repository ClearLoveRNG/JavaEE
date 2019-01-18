package com.demo.config;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: JavaEE
 * Author: jianghaotian
 * Create Time:2019/1/18 10:38
 */
@WebFilter(filterName = "Filter",urlPatterns = "/*")
public class Filter implements javax.servlet.Filter {
    @Override
    public void destroy() {
        System.out.println("destroy");
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        System.out.println(request.getRequestURI()+"__doFilter");
        chain.doFilter(req, resp);
    }
    @Override
    public void init(FilterConfig config) throws ServletException {
        System.out.println("init");
    }

}
