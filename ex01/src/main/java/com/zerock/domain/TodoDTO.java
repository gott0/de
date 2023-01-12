package com.zerock.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TodoDTO {

	private String title;
	@DateTimeFormat(pattern = "yyyy/MM/dd") // 문자(String)로 넘어오는 날짜형식을 날짜 타입(Date)으로 형변환 해주는 어노테이션(pattern은 직접 지정해준다)
	private Date dueDate;  // 어노테이셔 없을 시 타입이 불일치해 오류 발생 [파라미터는 문자열(String)로 들어온다.]
	
	
}//c
