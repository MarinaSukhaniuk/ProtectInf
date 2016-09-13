<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Marina
  Date: 13.09.2016
  Time: 18:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="layout/header.jsp"/>
<jsp:include page="layout/navbar.jsp"/>
<c:if test="${not empty alert}">
    <script>
        addFlashMessage("${alert.type}", "${alert.title}", "${alert.text}");
    </script>
</c:if>
<div class="container ">
    <div class="row center-block">
        <div class="grid_4  ">
            <div class="jumbotron text-center">
                <h3>Sign in form<span class="label label-default"></span></h3>
                <hr>
                <form class="myForm" action="/login/signin.htm" method="POST">

                    <div class="input-group col-lg-6 col-lg-offset-3">
                        <input type="email" class="form-control" placeholder="Email"
                               aria-describedby="basic-addon2" name="email" value="">
                        <span class="input-group-addon" id="basic-addon2">@example.com</span>
                    </div>
                    <div class="input-group col-lg-6 col-lg-offset-3">
                        <span class="input-group-addon" id="basic-addon1">@</span>
                        <input type="password" name="passwordes" class="form-control" placeholder="Password"
                               aria-describedby="basic-addon1" value="">
                    </div>
                    <input type="submit" value="Sign in" class="btn btn-default navbar-btn col-lg-4 col-lg-offset-4"/>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="layout/footer.jsp"/>
