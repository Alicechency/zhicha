/**
 * 本文件包含事件处理相关代码:
 * 1.控制面板左侧加载清除案情散点图层、加载清除警车位置数据图层、加载清除巡逻路线数据图层按钮点击事件
 * 2.改变热力图类型事件
 * 3.禁用和恢复地图拖拽的事件
 * 4.选框触发侧边栏弹出事件
 * 5.点击单个案件触发侧边栏弹出事件
 * 6.数据与开始时间、结束时间、关键词的联动事件(时间改变刷新地图、关键词改变刷新地图)
 * 7.重构ECharts原生的散点鼠标hover的tip事件
 * 8.控制面板初始化ctlboardInit函数: 热力图选择动态生成option；设置日期时间插件的显示
 * 9.页面加载时初始化控制面板，并判断语音助手是否要求开启热力图
 * 10.外来人口网格图按钮相关事件
 * 11.犯罪预测网格图按钮相关事件
 */


/**
 * 侧边栏相关变量声明
 */
// 侧边栏图表
var sideChart = null;
// 侧边栏图表配置
var sideOption = null;
// 侧边栏信息模块(选框时用于展示案件列表，点击单个案件时用于展示案件信息)
var $info = $('#side-list .panel-body');


/**
 * 初始化控制面板
 */
function ctlboardInit() {
    // 热力图 - 根据数据生成select-option
    $('#heatmap-select').text('');
    $('#heatmap-select').append('<option>关闭</option>');
    $('#heatmap-select').append('<option id="heatmap-all">全部</option>');
    var category = "";
    for (var i = 0; i < typeArr.length; i++) {
        category = typeArr[i];
        $('#heatmap-select').append('<option>' + category + '</option>');
    }

    //初始化起始时间和终止时间
    $('#startTime').attr('value', startTime);
    $('#endTime').attr('value', endTime);


    // 初始化时间刻度
    var gridMargin = ($('#outsider-time-choose').width() - 30) / 24;
    var textMargin = ($('#outsider-time-choose').width() - 15) / 4;
    $('#irs-1 .irs-grid .irs-grid-pol').each(function(index) {
        if (index === 25) {
            $(this).css('display', 'none');
        } else if (index % 6 === 0) {
            $(this).removeClass('small');
        } else {
            $(this).addClass('small');
        }
        $(this).css('left', 8 + index * gridMargin);
    });

    $('#irs-1 .irs-grid .irs-grid-text').each(function(index) {
        if (index === 0) {
            $(this).css('left', 5);
        } else if (index !== 0 && index !== 4) {
            $(this).css('left', index * textMargin - 49);
        } else {
            $(this).css('left', index * textMargin - 100);
        }
    });
}


// 页面加载
$(function() {
    // 初始化控制面板
    ctlboardInit();
    // 语音助手图层选项
    var assistantLayer = $('#layer').val();
    if (assistantLayer === 'heatmap') {
        $('#heatmap-select').val('全部').change();
    }
});


// 加载案情和清除案情按钮事件
$('#btn-eventctl').click(function(event) {
    // .btn-load为数据加载状态, .btn-clear为数据清除状态
    // 用这两个类来标记当前图层状态
    if ($(this).hasClass('btn-load')) {
        // 修改按钮图形
        $(this)[0].src = IMG_URL + 'event_load.png';
        // 修改按钮状态
        $(this).addClass('btn-clear').removeClass('btn-load');

        myChart.showLoading();
        // 对eventData数据按照初始化生成的id进行加载
        var i = 0;
        for (var each in eventData) {
            myChart.setOption({
                series: {
                    id: 'eventDataCategory' + i,
                    data: eventData[each]
                }
            });
            i++;
        }
        // 加载legend
        myChart.setOption({
            legend: {
                data: typeArr
            }
        });
        myChart.hideLoading();

    } else {
        // 修改按钮图形
        $(this)[0].src = IMG_URL + 'event_clear.png';
        // 修改按钮状态
        $(this).addClass('btn-load').removeClass('btn-clear');

        myChart.showLoading();
        // 清除数据
        var tmpSeries = myChart.getOption().series;
        for (var i = 0; i < typeArr.length; i++) {
            var tmp = tmpSeries.pop();
            if (tmp.type === 'scatter' && tmp.id !== 'carPosition') {
                myChart.setOption({
                    series: {
                        id: tmp.id,
                        data: []
                    }
                });
            }
        }
        // 清除legend
        myChart.setOption({
            legend: {
                data: []
            }
        });
        myChart.hideLoading();
    }
});


// 加载位置与清除位置按钮事件
$('#btn-positionctl').click(function(event) {
    // .btn-load为数据加载状态
    if ($(this).hasClass('btn-load')) {
        // 修改按钮图形
        $(this)[0].src = IMG_URL + 'position_load.png';
        // 修改按钮状态
        $(this).addClass('btn-clear').removeClass('btn-load');

        // 异步请求
        $.ajax({
            type: "get",
            cache: true,
            async: true,
            url: CAR_POSITION_URL,
            success: function(res) {
                policeCarData = $.parseJSON(res);
                // 加载数据
                myChart.showLoading();
                myChart.setOption({
                    series: [{
                        id: 'carPosition',
                        data: convertPoliceCarJsonData(policeCarData)
                    }]
                });
                myChart.hideLoading();
            }
        });
    } else {
        // 修改按钮图形
        $(this)[0].src = IMG_URL + 'position_clear.png';
        // 修改按钮状态
        $(this).addClass('btn-load').removeClass('btn-clear');
        // 清除数据
        myChart.showLoading();
        myChart.setOption({
            series: [{
                id: 'carPosition',
                data: []
            }]
        });
        myChart.hideLoading();
    }
});


