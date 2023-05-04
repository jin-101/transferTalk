// 아이디 중복체크 기능
$(".register-id-check").click(function(){
	const userid = $('#userid').val();
	console.log(userid);
	callAjax({
		url:  getContextPath() + "/login/duplicateIdCheck",
		method : "get",
		data: { 
			user_id: userid 
		},
		success: function(result) {
			console.log(result);
			if (result === 0) {
				$('#checkMessage').html('사용할 수 있는 아이디입니다.');
				$('#checkType').attr('class', 'modal-content panel-success');
			}
			else {
				$('#checkMessage').html('사용할 수 없는 아이디입니다.');
				$('#checkType').attr('class', 'modal-content panel-warning');
			}
			$('#checkModal').modal("show");
		},
		error: function(err){
			console.log(err);
		}
	})
});

/*$("#signup-btn").click(function(){
	console.log('singup btn');
	callAjax({
		url: getContextPath() + '/login/signup',
		method : "post",
		success: function(res) {
			console.log(res);
		},
		error: function(err){
			console.log(err);
		}
	})
});*/

/*function registerCheckfunction() {
	var userid = $('#userid').val();
	$.ajax({
		type: 'POST',
		url: './UserRegisterCheckServlet',
		data: { userid: userid },
		success: function(result) {
			if (result == 1) {
				$('#checkMessage').html('사용할 수 있는 아이디입니다.');
				$('#checkType').attr('class', 'modal-content panel-success');
			}
			else {
				$('#checkMessage').html('사용할 수 없는 아이디입니다.');
				$('#checkType').attr('class', 'modal-content panel-warning');
			}
			$('#checkModal').modal("show");
		}
	})
}
*/
