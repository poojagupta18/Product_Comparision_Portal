package com.sales.apigateway.filter;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class APIGatewayFilter extends ZuulFilter{
	private static final Logger logger = LoggerFactory.getLogger(APIGatewayFilter.class);

	@Override
	public boolean shouldFilter() {
		// this filter always has to run, so make it as true
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		 HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
		 logger.info("Request --> Request Method --> Request URI, Request URL {} {} {} {} ", request, request.getMethod(), request.getRequestURI(), request.getRequestURL());
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 1;
	}

}
