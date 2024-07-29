package sellphone.user.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserLogAspect {
	
	@Pointcut("execution(* sellphone.user.controller.DashBoardFilter.checkLogin(..))")
    public void dashBoardAcessPointCut() {
    }
	
	@Around("dashBoardAcessPointCut()")
	public Object dashboardAcessAround(ProceedingJoinPoint pjp) throws Throwable {
		
		System.out.println("before login");
		Object obj = pjp.proceed();
		System.out.println("After login");
		
		return obj;
	}

	//	@Around("execution(* sellphone.user.controller.UserController.checkLogin(..))")
//	public Object userLoginAround(ProceedingJoinPoint pjp) throws Throwable {
//		
//		System.out.println("before login");
//		Object obj = pjp.proceed();
//		System.out.println("After login");
//		
//		return obj;
//	}
	
}