// 加载路线和清除路线按钮事件
$('#btn-linectl').click(function(event) {
    // .btn-load为数据加载状态
    if ($(this).hasClass('btn-load')) {
        // 修改按钮图形
        $(this)[0].src = IMG_URL + 'line_load.png';
        // 修改按钮状态
        $(this).addClass('btn-clear').removeClass('btn-load');
        // 加载数据
        myChart.showLoading();
        myChart.setOption({
            series: [{
                id: 'policeCarLine',
                // 暂时去除线路效果
                data: linesData
            }, {
                id: 'policeCarEffect',
                data: linesData
            }]
        });
        myChart.hideLoading();
    } else {
        // 修改按钮图形
        $(this)[0].src = IMG_URL + 'line_clear.png';
        // 修改按钮状态
        $(this).addClass('btn-load').removeClass('btn-clear');
        // 更新数据
        myChart.showLoading();
        myChart.setOption({
            series: [{
                id: 'policeCarLine',
                data: []
            }, {
                id: 'policeCarEffect',
                data: []
            }]
        });
        myChart.hideLoading();
    }
});

//当前是否是外来人口加载状态
var ISOUTHEAT = false;  
// 加载和清除外来人口信息按钮事件
$('#btn-outsider').click(function(event) {
    // .btn-load为数据加载状态
    if ($(this).hasClass('btn-load')) {
        // 修改按钮图形
        $(this)[0].src = IMG_URL + 'Outsider_load.png';
        // 修改按钮状态
        $(this).addClass('btn-clear').removeClass('btn-load');
        // 显示时间面板
        $('#outsider-time-choose').css('display', 'block');
        ISOUTHEAT = true;

    } else {
        // 修改按钮图形
        $(this)[0].src = IMG_URL + 'Outsider_clear.png';
        // 修改按钮状态
        $(this).addClass('btn-load').removeClass('btn-clear');
        // 隐藏时间面板
        $('#outsider-time-choose').css('display', 'none');

        // 清除数据
        outsiderData = [];
        // 清除图层
        // mapvOutsiderLayer.destroy();
        // outsiderFirst = 1;
        myChart.setOption({
            series: {
                id: 'outsiderHeatmap',
                data: []
            },

            visualMap: {
                id: 'outsiderVisualMap',
                show: false
            }
        });
        ISOUTHEAT = false;
    }
});

// 外来人口数据时间选择、请求数据、加载数据
$('#outsider-time-choose input')
    .datetimepicker({
        //format: 'yyyy-mm-dd hh:00',
        format: 'yyyy-mm-dd',
        weekStart: 1,
        startView: 'month',
        minView: 'month',
        todayBtn: true,
        todayHighlight: true,
        language: 'zh-CN',
        minuteStep: 1,
        pickerPosition: 'bottom-right',
        autoclose: true
    })
    .on('click', function() {
        // 外来人口热力图没有时间选择限制
        $('#outsider-time-choose input').datetimepicker('setStartDate', null);
    })
    .on('changeDate', function() {
        // 传给后端的时间字符串
        var timeStr = '';
        // 获取开始时间
        timeStr = $(this).val();
        // 时间格式需要转换成字符串"YYYYMMDDhh"
        timeStr = timeStr.split(/\s|-|:/).join('');
        // 隐藏时间面板
        //$('#outsider-time-choose').css('display', 'none');

        // 测试数据
        /* outsiderData = [{ latitude: 39.971966, longitude: 116.557913 },
            { latitude: 40.014113, longitude: 116.478897 },
            { latitude: 39.955093, longitude: 116.457958 }
        ];
        mapvOutsiderLayer = new mapv.baiduMapLayer(bmap, cvtOutsiderData2MapvData(outsiderData), getOutsiderMapvOption());
        mapvOutsiderLayer.show(); */

        // 请求数据
        /* $.ajax({
            type: "POST",
            url: OUTSIDER_URL,
            data: JSON.stringify({
                params: {
                    timeStr: timeStr
                }
            }),
            dataType: "json",
            contentType: 'application/json',
            async: false,
            success: function(res) {
                outsiderData = res.detail.outsiders;
                // 绘制图层
                mapvOutsiderLayer = new mapv.baiduMapLayer(bmap, cvtOutsiderData2MapvData(outsiderData), getOutsiderMapvOption());
                mapvOutsiderLayer.show();
            }
        }); */
    });

