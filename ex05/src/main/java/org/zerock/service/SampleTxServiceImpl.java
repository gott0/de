package org.zerock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mapper.Sample1Mapper;
import org.zerock.mapper.Sample2Mapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class SampleTxServiceImpl implements SampleTxService {

	@Setter(onMethod_= {@Autowired})
	private Sample1Mapper mapper1;
	
	
	@Setter(onMethod_= {@Autowired})
	private Sample2Mapper mapper2;
	
	@Transactional	//트랜젝션(메소드 단위로 적용 됨): 해당 어노테이션이 붙은 메소드 실행 시 두가지 쿼리문 중 하나라도 문제가 있을 시 모두 실행 안됨(해당 어노테이션이 없을 시 문제가 없는 쿼리문만 실행 됨) 
					//(root-context에 <tx:annotation-driven/> 추가해야됨)
	@Override
	public void addData(String value) {

		log.info("mapper1..........");
		mapper1.insertCol1(value);
		
		
		log.info("mapper2..........");
		mapper2.insertCol2(value);
		
		
		log.info("end..............");
		
		
	}

}//c
