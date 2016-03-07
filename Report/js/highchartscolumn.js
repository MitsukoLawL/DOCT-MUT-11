$(function () {
    var testTot = $('.totTest').text();
    var testSuc = $('.successTest').text();

    var iter = $('.iter').text();

    console.log(iter);
    var tot = testTot.split("");
    var suc = testSuc.split("");
    var iter = iter.split(" :");
    console.log(iter);

    for(var i=0; i<tot.length;i++) tot[i] = parseInt(tot[i]);
    for(var i=0; i<suc.length;i++) suc[i] = parseInt(suc[i]);

    $('#containerColumn').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Comparaison du nombre de tests totaux et de tests réussis'
        },
        subtitle: {
            text: 'Source: Groupe11'
        },
        xAxis: {
            categories: iter,
            //,categories: [
            //     'Op12 - 100',
            //     'Op1 - 100',
            //     'Mar',
            //     'Apr',
            //     'May',
            //     'Jun',
            //     'Jul',
            //     'Aug',
            //     'Sep',
            //     'Oct',
            //     'Nov',
            //     'Dec'
            // ],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Nb test'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f}</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Tests Total',
            // data: [parseInt(testTot/10000), 71.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1, 106.6, 92.3],
            data: tot,
            color: '#1b192e'
        }, {
            name: 'Test success',
            data: [49.9, 78.8, 98.5, 93.4, 106.0, 84.5, 105.0, 104.3, 91.2, 83.5, 95.6, 54.4],
            data: suc,
            color: '#FF543A'
        }]
    });
});