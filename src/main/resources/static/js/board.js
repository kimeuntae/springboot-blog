 let index = {
	init:function(){
		$("#btn-board-save").on("click",()=>{ //function(){}, ()=> this를 바인딩 하기위해서 !!
			this.save();
		});
		$("#btn-board-delete").on("click",()=>{
			this.deleteByid();
		})
		$("#btn-board-update").on("click",()=>{
			this.update();
		})	
		$("#btn-reply-save").on("click",()=>{
			this.replySave();
		})					
	},	
 	save:function(){
		 	let data = {
				 title:$("#title").val()
				 ,content:$("#content").val()
			 }
		  // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
          $.ajax({
                type : "POST",            			 	// HTTP method type(GET, POST) 형식이다.
                url : "/api/board",    			 		// 컨트롤러에서 대기중인 URL 주소이다.
                data: JSON.stringify(data),  	 		// http body 데이터
                contentType:"application/json;charset=utf-8;", // body 데이터가 MIMEㅏ
                dataType:"json",							//요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 오브젝트
            }).done(function(resp){
				alert("글쓰기 완료");
				location.href = "/";
			}).fail(function(erro){
				alert(JSON.stringify(daerrota));
			});
			 // 디폴트가 비동기 호출
	 },
 	update:function(){
		 	var id = $("#id").val();
		 	let data = {
				 id:id,	 
				 title:$("#title").val(),
				 content:$("#content").val()
			 }
          $.ajax({
                type : "PUT",            			
                url : "/api/board/" + id,    			 
                data: JSON.stringify(data),  	 
                contentType:"application/json;charset=utf-8;",
                dataType:"json",
            }).done(function(resp){
				alert("글수정완료");
				location.href = "/";
			}).fail(function(erro){
				alert(JSON.stringify(daerrota));
			});
			 // 디폴트가 비동기 호출
	 },	 
 	deleteByid:function(){
		  var id = $("#id").text();
          $.ajax({
                type : "DELETE",            			 
                url : "/api/board/" + id,    			
                contentType:"application/json;charset=utf-8;",
                dataType:"json",
            }).done(function(resp){
				alert("삭제가 완료되었습니다.");
				location.href = "/";
			}).fail(function(erro){
				alert(JSON.stringify(daerrota));
			});
			 // 디폴트가 비동기 호출
	 },
 	replySave:function(){
		    let boardId = $("#boardId").val();
		 	let data = {
				 boardId:$("#boardId").val()
				 ,userId:$("#userId").val()
				 ,content:$("#reply-content").val()
			 }
          $.ajax({
                type : "POST",            			
                url : `/api/board/reply`,    			
                data: JSON.stringify(data),  	 
                contentType:"application/json;charset=utf-8;",
            }).done(function(resp){
				alert("댓글 작성이  완료");
				location.href = `/board/${boardId}`;
			}).fail(function(erro){
				alert(JSON.stringify(daerrota));
			});
			 // 디폴트가 비동기 호출
	 },
 	replyDelete:function(boardId,replyId){
		 		   
          $.ajax({
                type : "DELETE",														
                url : `/api/board/${boardId}/reply/${replyId}`,    		// 백틱
                contentType:"application/json;charset=utf-8;", 			
               dataType:"json", 														
            }).done(function(resp){
				alert("댓글 삭제 완료");
				location.href = `/board/${boardId}`;
			}).fail(function(erro){
				alert(JSON.stringify(daerrota));
			});
			 // 디폴트가 비동기 호출
	 }	 
}

index.init();
