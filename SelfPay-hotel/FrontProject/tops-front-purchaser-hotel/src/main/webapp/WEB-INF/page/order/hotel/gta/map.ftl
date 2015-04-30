<#-- GTA地图 -->
<#macro map hotel>
<div id="map" class="layerGrayContainer_noArrow" style="display:none;position:absolute;">
    <div style="margin: 0 auto" class="inner_img">
    <#if hotel?? && (hotel.photos)??>
		<#list hotel.photos?keys as photo>
		<#if photo=="mapLink">
		<iframe frameborder="0" width="480px" height="238px" scrolling="no" src="${(hotel.photos[photo])!}" ></iframe>
		</#if>
		</#list>
    </#if>
    </div>
</div>
</#macro>