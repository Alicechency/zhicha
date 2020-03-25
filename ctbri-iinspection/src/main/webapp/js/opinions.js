var myChart1 = echarts.init($('#chart-moodTrend')[0]);
option = {
    baseOption: {
        timeline: {
            // y: 0,
            axisType: 'category',
            // realtime: false,
            // loop: false,
            autoPlay: true,
            // currentIndex: 2,
            playInterval: 1000,
            // controlStyle: {
            //     position: 'left'
            // },
            data: [
                '2011-01-01', '2012-01-01', '2013-01-01', '2014-01-01', '2015-01-01'
            ],
            label: {
                formatter: function(s) {
                    return (new Date(s)).getFullYear();
                }
            }
        },
        title: {
            subtext: ''
        },
        tooltip: {},
        legend: {
            x: 'right',
            data: ['公开反对', '隐形反对', '中立', '隐形支持', '公开支持']
        },
        calculable: true,
        grid: {
            top: 80,
            bottom: 100
        },
        xAxis: [{
            'type': 'category',
            'axisLabel': { 'interval': 0 },
            'data': [
                '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'
            ],
            splitLine: { show: false }
        }],
        yAxis: [{
            type: 'value',
            name: '报道数量（篇数）',
            // max: 53500
            //max: 900
        }],
        series: [
            { name: '公开反对', type: 'bar' },
            { name: '隐形反对', type: 'bar' },
            { name: '中立', type: 'bar' },
            { name: '隐形支持', type: 'bar' },
            { name: '公开支持', type: 'bar' }, {
                name: '新闻态度占比',
                type: 'pie',
                center: ['75%', '35%'],
                radius: '28%'
            }
        ]
    },
    options: [{
        title: { text: '2011全网情绪走势' },
        series: [
            { data: dataMap.dataAttr5['2011'] },
            { data: dataMap.dataAttr4['2011'] },
            { data: dataMap.dataAttr3['2011'] },
            { data: dataMap.dataAttr2['2011'] },
            { data: dataMap.dataAttr1['2011'] }, {
                data: [
                    { name: '公开反对', value: dataMap.dataAttr5['2011sum'] },
                    { name: '隐形反对', value: dataMap.dataAttr4['2011sum'] },
                    { name: '中立', value: dataMap.dataAttr3['2011sum'] },
                    { name: '隐形支持', value: dataMap.dataAttr2['2011sum'] },
                    { name: '公开支持', value: dataMap.dataAttr1['2011sum'] }
                ]
            }
        ]
    }, {
        title: { text: '2012全网情绪走势' },
        series: [
            { data: dataMap.dataAttr5['2012'] },
            { data: dataMap.dataAttr4['2012'] },
            { data: dataMap.dataAttr3['2012'] },
            { data: dataMap.dataAttr2['2012'] },
            { data: dataMap.dataAttr1['2012'] }, {
                data: [
                    { name: '公开反对', value: dataMap.dataAttr5['2012sum'] },
                    { name: '隐形反对', value: dataMap.dataAttr4['2012sum'] },
                    { name: '中立', value: dataMap.dataAttr3['2012sum'] },
                    { name: '隐形支持', value: dataMap.dataAttr2['2012sum'] },
                    { name: '公开支持', value: dataMap.dataAttr1['2012sum'] }
                ]
            }
        ]
    }, {
        title: { text: '2013全网情绪走势' },
        series: [
            { data: dataMap.dataAttr5['2013'] },
            { data: dataMap.dataAttr4['2013'] },
            { data: dataMap.dataAttr3['2013'] },
            { data: dataMap.dataAttr2['2013'] },
            { data: dataMap.dataAttr1['2013'] }, {
                data: [
                    { name: '公开反对', value: dataMap.dataAttr5['2013sum'] },
                    { name: '隐形反对', value: dataMap.dataAttr4['2013sum'] },
                    { name: '中立', value: dataMap.dataAttr3['2013sum'] },
                    { name: '隐形支持', value: dataMap.dataAttr2['2013sum'] },
                    { name: '公开支持', value: dataMap.dataAttr1['2013sum'] }
                ]
            }
        ]
    }, {
        title: { text: '2014全网情绪走势' },
        series: [
            { data: dataMap.dataAttr5['2014'] },
            { data: dataMap.dataAttr4['2014'] },
            { data: dataMap.dataAttr3['2014'] },
            { data: dataMap.dataAttr2['2014'] },
            { data: dataMap.dataAttr1['2014'] }, {
                data: [
                    { name: '公开反对', value: dataMap.dataAttr5['2014sum'] },
                    { name: '隐形反对', value: dataMap.dataAttr4['2014sum'] },
                    { name: '中立', value: dataMap.dataAttr3['2014sum'] },
                    { name: '隐形支持', value: dataMap.dataAttr2['2014sum'] },
                    { name: '公开支持', value: dataMap.dataAttr1['2014sum'] }
                ]
            }
        ]
    }, {
        title: { text: '2015全网情绪走势' },
        series: [
            { data: dataMap.dataAttr5['2015'] },
            { data: dataMap.dataAttr4['2015'] },
            { data: dataMap.dataAttr3['2015'] },
            { data: dataMap.dataAttr2['2015'] },
            { data: dataMap.dataAttr1['2015'] }, {
                data: [
                    { name: '公开反对', value: dataMap.dataAttr5['2015sum'] },
                    { name: '隐形反对', value: dataMap.dataAttr4['2015sum'] },
                    { name: '中立', value: dataMap.dataAttr3['2015sum'] },
                    { name: '隐形支持', value: dataMap.dataAttr2['2015sum'] },
                    { name: '公开支持', value: dataMap.dataAttr1['2015sum'] }
                ]
            }
        ]
    }]
};