// 点击时间图标触发时间选择
$('#outsider-time-choose i').on('click', function() {
    $('#outsider-time-choose input').focus().click();
});
var outsiderFirst = 1;
// 外来人口时间轴插件
$("#outsider-time-line").ionRangeSlider({
    min: 0,
    max: 24,
    from: 0,
    to: 24,
    type: 'single', //设置类型,为单向
    step: 1,
    prefix: "", //设置数值前缀
    postfix: "时", //设置数值后缀
    prettify: true,
    hasGrid: true,
    onFinish: function(event) {
    	/*bmap.addEventListener("dragend",function(){
    		if (ISOUTHEAT == true ) {
    			lazy(event);
    		}
    	});
    	bmap.addEventListener("zoomend",function(){
    		if (ISOUTHEAT == true ) {
    			lazy(event);
    		}  		
    	});*/
    	//bmap.removeEventListener("moveend", lazy());
        var hour = event.fromNumber;
        var dateStr = $("#outsider-date").val();
        dateStr = dateStr.split(/\s|-|:/).join('');
        if (hour <= 9) {
            dateStr = dateStr + "0" + hour;
        } else {
            dateStr = dateStr + "" + hour;
        }

        //console.log(JSON.stringify(bmap.getBounds()));
        //获取可视区域
        var bs = bmap.getBounds();
        //可视区域左下角       
        var bssw = bs.getSouthWest();
        //可视区域右上角
        var bsne = bs.getNorthEast();
        //左下纬度
        var leftlatitude = bssw.lat;
        //左下经度
        var leftlongitude = bssw.lng;
        //右上纬度
        var rightlatitude = bsne.lat;
        //右上经度
        var rightlongitude = bsne.lng;
        $.ajax({
            type: "POST",
            url: OUTSIDER_URL,
            data: JSON.stringify({
                params: {
                    dateStr: dateStr,
                    leftlatitude: leftlatitude,
                    leftlongitude: leftlongitude,
                    rightlatitude: rightlatitude,
                    rightlongitude: rightlongitude
                }
            }),
            dataType: "json",
            contentType: 'application/json',
            async: false,
            success: function(res) {
                if (outsiderFirst != 1) {
                    //加载之前先清除上一次的图层
                    mapvOutsiderLayer.destroy();
                }

                outsiderData = res.detail.outsiders;
                // 测试数据
                // outsiderData = [{
                //         "date": "2017061505",
                //         "id": 1,
                //         "latitude": 39.931965,
                //         "longitude": 116.396435,
                //         "uniqueUsers": 2
                //     }, {
                //         "date": "2017061505",
                //         "id": 2,
                //         "latitude": 39.931965,
                //         "longitude": 116.396435,
                //         "uniqueUsers": 3
                //     }, {
                //         "date": "2017061505",
                //         "id": 3,
                //         "latitude": 39.931965,
                //         "longitude": 116.396435,
                //         "uniqueUsers": 4
                //     }, {
                //         "date": "2017061505",
                //         "id": 4,
                //         "latitude": 39.931965,
                //         "longitude": 116.396435,
                //         "uniqueUsers": 5
                //     }, {
                //         "date": "2017061505",
                //         "id": 6,
                //         "latitude": 39.931965,
                //         "longitude": 116.396435,
                //         "uniqueUsers": 6
                //     }];
                // 绘制图层
                // mapvOutsiderLayer = new mapv.baiduMapLayer(bmap, cvtOutsiderData2MapvData(outsiderData), getOutsiderMapvOption());
                // mapvOutsiderLayer.show();
                // outsiderFirst = outsiderFirst + 1;
                myChart.setOption({
                    series: {
                        id: 'outsiderHeatmap',
                        data: cvtOutsiderData2HeatmapData(outsiderData)
                    },

                    visualMap: {
                        id: 'outsiderVisualMap',
                        show: false
                    }
                });
            }
        });
    },
    onChange: function(event) {
        //console.log('onChange');
        //console.log(event.fromNumber);
    }
});

function lazy(event){
	var hour = event.fromNumber;
    var dateStr = $("#outsider-date").val();
    dateStr = dateStr.split(/\s|-|:/).join('');
    if (hour <= 9) {
        dateStr = dateStr + "0" + hour;
    } else {
        dateStr = dateStr + "" + hour;
    }
    //获取可视区域
    var bs = bmap.getBounds();
    //可视区域左下角       
    var bssw = bs.getSouthWest();
    //可视区域右上角
    var bsne = bs.getNorthEast();
    //左下纬度
    var leftlatitude = bssw.lat;
    //左下经度
    var leftlongitude = bssw.lng;
    //右上纬度
    var rightlatitude = bsne.lat;
    //右上经度
    var rightlongitude = bsne.lng;
    $.ajax({
        type: "POST",
        url: OUTSIDER_URL,
        data: JSON.stringify({
            params: {
                dateStr: dateStr,
                leftlatitude: leftlatitude,
                leftlongitude: leftlongitude,
                rightlatitude: rightlatitude,
                rightlongitude: rightlongitude
            }
        }),
        dataType: "json",
        contentType: 'application/json',
        async: false,
        success: function(res) {
            if (outsiderFirst != 1) {
                //加载之前先清除上一次的图层
                mapvOutsiderLayer.destroy();
            }

            outsiderData = res.detail.outsiders;
            myChart.setOption({
                series: {
                    id: 'outsiderHeatmap',
                    data: cvtOutsiderData2HeatmapData(outsiderData)
                },

                visualMap: {
                    id: 'outsiderVisualMap',
                    show: false
                }
            });
        }
    });
}


// 加载和清除犯罪预测信息按钮事件
$('#btn-crime-predict').click(function(event) {
    // .btn-load为数据加载状态
    if ($(this).hasClass('btn-load')) {
        // 显示时间面板
        $('#crime-predict-choose').css('display', 'block');
    } else {
        // 修改按钮图形
        $(this)[0].src = IMG_URL + 'CrimePredict_clear.png';
        // 修改按钮状态
        $(this).addClass('btn-load').removeClass('btn-clear');
        // 隐藏时间面板
        $('#crime-predict-choose').css('display', 'none');
        // 清除数据
        crimePredictData = [];
        // 清除图层
        mapvCrimePredictLayer.destroy();
    }
});

