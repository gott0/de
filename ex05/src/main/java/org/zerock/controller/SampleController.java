package org.zerock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

import lombok.extern.log4j.Log4j;
import sun.security.krb5.internal.Ticket;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {

	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {

		log.info("MIME TYPE: " + MediaType.TEXT_PLAIN_VALUE);

		return "안녕하세요";

	}

	
	@GetMapping(value = "/getSample", 
			produces = { MediaType.APPLICATION_JSON_UTF8_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public SampleVO getSample() {

		return new SampleVO(112, "스타", "로드");

	}

	
	@GetMapping(value = "/getSample2")
	public SampleVO getSample2() {
		
		return new SampleVO(113, "로켓", "라쿤");
		
	}

	
	@GetMapping(value = "/getList")
	public List<SampleVO> getList() {

		return IntStream.range(1, 10).mapToObj(i -> new SampleVO(i, i + "First", i + " Last"))
				.collect(Collectors.toList());

	}

	
	@GetMapping(value = "/getMap")
	public Map<String, SampleVO> getMap() {

		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(111, "그루트", "주니어"));

		return map;

	}

	
	@GetMapping(value = "/check", params = { "height", "weight" })
	public ResponseEntity<SampleVO> check(Double height, Double weight) {

		SampleVO vo = new SampleVO(000, "" + height, "" + weight);

		ResponseEntity<SampleVO> result = null;

		if (height < 150) {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}

		return result;
	}
	

	@GetMapping(value = "/product/{cat}/{pid}")    //@PathVariable방식 사용하기
	public String[] getPath( 
			@PathVariable("cat") String cat,
			@PathVariable("pid") Integer pid) {
		
		return new String[] {"category: " + cat, "product: " + pid}; // 배열 구조로 리턴 
	} // http://localhost:8081/sample/product/{cat}/{pid} 이런식으로 주소 요청
	  // ex) http://localhost:8081/sample/product/Sham/10.json = @PathVariable방식 제이슨 형식으로 요청
			
			
	@PostMapping("/Ticket")
	public Ticket convert(@RequestBody Ticket ticket) { //@RequestBody: JSON타입으로 요청 된 데이터를 Ticket타입으로 변환하기 위해 (타입변환)
		
		log.info("convert....ticket" + ticket);
		
		return ticket;
	}
	
	
	
}//c








