/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.62
 * Generated at: 2015-09-08 08:16:29 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.jsp.report.problem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class problemHistoryGraphs_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/WEB-INF/webres.tld", Long.valueOf(1437901994000L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
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
      out.write("<!DOCTYPE svg PUBLIC \"-//W3C//DTD SVG 1.1//EN\" \"http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd\">\n");
      com.dianping.cat.report.page.problem.Context ctx = null;
      ctx = (com.dianping.cat.report.page.problem.Context) _jspx_page_context.getAttribute("ctx", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (ctx == null){
        throw new java.lang.InstantiationException("bean ctx not found within scope");
      }
      out.write('\n');
      com.dianping.cat.report.page.problem.Payload payload = null;
      payload = (com.dianping.cat.report.page.problem.Payload) _jspx_page_context.getAttribute("payload", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (payload == null){
        throw new java.lang.InstantiationException("bean payload not found within scope");
      }
      out.write('\n');
      com.dianping.cat.report.page.problem.Model model = null;
      model = (com.dianping.cat.report.page.problem.Model) _jspx_page_context.getAttribute("model", javax.servlet.jsp.PageContext.REQUEST_SCOPE);
      if (model == null){
        throw new java.lang.InstantiationException("bean model not found within scope");
      }
      out.write("\n");
      out.write("<script type=\"text/javascript\" src=\"/cat/js/jquery-1.7.1.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"/cat/js/highcharts.js\"></script>\n");
      out.write("<script type=\"text/javascript\" src=\"/cat/js/baseGraph.js\"></script>\n");
      out.write("<style type=\"text/css\">\n");
      out.write(".graph {\n");
      out.write("\twidth: 450px;\n");
      out.write("\theight: 300px;\n");
      out.write("\tmargin: 4px auto;\n");
      out.write("}\n");
      out.write("</style>\n");
      out.write("\n");
      out.write("<table>\n");
      out.write("\t<tr>\n");
      out.write("\t\t<td>\n");
      out.write("\t\t<h5 style=\"text-align:center\"  class='text-center text-info'>错误量</h5>\n");
      out.write("\t\t<div id=\"errorTrend\" class=\"graph\"></div></td>\n");
      out.write("\t\t");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\n");
      out.write("\t</tr>\n");
      out.write("\t<tr><td  style=\"display:none\">\n");
      out.write("\t\t<div id =\"errorTrendMeta\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${model.errorsTrend}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</div>\n");
      out.write("\t</td><td  style=\"display:none\">\n");
      out.write("\t\t<div id =\"distributionChartMeta\">");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${model.distributionChart}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</div>\n");
      out.write("\t</td></tr>\n");
      out.write("</table>\n");
      out.write("<script type=\"text/javascript\">\n");
      out.write("\tvar errorData = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${model.errorsTrend}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(";\n");
      out.write("\tgraphLineChart(document.getElementById('errorTrend'), errorData);\n");
      out.write("\t\n");
      out.write("\tvar distributionChart = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${model.distributionChart}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(";\n");
      out.write("\t\n");
      out.write("\tif(distributionChart!=null&&distributionChart.length>0){\n");
      out.write("\t\tgraphPieChart(document.getElementById('distributionChart'), distributionChart);\n");
      out.write("\t}\n");
      out.write("</script>\n");
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

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
    _jspx_th_c_005fif_005f0.setParent(null);
    // /jsp/report/problem/problemHistoryGraphs.jsp(25,2) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
    _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${payload.ipAddress eq 'All' }", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
    int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
    if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
      do {
        out.write("\n");
        out.write("\t\t<td>\n");
        out.write("\t\t<h5 style=\"text-align:center\"  class='text-center text-info'>错误分布</h5>\n");
        out.write("\t\t<div id=\"distributionChart\" class=\"graph\"></div></td>\n");
        out.write("\t\t");
        int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
        if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
          break;
      } while (true);
    }
    if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      return true;
    }
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
    return false;
  }
}
