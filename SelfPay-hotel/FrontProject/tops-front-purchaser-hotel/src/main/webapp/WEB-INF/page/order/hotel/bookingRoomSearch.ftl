<#import "/common/userContainer.ftl" as uc/>
<@uc.container	jsFiles=['common/autoComplete.js','page/order/hotel/searchBookingRoomDetails.js']
				cssFiles=['css/store_ucenter.css']
				localCssFiles=[]
				menuItem="用户中心"
				userItem="酒店业务">
 <main class="us-content">
        <h1 class="us-title">酒店业务报表</h1>
        <div class="us-body">
            <aside>
                <ul class="tab_change clearfix">
                    <li class="currentState"><em><a href="javascript:void(0);">订房明细</a></em></li>
                           
                </ul>
            </aside>
            <div class="tab_item">
                <!-- 查询 -->
                <form id="bookingRoomSearchForm" action="${basepath}/order/report/hotel/seachDetail" method="POST">
                <section class="us-query clearfix" style="padding-bottom:30px;">
                      <div class="ureport">
                          <table class="us-query-table fixed-table us-query-report">
                              <colgroup>
                                  <col width="60">
                                  <col width="578">
                              </colgroup>
                              <tr>
                                  <th>入住日期：</th>
                                  <td><input class="datepicker dpUlt" id="" dateStartNoStart type="text" name="checkInBeginDateStr" style="width:138px;"><em> — </em><input class="datepicker dpUlt" id="" dateStartNoEnd type="text" name="checkInEndDateStr" style="width:138px;"></td>
                              </tr>
                              <tr>
                                  <th>离店日期：</th>
                                  <td><input class="datepicker dpUlt" id="" dateStartNoStart type="text" name="checkOutBeginDateStr" style="width:138px;"><em> — </em><input class="datepicker dpUlt" id="" dateStartNoEnd type="text" name="checkOutEndDateStr" style="width:138px;"></td>
                              </tr>
                              <tr>
                                  <th>下单日期：</th>
                                  <td><input class="datepicker dpUlt" id="" dateStartNoStart type="text" name="bookingBeginDateStr" style="width:138px;"><em> — </em><input class="datepicker dpUlt" id="" dateStartNoEnd type="text" name="bookingEndDateStr" style="width:138px;"></td>
                              </tr>
                          </table>
                    </div>
                    <a class="adaptiveButton brightRed_btn" href='javascript:;' style="vertical-align:bottom; margin:100px 0 0 26px" id="searchButton">
                        <span class="left"></span>
                        <span class="center" style="font-size:14px">搜&nbsp;索</span>
                        <span class="right"></span>
                    </a>
                    
                    <table class="us-query-table fixed-table">
                        <colgroup>
                            <col width="60">
                            <col width="160">
                            <col width="60">
                            <col width="160">
                            <col width="60">
                            <col width="160">
                        </colgroup>
                        <tr>
                            <th>城市：</th>
                            <td><input id="" style="width:120px;" type="text" name="cityName"></td>
                            <th>付款方式：</th>
                            <td>
                                <select data-role="dropdownlist" style="width:126px" id="" name="payWay">
      								<option selected="" value="">全部</option>
									<#list payTypes?keys as payTypeKey>
                                    <option value="${payTypeKey}">${payTypes[payTypeKey]}</option>
                                    </#list>
                                </select>
                            </td>
                            <th>订单类型：</th>
                            <td>
                                <select data-role="dropdownlist" style="width:126px" id="" name="orderType">
									<option selected="" value="">全部</option>
									<option value="NORMAL">正常</option>
									<option value="ENDORSE">变更</option>
									<option value="REFUND">退订</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <th>操作员：</th>
                            <td><input id="" style="width:120px;" type="text" name="creatorName"></td>
                            <th>酒店名称：</th>
                            <td><input id="" style="width:120px;" type="text" name="hotelName"></td>
                            <th>付款状态：</th>
                            <td> <select data-role="dropdownlist" style="width:126px" id="" name="payStatus">
									<option selected="" value="">全部</option>
									<#list payQueryStates?keys as payQueryStateKey>
                                    <option value="${payQueryStateKey}">${payQueryStates[payQueryStateKey]}</option>
                                    </#list>
                                </select></td>
                        </tr>
                        <tr>
                          <th>订单号：</th>
                            <td><input id="" style="width:120px;" type="text" name="orderId"></td>
                            <th>入住人：</th>
                            <td><input id="" style="width:120px;" type="text" name="guestName"></td>
                           
                        </tr>
                    </table>
                    
                </section>
                </form>
   			<!-- 无搜索结果 --> 
              <section class="us-noresult">
              	<div class="us-noresult_img"></div>
               	<div class="us-noresult_con">请输入您的筛选条件，并点击搜索</div>
              </section>
             
               <section class="us-result" style="display:none;">
               	<div class="us-result_tit"><span><a href='javascript:;' id="exportExcelButton">导出Excel</a></span>正常票：<em id="normalCount">0</em>变更：<em id="endorseCount">0</em>退订：<em id="refundCount">0</em>
               	</div>
               	   	<div id="searchResultTab"></div>
                
               </section>
            </div>
            <div style="clear:both;"></div>
        </div>
    </main>
</section>
</@uc.container>
