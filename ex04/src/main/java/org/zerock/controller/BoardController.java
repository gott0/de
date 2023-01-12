package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*")  //"http://localhost:8081/board/~~"의 모든 URL의 요청을 받음
@AllArgsConstructor
public class BoardController {
	
	private BoardService service; // 단일 생성자 자동 주입
	
	
//	@GetMapping("/list") // get방식으로 요청이 들어오면
//	// http://localhost:8081/board/list 주소로 요청 시 실행
//	public void list(Model model) {
//		
//		log.info("list");
//		model.addAttribute("list" , service.getList());
//	} // Model 객체를 이용하여 데이터 전달 방식: 결과를 만들어서 실제 필요한 곳으로 전달하기 위해 씀
	
//	@GetMapping("/list") 
//	public void list(Criteria cri, Model model) {
//		log.info("list: " + cri);
//		model.addAttribute("list", service.getList(cri));
//		model.addAttribute("pageMaker", new PageDTO(cri,123));
//		
//	}
	
	// 페이징 처리 + 전체 데이터 수 가져오기
	@GetMapping("/list")
	public void list(Criteria cri, Model model) {
		log.info("list : " + cri);
		model.addAttribute("list", service.getList(cri));
		// model.addAttribute("pageMaker", new PageDTO(cri, 123));
		
		int total = service.getTotal(cri);
		log.info("total : " + total);
		model.addAttribute("pageMaker", new PageDTO(cri, total));
	}
	
	
	@GetMapping("/register") // Get방식으로 요청이 들어오면
	public void register() { // 뷰 페이지만 띄워주는 역할
	// 반환 타입이 void 이기 때문에  "/register"주소 자체가  "register.jsp"뷰 페이지가 된다(void의 특징 - 호출하는 URL과 동일한 이름의 jsp를 실행 )
		
	}
	
	
	@PostMapping("/register") // Post방식으로 요청이 들어오면 
	public String register(BoardVO board, RedirectAttributes rttr) { //데이터를  폼에서 입력을 받아 DB에 저장하고 "list.jsp"이동
																		//RedirectAttributes를 이용한 전달방식 (일회성 데이터를 이동하는 페이지에 전달
																		//								  - 데이터를 단순하게 일회용으로 쓰기 위해)

		log.info("register: " + board);

		service.register(board);

		rttr.addFlashAttribute("result", board.getBno());  // list.jsp로 일회성 데이터 값을 전달

		return "redirect:/board/list";   //'redirect'방식으로 이동 시 앞에 redirect:'를 적어줌
	}	//redirect가 있다는 것은 컨트롤러를 실행하라는 뜻 (뷰를 거치지 않기 위해 쓰임)
		//반환 타입이 String 타입이기 때문에 redirect가 없을 시  board/list.jsp(뷰)가 동작(실행)하게 되어있음(페이지 이동이 이뤄짐, but DB를 거치치 않아서 아무내용도 보이지 않음) 
	
	
	/*
	 * @GetMapping({ "/get", "/modify" }) // 동일한 기능이 여러 URL을 통해 요청 될 때는 이런 식으로
	 * URL주소들을 {}로 객체화 함 public void get(@RequestParam("bno") Long bno, Model model)
	 * {
	 * 
	 * log.info("/get or modify"); model.addAttribute("board", service.get(bno)); }
	 */	
	
	
	@GetMapping({ "/get", "/modify" }) // 동일한 기능이 여러 URL을 통해 요청 될 때는 이런 식으로 URL주소들을 {}로 객체화 함
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) { //커멘드 객체인 Criteria를 파라메터로 받는다(pageNum,amount를 받기 위해 )
		       									  // └ @ModelAttribute : Model객체에  Criteria객체를 담아 보내기 위한 어노테이션('cri'를 통해 Criteria를 담음)
		log.info("/get or modify");
		model.addAttribute("board", service.get(bno));
	}

	
//	@PostMapping("/modify")
//	public String modify(BoardVO board, RedirectAttributes rttr) {
//		log.info("modify:" + board);
//
//		if (service.modify(board)) {
//			rttr.addFlashAttribute("result", "success");
//		}
//		return "redirect:/board/list";
//	}
	
	
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("modify:" + board);

		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}

		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/board/list";
	}
	
	
//	@PostMapping("/remove")
//	public String remove(@RequestParam("bno") Long bno, RedirectAttributes rttr) {
//
//		log.info("remove..." + bno);
//		if (service.remove(bno)) {
//			rttr.addFlashAttribute("result", "success");
//		}
//
//		return "redirect:/board/list";
//	}
	

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, Criteria cri, RedirectAttributes rttr) {

		log.info("remove..." + bno);
		if (service.remove(bno)) {
			rttr.addFlashAttribute("result", "success");
		}
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());

		return "redirect:/board/list";
	}
	
	
	
	
}//c
