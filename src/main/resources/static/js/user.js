 let index = {
	init:function(){
		$("#btn-save").on("click",()=>{ //function(){}, ()=> this를 바인딩 하기위해서 !!
			this.save();
		});
		$("#btn-login").on("click",()=>{ //function(){}, ()=> this를 바인딩 하기위해서 !!
			//this.login();
		});
 	},
 	save:function(){
		 //alert("user의 save함수 호출됌");
		 	let data = {
				 username:$("#username").val()
				 ,password:$("#password").val()
				 ,email:$("#email").val()
			 }
			 
		  // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
          $.ajax({
                type : "POST",            			 // HTTP method type(GET, POST) 형식이다.
                url : "/auth/joinProc",    			 // 컨트롤러에서 대기중인 URL 주소이다.
                data: JSON.stringify(data),  	 // http body 데이터
                contentType:"application/json;charset=utf-8;", // body 데이터가 MIMEㅏ
                dataType:"json", //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 오브젝트
            }).done(function(resp){
				alert("회원가입 완료");
				alert(resp);
				location.href = "/";
			}).fail(function(erro){
				alert(JSON.stringify(daerrota));
			});
			 // 디폴트가 비동기 호출
			
	 },
	 
 	login:function(){
		 //alert("user의 save함수 호출됌");
		 	let data = {
				 username:$("#username").val()
				 ,password:$("#password").val()
			 }
			 
		  // ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
          $.ajax({
                type : "POST",            			 // HTTP method type(GET, POST) 형식이다.
                url : "/api/user/login",    			 // 컨트롤러에서 대기중인 URL 주소이다.
                data: JSON.stringify(data),  	 // http body 데이터
                contentType:"application/json;charset=utf-8;", // body 데이터가 MIMEㅏ
                dataType:"json", //요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열(생긴게 json이라면) => javascript 오브젝트
            }).done(function(resp){
				alert("로그인 완료");				
				location.href = "/";
			}).fail(function(erro){
				alert(JSON.stringify(daerrota));
			});
			 // 디폴트가 비동기 호출
			
	 }	 
}

index.init();