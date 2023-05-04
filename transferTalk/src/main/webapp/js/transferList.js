const transferYearSelector = document.querySelector("#transfer-year");
optionTagCreateFunction(makeYearArray(),transferYearSelector);
const isTransferList = location.pathname.indexOf('/transferList')!== -1;
let transfers;


function makeYearArray(){
	const array = [];
	const start = 1992;
	const end = new Date().getFullYear();
	for(i=start; i<end; i++){
		array.push(i)
	}
	return array;
}

function callTransferList({
	containerData = {}
}) {
	return callAjax({
		url: getContextPath() + "/transfer/summary",
		data: {
			top5: false,
			isSummary:false,
			...containerData
		},
		//async: false,
		beforeSend: () => {
				const doc = document.querySelectorAll(".transfer-contents-content");
				for (const elem of doc) {
				    elem.remove();
				}
		},
		success: function(data) {
			const transferDivElements = [$(".transfer-contents-in"),$(".transfer-contents-out")];
			data.forEach(function(transfers, index) {
				if(transfers.length == 0){ //값이 없을 때
					const contentEl =document.createElement("div");
					const noData = document.createElement("div");
					noData.innerText = "선수 정보가 없습니다.";
					contentEl.className = "transfer-contents-content";
					noData.className = "transfer-noData-style";
					contentEl.append(noData)
					transferDivElements[index].append(contentEl);	
				}
				transfers.forEach(function(item) {
					const contentEl =document.createElement("div");
		            transferDivElements[index].append(contentEl);
		            contentEl.className="transfer-contents-content"
					contentEl.setAttribute ("value",item.player.player_id);
					
					const playerName = document.createElement("div");
					playerName.className = "transfer-name";
					playerName.innerText = item.player.player_name;

					const fee = document.createElement("div");
					fee.innerText = item.fee;
					fee.className = "transfer-fee";

					const previousTeam = document.createElement("div");
					previousTeam.innerText = item.previous_team.team_name;
					previousTeam.className = "transfer-previous-team";

					const newTeam = document.createElement("div");
					newTeam.innerText = item.new_team.team_name;
					newTeam.className = "transfer-new-team";

					const age = document.createElement("div");
					age.innerText = item.age;
					age.className = "transfer-age";

					const player_position = document.createElement("div");
					player_position.innerText = item.player_position;
					player_position.className = "transfer-position";

					const transfer_year = document.createElement("div");
					transfer_year.innerText = item.transfer_year;
					transfer_year.className = "transfer-year";

					contentEl.append(playerName);
					contentEl.append(fee);
					contentEl.append(previousTeam);
					contentEl.append(newTeam);
					contentEl.append(age);
					contentEl.append(player_position);
					contentEl.append(transfer_year);
					
					contentEl.addEventListener("click",function(){
						location.href = PATH +"/player/detail?playerId=" + this.getAttribute("value");
						});
					});
				});
		
		}
	});
}

function getContainerDataFromParameters(year) {
	let data = {};
	let params = location.search.substring(location.search.indexOf("?") + 1).split("&");
	for (let i = 0; i < params.length; ++i) {
		let tmp = params[i].split("=");
		data[tmp[0]] = tmp[1];
	}
	data['year'] = year;
	return data;
}

//transferList로 들어왔을 때,
if(isTransferList){
	 transfers = callTransferList({
			containerData: getContainerDataFromParameters(2022)
	});
}

$("#transfer-in-show-btn").click(function() {
	$(".transfer-contents-out").hide();
	$(".transfer-contents-in").show();
});

$("#transfer-out-show-btn").click(function() {
	$(".transfer-contents-in").hide();
	$(".transfer-contents-out").show();
	
});

$("#transfer-all-show-btn").click(function() {
	$(".transfer-contents-in").hide();
	$(".transfer-contents-out").hide();
	$(".transfer-contents-in").show();
	$(".transfer-contents-out").show();
	
});

if(isTransferList){
	$("#transfer-year").change(function(){
		const selectYear = $(this).val() == 'none' ? 2022 : $(this).val();
		transfers = callTransferList({
			containerData: getContainerDataFromParameters(selectYear)
		});
	})
}