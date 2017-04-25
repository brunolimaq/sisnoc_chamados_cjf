package br.com.sisnoc.chamados.config.init;

import java.util.EnumSet;

import javax.servlet.ServletContext;
import javax.servlet.SessionTrackingMode;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer  extends AbstractSecurityWebApplicationInitializer{
	
	@Override
	protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
		servletContext.getSessionCookieConfig().setMaxAge(86400); //tempo da sessão, idependetemente se tem movimento ou não
		servletContext.setSessionTrackingModes(EnumSet.of(SessionTrackingMode.COOKIE)); //mudando sistema para usar COOKIE e não SESSION ID na URL
		
	}

}
