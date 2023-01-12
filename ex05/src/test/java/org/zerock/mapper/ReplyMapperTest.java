package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//Java config
// @ContextConfiguration(classes = { org.zerock.config.RootConfig.class
//})
@Log4j
public class ReplyMapperTest {

	//테스트 전에 해당 번호의 게시물이 존재하는지 반드시 확인할 것
	private Long[] bnoArr = { 495625L, 495621L, 495601L, 495600L, 495599L};
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;

//	@Test
//	public void testMapper() {
//		
//		log.info(mapper);
//	}
	
	
//	@Test
//	public void testCreate() {
//		
//		IntStream.rangeClosed(1,10).forEach(i -> {  // (1,10)일때 보편 적으로 1~9(9개)까지만 생성되지만 
												    // 간혹 (1,10)일 때  마지막 숫자까지 포함되어 생성되는 메소드가 있긴 함 (현재 메소드에 해당)
//			
//			ReplyVO vo = new ReplyVO();
//			
//			//게시물 번호
//			vo.setBno(bnoArr[i % 5]);
//			vo.setReply("댓글 테스트" + i);
//			vo.setReplyer("replyer" + i);
//		
//			mapper.insert(vo);
//		});
//	}
	
	
//	@Test
//	public void testRead() {
//		
//		Long targetRno = 5L;    // 5번 글을 조회		
//		ReplyVO vo = mapper.read(targetRno);
//		
//		log.info(vo);
//	}

	
	@Test
	public void testDelete() {
		
		Long targetRno = 2L;  //2번 글을 삭제
		
		mapper.delete(targetRno);
	}

	
//	@Test
//	public void testUpdate() {
//		
//		Long targetRno = 10L;  //10번 글을 업데이트
//		
//		ReplyVO vo = mapper.read(targetRno);
//		vo.setReply("Update Reply");
//		
//		int count = mapper.update(vo);
//		
//		log.info("UPDATE COUNT: " + count);
//	}
	
	
//	@Test
//	public void testList() {
//		
//		Criteria cri = new Criteria();
//
//		//495625L
//		List<ReplyVO> replies = mapper.getListWithPaging(cri, bnoArr[0]);
//		
//		replies.forEach(reply -> log.info(reply));
//	}
	
}//c
