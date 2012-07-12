package csc.traning.wpsj.servlet.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class CountOnline
 * @author dangthehuynh
 */
public class LogFilter implements Filter {

	protected FilterConfig config;
	private ServletContext context;
	private String filterName;
	
    /**
     * Default constructor. 
     */
    public LogFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req =
			(HttpServletRequest)request;
			context.log(req.getRemoteHost() +
			" tried to access " +
			req.getRequestURL() +
			" on " + new Date() + ". " +
			"(Reported by " +
			filterName + ".)");

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// In case it is needed by subclass.
    	this.config = fConfig;
    	context = config.getServletContext();
    	filterName = config.getFilterName();
	}

}
