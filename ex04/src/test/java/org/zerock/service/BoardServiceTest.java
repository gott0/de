package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTest {

	@Setter(onMethod_ = @Autowired)
	private BoardService service;
	
	
//	@Test
//	public void testExist() {
//
//		log.info(service);
//		assertNotNull(service);
//	}
	
//	@Test
//	public void testRegister() {
//		BoardVO board = new BoardVO();
//		
//		board.setTitle("새로 작성하는 글 impl");        //BoardVO 객체의 셋터 메소드를 사용하여 데이터 저장 
//		board.setContent("새로 작성하는 내용 impl");
//		board.setWriter("newbie");
//		
//		service.register(board);
//		log.info("추가된 게시물: " + board.getBno());
//	}


//	@Test
//	public void testGetList() {
//	
//		service.getList().forEach(board -> log.info(board));
//	
//	}
	
	
//	@Test
//	public void testGet() {
//		
//		log.info(service.get(9L));
//		
//	}
	
	
//	@Test
//	public void testUpdate() {
//		BoardVO board = service.get(6L);   // 반환값 = BoardVO 타입
//		if(board == null) {
//			return;
//		}
//
//		board.setTitle("제목만 수정");
//		boolean b = service.modify(board);  // 반환값 = true or false , 잘 수정 됬으면 true
//		log.info("modify result : " + b);
//	}
	
	
	@Test
	public void testDelete() {
		boolean b = service.remove(4L);
		
		log.info("Delete result : " + b);  // 잘 삭제 됬으면 true
		
	}
	
	
	
	
	
	
	
}//c
