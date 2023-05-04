//** 처음 시작 시 호출------------------------------------------------------------------------------------------------------------------------------------------------
//초기 선언 변수
const mediaQueryWidth = 850;
let headerHeight;
const dropdownMenu=['#header-country','#header-league','#header-team'];
const arrowClass = ['.header-selecter-sign.sign1 > img','.header-selecter-sign.sign2 > img'];
const PATH = getContextPath();
const isIndex = ['/','/index.jsp'].includes(location.pathname.replace(PATH,''));

const topFiveLeague = ['Premier League','Bundesliga','LaLiga','Ligue 1','Serie A'];
optionTagCreateFunction(topFiveLeague,document.querySelector(dropdownMenu[1])); // 처음 리그 리스트 만들기


// index.jsp가 아닐때만 초기 header에 country데이터 가져오기
/*if(!isIndex) promiseAjax([callCountry()]);*/

// script시작과 동시에 실행이 되는 것이 아니고 promise함수가 실행될 때 시작하도록 변경 : 23.04.25 jin 수정
/*function callCountry() {
	const obj = {};
	obj.f = callAjax;
	obj.data = {
		url: PATH + "/transfer/country",
		method:"post",
		loadingEnd:!isIndex,
		success: (res) => optionTagCreateFunction(res,document.querySelector(dropdownMenu[0]))
	}
	return obj;
}*/

/*function callLeague(){
	const countryName = $(this).val();
	const obj = {};
	obj.f = callAjax;
	obj.data = {
		url: PATH +"/transfer/country/leagues",
		method:"post",
		data:{
			country : countryName
		},
		beforeSend : () => removeOriginElement ([dropdownMenu[1],dropdownMenu[2]]),
		success: (res) => {
			optionTagCreateFunction(res,document.querySelector(dropdownMenu[1]));
			arrowImgEffect(".header-selecter-sign.sign1 > img");
		}
	}
	return obj;
}*/

function callTeam(){
	const leagueName = $(this).val();
	const obj = {};
	obj.f = callAjax;
	obj.data = {
		url: PATH +"/transfer/country/league/teams",
		method:"post",
		data:{
			league : leagueName
		},
		loadingEnd:!isIndex,
		beforeSend : () => removeOriginElement([dropdownMenu[2]]),
		success: (res) => {
			optionTagCreateFunction(res,document.querySelector(dropdownMenu[2]));
			//arrowImgEffect(".header-selecter-sign.sign1 > img");
			arrowImgEffect(".header-selecter-sign.sign2 > img",0);
		}
	}
	return obj;
}

//** 이벤트 발생시 처리하는 함수------------------------------------------------------------------------------------------------------------------------------------------------
// scroll 위치가 변할 때 작업 함수
window.addEventListener('scroll', function(){
	//상단바 고정하기 변경 : 23.04.25 jin
	const headerHoverContainer= document.querySelector(".header-hover-container");
	headerHeight= document.querySelector("header").clientHeight;
	if(this.scrollY >= headerHeight && this.innerWidth >= mediaQueryWidth){
		headerHoverContainer.style.position = 'fixed';
		headerHoverContainer.style.top = 0;
		headerHoverContainer.style.backgroundColor = '#ACC2FA';
		headerHoverContainer.style.width = '100%';
		headerHoverContainer.style.height = '50px';
	}else if(this.scrollY >= headerHeight && this.innerWidth < mediaQueryWidth){
			headerHoverContainer.style.position = 'fixed';
			headerHoverContainer.style.top = 0;
			headerHoverContainer.style.backgroundColor = '#ACC2FA';
	}else{
		headerHoverContainer.style.position = 'static';
	}
});

// 화면 size가 변할 때 작업 함수
window.addEventListener("resize", function(){
	headerHeight= document.querySelector("header").clientHeight;
	const headerHoverContents= document.querySelector(".header-hover-container");
	if(this.innerWidth < mediaQueryWidth) headerHoverContents.style.height = 'auto';
	else headerHoverContents.style.height = '50px';
});

// country 선택시  header에 leagues데이터 가저오기
/*$("#header-country").change(function(){
    promiseAjax([callLeague.bind(this)()]);
});*/

$(".hover-playerSearch").click(function(){
	location.href = PATH +"/player/searchPage";
});


//league 선택시  header에 teams데이터 가저오기
$("#header-league").change(function(){
	if(isIndex){
		if($(this).val() =='none'){
		  promiseAjax([...callTotalSummary() ]);
		} else {
		  promiseAjax([callTeam.bind(this)(), ...callLeagueSummary.bind(this)()]);
		}
	}else{
		if($(this).val() !='none') promiseAjax([callTeam.bind(this)()]);
	}
	
	if($(this).val() =='none') {
		arrowImgEffect(".header-selecter-sign.sign2 > img", 1);
		removeOriginElement([dropdownMenu[2]]);
	}
	
	/*if(isIndex) promiseAjax([callTeam.bind(this)(), ...callLeagueSummary.bind(this)()]);
	else promiseAjax([callTeam.bind(this)()]);*/
});

