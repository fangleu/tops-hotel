<#-- 地图 -->

<#macro map hotelOrder>
<#if (hotelOrder.hotelDetail.hotelAddress)??>
<div id="map" class="layerGrayContainer_noArrow" style="position:absolute;z-index:110;display:none;width:480px;height:300px;background-color: #ffffff">
        <div id="dituContent" class="inner_img map" city="${(hotelOrder.hotelOrder.cityName)!}" address="${(hotelOrder.hotelDetail.hotelAddress)!}" style="width:480px;height:300px"></div>
</div>
</#if>
</#macro>