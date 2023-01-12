<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%> 

	<div class="row">
	  <div class="col-lg-12">
	    <h1 class="page-header">Board Read</h1>
	  </div>
	  <!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	
	<div class="row">
	  <div class="col-lg-12">
	    <div class="panel panel-default">
	
	      <div class="panel-heading">Board Read Page</div>
	      <!-- /.panel-heading -->
	      <div class="panel-body">
	
	        <div class="form-group">
	          <label>Bno</label> <input class="form-control" name='bno'
	            value='<c:out value="${board.bno }"/>' readonly="readonly">
	        </div>
	
	        <div class="form-group">
	          <label>Title</label> <input class="form-control" name='title'
	            value='<c:out value="${board.title }"/>' readonly="readonly">
	        </div>
	
	        <div class="form-group">
	          <label>Text area</label>
	          <textarea class="form-control" rows="3" name='content'
	            readonly="readonly"><c:out value="${board.content}" /></textarea>
	        </div>
	
	        <div class="form-group">
	          <label>Writer</label> <input class="form-control" name='writer'
	            value='<c:out value="${board.writer }"/>' readonly="readonly">
	        </div>
	        
	        
	        <%--<button data-oper='modify' class="btn btn-default"
	          onclick="location.href='/board/modify?bno=<c:out value="${board.bno }"/>'">Modify
	        </button>
			<button data-oper='list' class="btn btn-info"
			  onclick="location.href='/board/list'">List
			</button> --%>

			<button data-oper='modify' class="btn btn-default">Modify</button> <!-- 스크립트 형식으로 이벤트 처리하기 -->
			<button data-oper='list' class="btn btn-info">List</button>
				        
			<form id='operForm' action="/boad/modify" method="get">
			  <input type='hidden' id='bno' name='bno' value='<c:out value="${board.bno}"/>'>
			  <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
			  <input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
			  <input type='hidden' name='keyword' value='<c:out value="${cri.keyword}"/>'>
			  <input type='hidden' name='type' value='<c:out value="${cri.type}"/>'>  
 			</form>
			
	      </div>
	      <!--  end panel-body -->
	    </div>
	    <!--  end panel-body -->
	  </div>
	  <!-- end col-12 -->
	</div>
	<!-- /.row -->      

	<div class="bigPictureWrapper">		
		<div class="bigPicture">
		</div>
	</div>
	
<style>
	.uploadResult {
	  width:100%;
	  background-color: pink;
	}
	.uploadResult ul{
	  display:flex;
	  flex-flow: row;
	  justify-content: center;
	  align-items: center;
	}
	.uploadResult ul li {
	  list-style: none;
	  padding: 10px;
	  align-content: center;
	  text-align: center;
	}
	.uploadResult ul li img{
	  width: 100px;
	}
	.uploadResult ul li span {
	  color:white;
	}
	.bigPictureWrapper {
	  position: absolute;
	  display: none;
	  justify-content: center;
	  align-items: center;
	  top:0%;
	  width:100%;
	  height:100%;
	  background-color: gray; 
	  z-index: 100;
	  background:rgba(255,255,255,0.5);
	}
	.bigPicture {
	  position: relative;
	  display:flex;
	  justify-content: center;
	  align-items: center;
	}
	
	.bigPicture img {
	  width:600px;
	}

