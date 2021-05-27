<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:layout title="Adauare / Editare Birouri">
	<jsp:attribute name="body_area">                                 
        <h3>Adaugare / Editare Birouri</h3>
        <c:if test="${isEdit }">
        <div style="float:right; margin-right:300px;"> 
        <c:choose>
        <c:when test="${empty officePersons }">
        	<div style="color: red">
       				<b>Nu exista persoane atasate acestui birou!</b>
    	</div>
        </c:when>
        <c:otherwise>
        	<table>
        	<c:forEach items="${officePersons}" var="person">
        		<tr>
        			<td>${person.fname}</td>
        			<td>${person.lname}</td>
        			<td><a href="<c:url value="/person-edit" />?idperson=${person.idperson}">Modifica</a></td>
        		</tr>
        	</c:forEach>
        	</table>
        </c:otherwise>
        </c:choose>
        </div>
        </c:if>
        
            <jsp:useBean id="office" scope="request"
			class="oct.soft.model.Office" />
            <jsp:setProperty name="office" property="*" />
            <form action="<c:url value="office"/>" method="post">            
                <input type="hidden" name="idoffice"
				value="${office.idoffice}" />   
<c:if test="${not empty errors }">
<div style="color: red;">${errors}</div>
</c:if>
<table cellpadding="10px">
<tr>
<td><b>Filiala:</b></td>
<td style="font-weight: bold;">
<select name="branchId">
<c:forEach items="${branchList}" var="branch">
<option value="${branch.idoffice}" <c:if test="${branch.idoffice == selectedBranchId }"> selected </c:if> >${branch.name}</option>
</c:forEach>
</select>
</td>
</tr>
<tr>
<td><b>Denumire:</b></td>
<td style="font-weight: bold;">
<input type="text" name="name" id="name" value="${office.name}"/>
</td>
</tr>
<tr>
<td></td>
		<td align="right"><input type="submit" value="Adauga / Actualizeaza" />&nbsp;
        <a href="<c:url value="office"/>" style="text-decoration: none;">
            <input type="button" value="Cancel" /> 
                 </a> </td>
</tr>
</table>                
    </form>
    <!-- Editare -->
    <c:if test="${isEdit}">
     <form action="<c:url value="office-add-phone"/>" method="post">
     <input type="hidden" name="branchId" value="${office.parent}"/>
      <input type="hidden" name="idoffice"
				value="${office.idoffice}" />   
     	 <fieldset style="width:250px;   ">
    <legend><b>Adauga numar birou:</b></legend>
    <table>
       <tr>
    <td>Telefon:</td>
    <td><input type="text" name="number" required="required"/></td>
</tr>
<tr>
    <td>&nbsp;</td>
    <td> <label for="r1"><input type="radio" name="tel" id="r1" checked="true" value="interior"/>Interior&nbsp;</label>
        <label for="r2"><input type="radio" name="tel"  id="r2" value="fix"/>Fix&nbsp;</label>
        <label for="r3"><input type="radio" name="tel" id="r3"  value="mobil"/>Mobil</label>
    </td>
</tr>
<tr>
<td></td><td align="right"><input type="submit" value="Adauga"/>&nbsp;
        <a href="<c:url value="office"/>" style="text-decoration: none;">
            <input type="button" value="Cancel"/> 
                 </a> </td>
</tr>
    </table>    
</fieldset>
</form>     
    </c:if>    
    <!--  End of Editare -->  
    <!--  Numere birouri -->
    <c:if test="${not empty officePhones}">
    <p><b>Numere de telefon birou:</b></p>
    <div style="padding-left: 100px;">    
    <c:forEach items="${officePhones}" var="phone">    	
    		<p>${phone.number}&nbsp;-&nbsp;${phone.getTypeLabel()}&nbsp;&nbsp;
    		<a href="<c:url value="/office-delete-phone" />?number=${phone.number}&office=${office.idoffice}">Sterge</a>
    		</p>    	
    </c:forEach>    
    </div>
    </c:if>
    <!-- End Numere Birouri -->                      
</jsp:attribute>
</t:layout>