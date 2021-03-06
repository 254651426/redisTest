/**
 *
 * @author geloin
 * @date 2012-4-10 下午2:37:38
 */
package com.yangjie.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 登录过滤
 * 
 * @author geloin
 * @date 2012-4-10 下午2:37:38
 */
public class SessionFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// 不过滤的uri
		String[] notFilter = new String[] { "login.do", "index.do", "menu.do" , "getuser.do" , "logout.do" };

		// 请求的uri
		String uri = request.getRequestURI();

		// uri中包含background时才进行过滤
		if (uri.indexOf(".do") != -1) {
			// 是否过滤
			boolean doFilter = true;
			for (String s : notFilter) {
				if (uri.indexOf(s) != -1) {
					// 如果uri中包含不过滤的uri，则不进行过滤
					doFilter = false;
					break;
				}
			}
			if (doFilter) {
				// 执行过滤
				// 从session中获取登录者实体
				Object obj = request.getSession().getAttribute("user");
				if (null == obj) {
					// 如果session中不存在登录者实体，则弹出框提示重新登录
					// 设置request和response的字符集，防止乱码
					 String path = request.getContextPath();
					 String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
					response.sendRedirect(basePath+"login.jsp");
					return;
 			} else {
					// 如果session中存在登录者实体，则继续
					filterChain.doFilter(request, response);
				}
			} else {
				// 如果不执行过滤，则继续
				filterChain.doFilter(request, response);
			}
		} else {
			// 如果uri中不包含background，则继续
			filterChain.doFilter(request, response);
		}
	}

}