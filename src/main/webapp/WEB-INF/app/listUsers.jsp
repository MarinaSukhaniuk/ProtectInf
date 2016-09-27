<%--
  Created by IntelliJ IDEA.
  User: Marina
  Date: 26.09.2016
  Time: 23:02
  To change this template use File | Settings | File Templates.
--%>
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
<%}%>
<div class="container ">
    <div class="row center-block">
        <div class="jumbotron text-center">
            <div class="grid_4  ">
                <h3>View list of users<span class="label label-default"></span></h3>
                <hr>
                <form class="myFormlist" action="/admin/listUsers.htm" method="GET">

                    <div class="panel panel-default">
                        <!-- Default panel contents -->
                        <div class="panel-heading">Users</div>
                        <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                    <tr>
                                        <th>✗ <br>
                                        </th>
                                        <th>user id <br>
                                        </th>
                                        <th>role<br>
                                        </th>
                                        <th>email<br>
                                        </th>
                                        <th>password<br>
                                        </th>
                                        <%--<th>Edit</th>--%>
                                    </tr>
                                    </thead>

                                    <tbody class="text-left">
                                    <c:forEach var="num" items="${users}">
                                        <tr>
                                            <td><input type="checkbox" name="checkel_users"
                                                       value="${num.id}"></td>
                                            <td>${num.id}</td>
                                            <td>${num.role.name}</td>
                                            <td>${num.login}</td>
                                            <td>${num.password}</td>
                                            <%--<td><a href = "#">--%>
                                                <%--<img src="/Restaurant_course/boo/img/edit-xxl.png" class="img-left" height="23" width="23"></a></td>--%>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="layout/footer.jsp"/>
