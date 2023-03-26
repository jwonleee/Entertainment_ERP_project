$(document).ready(() => {
    //상품
    var plabel = [];
    var pdata = [];

    var topP = $("#pdata").val();
    topP = topP.replace("[", "");
    topP = topP.replace("]", "");
    var topParr = topP.split("}, ");
    for (var i = 0; i < topParr.length - 1; i++) {
        topParr[i] += '}';
    }
    var ProdArray = "[";
    topParr.forEach((item) => {
        item = item.replaceAll('=', ':"');
        item = item.replaceAll(',', '",');
        item = item.replaceAll('}', '"}');
        item = item.replaceAll('volume', '"volume"');
        item = item.replaceAll('art', '"art"');
        item = item.replaceAll('name', '"name"');
        ProdArray = ProdArray + item + ",";

    })
    ProdArray = ProdArray.substring(0, ProdArray.lastIndexOf(','));
    ProdArray = ProdArray + ']';
    ProdArray = JSON.parse(ProdArray);//여기까지가 json파일로 형변환

    ProdArray.forEach((item) => {
        var str = item.name + '[' + item.art + ']';
        plabel.push(str);
        pdata.push(Number(item.volume));
    })


    // 상품차트
    const pctx = $("#prodChart");
    const prodChart = new Chart(pctx, {
        type: 'bar',//차트 종류를 지정. bar/bubble/pie/doughnut/line 등
        data: {//차트 데이터를 넣는다. labels는 축 제목, datasets는 각 축에 들어갈 데이터, 색이나 두께 같은 꾸밈 요소지정
            labels: plabel,
            datasets: [{
                label: 'Top 10 of Product',
                data: pdata,
                backgroundColor: [
                    'rgba(127, 255, 0, 0.2)',
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                    'rgba(75, 192, 192, 0.2)',
                    'rgba(153, 102, 255, 0.2)',
                    'rgba(255, 159, 64, 0.2)',
                    'rgba(165, 42, 42, 0.2)',
                    'rgba(0, 128, 128, 0.2)',
                    'rgba(0, 255, 255, 0.2)'
                ],
                borderColor: [
                    'rgba(127, 255, 0, 1)',
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                    'rgba(75, 192, 192, 1)',
                    'rgba(153, 102, 255, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(165, 42, 42, 1)',
                    'rgba(0, 128, 128, 1)',
                    'rgba(0, 255, 255, 1)'
                ],
                borderWidth: 2
            }]
        },
        options: {//차트 모양 꾸미기
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });


//////////////////////////////////////////////////////////////////////////////


    //앨범
    var alable = [];
    var adata = [];
    var topA = $("#adata").val();

    topA = topA.replace("[", "");
    topA = topA.replace("]", "");
    var topAarr = topA.split("}, ");
    for (var i = 0; i < topAarr.length - 1; i++) {
        topAarr[i] += '}';
    }
    var AlbumeArray = "[";
    topAarr.forEach((item) => {
        item = item.replaceAll('=', ':"');
        item = item.replaceAll(',', '",');
        item = item.replaceAll('}', '"}');
        item = item.replaceAll('volume', '"volume"');
        item = item.replaceAll('art', '"art"');
        item = item.replaceAll('name', '"name"');
        AlbumeArray = AlbumeArray + item + ",";
    })
    AlbumeArray = AlbumeArray.substring(0, AlbumeArray.lastIndexOf(','));
    AlbumeArray = AlbumeArray + ']';
    AlbumeArray = JSON.parse(AlbumeArray);//여기까지가 json파일로 형변환

    AlbumeArray.forEach((item) => {
        var str = item.name + '[' + item.art + ']';
        alable.push(str);
        adata.push(Number(item.volume));
    });




    //앨범차트
    const actx = $("#albumChart");
    const albumChart = new Chart(actx, {
        type: 'bar',//차트 종류를 지정. bar/bubble/pie/doughnut/line 등
        data: {//차트 데이터를 넣는다. labels는 축 제목, datasets는 각 축에 들어갈 데이터, 색이나 두께 같은 꾸밈 요소지정
            labels: alable,
            datasets: [{
                label: 'Top 10 of Album',
                data: adata,
                backgroundColor: [
                    'red',
                    'orange',
                    'yellow',
                    'green',
                    'blue',
                    'navy',
                    'violet',
                    'gray',
                    'aqua',
                    'yellowgreen'
                ],
                borderColor: [
                    'red',
                    'orange',
                    'yellow',
                    'green',
                    'blue',
                    'navy',
                    'violet',
                    'gray',
                    'aqua',
                    'yellowgreen'
                ],
                borderWidth: 2
            }]
        },
        options: {//차트 모양 꾸미기
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        } 
       
            
    });

});