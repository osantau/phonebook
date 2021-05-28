<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Acasa">
	<jsp:attribute name="body_area">     
	 <jsp:useBean id="loginForm" scope="request"
			class="oct.soft.model.LoginForm" />
            <jsp:setProperty name="loginForm" property="*" />   
        <h3>Autentificare</h3>
        
            <form action="<c:url value="login"/>" method="post">
            <c:if test="${not empty errors }">
            <div style="color: red;">
            ${errors}
            </div>
            </c:if>
               <table align="center">
    <tr>
        <td>Nume utilizator: </td>
        <td>
            <input type="text" name="username" size="30" maxlength="30" value="${loginForm.username}"/>
        </td>
        
    </tr>
    <tr>
        <td>Parola: </td>
        <td>
            <input type="password" name="password" size="30" value="${loginForm.password}"		maxlength="30" />
        </td>
    </tr>
    <tr>
        <td></td>
        <td>
             <input type="submit" value="Login" />
        </td>
    </tr>
</table>
        
    </form>
</jsp:attribute>

</t:layout>