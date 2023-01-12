<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>


	<div class="row">
	  <div class="col-lg-12">
	    <h1 class="page-header">Board Modify</h1>
	  </div>
	  <!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	
	<div class="row">
	  <div class="col-lg-12">
	    <div class="panel panel-default">
	
	      <div class="panel-heading">Board Modify</div>
	      <!-- /.panel-heading -->
	      <div class="panel-body">
			<form role="form" action="/board/modify" method="post">
				<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
		        <input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
			    <input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
				<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'>
      
	<div class="form-group">
	  <label>Bno</label> 
	  <input class="form-control" name='bno' 
	     value='<c:out value="${board.bno }"/>' readonly="readonly">
	</div>
	
	<div class="form-group">
	  <label>Title</label> 
	  <input class="form-control" name='title' 
	    value='<c:out value="${board.title }"/>' >
	</div>
	
	<div class="form-group">
	  <label>Text area</label>
	  <textarea class="form-control" rows="3" name='content' ><c:out value="${board.content}"/></textarea>
	</div>
	
	<div class="form-group">
	  <label>Writer</label> 
	  <input class="form-control" name='writer'
	    value='<c:out value="${board.writer}"/>' readonly="readonly">            
	</div>
	
	<div class="form-group">
	  <label>RegDate</label> 
	  <input class="form-control" name='regDate'
	    value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.regdate}" />'  readonly="readonly">            
	</div>
	
	<div class="form-group">
	  <label>Update Date</label> 
	  <input class="form-control" name='updateDate'
	    value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.updateDate}" />'  readonly="readonly">            
	</div>
	
	          
	
	  <button type="submit" data-oper='modify' class="btn btn-default">Modify</button> <!-- submit => form 태그의 action속성의 페이지로 이동함 -->
	  <button type="submit" data-oper='remove' class="btn btn-danger">Remove</button>
	  <button type="submit" data-oper='list' class="btn btn-info">List</button> 
	  <!-- 다 같은 submit타입 버튼이라 같은 결과가 나온다. 여기서는  스크립트를 이용해서 차별 결과를 만듦 -->
	</form>
	
	
	      </div>
	      <!--  end panel-body -->
	
	    </div>
	    <!--  end panel-body -->
	  </div>
	  <!-- end panel -->
	</div>
	<!-- /.row -->
	
<script >

	$(function () {
		var formObj = $("form"); /* form태그의 정보를 formObj변수로 선언 */
		
		$("button").on("click" , function(e) { // 버튼을 눌렀을 때 이벤트 발생
			e.preventDefault();  /* submit 기능을 제거 */
			
			var operation = $(this).data("oper") /* this는 이벤트가 발생한 태그를 가르킴(button) */
			
			console.log(operation);
			
			if(operation == 'remove'){
				formObj.attr("action" , "/board/remove"); /* action 값 변경해주기 */
				
			}else if(operation == 'list'){
				formObj.attr("action" , "/board/list").attr("method","get"); /* 전달 방식(post -> get)까지 같이 바꿔준다(controller에서 get으로 받는 메소드기 때문) */
				var pageNumTag = $("input[name='pageNum']").clone();
				var amountTag = $("input[name='amount']").clone();
				var keywordTag = $("input[name='keyword']").clone();
				var typeTag = $("input[name='type']").clone();
				
				formObj.empty(); /* 태그만 남기고 다 제거 (차이점 : remove는 태그 자체를 제거 하는 것)*/
				formObj.append(pageNumTag);
				formObj.append(amountTag);
			    formObj.append(keywordTag);
			    formObj.append(typeTag);				
			}
			
			formObj.submit();
		});
		
	});

</script>

<%@include file="../includes/footer.jsp"%>