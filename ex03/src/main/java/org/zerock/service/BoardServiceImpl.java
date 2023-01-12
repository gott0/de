package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService { //DAO( = Mapper 인터페이스)를 실행하는 역할
	
	private BoardMapper mapper; //단일 생성자는 자동 의존 주입 됨.(BoardMapper를 땡겨온다)


	@Override
	public void register(BoardVO board) {
		
		log.info("register...." + board);
		mapper.insertSelectKey(board);
		
	}

//	@Override
//	public List<BoardVO> getList() {
//
//		log.info("전체조회.....");
//
//		return mapper.getList();
//	}
	
	
	@Override
	public BoardVO get(Long bno) {

		log.info("선택조회....." + bno);
		
		return mapper.read(bno);
	}

	
	@Override
	public boolean modify(BoardVO board) {

		log.info("modify......" + board);

		return mapper.update(board) == 1;   // mapper 타입은  int 이기 때문에 논리연산자 타입으로 바꾸기 위에 ==을 붙임
	}										// mapeer의  기본 반환값이  1이기 때문에 1과 비교 한다.

	
	@Override
	public boolean remove(Long bno) {

		log.info("remove...." + bno);

		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		
		log.info("get List with criteria: " +  cri);
		
		return mapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotal(Criteria cri) {

		log.info("get total count");
		return mapper.getTotalCount(cri);
	}
	
}//c

