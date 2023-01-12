package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;
import org.zerock.mapper.BoardMapper;
import org.zerock.mapper.ReplyMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@AllArgsConstructor
public class ReplyServiceImpl implements ReplyService{
	
	@Setter(onMethod_ = @Autowired)
	private ReplyMapper mapper;
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper boardmapper;  //댓글 카운터를 위해 추가
	
	@Transactional // A와 B를 같이 실행시키기 위해 추가하는 어노테이션
	@Override
	public int register(ReplyVO vo) {
		
		log.info("register....." + vo);
		boardmapper.updateReplyCnt(vo.getBno(), 1); //A = 게시물 테이블의 replycnt +1 시키는 메소드
		
		return mapper.insert(vo);  //B = 신규 댓글 추가
 	
	}
	
	
	@Override
	public ReplyVO get(Long rno) {
		
		log.info("get....." + rno);
		
		return mapper.read(rno);  
 	}	
	
	
	@Override
	public int modify(ReplyVO vo) {
		
		log.info("modify....." + vo);
		
		return mapper.update(vo);  
	}
	
	@Transactional // 위의 register메소드와 동일한 이유로 추가
	@Override
	public int remove(Long rno) { //*게시글(본글)에서 댓글 카운터를 감소 시켜야 되는데 해당 매개변수는 카운터 객체가 아니다 (bno에 영향을 줄 수 없다)
		log.info("remove....." + rno);
		ReplyVO vo = mapper.read(rno); // rno 값을 이용해서 ReplyVO의 정보를 가져온는 역할 위의 문제(*)를 해결 (bno를 다룰 수 있다)
		
		boardmapper.updateReplyCnt(vo.getBno(), -1); // 게시물 테이블의 replycnt -1 시키는 메소드 (bno를 이용하여)
		
		return mapper.delete(rno);  
	}
	
	
	@Override
	public List<ReplyVO> getList(Criteria cri, Long bno) {
		
		log.info("get Reply List of a Board " + bno);
		
		return mapper.getListWithPaging(cri, bno);  
	}
	
	
}//c