// 犯罪预测数据的时间选择
$('#crime-predict-choose input')
    .datetimepicker({
        format: 'yyyy-mm-dd',
        weekStart: 1,
        startView: 'month',
        minView: 'month',
        todayBtn: true,
        todayHighlight: true,
        language: 'zh-CN',
        minuteStep: 1,
        pickerPosition: 'bottom-right',
        autoclose: true
    })
    .on('click', function() {
        // 预测犯罪图层的时间选择限制为未来时间
        $('#crime-predict-choose input').datetimepicker('setStartDate', new Date());
    });

// 点击时间图标触发时间选择
$('#crime-predict-choose i').on('click', function() {
    $('#crime-predict-choose input').focus().click();
});

// 犯罪预测确定按钮事件
$('#crime-predict-btngrp button:first').click(function() {
    if ($('#btn-crime-predict').hasClass('btn-load')) {
        // 时间选择
        var timeStr = $('#crime-predict-choose input').val();
        timeStr = timeStr.split('-').join('');
        // 类型选择
        var category = $('#crime-predict-choose select').val();
        // 合法输入时
        if (timeStr !== '' && category !== '0') {
            // 请求数据
            $.ajax({
                type: "POST",
                url: CRIME_PREDICT_URL,
                data: JSON.stringify({
                    params: {
                        timeStr: timeStr,
                        category: category
                    }
                }),
                dataType: "json",
                contentType: 'application/json',
                async: false,
                success: function(res) {
                    crimePredictData = res.detail.crimePredictions;
                    // 绘制图层
                    mapvCrimePredictLayer = new mapv.baiduMapLayer(bmap, cvtCrimePredictData2MapvData(crimePredictData), getCrimePredictMapvOption());
                    mapvCrimePredictLayer.show();
                }
            });

            // 修改按钮图形
            $('#btn-crime-predict')[0].src = IMG_URL + 'CrimePredict_load.png';
            // 修改按钮状态
            $('#btn-crime-predict').addClass('btn-clear').removeClass('btn-load');
            // 隐藏面板
            $('#crime-predict-choose').css('display', 'none');
        }
        // 非法输入弹窗警告
        else {
            alert('请正确选择时间和预测类型！');
        }
    }
});


// 犯罪预测取消按钮事件
$('#crime-predict-btngrp button:last').click(function() {
    // 隐藏面板
    $('#crime-predict-choose').css('display', 'none');
});


// 加载和清除摄像头信息按钮事件
$('#btn-camera').click(function(event) {
    // .btn-load为数据加载状态
    if ($(this).hasClass('btn-load')) {
        // 修改按钮图形
        $(this)[0].src = IMG_URL + 'camera_load.png';
        // 修改按钮状态
        $(this).addClass('btn-clear').removeClass('btn-load');
        // 绘制图层
        //mapvCameraLayer = new mapv.baiduMapLayer(bmap, cvtCameraData2MapvData(cameraLocationData), getCameraMapvOption());
        //mapvCameraLayer.show();
        addCamera();

    } else {
        clearCamera();
        // 修改按钮图形
        $(this)[0].src = IMG_URL + 'camera_clear.png';
        // 修改按钮状态
        $(this).addClass('btn-load').removeClass('btn-clear');
    }
});

var cameraCircle = [];
//在地图上添加摄像头
function addCamera() {
    var myIcon = new BMap.Icon(IMG_URL + "careicon15.png", new BMap.Size(15, 15));
    var myIconRed = new BMap.Icon(IMG_URL + "careiconred.png", new BMap.Size(20, 20));
    var carStr = "";
    for (var i = 0; i < cameraLocationData.length; i++) {
        if (cameraLocationData[i].lng && cameraLocationData[i].lat) {
            var point = new BMap.Point(cameraLocationData[i].lng, cameraLocationData[i].lat);
            var circle = new BMap.Circle(point, cameraLocationData[i].range, { strokeColor: "red", strokeWeight: 2, strokeOpacity: 0.5, fillOpacity: 0.1 }); //创建圆
            //bmap.addOverlay(circle); 
            //cameraCircle.push(circle);

            var SW = new BMap.Point(cameraLocationData[i].minLng, cameraLocationData[i].minLat);
            var NE = new BMap.Point(cameraLocationData[i].maxLng, cameraLocationData[i].maxLat);

            groundOverlayOptions = {
                opacity: 0.8,
                displayOnMinLevel: 5,
                displayOnMaxLevel: 18
            }

            // 初始化GroundOverlay
            var groundOverlay = new BMap.GroundOverlay(new BMap.Bounds(SW, NE), groundOverlayOptions);

            // 设置GroundOverlay的图片地址
            groundOverlay.setImageURL(IMG_URL + 'care.png');
            bmap.addOverlay(groundOverlay);

            //cameraCircle.push(groundOverlay);
            var cameraObj = {
                overlay: groundOverlay,
                id: cameraLocationData[i].id
            };
            cameraCircle.push(cameraObj);
            //var marker = new BMap.Marker(point,{icon:myIcon});
            //bmap.addOverlay(marker); 
            //marker.setTitle(cameraLocationData[i].id);
            // var content = cameraLocationData[i].location;
            // addLabel(content,marker);

        }
    }


    for (var i = 0; i < cameraLocationData.length; i++) {
        if (cameraLocationData[i].lng && cameraLocationData[i].lat) {
            var point = new BMap.Point(cameraLocationData[i].lng, cameraLocationData[i].lat);
            var marker = new BMap.Marker(point, { icon: myIcon });
            marker.setTitle(cameraLocationData[i].id);
            marker.setTop(true);
            marker.addEventListener("mouseover", function() {
                this.setIcon(myIconRed);
                clearCamera(this.getTitle());
            });
            marker.addEventListener("mouseout", function() {
                this.setIcon(myIcon);
                addAllCamera();
            });
            bmap.addOverlay(marker);
            var cameraObj = {
                overlay: marker,
                id: cameraLocationData[i].id
            };
            cameraCircle.push(cameraObj);
        }
    }

}
//覆盖物添加监听事件
function addLabel(content, marker) {
    marker.addEventListener('click', function() {
        var label = new BMap.Label("我是文字标注哦", { offset: new BMap.Size(20, -10) });
        marker.setLabel(label);
    });
}
//清除地图上的摄像头
function clearCamera(id) {
    for (var obj in cameraCircle) {
        if (cameraCircle[obj].id != id) {
            bmap.removeOverlay(cameraCircle[obj].overlay);
        }
    }
}

