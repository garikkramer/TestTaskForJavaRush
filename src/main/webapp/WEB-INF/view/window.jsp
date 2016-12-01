<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<html>
<head>
    <title>Title</title>
    <base href="${pageContext.request.contextPath}">
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/app-resources/css/window.css"/>
</head>
<body>


<div id="overlay">
    <div class="popup">


<c:if test="${deal.id==0}">
    <h2 class='contact-title'>Add New Deal:</h2>
</c:if>

<c:if test="${deal.id!=0}">
    <h2 class='contact-title'> Update Deal for Id: <c:out value="${deal.id}"/></h2>
</c:if>




        <form:form id="myform" action="/addDeal" method="POST" commandName="deal">
            <table>
                <tbody>
                <tr>
                    <td class="lable">Id:</td>
                    <td class="input"><form:input path="id" value="${deal.id}" disable="false" type="text"/></td>
                </tr>
                <tr>
                    <td class="lable">Название:</td>
                    <td class="input"><form:input path="name" name="name" size="40" maxlength="40" class="input" type="text"/></td>
                </tr>

                <tr>
                    <td class="lable">Описание:</td>
                    <td class="input"><form:textarea path="description" name="text_message" cols="40" rows="10" class="input"/></td>
                </tr>

                <br><br>
                <tr><th></th>
                    <td align="center">
                        <c:if test="${deal.id==0}">
                            <input name="mail_submit" type="button" value="Добавить" id="btn-add">
                        </c:if>

                        <c:if test="${deal.id!=0}">
                            <input name="mail_submit" type="button" value="Обновить" id="btn-update">
                        </c:if>

                    </td>
                </tr>
                </tbody>
            </table>



        </form:form>
        <a href="/viewform/all" rel="#overlay">
            <button class="close" title="Закрыть"></button>
        </a>

    </div>
</div>

<script type="text/javascript">
    document.getElementById('overlay').style.display='block';
</script>
<script src="${pageContext.request.contextPath}/app-resources/js/lib/jquery-2.2.3.min.js"></script>
<script src="${pageContext.request.contextPath}/app-resources/js/window.js"></script>

</body>
</html>
