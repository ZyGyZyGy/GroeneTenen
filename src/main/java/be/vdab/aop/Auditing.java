package be.vdab.aop;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Aspect
@Component
class Auditing {
    
    private final static Logger LOGGER = Logger.getLogger(Auditing.class.getName());

    @Before("execution(* be.vdab.services.*.*(..))")
    void schrijfAudit(JoinPoint joinPoint) {
	StringBuilder builder =
		new StringBuilder("\nTijdstip\t").append(LocalDateTime.now());
	Authentication authentication =
		SecurityContextHolder.getContext().getAuthentication();
	if (authentication != null && !(authentication.getName()).equals("anonymousUser")) {
	    builder.append("\nGebruiker\t").append(authentication.getName());
	}
	builder.append("\nMethod\t\t").append(joinPoint.getSignature().toLongString());
	Arrays.stream(joinPoint.getArgs()).forEach(object -> builder.append("\nParameter\t").append(object));
	LOGGER.info(builder.toString());
    }
    
}