</style>
	
	
	
	<div class="row">
	  <div class="col-lg-12">
	    <div class="panel panel-default">
	    	
	    	<div class="panel-heading">Files</div>
	    	<!-- /.panel-heading -->
	    	<div class="panel-body">
	    		
	    		<div class="uploadResult">
	    			<ul>
	    			</ul>
	    		</div>
	    	
	    	</div>	
	     	<!--  end panel-body -->
	    </div>
	    <!--  end panel-body -->
	  </div>
	  <!-- end col-12 -->
	</div>
	<!-- /.row --> 
	
	
	
	<div class="row">
		
		<div class="col-lg-12">
			
			<!-- /.panel -->
			<div class="panel panel-default">
				<!-- <div class="panel-heading">
					<i class="fa fa-comments fa-fw"></i> Reply
				</div> -->
				
				<div class="panel-heading">
				<i class="fa fa-comments fa-fw"></i> Reply
				<button id="addReplyBtn" class="btn btn-primary btn-xs pull-right">New Reply</button>
				</div>
				
				<!-- /.panel-heading -->
				<div class="panel-body">
					
					<ul class="chat"> <!-- ul 태그의 클래스 "chat"에 이벤트 걸고 실질적인 이벤트 대상은 li 태그가 되도록 -->
						<!-- li태그의 코드는 없지만 동적으로 추가됨 -->
						
							<!-- start reply -->
							<!-- <li class="left clearfix" data-rno='12'>
								<div>
									<div class="header">
										<strong class="primary-font">user00</strong>
										<small class="pull-right text-muted">2018-01-01 13:13</small>
									</div>
									<p>Good job!</p>
								</div>
							</li> -->
							<!-- end reply -->	
					
					</ul>
					<!-- ./ end ul -->
				</div>
				<!-- /.panel .chat-panel -->
			</div>	
		</div>	
		<!-- ./end row -->
	</div>


	<!-- Modal start -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	  <div class="modal-dialog">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="btn-close" data-dismiss="modal" aria-hidden="true"></button>
			<h4 class="modal-title" id="myModalLabel">REPLY MODAL</h4> 
	      </div>
	      <div class="modal-body">
	        <div class="form-group">
	        	<label>Reply</label>
	        	<input class="form-control" name="reply" value="New Reply!!!">
	        </div>
	        
	        <div class="form-group">
	        	<label>Replyer</label>
	        	<input class="form-control" name="replyer" value="replyer">
	        </div>
	        
	        <div class="form-group">
	        	<label>Reply Date</label>
	        	<input class="form-control" name="replyDate" value="">
	        </div>
	      </div>
	      <div class="modal-footer">
	      
	        <button id="modalModBtn" type="button" class="btn btn-warning">Modify</button>
	        <button id="modalRemoveBtn" type="button" class="btn btn-danger">Remove</button>
	        <button id="modalRegisterBtn" type="button" class="btn btn-primary">Register</button>
	        <button id="modalCloseBtn" type="button" class="btn btn-default">Close</button>
	      </div>		
	     </div>
	     	 <!-- /.modal-content -->
	  </div>
	  <!-- /.modal-dialog -->
	</div>	
	<!-- Modal end -->


<!-- 모듈화 된 reply.js파일을 ajax통신을 이용하여 사용 -->
<script type="text/javascript" src="/resources/js/reply.js"></script>

