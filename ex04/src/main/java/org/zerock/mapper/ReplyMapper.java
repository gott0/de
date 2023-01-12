package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.zerock.domain.Criteria;
import org.zerock.domain.ReplyVO;

public interface ReplyMapper {

	public int insert(ReplyVO vo);
	
	public ReplyVO read(Long rno);
	
	public int delete(Long rno);

	public int update(ReplyVO reply);

	//Mybatis의 SQL 문장에 다수(2개 이상)의 파라미터를 전달할 때는 전달되는 변수들에 꼭 @Param 어노테이션을 붙여줘야한다.
	public List<ReplyVO> getListWithPaging( @Param("cri") Criteria cri, @Param("bno") Long bno);
			
}//c

