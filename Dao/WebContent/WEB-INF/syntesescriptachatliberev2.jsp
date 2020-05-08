<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SYNTHESE SCRIPT LIBERER ACHATS V2</title>
</head>
<body>
</head>
 <c:if test="${!empty session}">
<c:import url="/${deconnexionboutonpage}" var="file" scope="page" />
    ${file}
    </c:if>
    
           <c:if test="${!empty session}">
         <div id="menu">
<c:import url="/${menupage}" var="file" scope="page" />
    ${file}
    </div> 
</c:if>
  
<body>
                <table >           
                        <caption>Achats libérés</caption>
                        <tr>
                                <th>NUMERO LIGNE</th>
                                <th>IDENTIFIANT ACHAT</th>
                                <th>NUMERO AFFAIRE</th>
                                <th>NUMERO ACHAT</th>
                        </tr>
                        <c:forEach var="entry" items="${listeachatsliberesv2}" varStatus="status">
                                <tr>
                                        <td>${status.index}</td>
                                         <td><c:out value="${entry.id}"/></td>     
                                        <td><c:out value="${entry.numeroAffaire}"/></td>  
                                         <td><c:out value="${entry.numeroAchat}"/></td>             
                                </tr>
                        </c:forEach>
                </table>
           
        <br/>
        <br/>
</table>

           
</body>
</html>