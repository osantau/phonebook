<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Adauare / Editare Persoane">
	<jsp:attribute name="body_area">                                 
        <h3>Adaugare / Editare Persoane</h3>
        <jsp:useBean id="person" scope="request"
			class="oct.soft.model.Person" />
        <jsp:setProperty name="person" property="*" />
        
        <form action="<c:url value=""/>" method="post">
        <c:if test="${not empty errors }">
<div style="color: red;">${errors}</div>
</c:if>    
<fieldset style="width:300px;"><legend><b>Adauga Persoana</b></legend>
<table cellpadding="10px">
<tr>
<td><b>Nume:</b> </td>
<td>
<input type="text" name="fname" id="fname" value="${person.fname}" style="background-color: #A9F5A9; font-weight: bold;"/>
</td>
</tr>
<tr>
<td><b>Prenume:</b> </td>
<td>
<input type="text" name="lname" id="lname" value="${person.lname }" style="background-color: #A9F5A9;font-weight: bold;"/>
</td>
</tr>
<tr>
<td title="Porecla"><b>Pseudonim:</b></td>
<td>
<input type="text" name="nickname" id="lname" value="${person.nickname}" style="background-color: #A9F5A9;font-weight: bold;"/>
</td>
</tr>
<tr>
<td></td><td align="right"><input type="submit" value="Adauga"/>&nbsp;
        <a href="<c:url value="/person"/>" style="text-decoration: none;">
            <input type="button" value="Cancel"/> 
                 </a> </td>
</tr>
</table>
</fieldset>                         
</form>

</jsp:attribute>
</t:layout>