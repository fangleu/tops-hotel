<style type="text/css">
        .pagination{line-height:1}
</style>

<div class="filterCondition clearfix">
	<label style="float:left;width:70px; margin-top:10px; vertical-align: middle">筛选酒店：</label>
	<#if (conditions)??>
	<#list conditions as ls>
	    <div class="item" style="width:auto;">
			<span>${ls}</span><a class="close" href="javascript:;" onclick="delFilterItem('${ls}')" style="margin-left:8px;">x</a>
	    </div>
    </#list>
    </#if>
    <a href="javascript:;" class="clearAllFilterCondition" onclick="delModelAllItems()" style="text-decoration:none;">【清空筛选条件】</a>
</div>
<div class="hotel_resultInfo clearfix">
    <span class="hotel_resultNo">${page.totalItemCount}</span><span>条搜索结果</span>
    <span class="hotel_result_sortButtonContainer">
    <input type="hidden" id="sortItem" name="sortItem" value=""/>
	<input type="hidden" id="sortOrder" name="sortOrder" value=""/>
        <span class="moren" style="cursor:pointer;">默认</span>
        <span id="priceOrder" style="" class="sorttab 
        <#if (criteria.sortItem)?? && criteria.sortItem=="1"><#if criteria.sortOrder=="0">ascend<#elseif criteria.sortOrder=="1">descend</#if></#if>" 
        sortItem="1" sortOrder="<#if (criteria.sortItem)??><#if criteria.sortItem=="1">${(criteria.sortOrder)!}</#if></#if>">价格<i style="position:static;"></i>
        </span>
        <span id="startOrder" class="sorttab <#if (criteria.sortItem)?? && criteria.sortItem=="0"><#if criteria.sortOrder=="0">ascend<#elseif criteria.sortOrder=="1">descend</#if></#if>"
         sortItem="0" sortOrder="<#if (criteria.sortItem)??><#if criteria.sortItem=="0">${(criteria.sortOrder)!}</#if></#if>">星级<i style="position:static;"></i>
        </span>
    </span>
    <div class="pagination" style="width:auto;margin:7px 0;display:inline">
        <ul style="margin:10px 10px 0 20px;">
            <li class="pageNoCAT">${page.pageNo}/${page.totalPageCount}</li>
        	<#if page.pageNo != 1>
        		<li><a style="line-height:;" name="${page.pageNo-1}&${page.pageSize}">上一页</a></li>
        	</#if>
            <#if page.pageNo != page.totalPageCount>
            	<li><a style="line-height: ;" name="${page.pageNo+1}&${page.pageSize}">下一页</a></li>
            </#if>
        </ul>
    </div>
</div>
