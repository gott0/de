package com.zerock.sample;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Component
@ToString
@Getter
@RequiredArgsConstructor
public class SampleHotel {

	@NonNull
	private Chef chef;   //생성자의 의존 주입 (단일 생성자라 묵시적 의존주입이 실행 됨) = 스프링 컨테이서를 통해 자동으로 값이 들어온다
						 //생성자가 2개 이상일 경우 관련된 메소드를 적용시켜야 됨 (차후에 알려주겟지...?)
	
	private String name;
	
	
}//c
