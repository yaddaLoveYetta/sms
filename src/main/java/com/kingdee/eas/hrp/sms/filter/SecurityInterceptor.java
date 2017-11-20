package com.kingdee.eas.hrp.sms.filter;

import com.kingdee.eas.hrp.sms.model.User;
import com.kingdee.eas.hrp.sms.service.api.user.IUserService;
import com.kingdee.eas.hrp.sms.util.Environ;
import com.kingdee.eas.hrp.sms.util.ParameterUtils;
import com.kingdee.eas.hrp.sms.util.ResponseWriteUtil;
import com.kingdee.eas.hrp.sms.util.StatusCode;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 拦截请求
 *
 * @author yadda
 * @ClassName SecurityInterceptor
 * @Description 校验请求合法性
 * @date 2017-04-15 21:43:12 星期六
 */
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    /**
     * 后台管理系统不拦截的资源
     */
    public Map<String, String> allowUrls;
    /**
     * app请求不拦截的资源
     */
    public Map<String, String> clientUrls;

    public void setClientUrls(Map<String, String> clientUrls) {
        this.clientUrls = clientUrls;
    }

    public void setAllowUrls(Map<String, String> allowUrls) {
        this.allowUrls = allowUrls;
    }

    /**
     * 判断用户是否登陆，未登陆用户不允许访问
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     * @date 2017-04-15 21:42:32 星期六
     * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest,
     * javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String requestUrl = request.getRequestURI().replace(request.getContextPath(), "");

        if (null != allowUrls && allowUrls.containsKey(requestUrl)) {
            // 不拦截接口可能也会用到用户信息
            User user = (User) request.getSession().getAttribute("user");

            if (user != null) {
                request.getSession(true).setAttribute("user", user);
            }

            // 不拦截的请求
            return super.preHandle(request, response, handler);
        }

        if (null != clientUrls && clientUrls.containsKey(requestUrl)) {
            // HRP调用的业务接口--验证token合法性

            IUserService userService = Environ.getBean(IUserService.class);

            String token = ParameterUtils.getParameter(request, "token", "");

            User user = userService.getUserByToken(token);

            if (null == user) {
                ResponseWriteUtil.output(response, StatusCode.ACCESS_TOKEN_INVALID, "token无效或过期!");
                return false;
            }

            request.getSession(true).setAttribute("user", user);

            return super.preHandle(request, response, handler);
        }

        User user = (User) request.getSession().getAttribute("user");

        if (user == null) {
            ResponseWriteUtil.output(response, StatusCode.SESSION_LOST, "会话结束请重新登陆!");
            return false;
        }
        // 重新设值session--触发监听器将user放入ThreadLocal
        request.getSession(true).setAttribute("user", user);

        return super.preHandle(request, response, handler);

    }
}
