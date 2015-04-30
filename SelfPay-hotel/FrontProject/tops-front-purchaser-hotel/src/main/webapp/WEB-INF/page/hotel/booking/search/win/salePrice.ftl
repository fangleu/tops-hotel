<#macro salePrice id validDates  bookingClassDailyRates=[]>
<div id="${(id)!}" style="display:none; position:absolute; left:0px; top:0px; background-color:#000000; z-index:2; width:500px;">
	<#if (validDates)??>
	<#assign Maps={"0":0,"1":1,"2":2,"3":3,"4":4,"5":5,"6":6,"7":7,"8":8,"9":9}>
    <#assign weekMaps={"0":"周日","1":"周一","2":"周二","3":"周三","4":"周四","5":"周五","6":"周六"} >            
	<#assign start = 0 />
	<#assign end = (validDates?size)/7-1 />
	<#list start..end as i>
    <div class="salePriceTblContainer" style="color:#666666;font-size: 12px;height:130px; width:500px;border: 1px solid #DCC791;">
        <table class="hotelSalePriceTbl" style="width:500px;">
            <thead>
            <tr>
                <th class="first" style="width:36px;"><b>第${i+1}周</b></th>
                
                <#list 7*i..(7*(i+1)-1) as index>
            
	              <#if (validDates[index])??>
	              
		             <#assign monthAndDay=(validDates[index])?substring(0,10)>
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
			         <th <#if (week?c=="0")>class="weekend last"</#if><#if (week?c=="6")>class="weekend"</#if>  style="text-align:center; width:50px;" nowrap>
			             <#--${(m)!}-${(d)!}-->${(weekMaps[week?c])!} 
		             </th>  
	             </#if>
		
			</#list>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td class="first" style="vertical-align:top; padding:5px 0px;height:70px; width:36px;font-size: 12px;">
                    <div><b>单价</b></div>
                    <div><b>早餐</b></div>
                    <div><b>宽带</b></div>
                </td>
                
             <#list 7*i..(7*(i+1)-1) as index>
             	<#if (index < bookingClassDailyRates?size)>
             <td style="vertical-align:top; padding:5px 0px; width:50px;font-size: 12px;">
             	<div class="price" style="text-align:center; padding:5px 0px; height:18px;" >
		            <#if (bookingClassDailyRates[index].sellingRate)??>
		            	<#if bookingClassDailyRates[index].sellingRate != 0>
			            	&yen;${(bookingClassDailyRates[index].sellingRate)!}
		            	<#else>
		            	</#if>
		            </#if>
	             </div>
	             <div style="text-align:center; padding:5px 0px; height:18px;" >
		             <#if (bookingClassDailyRates[index].breakFastOptionalService[0].number)??>
		             <#assign breakFastNumber = bookingClassDailyRates[index].breakFastOptionalService[0].number />
		             	<#if breakFastNumber == 0>
		             	无早
		             	<#elseif (breakFastNumber == 10) || (breakFastNumber < 0) >
		             	含早
		             	<#else>
		             	${breakFastNumber}份
		             	</#if>
		             <#else>
		             -
		             </#if>
	             </div>
	            <div style="text-align:center; padding:5px 0px; height:18px;" >
					<#if (bookingClassDailyRates[index].internetOptionalService)??>
					包含
					<#else>
					-
					</#if>
	            </div>
	         </td>
	         </#if>
			</#list>
            </tr>
            </tbody>
    
        </table>
    </div>
    </#list>
    </#if>
</div>
</#macro>