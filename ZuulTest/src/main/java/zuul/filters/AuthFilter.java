package zuul.filters;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import java.util.Base64;

import javax.servlet.ServletException;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class AuthFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("+++++++++++++++++++++++++++++");
		System.out.println(RequestContext.getCurrentContext().getZuulRequestHeaders().get("Authorization"));
		RequestContext.getCurrentContext().addZuulResponseHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:123".getBytes()));
		RequestContext.getCurrentContext().addZuulRequestHeader("Authorization", "Basic " + Base64.getEncoder().encodeToString("admin:123".getBytes()));
//		try {
//			RequestContext.getCurrentContext().getRequest().login("admin", "123");
//		} catch (ServletException e) {
//			e.printStackTrace();
//		}
		System.out.println(RequestContext.getCurrentContext().getZuulRequestHeaders().get("Authorization"));
		return null;
	}

	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	@Override
	public int filterOrder() {
		return 3;
	}

}
