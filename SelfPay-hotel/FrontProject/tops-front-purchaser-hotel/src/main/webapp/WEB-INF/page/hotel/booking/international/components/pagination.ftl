<#macro pagination curPage=1 allCount=0 pageSize=2 action="" >
<#if (allCount%pageSize) gt 0>
<#assign pageCount=(allCount / pageSize)?int +1 />
<#else>
<#assign pageCount=(allCount / pageSize)?int/>
</#if>
<div class="pagination clearfix" style="float:none">
  <ul>
  		<#if curPage != 1>
        <li><a href="#" name="${curPage-1}&${pageSize}">上一页</a></li>
        </#if>
        <#assign fPage = curPage - 4 />
        <#assign nPage = curPage + 4 />
        <#if (fPage < 1)>
            <#assign fPage = 1 />
        </#if>
        <#if (nPage > pageCount)>
            <#assign nPage = pageCount />
        </#if>
        <#list fPage..nPage as i>
        	<li>
        	 <#if curPage == i>
                <a href="javascript:void(0);" class="present" name="${i}&${pageSize}">${i}</a>
            <#elseif i!=0>
            <#--<a href="${action}?page=${i}&pageSize=${pageSize}" name="${pageSize}">
            ${i}</a>-->
                <a href="javascript:void(0);" name="${i}&${pageSize}">${i}</a>
            </#if>
            </li>
        </#list>
        <#if curPage != pageCount>
        	<#if pageCount!=0>
                 <li><a href="javascript:void(0);"  name="${curPage+1}&${pageSize}">下一页</a> </li>
            </#if>
        </#if>
  </ul>
  <div>
        <span>共<em>${allCount}</em>条记录 &nbsp;&nbsp;&nbsp;共${pageCount}页</span>
		<input id="pageNo" name="pageNo" type="hidden" value=""/>
  </div>
</div>
</#macro>