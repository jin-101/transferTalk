function getContainerDataFromParameters() {
	let data = {};
	let params = location.search.substring(location.search.indexOf("?") + 1).split("&");
	for (let i = 0; i < params.length; ++i) {
		let tmp = params[i].split("=");
		const val =  decodeURIComponent(tmp[1]).replace(" TOP 5","");
		data[tmp[0]] = val;
	}
	return data;
}

callAjax({
	url :PATH +"/transfer/country/leagues",
	data : getContainerDataFromParameters(),
	success: function(res){
		optionTagCreateFunction(res, document.querySelector("#map-league"));
	},
})

$("#map-league").change(function(){
	const leagueName = $(this).val();
	callAjax({
		url :PATH +"/transfer/country/league/teams",
		data :{
			league: leagueName
		},
		beforeSend : () => removeOriginElement(["#map-team"]),
		success: function(res){
			optionTagCreateFunction(res, document.querySelector("#map-team"));
		},
	});
	
});

$("#map-team").change(function(){
	const leagueValue = $(document.querySelector("#map-league")).val();
	const leagueName = leagueValue ==="none" ? null : leagueValue;
	const teamName = $(this).val();
	console.log(leagueName,teamName)
	callTransferList({
		containerData : {
			year:2022,
			top5: false,
			isSummary:false,
			team:teamName,
			league:leagueName
		}
	});
	document.querySelector("#search-content").style.display = 'block';
});

$(".leagueSearch-year").change(function(){
		const selectYear = $(this).val() == 'none' ? 2022 : $(this).val();
		const leagueValue = $(document.querySelector("#map-league")).val();
		const teamValue = $(document.querySelector("#map-team")).val();
		const leagueName = leagueValue ==="none" ? null : leagueValue;
		const teamName = teamValue ==="none" ? null : teamValue;
		console.log(teamName,leagueName,selectYear)
		transfers = callTransferList({
			containerData: {
				year:selectYear,
				top5: false,
				isSummary:false,
				team:teamName,
				league:leagueName
			}
		});
})