function showCart(line)
{
	if(line=="day")
	{
		Highcharts.setOptions({  
			tooltip: { xDateFormat: '%Y-%m-%d, %A', color: '#f0f', changeDecimals: 4, borderColor: '#058dc7' }
		}); 
	}else
	{
		Highcharts.setOptions({  
			//tooltip: { xDateFormat: '%Y-%m-%d %H:%M %A', color: '#f0f', changeDecimals: 4, borderColor: '#058dc7' }
			tooltip: { xDateFormat: '%Y-%m-%d, %H:%M %A', color: '#f0f', changeDecimals: 4, borderColor: '#058dc7' }
		}); 
	}

	$.ajax({
		url: '/?s=Home/Index/getData/t/'+ new Date().getTime(),
		type: 'POST',
		dataType: 'text',
		data: "id="+ex+"&line="+line,
		timeout: 10000,
		error: function(){
		},
		success: function(data){
			var datas = eval(data);
			var rates = [];
			var vols = [];
			for(i = 0; i < datas.length; i++){
		
				rates.push([
					datas[i][0],
					datas[i][1],
					datas[i][2],
					datas[i][3],
					datas[i][4]
				]);

				vols.push([
					datas[i][0],
					datas[i][5]
				])
			}

			Highcharts.setOptions({
				colors: ['#DD1111','#FF0000','#DDDF0D', '#7798BF', '#55BF3B', '#DF5353', '#aaeeee', '#ff0066', '#eeaaee','#55BF3B', '#DF5353', '#7798BF', '#aaeeee'],
				
			lang: {
                    loading: 'Loading...',
		    months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
		    shortMonths: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
                    weekdays: ['星期日', '星期一', '星期二', '星期三', '星期四', '星期五', '星期六'],
                    decimalPoint: '.',
                    numericSymbols: ['k', 'M', 'G', 'T', 'P', 'E'], // SI prefixes used in axis labels
                    resetZoom: 'Reset zoom',
                    resetZoomTitle: 'Reset zoom level 1:1',
                    thousandsSep: ','
            },

		global: {useUTC: false},

			credits: { enabled: true,text: '',
                href: '',
                position: { align: 'right',
                        x: -10,
                        verticalAlign: 'bottom',
                        y: -5
                },
                style: {
                        cursor: 'pointer',
                        color: '#909090',
                        fontSize: '10px'
                }
            }

			});

	$('#chart_area').highcharts('StockChart', {
		rangeSelector : { selected : 1},
		//title: { text: ex_f+"/"+ex_l, useHTML:true },
		plotOptions: { candlestick: {
						color: '#f01717',
						upColor: '#0ab92b'
						}},

		scrollbar:{enabled: false},
		navigator:{enabled: false},
		rangeSelector: {enabled: false},

		yAxis: [
			{
				top: 43, height: 183,
             			labels: { style: { color: '#CC3300' } },
              			title: { text: 'Price ['+ex_l+']', style: { color: '#CC3300' }},
				gridLineDashStyle : 'Dash'
           	 	}, 
			{
				top: 226, height: 63,
              			labels: { style: { color: '#4572A7' } },
              			title: { text: '交易量 ['+ex_f+']', style: { color: '#4572A7'}},
				gridLineDashStyle : 'Dash',
				offset: 0 
         		 }
			],


			series: [
            {
              animation: false,
              name  : '交易量 ['+ex_f+']',
              type  : 'column',
	      color: '#4572A7',
              marker: { enabled: false },
              yAxis : 1,
			  //dataGrouping: {units: groupingUnits},
              data  : vols
            },
            {
              animation: false,			  
              name  : 'Price ['+ex_l+']',
			  type: 'candlestick',
              marker: { enabled: false },
			  // dataGrouping: {units: groupingUnits},
              data  : rates
            }
          ]
			});


		}//success
	}); //ajax end

}
