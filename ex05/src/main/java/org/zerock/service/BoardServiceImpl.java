package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.domain.BoardAttachVO;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardAttachMapper;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class BoardServiceImpl implements BoardService { //DAO( = Mapper 인터페이스)를 실행하는 역할
	
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper; //단일 생성자는 자동 의존 주입 됨.(BoardMapper를 땡겨온다)

	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;
	
	@Transactional
	@Override
	public void register(BoardVO board) {
		
		log.info("register...." + board);
		mapper.insertSelectKey(board);
		
		if(board.getAttachList() == null || board.getAttachList().size() <= 0) { // 첨부파일이 있는지 체크
			return;
		}
		
		board.getAttachList().forEach(attach ->{
			attach.setBno(board.getBno());
			attachMapper.insert(attach);
		});
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

	@Transactional
	@Override
	public boolean modify(BoardVO board) {

		log.info("modify......" + board);

		attachMapper.deleteAll(board.getBno());

		boolean modifyResult = mapper.update(board) == 1;   
															
		if (modifyResult && board.getAttachList() != null) {

			board.getAttachList().forEach(attach -> {

				attach.setBno(board.getBno());
				attachMapper.insert(attach);
			});
		}

		return modifyResult;	
	}								
	
//	@Override
//	public boolean modify(BoardVO board) {
//
//		log.info("modify......" + board);
//		
//		return mapper.update(board) == 1;   // mapper 타입은  int 이기 때문에 논리연산자 타입으로 바꾸기 위에 ==을 붙임
//	}										// mapeer의  기본 반환값이  1이기 때문에 1과 비교 한다.

	@Transactional // 게시글과 첨부파일 두가지가 같이 삭제 시키기 위해(둘 중 하나만 삭제되면  롤백이 일어나게 함)
	@Override
	public boolean remove(Long bno) { //게시글 삭제 시 첨부파일도 같이 삭제 되도록 

		log.info("remove...." + bno);

		attachMapper.deleteAll(bno); // 첨부파일 삭제
		
		return mapper.delete(bno) == 1; // 게시글 삭제
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
	
	
	@Override
	public List<BoardAttachVO> getAttachList(Long bno) {

		log.info("get Attach list by bno" + bno);

		return attachMapper.findByBno(bno);
	}
	
	
}//c

