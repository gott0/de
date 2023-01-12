package org.zerock.mapper;

import java.util.List;

import org.zerock.domain.BoardAttachVO;

public interface BoardAttachMapper {

	public void insert(BoardAttachVO vo);  // 첨부 파일 정보 삽입(어테치 테이블에)

	public void delete(String uuid); 

	public List<BoardAttachVO> findByBno(Long bno);
 
	public void deleteAll(Long bno); //게시글이 삭제 되었을 때 첨부 파일도 삭제되게 하는 메소드

	public List<BoardAttachVO> getOldFiles();

}