function addAllCamera() {
    for (var obj in cameraCircle) {
        //if(cameraCircle[obj].id != id){
        bmap.addOverlay(cameraCircle[obj].overlay);
        //}
    }
}

// select表单改变事件：改变热力图类型
$('#heatmap-select').change(function() {
    // 获取类别
    var category = $('#heatmap-select option:selected').text();
    // 根据类别加载热力图
    // 选择关闭: 清空热力图数据
    if (category === '关闭') {
        myChart.showLoading();
        myChart.setOption({
            series: {
                id: 'freqHeatmap',
                data: []
            },

            visualMap: {
                id: 'visualMapHeat',
                show: false
            }
        });
        myChart.hideLoading();
    }
    // 选择全部: 循环遍历全部类别数据，转换为热力图数据
    else if (category === '全部') {
        myChart.showLoading();
        var allHeatMapData = [];
        for (var eachCate in eventData) {
            allHeatMapData = allHeatMapData.concat(convertFreqData(eventData[eachCate]));
        }
        myChart.setOption({
            series: {
                id: 'freqHeatmap',
                data: allHeatMapData
            },

            visualMap: {
                id: 'visualMapHeat',
                show: false
            }
        });
        myChart.hideLoading();
    }
    // 选择其他类型: 加载对应类型的热力图数据
    else {
        myChart.showLoading();
        myChart.setOption({
            series: {
                id: 'freqHeatmap',
                data: convertFreqData(eventData[category])
            },

            visualMap: {
                id: 'visualMapHeat',
                show: false
            }
        });
        myChart.hideLoading();
    }
});


// brush事件: 每选中一个散点就会触发一次
myChart.on('brush', function(params) {
    // 禁用百度地图拖拽(此处的禁用很重要)
    bmap.disableDragging();
});


