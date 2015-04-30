<div class="headline">
	<h3>城市天气早知道</h3>
	<ul><#list cityList as city><li onclick="getCityWeather('${city}')" <#if cityName="${city}">class="pitch-on"</#if>>${city}</li></#list></ul>
</div>
<ul class="weather_ul">
	<#list cityWeather.beans as dayWeather>
		<li <#if dayWeather_index=0 || !dayWeather_has_next>class="grey_bg"</#if>>
			<ul>
				<li><#if dayWeather_index=0><em>今日</em></#if> ${dayWeather.date} ${dayWeather.week}</li>
				<li>${dayWeather.temp}</li>
				<li>${dayWeather.weather}</li>
				<li>${dayWeather.wind}</li>
			</ul>
		</li>
	</#list>
</ul>