<script>
	$(document).ready(function(){
		
		var bnoValue = '<c:out value="${board.bno}"/>';
		var replyUL = $(".chat");
		
			showList(1);
			
			function showList(page){
				replyService.getList({bno:bnoValue, page: page || 1}, function(list){
					
					var str = "";
					if(list == null || list.length == 0){
						replyUL.html("");
						
						return;
					}
					
					for (var i = 0, len = list.length || 0; i < len; i++){
						str +="<li class='left clearfix' data-rno='" + list[i].rno + "'>";
						str +="	<div><div class='header'> <strong class='primary-font'>" + list[i].replyer + "</strong>";
						str +="		<small class='pull-right text-muted'>" + replyService.displayTime(list[i].replyDate) + "</small></div>";
						str +="		<p>" + list[i].reply + "</p></div></li>";
					}	
						
				replyUL.html(str);		
				
				}); //end function
	
			}//end showList
	
			var modal = $(".modal");
		    var modalInputReply = modal.find("input[name='reply']");  //find : 원하는 요소 찾기 (input태크의  name=reply속성을 찾아 변수에 넣어 줌)
		    var modalInputReplyer = modal.find("input[name='replyer']");
		    var modalInputReplyDate = modal.find("input[name='replyDate']");
		    
		    var modalModBtn = $("#modalModBtn");
		    var modalRemoveBtn = $("#modalRemoveBtn");
		    var modalRegisterBtn = $("#modalRegisterBtn");
		    
		    $("#modalCloseBtn").on("click", function(e){
		    	
		    	modal.modal('hide'); //모달창 닫기 
		    });   
		    
		    
		    $("#addReplyBtn").on("click", function(e){
		      
		      modal.find("input").val("");
		      modalInputReplyDate.closest("div").hide(); //closest: 앞에 있는 대상(modalInputReplyDate)의 조상 중 첫번째에 있는 "div"요소를 숨김 (hide())
		      modal.find("button[id !='modalCloseBtn']").hide(); // id 값이 'modalCloseBtn' 아닌 것을 다 숨겨!
		      
		      modalRegisterBtn.show(); // Register버튼은 보이게 해!  (결국 modalCloseBtn 과 modalRegisterBtn만 보임)
		      
		      $(".modal").modal("show"); //모달창 보이기
		      
		    });
		    
		    
		    modalRegisterBtn.on("click",function(e){
		    	
		    	var reply = {
		    			reply : modalInputReply.val(),	//(모달창) 입력폼에서 옴
		    			replyer : modalInputReplyer.val(),	//(모달창) 입력폼에서 옴
						bno : bnoValue	// '<c:out value="${board.bno}"/>' (위에서 변수로 선언했었음)	    			
		    	};
		    	replyService.add(reply, function(result){
		    		
		    		alert(result);
		    		
		    		modal.find("input").val(""); //input 태그들의 값을 ""(빈값)으로 해주고
		    		modal.modal("hide");		// modal창 닫음
		    		
		    		showList(1);
		    	}); 
		  	}); 

		    //댓글 조회 클릭 이벤트 처리 
		    $(".chat").on("click", "li", function(e){ // <ul> 태그의 클래스 "chat"에 이벤트 걸고 실질적인 이벤트 대상은 <li> 태그가 되도록하는 메소드
		    	
		    	var rno = $(this).data("rno");  //data- 속성은  데이터 속성이라고 불리며 관리자가 임의로 사용하는 속성이라고 보면 됨 (브라우저가 관여하지X)
		    									//'data-'뒤의 속성명을 읽어옴
		    	
		    	//console.log(rno);  // 댓글 클릭 시 댓글 번호가 콘솔에 찍힘
		    
		    	replyService.get(rno, function(reply){
		    		
		    		modalInputReply.val(reply.reply);
		    		modalInputReplyer.val(reply.replyer);
		    		modalInputReplyDate.val(replyService.displayTime(reply.replyDate)).attr("readonly","readonly");
		    		modal.data("rno", reply.rno);
		    		
		    		modal.find("button[id != 'modalCloseBtn']").hide();
		    		modalModBtn.show();
		    		modalRemoveBtn.show();
		    		
		    		$(".modal").modal("show"); 
		    		
		    	});
		    });
		    
		    
		   modalModBtn.on("click", function(e){
			  
			   var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
			   
			   replyService.update(reply, function(result){
				  
				   alert(result);
				   modal.modal("hide"); // 수정되믄 모달창 닫기
				   showList(1);
			   });
		   });
		   
		   
		   modalRemoveBtn.on("click", function (e){
			   	  
		   	  var rno = modal.data("rno");
		   	  
		   	  replyService.remove(rno, function(result){
		   	        
		   	      alert(result);
		   	      modal.modal("hide");
		   	      showList(1);
		   	      
		   	  });
		   	  
		   	});
		    
	});
</script>

