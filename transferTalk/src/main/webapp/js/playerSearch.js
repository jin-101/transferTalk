//input창에 입력이 들어와서 검색 버튼을 눌렀을때
//ajax요청
$(".btn-search").click(function(){
	$(".search-result").empty();
	const inputData =$("input[type=text]").val();
	if(inputData === '') return;
	callAjax({
		url : getContextPath() + "/player/searchContents",
		data :{
			playerName: $("input[type=text]").val()
		},
		success : function(response){
			console.log(response);
			$.each(response, function(index, player){
				console.log(player);
				var div = $("<div>").attr("value",player.player_id);
				var img = $("<img>").attr("src",player.img_src).addClass('elMargin-right');
				var name = $("<span>").text(player.player_name).addClass('elMargin-right');
				var line = $('<span>').text('|')
				var country = $("<span>").text(player.player_nationality).addClass('elMargin-right');
				
				div.append(img);
				div.append(name);
				div.append(line);
				div.append(country);
				$(div).click(function(){
					location.href = PATH +"/player/detail?playerId="+ this.getAttribute("value");
				});
				$(".search-result").append(div);
			
			});
		} 
	});
});

