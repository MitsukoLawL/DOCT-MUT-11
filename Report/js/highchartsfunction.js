$(function () {

    var mutantT = $('#mutantT').text();
    var mutantV = $('#mutantV').text();
    var mutantMN = $('#mutantMN').text();

    $(document).ready(function () {

        // Build the chart
        $('#containerHigh').highcharts({
            chart: {
                plotBackgroundColor: null,
                plotBorderWidth: null,
                plotShadow: false,
                type: 'pie'
            },
            title: {
                text: 'Graphe représentant les états des mutants pour tous les tests'
            },
            tooltip: {
                pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
            },
            plotOptions: {
                pie: {
                    allowPointSelect: true,
                    cursor: 'pointer',
                    dataLabels: {
                        enabled: false
                    },
                    showInLegend: true
                }
            },
            series: [{
                name: 'Brands',
                colorByPoint: true,
                data: [{
                    name: 'Tués',
                    y: parseInt(mutantT),
                    color: 'rgb(101, 255, 108)'
                }, {
                    name: 'Vivants',
                    y: parseInt(mutantV),
                    sliced: true,
                    selected: true,
                    color: '#FF543A'
                }, {
                    name: 'Mort nés',
                    y: parseInt(mutantMN)
                }]
            }]
        });
    });
});