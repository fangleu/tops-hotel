 <#list hotelOrderBo.hotelPriceDetailBos as ls>
 	<input type="hidden" name="hotelPriceDetailBos[${ls_index}].sellingFee" value="${(ls.sellingFee)!}" />
 	<input type="hidden" name="hotelPriceDetailBos[${ls_index}].basicFee" value="${(ls.basicFee)!}" />
 	<input type="hidden" name="hotelPriceDetailBos[${ls_index}].markupFee" value="${(ls.markupFee)!}" />
 	<input type="hidden" name="hotelPriceDetailBos[${ls_index}].addedServiceFee" value="${(ls.addedServiceFee)!}" />
 	<input type="hidden" name="hotelPriceDetailBos[${ls_index}].breakfastName" value="${(ls.breakfastName)!}" />
 	<input type="hidden" name="hotelPriceDetailBos[${ls_index}].breakfastNo" value="${(ls.breakfastNo)!}" />
 	<input type="hidden" name="hotelPriceDetailBos[${ls_index}].isWifi" value="${(ls.isWifi)?string}" />
 	<input type="hidden" name="hotelPriceDetailBos[${ls_index}].checkinDate" value="${(ls.checkinDate)?date}" />
 	<input type="hidden" name="hotelPriceDetailBos[${ls_index}].roomNo" value="${(ls.roomNo)!}" />
 	<input type="hidden" name="hotelPriceDetailBos[${ls_index}].other" value="${(ls.other)!}" />
 </#list>
<#--  	<div>
		 <tr>
            <th style="vertical-align:top;">房费信息:</th>
            <td>
			<#if (hotelOrderBo.hotelPriceDetailBos)??>
				<#assign Maps={"0":0,"1":1,"2":2,"3":3,"4":4,"5":5,"6":6,"7":7,"8":8,"9":9}>
                <#assign weekMaps={"0":"周日","1":"周一","2":"周二","3":"周三","4":"周四","5":"周五","6":"周六"} >            
				<#assign start = 0 />
				<#assign end = (criteria.validDates?size)/7-1 />
				<#list start..end as i>
                <table class="room_fee">
                    <thead>
                        <tr>
                            <th>第${i+1}周</th>
                            <#list 7*i..(7*(i+1)-1) as index>
                            	<#if (criteria.validDates[index])??>
                            		 <#assign monthAndDay=(criteria.validDates[index])?substring(0,10)>
						             <#assign century=((monthAndDay)?substring(0,2))>
						             <#assign y=(Maps[monthAndDay?substring(2,3)]*10+Maps[monthAndDay?substring(3,4)])>
						             <#assign c=(Maps[monthAndDay?substring(0,1)]*10+Maps[monthAndDay?substring(1,2)])>
						             <#assign m=(Maps[monthAndDay?substring(5,6)]*10+Maps[monthAndDay?substring(6,7)])>
						             <#assign d=(Maps[monthAndDay?substring(8,9)]*10+Maps[monthAndDay?substring(9,10)])>
						             <#if m=1>
							              <#assign m=13>
							              <#assign y=y-1>
						             <#elseif m=2>
							              <#assign m=14>
							              <#assign y=y-1>
						              </#if>
						             <#assign week=(y+(y/4)?int+(c/4)?int-2*c+((26*(m+1))/10)?int+d-1)%7>
							         <#if (week<0)>
							         	<#assign week=week+7>
							         </#if>
							         <th <#if (week?c=="0")>class="weekend last"</#if><#if (week?c=="6")>class="weekend"</#if>>
						             ${(m)!}-${(d)!}${(weekMaps[week?c])!} 		             
						             </th>  
                                  </#if>
                            </#list>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td class="first">
                                <div>单价</div>
                                <div>早餐</div>
                                <div>宽带</div>
                            </td>
  		<#list 7*i..(7*(i+1)-1) as index>
  		<td>
  			<#list hotelOrderBo.hotelPriceDetailBos as ls>
  				<#if (ls.sellingFeeYuan)?? && (criteria.validDates[index] == ls.checkinDate?string("yyyy-MM-dd"))>
				<div class="price">&yen;${ls.sellingFeeYuan}</div>
                <div>
                <#if (ls.breakfastNo)??>
            		<#if ls.breakfastNo == 0>
            			无早
          			<#elseif (ls.breakfastNo == 10) || (ls.breakfastNo < 0)>
          				含早
          			<#else>
          				${(ls.breakfastNo)!}份&nbsp;
          			</#if>
          		<#else>
          			-
          		</#if>
                </div>
                <div><#if (ls.isWifi)??><#if (ls.isWifi)?string == 'true'>有<#else>无</#if><#else>-</#if></div>
                <#else>
                 <div class="price"></div>
                 <div></div>
                 <div></div>
  				</#if>
  			</#list>
  		</td>
  		</#list>
                        </tr>
                    </tbody>
                    <#if i == end>
                    <tfoot>
                        <tr>
                            <th class="first">合计</th>
                            <th colspan="7" class="price total last">&yen;<span id="hotelFee">${(hotelOrderBo.totalMarkupFeeYuan?eval) + (hotelOrderBo.totalBasicFeeYuan?eval)}</span></th>
                            <input type="hidden" id="oneRoomHotelFee" value="${(hotelOrderBo.totalMarkupFeeYuan?eval) + (hotelOrderBo.totalBasicFeeYuan?eval)}" />
                        </tr>
                    </tfoot>
                    </#if>
                </table>
                </#list>
             </#if>  
            </td>
        </tr>
        </div>-->
