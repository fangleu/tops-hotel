<div id="filterPanel">
	<div class="filter abroadHilter">
		<input type="radio" id="hotelPrice" style="display:none;" val="">
	    <ul class="clearfix" style="float:none">
	    
	        <li class="col" style="min-height: 86px;">
	            <h3>价格</h3>
	            <ul class="ul_col" style="width:400px;">
	                <li><label><input id="a" type="radio" name="hotelPrice" id="" checked>不限</label></li>
        			<li><label><input type="radio" name="hotelPrice"  max="300" min="0" val="&yen;300以下" model="#hotelPriceParams">&yen;300以下</label></li>
        			<li><label><input type="radio" name="hotelPrice"  max="500" min="300" val="&yen;300-500" model="#hotelPriceParams">&yen;300-500</label></li>
        			<li><label><input type="radio" name="hotelPrice"  max="800" min="500" val="&yen;500-800" model="#hotelPriceParams">&yen;500-800</label></li>
        			<li><label><input type="radio" name="hotelPrice"  max="1200" min="800" val="&yen;800-1200" model="#hotelPriceParams">&yen;800-1200</label></li>
	            
	            	<li id="PriceByHand">
	            		从 <input type="text" name="minPrice" id="minPrice" style="width:40px">
	            		到 <input type="text" name="maxPrice" id="maxPrice" style="width:40px">
	            		<a id="setHotelPrice" class="button_unimportant adaptiveButton small" style="vertical-align: middle; background:orangered; border:none;">
							<span class="left"></span>
							<span class="center" style="padding:0 7px;font-size:12px;">确定</span>
							<span class="right"></span>
						</a>
	            	</li>
	            </ul>
	        </li>
	         
	        <li class="col" style="border-right: none; min-height: 86px;">
	            <h3>星级</h3>
	            <ul class="ul_col">
	            	<li><label><input id="noLimitedhotelRating" type="checkbox" name="" value="" checked>不限</label></li>
	            	<li><label><input type="checkbox" name="hotelRatings" val="二星级及以下" value="2" model="#ratingParams">二星级及以下</label></li>
	            	<li><label><input type="checkbox" name="hotelRatings" val="三星级/舒适" value="3" model="#ratingParams">三星级/舒适</label></li>
	            	<li><label><input type="checkbox" name="hotelRatings" val="四星级/高档" value="4" model="#ratingParams">四星级/高档</label></li>
	            	<li><label><input type="checkbox" name="hotelRatings" val="五星级/豪华" value="5" model="#ratingParams">五星级/豪华</label></li>
	            </ul>
	        </li>
	        
	    </ul>
	</div>
<div>