// brushselected事件: 点击选框工具触发一次，选区结束触发一次
myChart.on('brushselected', function(params) {
    // 禁用百度地图拖拽
    bmap.disableDragging();

    // 此处条件判断避免了点击选框工具和取消选框工具时会触发侧边栏弹出
    // 点击选框工具和取消选框工具时触发的brushselected事件的params.batch[0].areas.length值为0, 否则为1
    if (params.batch[0].areas.length) {
        // params.batch[0].selected保存了myChart中全部series被选中的情况
        var mainSeries = params.batch[0].selected;
        // 保存全部被选中的散点数据
        var selectedItems = [];

        // 添加选中的案情信息到selectedItems中
        for (var index = 0; index < mainSeries.length; index++) {
            // seriesName用于保存某个series的name属性
            var seriesName = mainSeries[index].seriesName;
            // dataIndex属性保存了mainSeries[index]类别中被选中的散点在原始series中的索引值
            for (var i = 0; i < mainSeries[index].dataIndex.length; i++) {
                // rawIndex保存某个被选中的散点在原始series中的索引值
                var rawIndex = mainSeries[index].dataIndex[i];
                // 通过索引值从eventData中查找到被选中的散点的数据保存到selectedItems中
                var dataItem = eventData[seriesName][rawIndex];
                selectedItems.push(dataItem);
            }
        }

        // 按案情时间排序
        selectedItems.sort(function(a, b) {
            return Date.parse(caseTime[a.value[2]]) - Date.parse(caseTime[b.value[2]]);
        });

        // 更改侧边栏标题
        $('#side-chart .panel-title').text('案情统计图');
        $('#side-list .panel-title').text('案情信息列表');

        // 选框事件中侧边栏各个模块显示和隐藏的设置
        $('#side-chart-container').css('display', 'block');
        $('#side-station-info').css('display', 'none');
        $('#side-list').css('display', 'block');
        $('#side-detail').css('display', 'none');

        // 展开侧边栏
        if ($('#side-panel').width() === 0) {
            $('#side-panel').animate({
                width: '40%'
            }, 500, function() {
                // Animation complete.
            });
        }

        /**
         * 绘制统计图表
         */
        // 柱状图横轴按时间统计(精确到天，时间已经按从小到大排序)
        var xAxisData = [];

        // 类别legend
        var legendData = [];

        // 遍历全部数据，生成柱状图横轴数据和类别标签数据
        for (var i = 0; i < selectedItems.length; i++) {
            // 时间仅保留年月日部分，去掉时分秒
            var tmpTime = caseTime[selectedItems[i].value[2]].split(' ')[0];
            // 获取类别
            var tmpType = subcaseType[selectedItems[i].value[2]];
            // 构造柱状图横轴时间数据
            if (xAxisData.indexOf(tmpTime) === -1) {
                xAxisData.push(tmpTime);
            }
            // 构造类别标签数据
            if (legendData.indexOf(tmpType) === -1) {
                legendData.push(tmpType);
            }

        }

        // 柱状图数据声明
        var statisticBarData = {};

        // 饼图数据统计的中间变量声明
        var statisticPieData = {};

        // 饼图数据声明
        var cvtPieData = [];

        // 侧边栏图表的series声明
        var sideChartSeries = [];

        // 柱状图和饼图的series数据结构初始化
        // 柱状图数据为statisticBarData[类型] = [按照xAxisData数组中时间对应的案件计数数据]
        // 饼图数据为statisticPieData[类型] = 计数
        // 初始化计数为0
        for (var i = 0; i < legendData.length; i++) {
            var barDataTemp = [];
            for (var j = 0; j < xAxisData.length; j++) {
                barDataTemp.push(0);
            }
            statisticBarData[legendData[i]] = barDataTemp;
            statisticPieData[legendData[i]] = 0;
        }

        // 统计数据
        for (var eachCase = 0; eachCase < selectedItems.length; eachCase++) {
            // 当前案件的id
            var curCaseId = selectedItems[eachCase].value[2];
            // 当前案件的类型
            var curCaseType = subcaseType[curCaseId];
            // 当前案件的时间
            var curCaseTime = caseTime[curCaseId].split(' ')[0];
            // 当前案件时间对应xAxis上的索引位置
            var timeIndex = xAxisData.indexOf(curCaseTime);
            // 柱状图统计
            statisticBarData[curCaseType][timeIndex]++;
            // 饼图统计
            statisticPieData[curCaseType]++;
        }

        // 饼图数据转换：key-value -> name, value
        for (var eachPieData in statisticPieData) {
            var cvtPieDataTemp = {};
            cvtPieDataTemp.name = eachPieData;
            cvtPieDataTemp.value = statisticPieData[eachPieData];
            cvtPieData.push(cvtPieDataTemp);
        }

        // 将柱状图按类别添加进sideChartSeries
        for (var eachBarData in statisticBarData) {
            seriesTemp = {
                name: eachBarData,
                type: 'bar',
                stack: 'total',
                data: statisticBarData[eachBarData]
            }
            sideChartSeries.push(seriesTemp);
        }

        // 将饼图添加进sideChartSeries
        sideChartSeries.push({
            name: '数量占比',
            type: 'pie',
            center: ['80%', '50%'],
            radius: '18%',
            data: cvtPieData
        });

        // 显示图表
        sideChart = echarts.init($('#side-chart-container')[0]);
        sideOption = {
            tooltip: {
                trigger: 'axis',
                // 坐标轴指示器，坐标轴触发有效
                axisPointer: {
                    // 默认为直线，可选为：'line' | 'shadow' 
                    type: 'shadow'
                }
            },
            legend: {
                textStyle: {
                    color: '#ccc'
                },
                data: legendData
            },
            grid: {
                left: '3%',
                right: '40%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                data: xAxisData
            }],
            yAxis: [{
                type: 'value'
            }],
            axisLabel: {
                textStyle: {
                    color: '#ccc'
                }
            },
            series: sideChartSeries
        };
        sideChart.showLoading();
        sideChart.setOption(sideOption);
        sideChart.hideLoading();

        /**
         * 添加事件列表
         */
        // 清空显示区域
        $info.empty();

        // 创建事件列表组: bootstrap - panel-group
        var $panelGrp = $('<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true"></div>');
        for (var i = 0; i < selectedItems.length; i++) {
            // 按时间顺序插入
            // 其中case-id为自定义标签，存储案情id，用于点击跳转到单个案件侧边栏
            $panelGrp.prepend(
                '<div class="panel panel-default">' +
                '<div class="panel-heading" role="tab" id="heading' + i + '" data-toggle="collapse" data-parent="#accordion" href="#collapse' + i + '" aria-expanded="false" aria-controls="collapse' + i + '" case-id="' + selectedItems[i].value[2] + '">' +
                '<h4 class="panel-title">' +
                caseTime[selectedItems[i].value[2]] + '：&nbsp;' + selectedItems[i].name +
                '</h4>' +
                '</div>' +
                '<div id="collapse' + i + '" class="panel-body panel-collapse collapse" role="tabpanel" aria-labelledby="heading' + i + '">' +
                caseAbstract[selectedItems[i].value[2]] +
                '</div>' +
                '</div>'
            );
        }
        $panelGrp.appendTo($info);

        // 侧边栏选框的事件列表相关事件
        $('#side-list .panel-group .panel-heading')
            // 侧边栏事件列表点击单个案件时跳转到事件详情
            .click(function(event) {
                var caseId = $(this).attr('case-id');
                clickCase(caseId);
            })
            // 侧边栏事件列表鼠标进入时展开简介
            .mouseenter(function() {
                $(this).siblings('.collapse').collapse('show');
            })
            // 侧边栏事件列表鼠标离开时收回简介
            .parent('.panel')
            .mouseleave(function() {
                $(this).children('.collapse').collapse('hide');
            });
    }
});


