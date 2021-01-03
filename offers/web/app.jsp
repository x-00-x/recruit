<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<%
    String appPath=pageContext.getServletContext().getContextPath();
    out.print(appPath);
    pageContext.getRequest().getServletContext().getContextPath();
%>--%>
<%-------当前页面获取应用上下文，项目名称---%>
<c:set value="${pageContext.request.contextPath}" var="appPath"/>
<%--<c:out value="${appPath}"/>--%>

