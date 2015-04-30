<#import "/common/mainContainer.ftl" as mc/>
<@mc.container jsFiles=['common/autoComplete.js', 'page/hotel/booking/teamApply/teamApply.js', 'common/formValidator.js']
cssFiles=['css/store_apply.css'] menuItem="酒店">
<div class="k_place">  <!-- k_place start -->
	<h2>酒店团队申请</h2>

	<div class="message">  <!--message start-->
		<div class="mes_h3"><h3>预订信息</h3></div>
		<form action="${basepath}/hotel/booking/teamApply/save" method="post" id='teamApplyForm'>
			<table cellpadding="0" cellspacing="0" class="mes_tab">
				<colgroup>
					<col width="105px"/>
				</colgroup>
				<tr>
					<td><em> * </em>城市：</td>
					<td>
						<input type="text" class="city-ac-hotel gray-border" id='cityName' name='cityName' value="${(cityName)!}"/>
						<input type="hidden" name='cityIsoCode' id='cityIsoCode' value="${(cityIsoCode)!}"/>
					</td>
				</tr>
				<tr>
					<td><em> * </em>入住日期：</td>
					<td><input class="datepicker" type="text" id='fromDate' name='fromDate' onchange="validateInput('#fromDate')" value="${(checkinDate)!}" /></td>
				</tr>
				<tr>
					<td><em> * </em>离店日期：</td>
					<td><input class="datepicker" type="text" id='leaveDate' name='leaveDate' onchange="validateInput('#leaveDate')" value="${(checkoutDate)!}" /></td>
				</tr>
				<tr>
					<td><em> * </em>入住房间数：</td>
					<td class="journey">
						<input style="width:70px;" type="text" name='roomNumber1' id='roomNumber1'  onpropertychange="roomChange('#roomNumber1', 'roomNumber2')"  oninput="roomChange('#roomNumber1', 'roomNumber2')" />
						<span style="padding-right:20px;"> 间</span>
						<label>
							<input type="radio" name="roomNumber2" value='0' checked/> 6-8间
						</label>
						<label>
							<input type="radio" name="roomNumber2" value='1'/> 8-10间
						</label>
						<label>
							<input type="radio" name="roomNumber2" value='2'/> 10间以上
						</label>
					</td>
				</tr>
				<tr class="journey">
					<td>星级：</td>
					<td>
						<label>
							<input type="checkbox" name="hotelLevel" value='' checked/> 不限
						</label>
						<label>
							<input type="checkbox" name="hotelLevel" value="2"/> 二星级及以下/经济
						</label>
						<label>
							<input type="checkbox" name="hotelLevel" value="3"/> 三星级/舒适
						</label>
						<label>
							<input type="checkbox" name="hotelLevel" value="4"/> 四星级/高档
						</label>
						<label>
							<input type="checkbox" name="hotelLevel" value="5"/> 五星级/豪华
						</label>
					</td>
				</tr>
				<tr class="journey">
					<td>品牌：</td>
					<td>
						<input class="brand gray-border" type="text" id='brand' onpropertychange="roomChange('#brand', 'hotelBrand')" oninput="roomChange('#brand', 'hotelBrand')" placeholder="请输入品牌" />
						<label>
							<input type="checkbox" name="hotelBrand" value="0" checked/> 不限
						</label>
						<label>
							<input type="checkbox" name="hotelBrand" value="1"/> 希尔顿
						</label>
						<label>
							<input type="checkbox" name="hotelBrand" value="2"/> 喜来登
						</label>
						<label>
							<input type="checkbox" name="hotelBrand" value="3"/> 万豪
						</label>
						<label>
							<input type="checkbox" name="hotelBrand" value="4"/> 汉庭
						</label>
						<label>
							<input type="checkbox" name="hotelBrand" value="5"/> 格林豪泰
						</label>
					</td>
				</tr>
				<tr class="journey">
					<td>价格：</td>
					<td>
						<span class="price">从</span>
						<input style="width:50px;" type="text" name='fromPrice' id='fromPrice' onpropertychange="roomChange('#fromPrice', 'price')" oninput="roomChange('#fromPrice', 'price')"/>
						<span class="price">到</span>
						<input style="width:50px; margin-right:20px;" type="text" name='toPrice' id='toPrice' onpropertychange="roomChange('#toPrice')" oninput="roomChange('#toPrice')"/>
						<label>
							<input type="radio" name="price" value="0" checked/> 不限
						</label>
						<label>
							<input type="radio" name="price" value="1"/> &yen;150以下
						</label>
						<label>
							<input type="radio" name="price" value="2"/> &yen;151-300
						</label>
						<label>
							<input type="radio" name="price" value="3"/> &yen;301-600
						</label>
						<label>
							<input type="radio" name="price" value="4"/> &yen;601以上
						</label>
					</td>
				</tr>
				<tr>
					<td style="vertical-align:top;">备注：</td>
					<td>
						<textarea name='remark' id='remark'> </textarea>
					</td>
				</tr>
			</table>

			<div class="contacts">
				<div class="mes_h3"><h3>联系人信息</h3></div>
				<table cellpadding="0" cellspacing="0" class="submitAsk">
					<colgroup>
						<col width="100px"/>
					</colgroup>
					<tr>
						<td><em> * </em>联系人姓名：</td>
						<td><input style="width:160px;" type="text" name='clientName' id='clientName' onpropertychange="validateInput('#clientName')" oninput="validateInput('#clientName')"/></td>
					</tr>
					<tr>
						<td><em> * </em>联系人手机：</td>
						<td><input style="width:160px;" type="text" name='clientPhone' id='clientPhone' onpropertychange="validateInput('#clientPhone')" oninput="validateInput('#clientPhone')"/></td>
					</tr>
					<tr>
						<td></td>
						<td>
							<a class="adaptiveButton brightRed_btn" id='teamApplyFormSubmit'>
								<span class="left"></span>
								<span class="center center_1">提交申请</span>
								<span class="right"></span>
							</a>
						</td>
					</tr>
				</table>
		</form>
	</div>