<div class="row gray-border" style="display:none">
    <h3 class="title"><strong>增值服务</strong></h3>
    <section>
        <table class="addon" style="width:100%">
        	         <colgroup>
                    </colgroup>
            <thead>
                <tr>
                	<th class="first"><span class="tt">日期</span></th>
                	<#if (hotelOrderBo.hotelAddServicePriceDetailBos)??>
                	<#list hotelOrderBo.hotelAddServicePriceDetailBos as ls>
                    <th>
                   
                        <div class="tt">${(ls.addServiceName)!}<strong>&yen;<span class="ttPrice">${(ls.addServiceUnitCostYuan)!}</span></strong>人</div>
                        <div class="op">
                        	
                        	 <#if (ls.addServiceName)??>
                    			<#if (ls.addServiceName?string?contains("早餐"))>
                    			<label> <input type="checkbox" value="col${ls_index}_checkall" class="people"/></label>
	                            <span style="display:none">
	                                <a class="minus" href="javascript:void(0)">-</a>
	                                <input type="text" class="gray-border" readonly style="width:30px;height:20px;text-align:center;" value="1" name="num" />
	                                <a class="add_up" href="javascript:void(0)">+</a>	                                
	                            </span>
	                            <#else>
	                            <label> <input type="checkbox" value="col${ls_index}_checkall" class="room"/></label>
                            	</#if>
                    		 </#if>
                    		
                        </div>
                    </th>         
                    </#list>
                    </#if>
                    <th class="last"><span class="tt">小计</span></th>
                    
                </tr>
            </thead>
            <tbody>
            	<#if (hotelOrderBo.hotelAddServicePriceDetailBos)??>
            	<#list hotelOrderBo.hotelPriceDetailBos as ls>
            	<#if (ls.sellingFeeYuan)??>
                <tr>
                	<td class="first">
                	 <#assign monthAndDay=(ls.checkinDate)?string("yyyy-MM-dd")>
		             <#assign century=((monthAndDay)?substring(0,2))>
		             <#assign y=(Maps[monthAndDay?substring(2,3)]*10+Maps[monthAndDay?substring(3,4)])>
		             <#assign c=(Maps[monthAndDay?substring(0,1)]*10+Maps[monthAndDay?substring(1,2)])>
		             <#assign m=(Maps[monthAndDay?substring(5,6)]*10+Maps[monthAndDay?substring(6,7)])>
		             <#assign d=(Maps[monthAndDay?substring(8,9)]*10+Maps[monthAndDay?substring(9,10)])>
		             <#if m=1>
			              <#assign m=13>
			              <#assign y=y-1>
		             <#elseif m=2>
			              <#assign m=14>
			              <#assign y=y-1>
		              </#if>
		             <#assign week=(y+(y/4)?int+(c/4)?int-2*c+((26*(m+1))/10)?int+d-1)%7>
			         <#if (week<0)>
			         	<#assign week=week+7>
			         </#if>
		             ${(m)!}-${(d)!}&nbsp;${(weekMaps[week?c])!} 
                	</td>
                	<#if (hotelOrderBo.hotelAddServicePriceDetailBos)??>
	                    <#list hotelOrderBo.hotelAddServicePriceDetailBos as addServicePriceDetail>
	                    <td>
	                    <#if (addServicePriceDetail.addServiceName)??>
	                    	<#if (addServicePriceDetail.addServiceName?string?contains("早餐"))>
	                       	 	<div class="acell">&yen;<input type="text" style="width:20px;" readonly value="${(addServicePriceDetail.addServiceUnitCostYuan)!}" name="" class="num_of_price"/> x <input type="text" style="width:20px;" readonly value="1" name="num_of_person" class="num_of_person"/>人 x <input type="text" readonly style="width:15px;" name="" class="num_of_room" value="${(hotelOrderBo.hotelDetailBo.roomNo)!0}"/>间</div>
	                        <#else>
	                        	<div class="acell">&yen;<input type="text" style="width:20px;" readonly value="${(addServicePriceDetail.addServiceUnitCostYuan)!}" name="" class="num_of_price"/> x <input type="text" readonly  style="width:20px;" value="${(hotelOrderBo.hotelDetailBo.roomNo)!0}" name="" class="num_of_room"/>间</div>
	                       </#if>
	                       <#if (ls_index==0)>
	                       <input type="hidden" value="${(addServicePriceDetail.addServiceUnitCostYuan)!}" name="hotelAddServicePriceDetailBos[${addServicePriceDetail_index}].addServiceUnitCostYuan" />
	            	   	   <input type="hidden" value="${(hotelOrderBo.hotelDetailBo.roomNo)!}" name="hotelAddServicePriceDetailBos[${addServicePriceDetail_index}].roomNo" />
	                       <input type="hidden" name="hotelAddServicePriceDetailBos[${addServicePriceDetail_index}].addServiceName" value="${(addServicePriceDetail.addServiceName)!}"/> 
	                       <input type="hidden" name="hotelAddServicePriceDetailBos[${addServicePriceDetail_index}].isAddService" value="${(addServicePriceDetail.isAddService)!}"/>
	                       <input type="hidden" name="hotelAddServicePriceDetailBos[${addServicePriceDetail_index}].addServiceNo" value="${(addServicePriceDetail.addServiceNo)!}"/>
	                       <input type="hidden" name="hotelAddServicePriceDetailBos[${addServicePriceDetail_index}].addServiceCost" value="${(addServicePriceDetail.addServiceCost)!}"/>
	                       </#if>      
	                    </#if>
	                    </td>      
	                    </#list>
	                 </#if>
                    <td class="last">
                        <div class="t_orange">&yen;<input type="text" readonly style="width:40px;" name="sub_total" value="0"/></div>
                    </td>
                </tr>
                </#if>
                </#list>
               </#if>
            </tbody>
            <tfoot>
                <tr>
                    <td>合计</td>
                    <td colspan="<#if (hotelOrderBo.hotelAddServicePriceDetailBos)??>${hotelOrderBo.hotelAddServicePriceDetailBos?size+1}</#if>" class="price total t_orange">&yen;<input id="totalAddService" type="text" name="total" value="0" style="border:0;width:50px;"/></td>
                </tr>
                
            </tfoot>
        </table>

    </section>
</div>
