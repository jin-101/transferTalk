const callSummary = function ({
	containerData = {},
	containerIndex = 0,
	loadingStart = true, //23.04.25 jin 추가
	loadingEnd = true, //23.04.25 jin 추가
}) {
	callAjax({
		url: PATH +"/transfer/summary",
		data: {
			top5:true,
			isSummary:true,
			...containerData
		},
		loadingStart: loadingStart,
		loadingEnd : loadingEnd,
		beforeSend:function(){
			document.querySelectorAll(".summary-contents").forEach(parent=>{
				while(parent.children.length > 1){
					parent.removeChild(parent.lastChild);
				}
			})
			
		},
		success:function(data) {
			const parentElement = $(".summary-contents");
			if(data.length == 0){
				const noData = document.createElement("div");
				noData.innerText = "선수 정보가 없습니다.";
				noData.className = "grid-noData-style";
				parentElement.get(containerIndex).append(noData);
			}else {
				data.forEach(function(item,i) {
					const contentEl =document.createElement("div");
		            parentElement.get(containerIndex).append(contentEl);
		            contentEl.className="summary-contents-content"
					contentEl.setAttribute ("value",item.player.player_id);
					const playerRank = document.createElement("div");
					playerRank.innerText = i+1;
					playerRank.className = "summary-rank";
					const playerName = document.createElement("div");
					playerName.className = "summary-name";
					playerName.innerText = item.player.player_name;
					const fee = document.createElement("div");
					fee.innerText = item.fee;
					fee.className = "summary-fee";
					const previousTeam = document.createElement("div");
					previousTeam.innerText = item.previous_team.team_name;
					previousTeam.className = "summary-previous-team";
					const newTeam = document.createElement("div");
					newTeam.innerText = item.new_team.team_name;
					newTeam.className = "summary-new-team";
					const age = document.createElement("div");
					age.innerText = item.age;
					age.className = "summary-age";
					
					contentEl.append(playerRank);
				  	contentEl.append(playerName);
				  	contentEl.append(fee);
				  	contentEl.append(previousTeam);
				  	contentEl.append(newTeam);
				  	contentEl.append(age);
				  	
				  	contentEl.addEventListener("click",function(){
						location.href = PATH +"/player/detail?playerId=" + this.getAttribute("value");
					});
				});
			}
		}
	});
}


const totalSummaryArray = [
		{f:callSummary, data:{loadingEnd:false}},
		{f:callSummary, data:{containerData:{'year':'2022'},containerIndex:1, loadingStart:false}}
];
	
function callLeagueSummary (){
	const leagueName = $(this).val();
	const array = [
		{f:callSummary, data:{ containerData:{'league':leagueName}, loadingStart:false, loadingEnd:false}},
		{f:callSummary, data:{ containerData:{'year':'2022','league':leagueName}, containerIndex : 1, loadingStart:false}}
	];
	return array;
}
function callTeamSummary (){
	const teamName = $(this).val();
	const array = [
		{f:callSummary, data:{ containerData:{team:teamName}, loadingEnd:false}},
		{f:callSummary, data:{ containerData:{year:'2022', team:teamName}, containerIndex:1, loadingStart:false}}
	];
	return array;
}

document.querySelectorAll(".summary-detail-container").forEach((element,index)=>{
	element.querySelector("button").addEventListener('click',()=>{
		const titleText = element.parentElement.querySelector(".summary-category").innerHTML;
		console.log(index, titleText);
		location.href = PATH + "/transfer/summaryDetail?index="+index+"&title="+titleText;
	});
});


$(".summary-contents-content").click(function(){
	console.log('....')
	console.log(this.getAttribute("value"))
	/*location.href = PATH + "/player/" + item.player.player_id;*/
});
//다수의 ajax가 실행되고 화면이 보여지도록 refactoring : 23.04.25 jin
if(isIndex) promiseAjax([...totalSummaryArray ]);