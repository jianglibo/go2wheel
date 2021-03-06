package com.go2wheel.jwt;

import java.io.IOException;

import javax.annotation.Priority;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.go2wheel.vo.BootUserAuthentication;
import com.go2wheel.vo.BootUserPrincipal;

/**
 * copy some code from @see {@link BasicAuthenticationFilter}
 * 
 * Maybe it's no need to match pattern. Check every income request, authenticate if jwt header exists.
 * 
 * @author jianglibo@gmail.com
 *         2015年8月20日
 *
 */
@Component
@Priority(18)
public class JwtBasicFilter implements Filter {

	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory.getLogger(JwtBasicFilter.class);
	
//	public static Pattern pathPattern;
	
//	public static Pattern negPathPattern;
	
	@Autowired
	private JwtUtil jwtUtil;

	
//	@Autowired
//	public void setPattern(@Value("${katharsis.pathPrefix}") String prefix) {
//		String t = String.format("^%s.*", prefix);
//		pathPattern = Pattern.compile(t);
//		logger.info("apply url pattern: {}", t);
//		
//		String t1 = String.format("^%s/loginAttempts", prefix);
//		String t2 = String.format("^%s/users", prefix);
//		negPathPattern = Pattern.compile(String.format("%s/.*|%s|%s/.*|%s", t1, t1, t2, t2));
//		logger.info("negtive url pattern: {}", t1);
//	}

    
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
			HttpServletRequest request = (HttpServletRequest) req;
			HttpServletResponse response = (HttpServletResponse) res;
			try {
				processBasicLogin(request, response);
				chain.doFilter(req, res);
			} catch (AuthenticationException e) {
				SecurityContextHolder.clearContext();
				throw new AccessDeniedException("authentication exception.");
			}
			
//			if (HttpMethod.POST.matches(request.getMethod()) && negPathPattern.matcher(request.getRequestURI()).matches()) {
//				chain.doFilter(request, response);
//			} else if (pathPattern.matcher(request.getRequestURI()).matches()
//					|| UrlConstants.UPLOAD_ENDPOINT.equals(request.getRequestURI())
//					|| UrlConstants.POSTFORM_ENDPOINT.equals(request.getRequestURI())) {
//				try {
//					processBasicLogin(request, response);
//					chain.doFilter(req, res);
//				} catch (AuthenticationException e) {
//		            SecurityContextHolder.clearContext();
//		            throw new AccessDeniedException("authentication exception.");
//				}
//			} else {
//				chain.doFilter(request, response);
//			}
		}
	}

    private void processBasicLogin(HttpServletRequest request, HttpServletResponse response) throws AccessDeniedException, IOException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
        	return;
        	// don't terminate procedure, cause wxsession validate follows.
        	// throw new AccessDeniedException("no jwt header.");
        }
        String jwttoken = header.substring(7);
        SecurityContextHolder.clearContext();
        DecodedJWT decodedJwt = jwtUtil.getVerifier().verify(jwttoken);
        BootUserPrincipal principal = jwtUtil.toPrincipal(decodedJwt);
        BootUserAuthentication buan = new BootUserAuthentication(principal);
        SecurityContextHolder.getContext().setAuthentication(buan);
        String refreshToken = jwtUtil.regenToken(decodedJwt, principal);
        if (refreshToken != null) {
        	response.setHeader(JwtUtil.REFRESH_HEADER_NAME, refreshToken);
        }
    }

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}
}
