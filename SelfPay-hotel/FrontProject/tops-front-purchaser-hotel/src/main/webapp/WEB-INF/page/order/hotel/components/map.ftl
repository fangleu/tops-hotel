<#-- 地图 -->

<#macro map hotelOrderVo>
<#if (hotelOrderVo.hotelDetailBo.hotelAddress)??>
<div id="map" class="layerGrayContainer_noArrow" style="position:absolute;z-index:110;width:480px;height:300px;background-color: #ffffff">
        <div id="dituContent" class="inner_img map" city="${(hotelOrderVo.hotelOrder.cityName)!}" address="${(hotelOrderVo.hotelDetailBo.hotelAddress)!}" style="width:480px;height:300px"></div>
</div>
</#if>
</#macro>