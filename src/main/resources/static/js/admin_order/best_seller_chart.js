$(document).ready(()=>{
    console.log($("#pdata").data("pdata"));
    var plabel=[];
    var pdata=[];
    console.log(typeof $("#pdata").data("pdata"))

    $("#pdata").data("pdata").forEach((item)=>{
        plabel.push(item.name+'['+item.art+']');
        pdata.push(item.volume);
    });

    console.log(plabel)
    console.log(pdata)


    console.log($("#adata").data("adata"));
    var alable=[];
    var adata=[];




})




// 상품차트
const pctx = $("#prodChart");
const prodChart = new Chart(pctx, {
    type: 'bar',//차트 종류를 지정. bar/bubble/pie/doughnut/line 등
    data: {//차트 데이터를 넣는다. labels는 축 제목, datasets는 각 축에 들어갈 데이터, 색이나 두께 같은 꾸밈 요소지정
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange','brown','teal','aqua','chartreuse'],
        datasets: [{
            label: 'Top 10 of Product',
            data: [12, 19, 3, 5, 2, 3, 7, 9, 25, 4],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(165, 42, 42, 0.2)',
                'rgba(0, 128, 128, 0.2)',
                'rgba(0, 255, 255, 0.2)',
                'rgba(127, 255, 0, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(165, 42, 42, 1)',
                'rgba(0, 128, 128, 1)',
                'rgba(0, 255, 255, 1)',
                'rgba(127, 255, 0, 1)'
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

//앨범차트
const actx = $("#albumChart");
const albumChart = new Chart(actx, {
    type: 'bar',//차트 종류를 지정. bar/bubble/pie/doughnut/line 등
    data: {//차트 데이터를 넣는다. labels는 축 제목, datasets는 각 축에 들어갈 데이터, 색이나 두께 같은 꾸밈 요소지정
        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange','brown','teal','aqua','chartreuse'],
        datasets: [{
            label: 'Top 10 of Album',
            data: [12, 19, 3, 5, 2, 3, 7, 9, 25, 4],
            backgroundColor: [
                'rgba(255, 99, 132, 0.2)',
                'rgba(54, 162, 235, 0.2)',
                'rgba(255, 206, 86, 0.2)',
                'rgba(75, 192, 192, 0.2)',
                'rgba(153, 102, 255, 0.2)',
                'rgba(255, 159, 64, 0.2)',
                'rgba(165, 42, 42, 0.2)',
                'rgba(0, 128, 128, 0.2)',
                'rgba(0, 255, 255, 0.2)',
                'rgba(127, 255, 0, 0.2)'
            ],
            borderColor: [
                'rgba(255, 99, 132, 1)',
                'rgba(54, 162, 235, 1)',
                'rgba(255, 206, 86, 1)',
                'rgba(75, 192, 192, 1)',
                'rgba(153, 102, 255, 1)',
                'rgba(255, 159, 64, 1)',
                'rgba(165, 42, 42, 1)',
                'rgba(0, 128, 128, 1)',
                'rgba(0, 255, 255, 1)',
                'rgba(127, 255, 0, 1)'
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