myChart1.setOption(option);







var myChart = echarts.init($('#chart-volumeTrend')[0]);
option = {
    // title: {
    //     text: '全网声量走势'
    // },
    
    // type: 'category',
    tooltip: {
        trigger: 'axis'
    },
    // stack: 'tiled',
    // toolbox: {
    //     show: true,
    //     feature: {
    //         magicType: {show: true, type: ['stack', 'tiled']},
    //         saveAsImage: {show: true}
    //     }
    // },
    legend: {
        data: ['新闻', '论坛', '微博', '微信公众号', '博客', '贴吧', '问答']
    },
    grid: {
        left: '0%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    // toolbox: {
    //     feature: {
    //         saveAsImage: {}
    //     }
    // },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
    },
    yAxis: {
        type: 'value'
    },
    series: [{
        name: '新闻',
        type: 'line',
        data: [9234, 10291, 16395, 18941, 19625, 14614, 1025],
        smooth: true
    }, {
        name: '论坛',
        type: 'line',
        data: [458, 481, 718, 472, 290, 655, 39],
        smooth: true
    }, {
        name: '微博',
        type: 'line',
        data: [425, 405, 528, 513, 533, 557, 254],
        smooth: true
    }, {
        name: '微信公众号',
        type: 'line',
        data: [2, 110, 428, 407, 444, 75, 0],
        smooth: true
    }, {
        name: '博客',
        type: 'line',
        data: [117, 108, 209, 239, 196, 151, 24],
        smooth: true
    }, {
        name: '贴吧',
        type: 'line',
        data: [17, 13, 24, 27, 19, 22, 3],
        smooth: true
    }, {
        name: '问答',
        type: 'line',
        data: [11, 8, 3, 4, 8, 11, 0],
        smooth: true
    }]
};
myChart.setOption(option);

var myChart = echarts.init(document.getElementById('sourceType'));
option = {
    // title: {
    //     text: '来源分类',
    //     // subtext: '纯属虚构',
    //     x: 'center'
    // },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
        x: 'center',
        y: 'bottom',
        data: ['新闻', '论坛', '微博', '微信公众号', '博客', '贴吧', '问答', '传统纸媒']
    },
    // toolbox: {
    //     show: true,
    //     feature: {
    //         mark: {
    //             show: true
    //         },
    //         dataView: {
    //             show: true,
    //             readOnly: false
    //         },
    //         magicType: {
    //             show: true,
    //             type: ['pie', 'funnel']
    //         },
    //         restore: {
    //             show: true
    //         },
    //         saveAsImage: {
    //             show: true
    //         }
    //     }
    // },
    calculable: true,
    series: [{
        name: '面积模式',
        type: 'pie',
        radius: [30, 110],
        center: ['50%', '50%'],
        roseType: 'area',
        data: [{
            value: 91058,
            name: '新闻'
        }, {
            value: 3452,
            name: '论坛'
        }, {
            value: 3244,
            name: '微博'
        }, {
            value: 1466,
            name: '微信公众号'
        }, {
            value: 1052,
            name: '博客'
        }, {
            value: 128,
            name: '贴吧'
        }, {
            value: 35,
            name: '问答'
        }, {
            value: 12510,
            name: '传统纸媒'
        }]
    }]

};
myChart.setOption(option);

var myChart = echarts.init(document.getElementById('mediaFriend'));
option = {

    tooltip: {
        trigger: 'item',
        formatter: "{b} : {c}%"
    },
    toolbox: {
        orient: 'vertical',
        top: 'center'
            // feature: {
            //     dataView: {
            //         readOnly: false
            //     },
            //     restore: {},
            //     saveAsImage: {}
            // }
    },
    legend: {
        orient: 'vertical',
        left: 'left',
        data: ['北方网', '威海新闻网', '娄底新闻网', '深圳热线', '中国网', '南京金陵热线', '未来网', '张家界新闻网', '焦作网', '证券中心']
    },
    calculable: true,
    series: [{
        type: 'funnel',
        left: '30%',
        label: {
            normal: {
                position: 'left'
            }
        },
        data: [{
            value: 2683,
            name: '北方网'
        }, {
            value: 2558,
            name: '威海新闻网'
        }, {
            value: 1272,
            name: '娄底新闻网'
        }, {
            value: 1056,
            name: '深圳热线'
        }, {
            value: 959,
            name: '中国网'
        }, {
            value: 947,
            name: '南京金陵热线'
        }, {
            value: 937,
            name: '未来网'
        }, {
            value: 888,
            name: '张家界新闻网'
        }, {
            value: 827,
            name: '焦作网'
        }, {
            value: 762,
            name: '证券中心'
        }]
    }]
};
myChart.setOption(option);
