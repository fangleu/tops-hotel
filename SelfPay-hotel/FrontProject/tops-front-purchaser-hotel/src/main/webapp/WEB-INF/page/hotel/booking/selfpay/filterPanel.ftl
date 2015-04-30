	<div id="filterPanel" class="inlandHilter">
	 <div class="searchRowUl">
                    <b>行政区</b>
                    <span id="district" class="sel" onclick="clearDistrict()">不限</span>
                    <ul>
                         <#if districtPageList??>
                        <#list districtPageList as f>
                        <#if f_index lt 6>
                    		<li><label><input id="district" type="radio" name="district" vale=${f.locationId}  val=${f.name}>${f.name}
                    		</label></li>
                    		</#if>
                        </#list>
                        </#if>
                        <li class="last"><span class="moreBtnblock" id="moreSelect_trigger" onclick="openMore('#more_district')">更多<i class="ico_down"></i></span></li>
                    </ul>
                </div>
                <!--hide area more-->
                                
                <div id="more_district" style="display:none;" class="newEndorseOrder moreAreaList" >
                    <div class="moreArea_search">
                    <div class="inner_moreArea">
                        <ul>
                          <#if districtPageList??>
                        <#list districtPageList as f>
                        <#if f_index gte 6>
                    		<li><label><input id="district" type="radio" name="district" vale=${f.locationId}  val=${f.name}>${f.name}
                    		</label></li>
                    		</#if>
                        </#list>
                        </#if>
                        </ul>
                        <span class="search_close" onclick="closeMore('#more_district')"></span>
                    </div>
                    </div>
                </div>
                
                
	        <div style="left;" class="searchRowUl">
	         <b>酒店星级:</b>
	            	<span id="tatings" class="sel" onclick="clearRatings()">不限</span>
	            <ul>	<li><label><input type="checkbox" name="hotelRatings" val="二星级及以下" value="1,2" model="#ratingParams">二星级及以下</label></li>
	            	<li><label><input type="checkbox" name="hotelRatings" val="三星级/舒适" value="3" model="#ratingParams">三星级/舒适</label></li>
	            	<li><label><input type="checkbox" name="hotelRatings" val="四星级/高档" value="4" model="#ratingParams">四星级/高档</label></li>
	            	<li><label><input type="checkbox" name="hotelRatings" val="五星级/豪华" value="5" model="#ratingParams">五星级/豪华</label></li>
	        </ul>
	            </div>
	            <div class="searchRowUl">
                    <b>价格范围</b>
                    <span id="price" class="sel" onclick="clearPrice()">不限</span>
	               
        		<ul><li><label ><input id="hotelPrice" type="radio" name="hotelPrice"  max="300" min="0" val="&yen;300以下" model="#hotelPriceParams">&yen;300以下</label></li>
        			<li><label ><input  id="hotelPrice" type="radio" name="hotelPrice"  max="500" min="300" val="&yen;300-500" model="#hotelPriceParams">&yen;300-500</label></li>
        			<li><label ><input  id="hotelPrice" type="radio" name="hotelPrice"  max="800" min="500" val="&yen;500-800" model="#hotelPriceParams">&yen;500-800</label></li>
        			<li><label ><input  id="hotelPrice" type="radio" name="hotelPrice"  max="1200" min="800" val="&yen;800-1200" model="#hotelPriceParams">&yen;800-1200</label></li>
	            	<li id="PriceByHand" style="width: 250px;">
	            	<sapn style="white-space: nowrap;">
		            	从<input  id="minPrice" name="minPrice" type="text"/>
		            	到<input id="maxPrice" name="maxPrice" type="text"/>
		            	<button id="setHotelPrice" name="setHotelPrice" type="button" class="sureBtn">确定</button>
	            	</sapn>
	            	</li>
	            </ul>
	      </div>
	  </div>
