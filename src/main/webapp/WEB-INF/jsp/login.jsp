<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Acasa">
	<jsp:attribute name="body_area">        
        <h3>Login Page</h3>
        
            <form action="<c:url value="login"/>" method="post">
               <table align="center">
    <tr>
        <td>Nume utilizator: </td>
        <td>
            <input type="text" name="username" size="30" maxlength="30" />
        </td>
        
    </tr>
    <tr>
        <td>Parola: </td>
        <td>
            <input type="password" name="password" size="30"
						maxlength="30" />
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