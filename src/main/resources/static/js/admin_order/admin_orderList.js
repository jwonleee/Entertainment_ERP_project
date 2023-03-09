
   
// 테스트 더미데이터
var arr = ['testno', 'testname', 'textcategory', 'testcnt', 'testsize', 'testdate', 'testprice', 'testcomapny'];
	var tdarr = [];
	for (let i = 1; i <= 7; i++) {
		var arr2 = [];
		arr2 = arr.map(item => item + i);
		tdarr = [...tdarr, arr2];
	}
	tdarr = [arr, ...tdarr];
	const tdmap = [];
	for (let i = 0; i < tdarr.length; i++) {
		tdmap[i] = [tdarr[i].map((item, index) => `<td key=${index} style="border:1px solid #777;"><a class="toDetail" href="/order/orderDetail" style="text-decoration:none; color:black">${item}</a></td>`)];
	}
	const trmap = tdmap.map((item, index) => `<tr key=${index} style="border: 1px solid #777">${item}</tr>`);

	$("#tbody2").append(trmap);