//league 선택시  header에 teams데이터 가저오기
$("#header-team").change(function(){
	if(isIndex){
		if($(this).val() =='none'){
		 promiseAjax([...callLeagueSummary.bind($("#header-league"))()]);
		}else{
		 promiseAjax([...callTeamSummary.bind(this)()]);	
		}
	}
	arrowImgEffect(".header-selecter-sign.sign2 > img", $(this).val() =='none' ? 0 : 1);
});

//header에 자세히보기 버튼클릭 시 함수
$(".header-detail-btn").click(function(){
	const leagueValue = $(document.querySelector("#header-league")).val();
	const teamValue =$(document.querySelector("#header-team")).val();
	const leagueName = leagueValue ==="none" ? null : leagueValue;
	const teamName = teamValue === "none" ? null : teamValue;
	let url = PATH + "/transfer/transferList?"
	let needAndChar = false;
	if (leagueName !== null) {
		url += "league=" + leagueName;
		needAndChar = true;
	}
	if (teamName !== null) {
		if (needAndChar) {
			url += "&";
		}
		url += "team=" + teamName;
		needAndChar = true;
	}
	if (needAndChar) {
		url += "&";
	}
	url += "top5=false";
	location.href=url;
});

//home버튼 클릭 시 함수
$(".header-homelogo").click(() => location.href = PATH);

//login버튼 클릭 시 함수
$(".login-container").click(() => {
	location.href = PATH +"/login/loginPage";
});

//logout버튼 클릭 시 함수
$(".logout-container").click(function(){
	location.href = PATH +"/login/logoutPage";
});

//localStorage 로그인 되었는지 상태저장
if($("#loginUserId").val()!==undefined) localStorage.setItem("loginUserId", $("#loginUserId").val());

//login 처리 함수
const loginContainer = document.querySelector(".login-container");
const logoutContainer = document.querySelector(".logout-container");
const myMenu = document.querySelector(".login-item");
if (localStorage.getItem("loginUserId") !== "") { // 로그인시
	loginContainer.classList.add("hidden");
	if(logoutContainer.classList.contains('hidden')){
		logoutContainer.classList.remove("hidden");
	}
	if(myMenu.classList.contains('hidden'))myMenu.classList.remove("hidden");
}else{ // 로그아웃, 미 로그인 시
	logoutContainer.classList.add("hidden");
	if(loginContainer.classList.contains('hidden')){
		loginContainer.classList.remove("hidden");
	}
	myMenu.classList.add("hidden");
}

$(".hover-myFavoritePlayers").click(function() {
	location.href=getContextPath() + "/user/myFavoritePlayers";
});


//** 생성한 함수 ------------------------------------------------------------------------------------------------------------------------------------------------

/* callAjax : ajax 호출함수
 * params url, method, dataType, data(object타입), beforeSend(callback함수), success(callback함수), error(callback함수)
 * retrun : 없음
 */
function callAjax({
	url,
	method="get",
	dataType="json",
	data,
	async = true,
	beforeSend,
	success,
	error,
	loadingStart = true,
	loadingEnd =true
}){
	let result;
	$.ajax({
		url:url,
		method:method,
		dataType:dataType,
		async:async,
		beforeSend : function(){
			if(beforeSend)beforeSend();
	        if(loadingStart) $("#my-spinner").show();
	     },
	    data:data,
		success:function(res){
			result = res;
			//console.log('success',res)
			if(success)success(res);
			if(loadingEnd) $("#my-spinner").hide();
		},
		error:function(err){
			console.warn('error',err)
			if(error)error(err);
			if(loadingEnd) $("#my-spinner").hide();
		}
	});
	return result;
};


/* createElement : 태그 생성함수
 * params : tag(태그이름), appndElement(append할 대상), text(innerHTML값), value(value속성 값)
 * retrun : 없음
 */
function createElement ({
	tag ='div', 
	appndElement, 
	innerText,
	value
}){
	const newTag = document.createElement(tag);
	newTag.setAttribute('value',value);
	newTag.innerHTML = innerText;
	appndElement.appendChild(newTag);
}

/* getContextPath : ContextPath 구하는 함수
 * retrun : 없음
 */
function getContextPath() {
  var hostIndex = location.href.indexOf( location.host ) + location.host.length;
  return location.href.substring( hostIndex, location.href.indexOf('/', hostIndex + 1) );
} 

//다수의 ajax가 순차적 실행되도록 promise함수생성 : 23.04.25 jin
async function promiseAjax(params){
	 params.map(async obj => {
		const data = obj.data || {};
		await obj.f(data);
	})
}


function optionTagCreateFunction(response,parent){
	response.sort();
	response.forEach((el) => {
		createElement({
			tag:'option', 
			appndElement:parent, 
			innerText:el, 
			value:el
		});
	});	
}

function removeOriginElement (array){
	array.forEach(classEl=>{
		 const classElement = document.querySelector(classEl);
		 while (classElement.children.length > 1) {
			    classElement.removeChild(classElement.lastChild);
		 }
	});
}

function arrowImgEffect (element,removeIndex) {
	console.log(element,removeIndex)
	$(element).each((i,item)=>{
		   if(i==removeIndex) $(item).removeClass('selected');
		   else $(item).addClass('selected');
	});
}