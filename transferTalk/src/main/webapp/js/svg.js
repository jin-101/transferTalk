SVGElement = Snap('#frame');
const g = SVGElement.g();
const viewWH = {w:800,h:500}
const imgWH = {w:1110,h:1280};

const bg = g.image(PATH+"/images/EuropeMap.svg",(viewWH.w-imgWH.w)/2,(viewWH.h-imgWH.h)/2,imgWH.w,imgWH.h);
const nations = [
	{'nation':'노르웨이', 'transform':[200,100], 'country':'Norway'},
	{'nation':'스웨덴', 'transform':[320,200], 'country':'Sweden'},
	{'nation':'핀란드', 'transform':[550,50], 'country':'Finland'},
	{'nation':'잉글랜드', 'transform':[-40,350], 'country':'England'},
	{'nation':'아일랜드', 'transform':[-160,380], 'country':'Ireland'},
	{'nation':'포르투갈', 'transform':[-160,780], 'country':'Portugal'},
	{'nation':'스페인', 'transform':[-40,780], 'country':'Spain'},
	{'nation':'프랑스', 'transform':[80,580], 'country':'France'},
	{'nation':'스위스', 'transform':[200,585], 'country':'Switzerland',fontSize:'10'},
	{'nation':'이탈리아', 'transform':[270,700], 'country':'Italy'},
	{'nation':'벨기에', 'transform':[130,476], 'country':'Belgium',fontSize:'10'},
	{'nation':'네덜란드', 'transform':[140,416], 'country':'Netherlands',fontSize:'10'},
	{'nation':'독일', 'transform':[240,460], 'country':'Germany'},
	{'nation':'체코', 'transform':[345,500], 'country':'Czech Republic'},
	{'nation':'오스트리아', 'transform':[305,565], 'country':'Austria',fontSize:'13'},
	{'nation':'슬로베니아', 'transform':[320,605], 'country':'Slovenia',fontSize:'8'},
	{'nation':'폴란드', 'transform':[420,410], 'country':'Poland'},
	{'nation':'우크라이나', 'transform':[620,500], 'country':'Ukraine'},
	{'nation':'헝가리', 'transform':[419,572], 'country':'Hungary'},
	{'nation':'크로아티아', 'transform':[370,620], 'country':'Croatia',fontSize:'8'},
	{'nation':'그리스', 'transform':[480,800], 'country':'Greece'},
	{'nation':'루마니아', 'transform':[520,610], 'country':'Romania'},
	{'nation':'세르비아', 'transform':[450,660], 'country':'Serbia'},
	{'nation':'덴마크', 'transform':[210,290], 'country':'Denmark'},
	{'nation':'터키', 'transform':[750,800], 'country':'Turkey'},
	{'nation':'슬로바키아', 'transform':[419,525], 'country':'Slovakia',fontSize:'8'}
]
//g.transform('t'+[50,-100]+' s'+0.8);
g.transform('t'+[50,-400]+' s'+1.5);
const group=[]
nations.forEach((el,i) => {
	group[i] = g.g();
	const g1=group[i].g();
	const g2=group[i].g().addClass(el.country);
		const text = g2.text(el.transform[0],el.transform[1],el.nation).attr({
			"fill":"#FFFFFF",
			"fontSize": el.fontSize || '15' +'px'
		});
		const bbox = g2.getBBox();
		const gap = 20;
		const btnGap =3;
		g1.rect(bbox.cx-(bbox.w+gap)/2 +btnGap, bbox.cy-(bbox.h+gap)/2+btnGap, bbox.w+gap, bbox.h+gap,gap,gap).attr({
			"fill":"#0062cc"
		});
		g2.rect(bbox.cx-(bbox.w+gap)/2, bbox.cy-(bbox.h+gap)/2, bbox.w+gap, bbox.h+gap,gap,gap).attr({
			"fill":"#0069d9"
		});
		text.appendTo(g2);
		g2.click((e)=>{
			g2.attr({"pointer-events":"none"})
			g2.animate({
				transform: g2.transform().local + 't'+[3,3]
			},200,function(){
				g2.animate({
					transform: g2.transform().local + 't'+[-3,-3]
				},200,function(){
					location.href = PATH +"/transfer/country/searchLeague?country="+ g2.node.getAttribute('class');
				});
			});
		})
})
let pointX;
let pointY;
g.drag(function(dx,dy,x,y,e){
	g.transform(g.transform().local+'t'+[x-pointX, y-pointY]);
	pointX = x;
	pointY = y;
},function(x,y,e){
	pointX = x;
	pointY = y;
	//console.log(x,y,e)
},function(e){
    //console.log(e)
});

/*fragment (g,bg,(img)=>{
	const aaa =[...img.node.children[0].querySelectorAll("path")]
	console.log(aaa[0])
	aaa[0].fill = "#f3f3f3";
})
function fragment (g,img,callback){
		const myImgG = g.g();
		const myImg = myImgG.g()
		nations.forEach(el => {
		myImg.text(el.transform[0],el.transform[1],el.nation).attr({"fill":"#000000"})
	})
	Snap.load(PATH+"/images/EuropeMap.svg", function(frag){
		myImg.append(frag);
		callback(myImgG);
	})
}*/