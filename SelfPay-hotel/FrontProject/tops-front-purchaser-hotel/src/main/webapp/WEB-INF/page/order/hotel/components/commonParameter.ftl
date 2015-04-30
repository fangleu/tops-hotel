<#--获取日期的星期-->
<#--return week:返回星期数字-->
<#function getWeek date>
	<#assign Maps={"0":0,"1":1,"2":2,"3":3,"4":4,"5":5,"6":6,"7":7,"8":8,"9":9}>
	<#assign monthAndDay=date>
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
	<#return week>
</#function>