</div>
<!--message end-->
</div>  <!-- k_place end -->

<script type="text/x-kendo-template" id="city_popup">
	<div id="gn-tabstrip" class="tcy_tabstrip">
<span class="tcy_title">
    热门城市/国家（支持中文名/拼音/英文名/三字码）
</span>
		<ul>
			<li id="hot_city" class="k-state-active">热门</li>
			# for (var i=0; i
			<data.group.length
			; i++ ) { #
			<li>#= data.group[i] #</li>
			# } #
		</ul>
		<div id="hot_city_tab">
			<ul class="tcy_list clearfix">

				# for(var j=0;j
				<hotcitylist.length
				;j++){ #
				# var citylist_item = hotcitylist[j] #
				<li class="item" title="#= citylist_item.code #" data-code="#= citylist_item.code #">#=
					citylist_item.name #
				</li>
				# } #
			</ul>
		</div>

		# for(var i=0;i
		<data.citylist.length
		;i++){ #
		# var citylist_item = data.citylist[i] #
		<div class="city_tab">
			<ul class="tcy_list clearfix">
				# for(var k=0;k
				<data.group
				[i].length;k++){ #
				<li class="tcy_list_sep">#= data.group[i].split('')[k] #</li>
				# for(var j=0;j
				<citylist_item.value.length
				;j++){ #
				# var citylist_value_item = citylist_item.value[j]; #
				# if(citylist_value_item.py.slice(0,1) == data.group[i].split('')[k]) {#
				<li class="item" title="#= citylist_value_item.code #" data-code="#= citylist_value_item.code #">#=
					citylist_value_item.name #
				</li>
				# } #
				# } #
				# } #
			</ul>
		</div>

		# } #
	</div>
</script>
</@mc.container>