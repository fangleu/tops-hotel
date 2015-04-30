<#import "/common/mainContainer.ftl" as mc/>
<@mc.container jsFiles=["common/cityWeather.js"] cssFiles=[]>
<!-- 首页 start -->
<div class="bannerAndTicket">
    <div class="banner">
        <img src="${host}/images/banner/procurer_banner1.jpg"/>
    </div>
    <div class="ticket">
        <ul>
           <li class="ticket_li"> <em>&nbsp;</em> 国内机票 </li>
           <li> <i>&nbsp;</i> 国际机票</li>
           <li> <span>&nbsp;</span> 酒店</li>
        </ul>
        <div class="range">
           <div class="cut">
              <b>航程类型：</b>
              <label> <input type="radio" name="cut"/> 单程</label>
              <label> <input type="radio" name="cut"/> 往返</label>
           </div>
           <table cellpadding="0" cellspacing="0" class="range_tab">
             <colgroup>
               <col width="35px"/>
               <col width="20px"/>
               <col width="200px"/>
               <col width="25px"/>
             </colgroup>
              <tr>
                 <td rowspan="2"><b>城<br>市</b></td>
                 <td>从</td>
                 <td><input type="text" class="gray-border" value="上海" /></td>
                 <td rowspan="2">
                     <i> </i> <em>换</em> <span> </span>
                 </td>
              </tr>
              <tr>
                 <td>到</td>
                 <td><input type="text" class="gray-border" value="北京" /></td>
              </tr>
              <td rowspan="2"><b>日<br>期</b></td>
                 <td>去</td>
                 <td><input type="text" class="datepicker gray-border" value=" " /></td>
                 <td rowspan="2">&nbsp;  </td>
              </tr>
              <tr>
                 <td>回</td>
                 <td><input type="text" class="datepicker gray-border" value=" " /></td>
              </tr>
           </table>
           <div class="search_btn">
               <a class="adaptiveButton brightRed_btn">
                      <span class="left"> &nbsp; </span>
                      <span class="center">搜索</span>
                      <span class="right"> &nbsp; </span>
               </a>
           </div>
        </div>
    </div>
