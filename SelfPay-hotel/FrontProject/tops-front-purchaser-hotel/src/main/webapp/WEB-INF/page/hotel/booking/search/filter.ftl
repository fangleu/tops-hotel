<div id="filterPanel">
	<div class="filter" style="background:#fff; border-bottom:2px solid #ececec;">
		<input type="radio" id="hotelPrice" style="display:none;" val="">
	    <ul class="clearfix" style="float:none">
	    	<#if (priceRange)?? && (priceRange?size gt 0)>
	        <li class="col" style="padding:0px;">
	            <h3>价格</h3>
	            <ul>
	                <li><label><input id="a" type="radio" name="hotelPrice" id="" checked>不限</label></li>
	            	<#list priceRange as pr>
	            		<#if pr_index!=0>
	            			<li style="width:150px"><label><input type="radio" name="hotelPrice"  max="${(pr.max)!}" min="${(pr.min)!}" val="${(pr.nameCn)!}" model="#hotelPriceParams">${(pr.nameCn)!}</label></li>
	            		</#if>
	            	</#list>
	            	<li id="PriceByHand">
	            		从 <input type="text" name="minPrice" id="minPrice" style="width:40px">
	            		到 <input type="text" name="maxPrice" id="maxPrice" style="width:40px" k-msg-position="bottom" k-msg-css="{width:150, top:10, left:-40}">
	            		<a id="setHotelPrice" class="button_unimportant adaptiveButton small" style="vertical-align: middle; background:orangered; border:none;">
							<span class="left"></span>
							<span class="center" style="padding:0 7px;font-size:12px;">确定</span>
							<span class="right"></span>
						</a>
	            	</li>
	            </ul>
	        </li>
	         </#if>
	        <#if (hotelRatings)?? && (hotelRatings?size gt 0)>
	        <li class="col" style="width:210px; padding:0px;">
	            <h3>星级</h3>
	            <ul>
	            	<li><label><input id="noLimitedhotelRating" type="checkbox" name="" value=""  <#if criteria ?? && criteria.hotelRatings?? && criteria.hotelRatings[0]=='all'>checked</#if>>不限</label></li>
	        		<#list hotelRatings as rating>
	        			<#if (rating == 'TwoStar')>
	            			<li><label><input type="checkbox" name="hotelRatings" val="${(rating.nameCn)!}" value="${(rating.rating)!}" model="#ratingParams" <#if criteria ?? && criteria.hotelRatings?? && criteria.hotelRatings[0]==rating.rating>checked</#if> >${(rating.nameCn)!}</label></li>
	            		<#else>
	            			<li style="width:82px;float:left;"><label><input type="checkbox" name="hotelRatings" val="${(rating.nameCn)!}" value="${(rating.rating)!}" model="#ratingParams" <#if criteria ?? && criteria.hotelRatings?? && criteria.hotelRatings[0]==rating.rating>checked</#if>>${(rating.nameCn)!}</label></li>
	            		</#if>
	            	</#list>
	            </ul>
	        </li>
	        </#if>
	        <#if (districts)?? && (districts?size gt 0)>
	        <li class="col" style="padding:0px;">
	            <h3>位置</h3>
	            <ul>
	                <li><label><input type="radio" name="" id="noLimiteddistrictCodes" value="" checked>不限</label></li>
	            	<#list districts as dis>
	            		<#if dis_index lt 4>
	            			<li>
	            				<label>
	            					<input type="radio" name="districtCodes" val="${(dis.nameCn)!}" value="${(dis.id)!}" model="#districtParams"><span>${(dis.nameCn)!}</span>
	            				</label>
	            			</li>
	            		</#if>
	            	</#list>
		            <li>
	            		<a onclick="openMore('#more_district')" href="javascript:;">更多位置<i class="ico_down"></i></a>
	            	</li>
	            	<li>
		            	<div id="more_district" class="more_district" style="width: 105px; position: absolute; z-index: 10; display: none; border-top:1px solid #eee;">
				    		<ul class="district_list">
					    		<#list districts as dis>
					        		<#if dis_index gte 4>
					        			<li>
					        				<label>
					        					<input type="radio" name="districtCodes" val="${(dis.nameCn)!}" value="${(dis.id)!}" model="#districtParams"><span>${(dis.nameCn)!}</span>
					        				</label>
					        			</li>
					        		</#if>
					        	</#list>
				        	</ul>
				        </div>
			        </li>
	            </ul>
	        </li>
	        </#if>
	         <#if (hotelChainBrands)?? && (hotelChainBrands?size gt 0)>
	        <li class="col" style="padding:0px;">
	            <h3>品牌</h3>
	            <ul>
	                <li><label><input type="checkbox" name="" id="noLimitedChainBrand" checked>不限</label></li>
		            	<#list hotelChainBrands as chainBrand>
		            		<#if chainBrand_index lt 4>
		            			<li>
		            				<label>
		            					<input type="checkbox" name="chainBrandIds" val="<#if (chainBrand.labelMeta.labelSc)?? && (chainBrand.labelMeta.labelSc)?length gt 1>${(chainBrand.labelMeta.labelSc)!}<#else>${(chainBrand.name)!}</#if>" value="${(chainBrand.key)!}" model="#chainBrandParams">
		            					<#if (chainBrand.labelMeta.labelSc)?? && (chainBrand.labelMeta.labelSc)?length gt 1>
		            						${(chainBrand.labelMeta.labelSc)!}
		            					<#else>${(chainBrand.name)!}
			            				</#if>
			            			</label>
			            		</li>
		            		</#if>
		            	</#list>
		              <li>
		              	<a onclick="openMore('#more_brand');" href="javascript:;">更多品牌<i class="ico_down"></i></a>
		           	 </li>
		           	 <li>
		           	 	<div id="more_brand" class="more_brand" style="width: 345px; position: absolute; z-index: 10; display: none; border-top:1px solid #eee;">
							<ul class="brand_list">
								<#list hotelChainBrands as chainBrand>
									<#if chainBrand_index gte 4>
										<li  style="width:310px; padding:-2px;">
											<label>
												<input type="checkbox" name="chainBrandIds" val="<#if (chainBrand.labelMeta.labelSc)?? && (chainBrand.labelMeta.labelSc)?length gt 1>${(chainBrand.labelMeta.labelSc)}<#else>${(chainBrand.name)!}</#if>" value="${(chainBrand.key)!}" model="#chainBrandParams">
												<#if (chainBrand.labelMeta.labelSc)?? && (chainBrand.labelMeta.labelSc)?length gt 1>
		            								${(chainBrand.labelMeta.labelSc)!}
		            							<#else>${(chainBrand.name)!}
			            						</#if>
											</label>
										</li>
									</#if>
								</#list>
									<div class="clear"></div>
							</ul>
							<div class="tail" style="text-align:right;">
								<a class="button_secondary adaptiveButton medium" style="vertical-align: middle" onclick="closeMore('#more_brand')" href="javascript:;">
									<span class="left"></span>
									<span class="center">确 定</span>
									<span class="right"></span>
								</a>
							</div>
						</div>
		           	 
		           	 </li>
	            </ul>
	        </li>
	        </#if>
	        <li class="col" style="border:none;padding:0px;">
	            <h3>设施</h3>
	            <ul>
	                <#list hotelFacilitys as ls>
						<#if (ls_index <= 4)>
							<li >
								<label>
									<input type="checkbox" name="facilitys" val="${(ls.name)!}" value="${(ls.name)!}" model="#facilityParams">
									${(ls.name)!}
								</label>
							</li>
						</#if>
					</#list>
	                <li>
	                	<a onclick="openMore('#more_facility');" href="javascript:;">更多设施<i class="ico_down"></i></a>
	                </li>
	                <li>
	                	<div id="more_facility" class="more_tools" style="width: 300px; position: absolute; z-index: 10; top: 366px; left: 1042px; display: none; border-top:1px solid #eee;">
							<ul class="tools_list">
								<#list hotelFacilitys as ls>
									<#if ls_index gt 4>
										<li style="float:left;width:115px; padding:-2px;">
											<label>
												<input type="checkbox" name="facilitys" val="${(ls.name)!}" value="${(ls.name)!}" model="#facilityParams">${(ls.name)!}
											</label>
										</li>
									</#if>
								</#list>
								<div class="clear"></div>
							</ul>
							<div class="tail" style="text-align:right;">
								<a class="button_secondary adaptiveButton medium" style="vertical-align: middle" onclick="closeMore('#more_facility')" href="javascript:;">
									<span class="left"></span>
									<span class="center">确 定</span>
									<span class="right"></span>
								</a>
							</div>
						</div>
	                </li>
	            </ul>
	        </li>
	    </ul>
	
	</div>
	
<!--	<div id="btn_toggleFilterPanel">
	    收起
	</div>-->
<div>