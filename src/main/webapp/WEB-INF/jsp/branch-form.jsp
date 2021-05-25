<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Adauare / Editare Filiala">
	<jsp:attribute name="body_area">                                 
        <h3>Adaugare / Editare Filiala</h3> 
        
        
            <jsp:useBean id="branch" scope="request"
			class="oct.soft.model.Office" />
            <jsp:setProperty name="branch" property="*" />
            <form action="<c:url value="branches"/>" method="post">            
                <input type="hidden" name="idoffice"
				value="${branch.idoffice}" />   
<c:if test="${not empty errors }">
<div style="color: red;">${errors}</div>
</c:if>
<table cellpadding="10px">
<tr>
<td><b>Denumire:</b></td>
<td style="font-weight: bold;">
<input type="text" name="name" id="name" value="${branch.name}"/>
</td>
</tr>
<tr>
<td></td>
		<td align="right"><input type="submit" value="Adauga / Actualizeaza" />&nbsp;
        <a href="<c:url value="branches"/>" style="text-decoration: none;">
            <input type="button" value="Cancel" /> 
                 </a> </td>
</tr>
</table>        
        </div>
    </form>
                            
</jsp:attribute>
</t:layout>