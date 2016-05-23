
package cn.dms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.dms.dto.UserInfo;
import cn.dms.enums.loginStatusEnum;

public class SessionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpServletRequest.getSession();
        if (httpSession != null) {
            if (httpServletRequest.getRequestURI().endsWith("/um/login.do")) {
                chain.doFilter(request, response);
            } else {
                if (httpSession.getAttribute("userInfo") != null 
                		&& httpSession.getAttribute("userInfo") instanceof UserInfo) {
                    UserInfo userInfo = (UserInfo) httpSession.getAttribute("userInfo");
                    if (userInfo.getLoggingStatus() == loginStatusEnum.SUCCESS.getCode()) {
                        chain.doFilter(request, response);
                    } else {
                        gotoLoginPage(request, response);
                    }
                } else {
                    gotoLoginPage(request, response);
                }
            }
        } else {
            gotoLoginPage(request, response);
        }
    }

    @Override
    public void destroy() {

    }

    private void gotoLoginPage(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login.html").forward(request, response);
    }
}
