package com.zerock.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

@Component
@Data
public class Restaurant { //객체 관리를 스프링 컨테이너가 한다.(제어의 역전)

	//의존주입
	@Setter(onMethod_ = @Autowired)
	private Chef chef;
	
	
}
