(function($){
    $.fn.zcitytip=function(options){
        var defaults={
            w_width:"350px",
            callback:function(){},
            select:null,
            city_input_id:""
        };
        var opt=$.extend(defaults,options);


            var lidata="";
            var city_json="";
            var $this=$(this);

            function init()
            {
                $("body").append("<div class='citytip-containers'><div class='citytip-title'><div style='width:250px'>热门城市（可直接输入城市或城市拼音)</div><div class='citytip-close'>关闭</div></div><div style='clear:both'></div><ul id='city-select'></ul><ul id='city-details'></ul><div style='clear:both'></div></div>");

                $(".citytip-containers").css("width",opt.w_width);
               
                city_json=cityTabList;
                $.each(city_json,function(i,item){
                    lidata+="<li class='citytip-head'>"+item.title+"</li>";
                });
                $("#city-select").html(lidata);
                    
                


                /*$(".citytip-head").live('click',(function(){//removed*/
                $("#city-select").delegate(".citytip-head","click",function(){
                    $(".citytip-head").removeClass("citytip-selected");
                    $(this).addClass("citytip-selected");
                    read_city_by_tag($(this));
                    return false;
                });

                

                /*$(".city-detail").live('mouseover',function(){//removed*/
                $("#city-details").delegate(".city-detail","mouseover",function(){
                    $(this).addClass("hover");
                });

                $("#city-details").delegate(".city-detail","mouseout",function(){
                    $(this).removeClass("hover");
                });

                $("#city-details").delegate(".city-detail","click",choosecity);

                $(document).bind('click',function(){
                    $(".citytip-containers").css("display","none");
                    //$(".city-detail").die("click",choosecity);
                });

            }

        function choosecity(){
            $("#"+opt.city_input_id).val($(this).text());
            if(getType(opt.select)=="function")
            {
                opt.select.call(this,$(this),opt);
            }
        }

                if(!$(".citytip-containers").length)
                {
                    init();
                }



                $(this).bind('click',function(event){
                    $(".citytip-containers").css("display","block");
                    opt.city_input_id=$(this).attr("id");
                    $(".citytip-containers").css("top",$("#"+opt.city_input_id).offset().top+$("#"+opt.city_input_id).height()+6);
                    $(".citytip-containers").css("left",$("#"+opt.city_input_id).offset().left);
                    $(".citytip-head:first").trigger('click');
                    event.stopPropagation();
                    return false;
                });

                $(this).bind("keydown",function(){
                    $(".citytip-containers").css("display","none");
                    //$(".city-detail").die("click",choosecity);
                });


            function read_city_by_tag($this)
            {
                $("#city-details").html("");//清空原来内容
                var selectHeadIndex=$(".citytip-head").index($this);
                var cityTabListItem=cityTabList[selectHeadIndex];
                if(cityTabListItem.cityListHTML=="")
                {
                    if(cityTabListItem.begin=="")//热门列表
                    {
                        var tmp="";
                        $.each(_hotCityList,function(i,item)
                        {
                            tmp+="<li class='city-detail' code='"+item[0]+"'>"+item[1]+"</li>";
                        });
                        cityTabListItem.cityListHTML=tmp;
                    }
                    else
                    {
                        var tagname=cityTabListItem.title;
                        var tmp="";
                        for(i=0;i<tagname.length;i++)
                        {
                            var charstr=tagname.substr(i,1);
                            tmp+="<li class='city-type'>"+charstr+"</li>";
                            $.each(_cityList,function(j,item){
                                if(item[2].substr(0,1)==charstr)
                                    tmp+="<li class='city-detail' code='"+item[0]+"'>"+item[1]+"</li>";
                            });
                            cityTabListItem.cityListHTML=tmp;
                        }
                    }
                }
                $("#city-details").append(cityTabListItem.cityListHTML);

                // 

                // //添加热门城市
                // if(getType(city_json[tagname])=="array")
                // {
                //     for(item in city_json[tagname])
                //     {
                //         var tmp="<li class='city-detail'>"+city_json[tagname][item].name+"</li>";
                //         $("#city-details").append(tmp);
                //     }
                // }

                // //添加其他城市
                // if(getType(city_json[tagname])=="object")
                // {
                //     $.each(city_json[tagname],function(i,item){
                //         var tmp="<li class='city-type'>"+i+"</li>";
                //         $("#city-details").append(tmp);
                //         for(num in item)
                //         {
                //             var tmp="<li class='city-detail'>"+item[num].name+"</li>";
                //             $("#city-details").append(tmp);
                //         }
                //     });
                // }
            }

            function getType(o) {
                var _t;
                return ((_t = typeof(o)) == "object" ? Object.prototype.toString.call(o).slice(8,-1):_t).toLowerCase();
            }



    };


})(jQuery)