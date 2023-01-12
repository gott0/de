package com.zerock.controller;


import java.util.ArrayList;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.zerock.domain.SampleDTO;
import com.zerock.domain.SampleDTOList;
import com.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller  // 컨트롤러 만들기
@RequestMapping("/sample/*")  // 어떤 요청인지 판단 (@RequestMapping -Class와 method에 불일 수 있다)
@Log4j
public class SampleController {
	@RequestMapping("")  // 서브 uri정보 작성
	public void basic() {  // 서브 uri 실행
		
		
		log.info("basic~~~");
	}
		
	@GetMapping("/ex01")  //요청을 받아 처리 (@GetMapping method에만 불일 수 있다)
	//http://localhost:8081/sample/ex01?name=hong&age=10
	public String ex01(SampleDTO dto) { //커멘드객체 : 파라미터 값을 알아서 받아와서 처리해주는 객체  
		
		log.info("" + dto);
		
		return "ex01";
	}

	@GetMapping("/ex02") // @GetMapping: URI값  즉, localhost:8080/ex02   요청이 들어오면 아래의 함수를 실행하라고 해석하면 됨.
	//http://localhost:8081/sample/ex02?name=hong&age=10
	public String ex02( @RequestParam("name") String name, @RequestParam("age") int age) {  // 밖에서 파라메터를 받아오는 방법
	
		log.info(name);
		log.info(age);
		
		return "ex02";  // ex02를 반환 (차후에 ex02라는 이름의 View를 찾아서 실행) - 현 단계에선 뷰 생성을 아직 안 했음
	}
	
	@GetMapping("/ex02List") 
	//http://localhost:8081/sample/ex02List?ids=aaa&ids=bbb&ids=ccc
	public String ex02List( @RequestParam("ids") ArrayList<String> ids) {  
		
		log.info("ids : "+ ids);
		
		return "ex02List";
	}
	
	@GetMapping("/ex02Bean") 
	//http://localhost:8081/sample/ex02Bean?list%5B0%5D.name=aaa&list%5B1%5D.name=bbb&list%5B1%5D.age=20
	public String ex02Bean( SampleDTOList list) {  
		
		log.info("list : " + list);
		
		return "ex02Bean";
	}
	
	@GetMapping("/ex03") 
	//http://localhost:8081/sample/ex03?title=test&dueDate=2022/12/21
	public String ex03(TodoDTO todo) {  
		
		log.info("todo: " + todo);
		
		return "ex03";
	}
	
	@GetMapping("/ex04") 
	//http://localhost:8081/sample/ex04?name=hong&age=10&page=9
	public String ex03(SampleDTO dto,  @ModelAttribute("page") int page) {  
		// 커멘드 객체
		// 1. 값(파라메터)을 자동으로 받아옴.
		// 2. 뷰 페이지로 커멘드 객체의 정보를 전달한다.(클래스의 첫글자를 소문자로 구성해서 전달)
		//									└ex) SampleDTO => sampleDTO
		// 3. 기본형 매개변수에 대해 뷰페이지로 값 전달을 위해 @ModelAttribute를 적용한다.
		//		Model객체에 담겨서 전달 된다.
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return "/sample/ex04";
	}
	
	@GetMapping("/ex06") 
	//http://localhost:8081/sample/ex06
	public @ResponseBody SampleDTO ex06() {  // json형태로 데이터 처리
		
		SampleDTO dto = new SampleDTO();
		dto.setName("hong");
		dto.setAge(10);
		
		return dto;
	}
	
	@GetMapping("/ex07") 
	//http://localhost:8081/sample/ex07
	public ResponseEntity<String> ex07() {  
		
		String msg = "{\"name\":\"홍길동\"}";   // {"name":"홍길동"} 속성과 값이 문자열인 데이터를 json형태로 전달
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<>(msg,header,HttpStatus.OK);
	}
	
	@GetMapping("/exUpload") 
	//http://localhost:8081/sample/exUpload
	public void exUpload() {  //void인 경우  uri 주소의 끝단의 문자열과 같은 이름의 jsp파일을 실행 
		
		log.info("/exUpload.......");
	}
	
	@PostMapping("/exUploadPost") 
	////http://localhost:8081/sample/exUploadPost
	public void exUploadPost(ArrayList<MultipartFile> files) {  
		files.forEach(file -> {
			log.info("----------");
			log.info("name: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
		});		
	}
	
}//c
