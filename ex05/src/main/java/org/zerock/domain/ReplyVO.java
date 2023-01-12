package org.zerock.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyVO {

	private long rno;	//댓글 넘버
	private long bno;	//본 게시물 넘버
	
	private String reply;	//댓글
	private String replyer;	//댓글 작성자

	private Date replyDate;		//작성일 
	private Date updateDate;	//수정일
		
}//c