</div>
<div class="specialAndInquiries"> <!--specialAndInquiries start-->
    <div class="preference"> <!--preference start-->
        <div class="headline">
            <h3>热门城市酒店特惠</h3>
            <ul>
               <li class="pitch-on">上海</li>
               <li>广州</li>
               <li>深圳</li>
               <li>武汉</li>
               <li>更多</li>
            </ul>
        </div>
        <div class="showImg clearfix"> <!--showImg start-->
            <div class="showBigImg">
               <em> &nbsp;</em>
               <div>
                    <div><a href="#"><img src="${host}/images/store/showImg1.jpg" title="上海宝华国际酒店"></a></div>
                    <div class="state">
                        <p>上海宝华国际酒店</p>
                        <span>￥450</span>
                    </div>
               </div>
            </div>
            <div class="showright pull-right">
                <dl>
                   <dt> <a href="#"><img src="${host}/images/store/showright1.jpg" title="上海宝华国际酒店"/></a> </dt>
                   <dd>
                      <p> <a href="#">上海宝华国际酒店</a></p>
                      <span>￥450</span>
                   </dd>
                </dl>
                <dl>
                   <dt> <a href="#"><img src="${host}/images/store/showright2.jpg" title="上海宝华国际酒店"/></a> <em>&nbsp;</em> </dt>
                   <dd>
                      <p> <a href="#">上海宝华国际酒店</a></p>
                      <span>￥450</span>
                   </dd>
                </dl>
                <dl>
                   <dt> <a href="#"><img src="${host}/images/store/showright3.jpg" title="上海宝华国际酒店"/></a> </dt>
                   <dd>
                      <p> <a href="#">上海宝华国际酒店</a></p>
                      <span>￥450</span>
                   </dd>
                </dl>
                <dl style="margin-bottom:0;">
                   <dt> <a href="#"><img src="${host}/images/store/showright4.jpg" title="上海宝华国际酒店"/></a> <em>&nbsp;</em> </dt>
                   <dd>
                      <p> <a href="#">上海宝华国际酒店</a></p>
                      <span>￥450</span>
                   </dd>
                </dl>
                <dl style="margin-bottom:0;">
                   <dt> <a href="#"><img src="${host}/images/store/showright5.jpg" title="上海宝华国际酒店"/></a> </dt>
                   <dd>
                      <p> <a href="#">上海宝华国际酒店</a></p>
                      <span>￥450</span>
                   </dd>
                </dl>
                <dl style="margin-bottom:0;">
                   <dt> <a href="#"><img src="${host}/images/store/showright6.jpg" title="上海宝华国际酒店"/></a> <em>&nbsp;</em> </dt>
                   <dd>
                      <p> <a href="#">上海宝华国际酒店</a></p>
                      <span>￥450</span>
                   </dd>
                </dl>
            </div>
        </div>  <!--showImg emd-->
        <div class="advert">
          <a href="#"> <img src="${host}/images/store/advert1.jpg"/> </a>
        </div>
        <div class="airline"> <!--airline start-->
            <div class="headline">
                 <h3>国际K位特惠</h3>
                 <ul>
                    <li class="pitch-on">东南亚航线</li>
                    <li>日本航线</li>
                    <li>港澳航线</li>
                    <li>更多</li>
                </ul>
            </div>
            <ul class="airline_ul clearfix">
               <li>
                  <div>
                      <span>厦门航空</span>
                       <label>  <em>成功率</em> <i>90%</i> </label>
                  </div>
                  <p>
                      <strong>日本航线</strong>
                      <strong>东南亚航线</strong>
                  </p>
               </li>
               <li>
                  <div>
                      <span>厦门航空</span>
                       <label>  <em>成功率</em> <i>90%</i> </label>
                  </div>
                  <p>
                      <strong>日本航线</strong>
                      <strong>东南亚航线</strong>
                  </p>
               </li>
               <li style="margin-right:0;">
                  <div>
                      <span>厦门航空</span>
                       <label>  <em>成功率</em> <i>90%</i> </label>
                  </div>
                  <p>
                      <strong>日本航线</strong>
                      <strong>东南亚航线</strong>
                  </p>
               </li>
               <li>
                  <div>
                      <span>厦门航空</span>
                       <label>  <em>成功率</em> <i>90%</i> </label>
                  </div>
                  <p>
                      <strong>日本航线</strong>
                      <strong>东南亚航线</strong>
                  </p>
               </li>
               <li>
                  <div>
                      <span>厦门航空</span>
                       <label>  <em>成功率</em> <i>90%</i> </label>
                  </div>
                  <p>
                      <strong>日本航线</strong>
                      <strong>东南亚航线</strong>
                  </p>
               </li>
               <li style="margin-right:0;">
                  <div>
                      <span>厦门航空</span>
                       <label>  <em>成功率</em> <i>90%</i> </label>
                  </div>
                  <p style="margin-right:0;">
                      <strong>日本航线</strong>
                      <strong>东南亚航线</strong>
                  </p>
               </li>
            </ul>
        </div> <!--airline end-->
        <div class="weather clearfix"> <!--weather start-->
        </div> <!--weather end-->
    </div> <!--preference end-->
    <div class="enquiry"> <!--enquiry start-->
         <div class="dimension clearfix">
             <dl>
                 <dt><img src="${host}/images/store/two-dimension.png" /></dt>
                 <dd>
                    <h3>天地行官方微信</h3>
                    <p>微信扫描二维码，随时随地与天地行亲密接触。</p>
                    <p>订单状态，特价特惠触手可得。</p>
                 </dd>
             </dl>
             <ul class="commitment">
                 <li>
                    <img src="${host}/images/store/safe.png"/>
                    <span>安心购票</span>
                 </li>
                 <li>
                    <img src="${host}/images/store/card.png"/>
                    <span>航协认证</span>
                 </li>
                 <li>
                    <img src="${host}/images/store/low.png"/>
                    <span>低价承诺</span>
                 </li>
            </ul>
       </div>
       <div class="inland clearfix">
          <h3>国内机票询价</h3>
          <dl>
             <dt> <a href="#"><img src="${host}/images/store/inlandgroup.png" title="团队申请"/></a> </dt>
             <dd> <a href="#">团队申请</a> </dd>
          </dl>
          <dl>
             <dt> <a href="#"><img src="${host}/images/store/officialbusiness.png" title="公务机申请"/></a> </dt>
             <dd> <a href="#">公务机申请</a> </dd>
          </dl>
      </div>
      <div class="inland clearfix">
          <h3>国际机票询价</h3>
          <dl>
             <dt> <a href="${basepath}/flighti18n2groupapply"><img src="${host}/images/store/inlandgroup.png" title="团队申请"/></a> </dt>
             <dd> <a href="${basepath}/flighti18n2groupapply">团队申请</a> </dd>
          </dl>
          <dl>
             <dt> <a href="${basepath}/flighti18n2kapply"><img src="${host}/images/store/k-place.png" title="K位申请"/></a> </dt>
             <dd> <a href="${basepath}/flighti18n2kapply">K位申请</a> </dd>
          </dl>
          <dl>
             <dt> <a href="${basepath}/flighti18n2complexapply"><img src="${host}/images/store/enquiry.png" title="团队询价"/></a> </dt>
             <dd> <a href="${basepath}/flighti18n2complexapply">复杂航段询价</a> </dd>
          </dl>
          <#--><dl>
             <dt> <a href="#"><img src="${host}/images/store/charteredAirplane.png" title="包机询价"/></a> </dt>
             <dd> <a href="#">包机询价</a> </dd>
          </dl>-->
      </div>
      <div class="templateid"> <a href="#"><img src="${host}/images/store/templateid.jpg"/></a> </div>
    </div> <!--enquiry end-->
</div> <!--specialAndInquiries end-->
</@mc.container>