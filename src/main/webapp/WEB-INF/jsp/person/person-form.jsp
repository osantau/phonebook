<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Adauare / Editare Persoane">
	<jsp:attribute name="body_area">                                 
        <h3>Adaugare / Editare Persoane</h3>
        <jsp:useBean id="person" scope="request"
			class="oct.soft.model.Person" />
        <jsp:setProperty name="person" property="*" />
        
        <c:if test="${isEdit }">
        <form action="<c:url value="/person?action=add-office"/>" method="post">
        <input type="hidden" name="idperson" value="${person.idperson}"/>
     <div style="float:right; margin-right:150px;"> 
     <table >
   <tr><td><b>Birouri:</b></td>
   <td>     <select  name="idoffice">       
		<c:forEach items="${officeCombo}" var="b" >
        <option value="10" disabled="disabled" style="font-weight: bold;">${fn:toUpperCase(b.key.name)}</option>
        <c:forEach items="${officeCombo[b.key]}" var="o">        	
        	 <option value="${o.idoffice }" style="margin-left: 15px;">${o.name}</option> 
        </c:forEach>        
        </c:forEach>
        </select></td>
       <td style="text-align: right;"><input type="submit" value="Adauga"/> </td>
   </tr>
   <c:forEach items="${offices}" var="o">
   	<tr>   	
   	 <td></td>
        <td style="font-weight: bold;">${o.name}</td>
        <td style="font-weight: bold;">${o.branch.name }</td>
        <td><a href="<c:url value="/office"/>?edit=${o.idoffice}">Modifica Birou</a>
            &nbsp;
            <a href="<c:url value="/person?action=remove-office"/>&idperson=${person.idperson}&idoffice=${o.idoffice}">Deasociaza</a></td>
   	</tr>
   </c:forEach>
 
</table>
 </div>
 </form>
     </c:if>   
        
        <form action="<c:url value="person?action=add-or-update"/>" method="post">
        <input type="hidden" name ="idperson" value = "${person.idperson }"/>
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
<td></td><td align="right"><input type="submit" value="Adauga / Actualizeaza"/>&nbsp;
        <a href="<c:url value="/person"/>" style="text-decoration: none;">
            <input type="button" value="Cancel"/> 
                 </a> </td>
</tr>
</table>
</fieldset>                         
</form>
<c:if test="${isEdit }">

</c:if>
</jsp:attribute>
</t:layout>