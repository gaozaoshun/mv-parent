package org.exotic.filter;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by gaozs on 2017/10/26.
 */
@Component
public class TokenFilter extends ZuulFilter {
    private static Logger logger = LoggerFactory.getLogger(TokenFilter.class.getName());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        if (logger.isDebugEnabled()){
            logger.debug(request.getMethod()+" >>> "+request.getRequestURI());
        }
        Object token = request.getParameter("token");
        if (token == null){
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try {
                ctx.getResponse().getWriter().write("token is empty!");
            } catch (IOException e) {
                logger.error("ZuulFilter Error:",e);
            }
        }else {
            if (!token.equals("99999")){
                try {
                    ctx.setSendZuulResponse(false);
                    ctx.setResponseStatusCode(402);
                    ctx.getResponse().setContentType("application/json;charset=UTF-8");
                    ctx.getResponse().getWriter().write("{\"code:\":402,\"msg\":\"token is error\"}");
                } catch (IOException e) {
                    logger.error("ZuulFilter Error:",e);
                }
            }
        }
        return null;
    }
}
