<%@ page import="com.sukhaniuk.databases.models.Alert" %>
<%@ page import="com.sukhaniuk.storage.UsersStorage" %>
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
<%}%>
<%
    if(session.getAttribute("storage")!=null){
%>
<div class="container ">
    <div class="row center-block">
        <div class="jumbotron text-center">
            <div class="grid_4  ">
                <h3>Hello, <%=session.getAttribute("login")%></h3>
            </div>
        </div>
    </div>
    <div class="list-group">

        <a href="/admin/changePassword.htm" class="list-group-item">Change password</a>
        <a href="/admin/listUsers.htm" class="list-group-item">View list of users</a>
        <button type="button" class="list-group-item">Morbi leo risus</button>
        <button type="button" class="list-group-item">Porta ac consectetur ac</button>
        <button type="button" class="list-group-item">Vestibulum at eros</button>
    </div>
</div>
<%}%>
<jsp:include page="layout/footer.jsp"/>


