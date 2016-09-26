<%@ page import="com.sukhaniuk.databases.models.Alert" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="layout/header.jsp"/>
<jsp:include page="layout/navbar.jsp"/>
<%
    if (session.getAttribute("alert") != null) {
        Alert alert = (Alert) session.getAttribute("alert");
%>
<script>
    addFlashMessage("<%=alert.getType()%>", "<%=alert.getTitle()%>", "<%=alert.getText()%>");
    <%session.removeAttribute("alert"); %>
</script>
Hello <%=session.getAttribute("login")%>
<%}%>
<jsp:include page="layout/footer.jsp"/>


