<#macro operateLocation operateMark="add">

<div id='locationPanel'>
	<#if operateMark=='add'>
		<script type="text/javascript">
			setIndex(1);
		</script>
	<#elseif operateMark=='detail'>
		<script type="text/javascript">
			setIndex(2);
		</script>
	<#else>
		<script type="text/javascript">
			setIndex(3);
		</script>
	</#if>

	<div class="subPanel" style="padding:10px">
		<div class="head">酒店范围：</div>${(provinceCount)!0}
		<label style="margin-right:20px"><input type="radio" name="hotelRangeSelect" id="">全部地区</label>
		<label><input type="radio" name="hotelRangeSelect" id="">签约经理</label>
		<select name="" id="" style="width:132px">
			<option value="">签约经理名单</option>
		</select>
		<a href="javascript:;" class="button_unimportant adaptiveButton small" style="margin-right:30px">
			<div class="left"></div>
			<div class="center">查 询</div>
			<div class="right"></div>
		</a>
		<label><input type="radio" name="hotelRangeSelect" id="">城市</label>
		<input type="text" name="" id="" style="width:113px">
		<a href="javascript:;" class="button_unimportant adaptiveButton small" style="margin-right:30px">
			<div class="left"></div>
			<div class="center">查 询</div>
			<div class="right"></div>
		</a>
	</div>

	<div class="subPanel" id='regionPanel'></div>

	<div class='subPanel' id='cityPanel'></div>

	<div class='subPanel' id='districtPanel'></div>
</div>

<script type="text/x-kendo-template" id="ac-show-location-Multiple-select-template">
	<div class="tcy_tabstrip clearfix floatPanel" data-animation="false" data-role="tabstrip">
<span class="tcy_title">
可直接选择地理位置
</span>
			<span style="float:right;margin:10px 10px 0 0">
				<label>
					<input type="checkbox" class='selectAll_#=cityCode#'>全选
				</label>
			</span>
		<ul class="tab-head-list">
			# for(var i=0; i
			<data.cityData.length
			; i++){ #
			<li
			# if(i==0){ # class="k-state-active" # } #>
			#= cityData[i].type #
			</li>
			# } #
		</ul>
		# for(var i=0; i
		<data.cityData.length
		; i++){ #
		<div class="tcy-tabitem">
			# for(var j=0; j
			<data.cityData
			[i].typeValue.length; j++){ #
			<div class="row clearfix">
				<span class="row-title">#= cityData[i].typeValue[j].group #</span>
				<ul class="tcy_list clearfix">
					# for(var k=0; k
					<data.cityData
					[i].typeValue[j].groupValue.length; k++){ #
					<li class="item"><label><input type="checkbox" value='#=cityData[i].typeValue[j].districtCode[k]#'
												   name='#=data.cityCode#'
						# if(cityData[i].typeValue[j].isChecked[k]) { # checked #} #
						# if(cityData[i].typeValue[j].disabled[k]) { # disabled #} #
						class='districtAndCommercial_#=cityCode# #=cityData[i].value#Checks'>#=
						cityData[i].typeValue[j].groupValue[k] #</label></li>
					# } #
				</ul>
			</div>
			# } #
		</div>
		# } #
    <span style="float:right;padding:10px">
        <a href="javascript:;" class="button_secondary adaptiveButton medium closeLocationLayer"
		   style="vertical-align: middle">
			<span class="left"></span>
			<span class="center">确 定</span>
			<span class="right"></span>
		</a>
    </span>
	</div>
</script>


</#macro>