<script type="text/javascript">
	
	console.log("===============");
	console.log("JS TEST");
	
	// 아래의 메소드들은 get.jsp(상세보기)요청 시 실행 됨(테스트라서ㅋ)
	var bnoValue = '<c:out value = "${board.bno}"/>';
	
	//for replyService add test
	
	//댓글 추가하기
	/* replyService.add(
		{reply:"JS Test", replyer:"tester", bno: bnoValue} //1번째 매개변수(reply)
		,
		function(result){ //callback함수
			alert("RESULT: "+ result);
		} //2번째 매개변수(callback)
	); 
	*/
	
	
	//댓글 전체 조회
	/* replyService.getList({bno:bnoValue, page:1},	//1번째 매개변수(param)
		function(list){	//2번째 매개변수(callback)
	    
		  for(var i = 0,  len = list.length||0; i < len; i++ ){
		    console.log(list[i]);
		  }
		}
	); */
	
	
	//8번 댓글 삭제 테스트 
	/* replyService.remove(8, 
		function(count){
		
		console.log(count);
		
			if (count == "success"){
				alert("REMOVE");
			}
		},
		function(err){
			alert("ERROR...");
		}
	); */
	
	
	//7번 댓글 수정 
	/* replyService.update({
	  rno : 7,
	  bno : bnoValue,
	  reply : "Modified Reply...."
	}, 
	function(result) {

	  alert("수정 완료...");

	});   */
	
	
	//하나의 글(10번 댓글) 조회
	/* replyService.get(10, 
	 	function(data){
			console.log(data);
	 	});	
	 */ 
</script>
	

<script>

	$(document).ready(function() {

		var operForm = $("#operForm");

		$("button[data-oper='modify']").on("click", function(e) {

			operForm.attr("action", "/board/modify").submit();

		}); //modify 버튼의 이벤트

		$("button[data-oper='list']").on("click", function(e) {

			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list")
			operForm.submit();

		}); //list 버튼의 이벤트
		
	});
	
</script>


<script>

	$(document).ready(function(){
		  
		  (function(){
			
			var bno = '<c:out value="${board.bno}"/>';
			
			$.getJSON("/board/getAttachList", {bno: bno}, function(arr){
				
				console.log(arr);
				
				var str = "";
			       
			       $(arr).each(function(i, attach){
			       
			         //image type
			         if(attach.fileType){
			           var fileCallPath = encodeURIComponent( 
			        		   attach.uploadPath + "/s_"+attach.uuid + "_" + attach.fileName);
			           
			           str += "<li data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName+"' data-type='" + attach.fileType + "' ><div>";
			           str += "<img src='/display?fileName=" + fileCallPath + "'>";
			           str += "</div>";
			           str +"</li>";
			         }else{
			             
			           str += "<li data-path='" + attach.uploadPath + "' data-uuid='" + attach.uuid + "' data-filename='" + attach.fileName + "' data-type='" + attach.fileType + "' ><div>";
			           str += "<span> " + attach.fileName + "</span><br/>";
			           str += "<img src='/resources/img/attach.png'></a>";
			           str += "</div>";
			           str +"</li>";
			         }
			       });
			       
			       $(".uploadResult ul").html(str);
				
			}); //end getjson
		
		  })(); //end function
		  
		  
		  $(".uploadResult").on("click","li", function(e){
		      
			    console.log("view image");
			    
			    var liObj = $(this);
			    
			    var path = encodeURIComponent(liObj.data("path")+"/" + liObj.data("uuid")+"_" + liObj.data("filename"));
			    
			    if(liObj.data("type")){
			      showImage(path.replace(new RegExp(/\\/g),"/"));
			    }else {
			      //download 
			      self.location ="/download?fileName="+path
			    }
		  });

		  
		  function showImage(fileCallPath){
			    
		    //alert(fileCallPath);
		    
		    $(".bigPictureWrapper").css("display","flex").show();
		    
		    $(".bigPicture").html("<img src='/display?fileName="+fileCallPath+"' >")
		    .animate({width:'100%', height: '100%'}, 1000);
		    
		  }
	
		  
		  $(".bigPictureWrapper").on("click", function(e){
			    $(".bigPicture").animate({width:'0%', height: '0%'}, 1000);
			    
			    setTimeout(function(){
			      $('.bigPictureWrapper').hide();
			    }, 1000);
		  });
		  
	});

</script>






<%@include file="../includes/footer.jsp"%>





