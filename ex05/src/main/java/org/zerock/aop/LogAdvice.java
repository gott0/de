package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Aspect	//(공통 관심사)
@Log4j
@Component	//스프링 컨테이너에 빈으로 등록 (root-context 스캔작업까지 해줘야됨)
public class LogAdvice {

	@Before( "execution(* org.zerock.service.SampleService*.*(..))") // ""의 경로는 AOP의 advice(대상)가 된다
	public void logBefore() {
	
	  log.info("========================");
	} // 해당(advice)되는 메소드 호출 전에 자동실행 됨
	
	
	@Around("execution(* org.zerock.service.SampleService*.*(..))")  //대상
	public Object logTime( ProceedingJoinPoint pjp) {
		
		long start = System.currentTimeMillis();
		
		log.info("Taget: " + pjp.getTarget()); // 대상 실행 전에 실행
		log.info("Param: " + Arrays.toString(pjp.getArgs())); // 대상 실행 전에 실행
		
		//invoke method
		Object result = null;
		
		try {
			result = pjp.proceed();
		}catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("TIMEL: " + (end - start)); // 대상 실행 후에 실행
		
		return result;
	} // 해당(advice)되는 메소드의 호출 전과 후에 자동실행(호출) 됨
  
  
	@After( "execution(* org.zerock.service.SampleService*.*(..))") // ""의 경로는 AOP의 advice(대상)가 된다
	public void logAfter() {
	
	  log.info("========================end");
	} // 해당(advice)되는 메소드 호출 후에 자동실행 됨
	
  
}//c