// 热力图视觉映射组件事件：阻止地图拖拽(此组件暂时未显示)
myChart.on('datarangeselected', function() {
    // 禁用百度地图拖拽
    bmap.disableDragging();
});

/**
 * 点击单个案件后对侧边栏布局的函数
 * @param  {String} caseId     [事件id]
 */
function clickCase(caseId) {
    // 更改标题
    $('#side-chart .panel-title').text('关系图');
    $('#side-list .panel-title').text('警情信息');
    $('#side-abstract .panel-title').text('警情概要');
    $('#side-detail .panel-title').text('警情详情');

    // 点击单个案件侧边栏各个模块显示与隐藏设置
    $('#side-chart-container').css('display', 'block');
    $('#side-station-info').css('display', 'none');
    $('#side-list').css('display', 'block');
    $('#side-detail').css('display', 'block');

    // 侧边栏展开
    if ($('#side-panel').width() === 0) {
        $('#side-panel').animate({
            width: '40%'
        }, 500, function() {
            // Animation complete.
        });
    }

    // 添加案情信息
    $info.empty();
    var $ulInfo = $('<ul>');
    $('<li>').addClass('time').text('作案时间：' + caseTime[caseId]).appendTo($ulInfo);
    $('<li>').addClass('person').text('事主身份：' + identity[caseId]).appendTo($ulInfo);
    $('<li>').addClass('brief').text('案件摘要：' + caseAbstract[caseId]).appendTo($ulInfo);
    $('<li>').addClass('way').text('作案手段：' + subcaseType[caseId]).appendTo($ulInfo);
    $('<li>').addClass('station').text('处警单位派出所：' + localSubstation[caseId]).appendTo($ulInfo);
    $ulInfo.appendTo($info);

    loadCaseBrief(caseId);
    loadCaseChart(caseId);
    // 显示案件摘要
    //$('#side-abstract .panel-body').empty().append(caseAbstract[caseId]);   

    // 显示案件详情
    $('#side-detail .panel-body').empty().append(feedback[caseId]);



    // ajax请求关系图数据
    $.ajax({
        type: "POST",
        url: RELATION_DATA_URL,
        data: JSON.stringify({
            params: {
                id: caseId
            }
        }),
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) {
            relationData = data.detail.crimeRelationships;
        }
    });


    // 绘制关系图
    sideChart = echarts.init($('#side-chart-container')[0]);
    // 如果有关系图数据则显示
    if (relationData.id) {
        sideOption = createRelationGraphOption(relationData);
        sideChart.setOption(sideOption);
    }
    // 没有关系图数据时显示暂无信息
    else {
        sideChart.showLoading({
            text: '暂无信息',
            color: '#c23531',
            textColor: '#fff',
            maskColor: 'rgba(37, 40, 48, 0)',
            zlevel: 0
        });
    }
}


/**
 * 加载案情简要信息
 * 
 * @param caseId
 * @returns
 */
function loadCaseBrief(caseId) {
    // ajax请求数据
    $.ajax({
        type: "POST",
        url: "/iinspection/case/loadCaseBrief",
        data: JSON.stringify({
            params: {
                caseId: caseId
            }
        }),
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) {
            var cas = data.detail.cas;
            if (cas != null && cas.length != 0) {
                $('#side-abstract .panel-body').empty().append(cas.crimeBrief);
            }
        }
    });
}

/**
 * 加载案情时间图表信息
 * 
 * @param caseId
 */
function loadCaseChart(caseId) {
    // ajax请求关系图数据
    $.ajax({
        type: "POST",
        url: "/iinspection/case/loadCaseChart",
        data: JSON.stringify({
            params: {
                caseId: caseId
            }
        }),
        async: false,
        dataType: 'json',
        contentType: 'application/json',
        success: function(data) {
            var caseChart = data.detail.caseChart;
            if (caseChart != null && caseChart.length) {
                $("#case-chart-container").show();
                $("#case-chart-container").html(caseChart.length);
                var hours = new Array();
                var nums = new Array();
                for (var i = 0; i < caseChart.length; i++) {
                    hours[i] = caseChart[i].byHour;
                    nums[i] = caseChart[i].sumByHourCount;
                }
                if (hours != null && nums != null) {
                    loadChartInfo(hours, nums);
                }
            } else {
                $("#case-chart-container").hide();
            }
        }
    });
}

function loadChartInfo(hours, nums) {
    //$("#case-chart-container canvas").css("background","white");
    var myChart = echarts.init($("#case-chart-container")[0]);
    myChart.title = '警情时间信息';

    option = {
        color: ['#3398DB'],
        tooltip: {
            trigger: 'axis',
            axisPointer: { // 坐标轴指示器，坐标轴触发有效
                type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
            }
        },
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
        },
        xAxis: [{
            type: 'category',
            data: hours,
            axisTick: {
                alignWithLabel: true
            },
            axisLabel: {
                show: true,
                margin: 10,
                textStyle: {
                    color: 'white',
                },

            },
            axisLine: {
                lineStyle: {
                    color: 'white',
                }
            }
        }],
        yAxis: [{
                type: 'value',
                axisLabel: {
                    show: true,
                    margin: 10,
                    textStyle: {
                        color: 'white',
                    }
                },
                axisLine: {
                    lineStyle: {
                        color: 'white',
                    }
                }
            }

        ],
        series: [{
            name: '直接访问',
            type: 'bar',
            //barWidth: '60%',
            barWidth: 30, //固定柱子宽度
            data: nums
        }]
    };
    myChart.setOption(option);

}

