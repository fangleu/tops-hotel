/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.62
 * Generated at: 2015-09-09 05:41:20 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.report.cross;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class crossMethodQuery_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

private static org.apache.jasper.runtime.ProtectedFunctionMapper _jspx_fnmap_0;

static {
  _jspx_fnmap_0= org.apache.jasper.runtime.ProtectedFunctionMapper.getMapForFunction("w:format", org.unidal.web.jsp.function.FormatFunction.class, "format", new Class[] {java.lang.Object.class, java.lang.String.class});
}

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(6);
    _jspx_dependants.put("/jsp/report/cross/crossQuery.jsp", Long.valueOf(1437901994000L));
    _jspx_dependants.put("/WEB-INF/app.tld", Long.valueOf(1437901994000L));
    _jspx_dependants.put("/WEB-INF/tags/body_with_nav.tag", Long.valueOf(1437901994000L));
    _jspx_dependants.put("/WEB-INF/tags/hourlyReport.tag", Long.valueOf(1437901994000L));
    _jspx_dependants.put("/WEB-INF/tags/base_with_nav.tag", Long.valueOf(1437901994000L));
    _jspx_dependants.put("/WEB-INF/webres.tld", Long.valueOf(1437901994000L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fres_005fuseCss_0026_005fvalue_005ftarget_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fres_005fuseJs_0026_005fvalue_005ftarget_005fnobody;
  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fres_005fuseCss_0026_005fvalue_005ftarget_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fres_005fuseJs_0026_005fvalue_005ftarget_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fres_005fuseCss_0026_005fvalue_005ftarget_005fnobody.release();
    _005fjspx_005ftagPool_005fres_005fuseJs_0026_005fvalue_005ftarget_005fnobody.release();
    _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=utf-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      com.dianping.cat.report.page.cross.Context ctx = null;
      ctx = (com.dianping.cat.report.page.cross.Context) _jspx_page_context.getAttribute("ctx", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (ctx == null){
        throw new java.lang.InstantiationException("bean ctx not found within scope");
      }
      out.write('\n');
      com.dianping.cat.report.page.cross.Payload payload = null;
      payload = (com.dianping.cat.report.page.cross.Payload) _jspx_page_context.getAttribute("payload", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (payload == null){
        throw new java.lang.InstantiationException("bean payload not found within scope");
      }
      out.write('\n');
      com.dianping.cat.report.page.cross.Model model = null;
      model = (com.dianping.cat.report.page.cross.Model) _jspx_page_context.getAttribute("model", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (model == null){
        throw new java.lang.InstantiationException("bean model not found within scope");
      }
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_a_005freport_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("<style>\n");
      out.write(".dataTables_wrapper {\n");
      out.write("\t/* position: relative; */\n");
      out.write("\tclear: none;\n");
      out.write("}\n");
      out.write("</style>\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_a_005freport_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  a:report
    org.apache.jsp.tag.webhourlyReport_tag _jspx_th_a_005freport_005f0 = (new org.apache.jsp.tag.webhourlyReport_tag());
    _jsp_instancemanager.newInstance(_jspx_th_a_005freport_005f0);
    _jspx_th_a_005freport_005f0.setJspContext(_jspx_page_context);
    // /jsp/report/cross/crossMethodQuery.jsp(10,0) name = title type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = java.lang.String deferredMethod = false methodSignature = null
    _jspx_th_a_005freport_005f0.setTitle("Cross Report");
    // /jsp/report/cross/crossMethodQuery.jsp(10,0) name = navUrlPrefix type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = java.lang.String deferredMethod = false methodSignature = null
    _jspx_th_a_005freport_005f0.setNavUrlPrefix((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("ip=${model.ipAddress}&domain=${model.domain}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    javax.servlet.jsp.tagext.JspFragment _jspx_temp0 = new Helper( 0, _jspx_page_context, _jspx_th_a_005freport_005f0, null);
    // /jsp/report/cross/crossMethodQuery.jsp(10,0) null
    _jspx_th_a_005freport_005f0.setSubtitle(_jspx_temp0);
    _jspx_th_a_005freport_005f0.setJspBody(new Helper( 1, _jspx_page_context, _jspx_th_a_005freport_005f0, null));
    _jspx_th_a_005freport_005f0.doTag();
    _jsp_instancemanager.destroyInstance(_jspx_th_a_005freport_005f0);
    return false;
  }

  private boolean _jspx_meth_res_005fuseCss_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  res:useCss
    org.unidal.webres.taglib.basic.UseCssTagHandler _jspx_th_res_005fuseCss_005f0 = (org.unidal.webres.taglib.basic.UseCssTagHandler) _005fjspx_005ftagPool_005fres_005fuseCss_0026_005fvalue_005ftarget_005fnobody.get(org.unidal.webres.taglib.basic.UseCssTagHandler.class);
    _jspx_th_res_005fuseCss_005f0.setPageContext(_jspx_page_context);
    _jspx_th_res_005fuseCss_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    // /jsp/report/cross/crossMethodQuery.jsp(14,0) name = value type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_res_005fuseCss_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${res.css.local.table_css}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    // /jsp/report/cross/crossMethodQuery.jsp(14,0) name = target type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_res_005fuseCss_005f0.setTarget("head-css");
    int _jspx_eval_res_005fuseCss_005f0 = _jspx_th_res_005fuseCss_005f0.doStartTag();
    if (_jspx_th_res_005fuseCss_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fres_005fuseCss_0026_005fvalue_005ftarget_005fnobody.reuse(_jspx_th_res_005fuseCss_005f0);
      throw new javax.servlet.jsp.SkipPageException();
    }
    _005fjspx_005ftagPool_005fres_005fuseCss_0026_005fvalue_005ftarget_005fnobody.reuse(_jspx_th_res_005fuseCss_005f0);
    return false;
  }

  private boolean _jspx_meth_res_005fuseJs_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  res:useJs
    org.unidal.webres.taglib.basic.UseJsTagHandler _jspx_th_res_005fuseJs_005f0 = (org.unidal.webres.taglib.basic.UseJsTagHandler) _005fjspx_005ftagPool_005fres_005fuseJs_0026_005fvalue_005ftarget_005fnobody.get(org.unidal.webres.taglib.basic.UseJsTagHandler.class);
    _jspx_th_res_005fuseJs_005f0.setPageContext(_jspx_page_context);
    _jspx_th_res_005fuseJs_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    // /jsp/report/cross/crossMethodQuery.jsp(16,0) name = value type = java.lang.Object reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_res_005fuseJs_005f0.setValue((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${res.js.local['jquery.dataTables.min.js']}", java.lang.Object.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
    // /jsp/report/cross/crossMethodQuery.jsp(16,0) name = target type = java.lang.String reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_res_005fuseJs_005f0.setTarget("head-js");
    int _jspx_eval_res_005fuseJs_005f0 = _jspx_th_res_005fuseJs_005f0.doStartTag();
    if (_jspx_th_res_005fuseJs_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fres_005fuseJs_0026_005fvalue_005ftarget_005fnobody.reuse(_jspx_th_res_005fuseJs_005f0);
      throw new javax.servlet.jsp.SkipPageException();
    }
    _005fjspx_005ftagPool_005fres_005fuseJs_0026_005fvalue_005ftarget_005fnobody.reuse(_jspx_th_res_005fuseJs_005f0);
    return false;
  }

  private boolean _jspx_meth_c_005fforEach_005f0(javax.servlet.jsp.tagext.JspTag _jspx_parent, javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_005fforEach_005f0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_005fforEach_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fforEach_005f0.setParent(new javax.servlet.jsp.tagext.TagAdapter((javax.servlet.jsp.tagext.SimpleTag) _jspx_parent));
    // /jsp/report/cross/crossMethodQuery.jsp(55,1) name = var type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVar("item");
    // /jsp/report/cross/crossMethodQuery.jsp(55,1) name = items type = javax.el.ValueExpression reqTime = true required = false fragment = false deferredValue = true expectedTypeName = java.lang.Object deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setItems(new org.apache.jasper.el.JspValueExpression("/jsp/report/cross/crossMethodQuery.jsp(55,1) '${model.info.items}'",_el_expressionfactory.createValueExpression(_jspx_page_context.getELContext(),"${model.info.items}",java.lang.Object.class)).getValue(_jspx_page_context.getELContext()));
    // /jsp/report/cross/crossMethodQuery.jsp(55,1) name = varStatus type = java.lang.String reqTime = false required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fforEach_005f0.setVarStatus("status");
    int[] _jspx_push_body_count_c_005fforEach_005f0 = new int[] { 0 };
    try {
      int _jspx_eval_c_005fforEach_005f0 = _jspx_th_c_005fforEach_005f0.doStartTag();
      if (_jspx_eval_c_005fforEach_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t<tr class=\" right\">\n");
          out.write("\t\t\t<td class=\"left\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.type}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t\t<td class=\"left\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.domain}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t\t<td class=\"left\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.ip}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t\t<td class=\"left\">");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.method}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t\t <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${item.totalCount}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
          out.write("</td>\n");
          out.write("\t\t     <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${w:format(item.failureCount,'#,###,###,###,##0')}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, _jspx_fnmap_0, false));
          out.write("</td>\n");
          out.write("\t\t     <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${w:format(item.failurePercent,'0.0000%')}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, _jspx_fnmap_0, false));
          out.write("</td>\n");
          out.write("\t\t     <td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${w:format(item.avg,'0.00')}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, _jspx_fnmap_0, false));
          out.write("</td>\n");
          out.write("\t\t</tr>\n");
          out.write("\t");
          int evalDoAfterBody = _jspx_th_c_005fforEach_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fforEach_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        throw new javax.servlet.jsp.SkipPageException();
      }
    } catch (java.lang.Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_005fforEach_005f0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_005fforEach_005f0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_005fforEach_005f0.doFinally();
      _005fjspx_005ftagPool_005fc_005fforEach_0026_005fvarStatus_005fvar_005fitems.reuse(_jspx_th_c_005fforEach_005f0);
    }
    return false;
  }

  private class Helper
      extends org.apache.jasper.runtime.JspFragmentHelper
  {
    private javax.servlet.jsp.tagext.JspTag _jspx_parent;
    private int[] _jspx_push_body_count;

    public Helper( int discriminator, javax.servlet.jsp.JspContext jspContext, javax.servlet.jsp.tagext.JspTag _jspx_parent, int[] _jspx_push_body_count ) {
      super( discriminator, jspContext, _jspx_parent );
      this._jspx_parent = _jspx_parent;
      this._jspx_push_body_count = _jspx_push_body_count;
    }
    public boolean invoke0( javax.servlet.jsp.JspWriter out ) 
      throws java.lang.Throwable
    {
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${w:format(model.report.startTime,'yyyy-MM-dd HH:mm:ss')}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, _jspx_fnmap_0, false));
      out.write(" to ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${w:format(model.report.endTime,'yyyy-MM-dd HH:mm:ss')}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, _jspx_fnmap_0, false));
      return false;
    }
    public boolean invoke1( javax.servlet.jsp.JspWriter out ) 
      throws java.lang.Throwable
    {
      out.write('\n');
      if (_jspx_meth_res_005fuseCss_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write('\n');
      out.write('\n');
      if (_jspx_meth_res_005fuseJs_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\t$(document).ready(function() {\n");
      out.write("\t\t$(\".breadcrumbs .nav\").hide();\n");
      out.write("\t\t$('#contents').dataTable({\n");
      out.write("\t\t\t\"sPaginationType\": \"full_numbers\",\n");
      out.write("\t\t\t'iDisplayLength': 100,\n");
      out.write("\t\t\t\"oLanguage\": {\n");
      out.write("\t            \"sProcessing\": \"正在加载中......\",\n");
      out.write("\t            \"sLengthMenu\": \"每页显示 _MENU_ 条记录\",\n");
      out.write("\t            \"sZeroRecords\": \"对不起，查询不到相关数据！\",\n");
      out.write("\t            \"sEmptyTable\": \"表中无数据存在！\",\n");
      out.write("\t            \"sInfo\": \"当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录\",\n");
      out.write("\t            \"sInfoFiltered\": \"数据表中共为 _MAX_ 条记录\",\n");
      out.write("\t            \"sSearch\": \"搜索\",\n");
      out.write("\t            \"oPaginate\": {\n");
      out.write("\t                \"sFirst\": \"首页\",\n");
      out.write("\t                \"sPrevious\": \"上一页\",\n");
      out.write("\t                \"sNext\": \"下一页\",\n");
      out.write("\t                \"sLast\": \"末页\"\n");
      out.write("\t            }\n");
      out.write("\t        }\n");
      out.write("\t\t});\n");
      out.write("\t});\n");
      out.write("</script>\n");
      out.write("\n");
      out.write("<script>\n");
      out.write("\tfunction query(){\n");
      out.write("\t\tvar reportType = '");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${payload.reportType}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\n");
      out.write("\t\tvar domain='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${model.domain}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\n");
      out.write("\t\tvar date='");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${model.date}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("';\n");
      out.write("\t\tvar method = $('#method').val();\n");
      out.write("\t\t\n");
      out.write("\t\twindow.location.href=\"?op=query&domain=\"+domain+\"&date=\"+date+\"&reportType=\"+reportType+\"&method=\"+method;\n");
      out.write("\t}\n");
      out.write("</script>\n");
      out.write("<table>\n");
      out.write("\t<tr>\n");
      out.write("\t<th><span class='text-danger' style=\"padding-left:5px;\">查询当前这个时段段内，一个方法被哪些应用调用</span>\n");
      out.write("\t<input type=\"text\" class='input-xxlarge' id=\"method\" size=\"100\" value=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${payload.method}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("\"></input>\n");
      out.write("\t<input type=\"submit\" class='btn btn-primary btn-sm' onClick=\"query()\"></input></th></tr>\n");
      out.write("</table>\n");
      out.write("\n");
      out.write("\n");
      out.write("</br>\n");
      out.write("<table id=\"contents\" width=\"100%\">\n");
      out.write("\t<thead>\n");
      out.write("\t<tr>\n");
      out.write("\t\t<th>类型</th>\n");
      out.write("\t\t<th>项目</th>\n");
      out.write("\t\t<th>IP</th>\n");
      out.write("\t\t<th>方法名</th>\n");
      out.write("\t\t<th class=\"right\">Total</th>\n");
      out.write("\t\t<th class=\"right\">Failure</th>\n");
      out.write("\t\t<th class=\"right\">Failure%</th>\n");
      out.write("\t\t<th class=\"right\">Avg(ms)</th>\n");
      out.write("\t</tr></thead><tbody>\n");
      out.write("\t");
      if (_jspx_meth_c_005fforEach_005f0(_jspx_parent, _jspx_page_context))
        return true;
      out.write("</tbody>\n");
      out.write("</table>\n");
      out.write("</br>\n");
      return false;
    }
    public void invoke( java.io.Writer writer )
      throws javax.servlet.jsp.JspException
    {
      javax.servlet.jsp.JspWriter out = null;
      if( writer != null ) {
        out = this.jspContext.pushBody(writer);
      } else {
        out = this.jspContext.getOut();
      }
      try {
        Object _jspx_saved_JspContext = this.jspContext.getELContext().getContext(javax.servlet.jsp.JspContext.class);
        this.jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,this.jspContext);
        switch( this.discriminator ) {
          case 0:
            invoke0( out );
            break;
          case 1:
            invoke1( out );
            break;
        }
        jspContext.getELContext().putContext(javax.servlet.jsp.JspContext.class,_jspx_saved_JspContext);
      }
      catch( java.lang.Throwable e ) {
        if (e instanceof javax.servlet.jsp.SkipPageException)
            throw (javax.servlet.jsp.SkipPageException) e;
        throw new javax.servlet.jsp.JspException( e );
      }
      finally {
        if( writer != null ) {
          this.jspContext.popBody();
        }
      }
    }
  }
}
