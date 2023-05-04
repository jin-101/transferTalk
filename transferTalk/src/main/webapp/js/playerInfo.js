function getContainerDataFromParameters() {
	let data = {};
	let params = location.search.substring(location.search.indexOf("?") + 1).split("&");
	for (let i = 0; i < params.length; ++i) {
		let tmp = params[i].split("=");
		data[tmp[0]] = tmp[1];
	}
	return data;
}

callAjax({
	url: getContextPath() + "/player/img",
	data: getContainerDataFromParameters(),
	success: (res) => {
		const imgSrc = res || getContextPath() + "/images/defaultPlayer.webp";
		console.log(imgSrc);
		const parent = document.querySelector("#picture_player");
		const imgEl = document.createElement("img");
		imgEl.setAttribute("src", imgSrc);
		parent.appendChild(imgEl);
	},
})

function myFunction(isFavorite) {
	const user_id = localStorage.getItem("loginUserId"); // 사용자 ID
	const player_id = new URLSearchParams(location.search).get('playerId'); // 선수 ID
	const star = document.querySelector('.starImg');
	if (star.style.color == 'yellow') {
		star.style.color= 'white';
		callAjax({
			url: getContextPath() + "/user/favoritePlayer",
			type: "POST",
			data: {
				user_id: user_id,
				player_id: player_id,
				isAdd: false
			},
			success: function(response) {
				console.log("데이터 저장 완료");
			},
			error: function(xhr, status, error) {
				console.error("에러 발생:", error);
			}

		});
	} else {
		star.style.color= 'yellow';
		callAjax({
			url: getContextPath() + "/user/favoritePlayer",
			type: "POST",
			data: {
				user_id: user_id,
				player_id: player_id,
				isAdd: true
			},
			success: function(response) {
				console.log("데이터 저장 완료",response);
			},
			error: function(xhr, status, error) {
				console.error("에러 발생:", xhr, status, error);
			}

		});
	}

}

/*callAjax({
	url : getContextPath() + "/player/img",
	data: getContainerDataFromParameters(),
	success : (res)=>{
		const imgSrc = res || getContextPath() + "/images/defaultTeam.webp";
		console.log(imgSrc);
		const parent = document.querySelector("#picture_previousteam");
		const imgEl = document.createElement("img");
		imgEl.setAttribute("src",imgSrc);
		parent.appendChild(imgEl);
	},
})
callAjax({
	url : getContextPath() + "/player/img",
	data: getContainerDataFromParameters(),
	success : (res)=>{
		const imgSrc = res || getContextPath() + "/images/defaultTeam.webp";
		console.log(imgSrc);
		const parent = document.querySelector("#picture_newteam");
		const imgEl = document.createElement("img");
		imgEl.setAttribute("src",imgSrc);
		parent.appendChild(imgEl);
	},
})*/