// 点击单个案情事件触发侧边栏展开
myChart.on('click', function(event) {
    var caseId = event.data.value[2];
    clickCase(caseId);
});


/**
 * 地图容器上的点击事件
 * 恢复百度地图拖拽
 */
$('#container').click(function(event) {
    // 恢复百度地图拖拽
    bmap.enableDragging();
});


/**
 * 侧边栏关闭按钮事件
 */
$('.iconfont').click(function() {
    $('#side-panel').animate({
        width: '0%'
    }, 500, function() {
        // Animation complete.
    });
});


/***********************************************************
 * 数据与时间和搜索关键词的联动
 ***********************************************************/

/**
 * 去掉字符串首尾的空格
 * @param  {String} str [输入的字符串，主要用于对搜索框的查询词处理]
 * @return {String}     [返回去掉首尾空格的查询词]
 */
function trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}


/**
 * 刷新地图，数据由开始时间、结束时间、关键词唯一确定
 * @param  {String} startTime [开始时间]
 * @param  {String} endTime   [结束时间]
 * @param  {String} keyword   [关键词]
 */
function refreshMap(startTime, endTime, keyword) {
    // 获取刷新前的中心位置和缩放级别
    var center = bmap.getCenter();
    var zoom = bmap.getZoom();
    // 去除关键词前后空格
    keyword = trim(keyword);
    // 请求ESData
    getESData(startTime, endTime, keyword);
    // ESData转换
    eventData = cvtESData2eventData(ESData);
    // 加载echarts配置
    setMyChartOption();
    // 加载百度地图配置
    setBmapOption();
    // 初始化控制面板
    ctlboardInit();
    // 恢复刷新前的中心位置和缩放级别
    bmap.centerAndZoom(new BMap.Point(center.lng, center.lat), zoom);
}


// 起始时间配置以及与结束时间的联动
$('#startTime')
    .datetimepicker({
        format: 'yyyy-mm-dd hh:00',
        weekStart: 1,
        startView: 'month',
        minView: 'day',
        todayBtn: true,
        todayHighlight: true,
        language: 'zh-CN',
        minuteStep: 1,
        pickerPosition: 'bottom-right',
        autoclose: true
    })
    .on('click', function() {
        // 开始时间不能选择结束时间之后的时间
        $('#startTime').datetimepicker('setEndDate', $('#endTime').val());
    })
    // 开始时间改变，刷新地图
    .on('changeDate', function() {
        // 获取开始时间
        startTime = $(this).val();
        // 获取结束时间
        endTime = $('#endTime').val();
        // 获取关键词
        var keyword = $('#search-input').val();
        // 刷新地图
        refreshMap(startTime, endTime, keyword);
    });

// 点击时间图标触发起始时间选择
$('#startTime + span i').on('click', function() {
    $('#startTime').focus().click();
});

// 终止时间配置以及和开始时间的联动
$('#endTime')
    .datetimepicker({
        format: 'yyyy-mm-dd hh:00',
        weekStart: 1,
        startView: 'month',
        minView: 'day',
        todayBtn: true,
        todayHighlight: true,
        language: 'zh-CN',
        minuteStep: 1,
        pickerPosition: 'bottom-right',
        autoclose: true
    })
    .on('click', function() {
        // 终止时间不能选择开始时间之前的时间
        $('#endTime').datetimepicker('setStartDate', $('#startTime').val());
    })
    // 终止时间改变，刷新地图
    .on('changeDate', function() {
        // 获取开始时间
        startTime = $('#startTime').val();
        // 获取结束时间
        endTime = $(this).val();
        // 获取关键词
        var keyword = $('#search-input').val();
        // 刷新地图
        refreshMap(startTime, endTime, keyword);
    });

// 点击时间图标触发终止时间
$('#endTime + span i').on('click', function() {
    $('#endTime').focus().click();
});


// 搜索按钮提交时刷新地图
$('#search-btn').click(function(event) {
    // 获取关键词
    var keyword = $('#search-input').val();
    // 刷新地图
    refreshMap(startTime, endTime, keyword);
});

// 回车提交搜索请求
$('#search-input').keydown(function(event) {
    if (event.keyCode === 13) {
        $('#search-btn').click();
    }
});


// 对ECharts原生的散点鼠标hover的tip重构
myChart.on('mouseover', function(event) {
    var curId = event.data.value[2];
    $('#map-scatter-tip').text(caseAbstract[curId]).css('display', 'block');

});

myChart.on('mouseout', function(event) {
    $('#map-scatter-tip').css('display', 'none');
});

var $scatterTip = $('#map-scatter-tip');
$('#container').mousemove(function(event) {
    if ($scatterTip.css('display') !== 'none') {
        $scatterTip.css('left', event.pageX + 'px');
        $scatterTip.css('top', event.pageY + 'px');
    }
});
