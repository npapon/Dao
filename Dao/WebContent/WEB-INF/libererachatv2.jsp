<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>LIBERER ACHAT V2</title>

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

<div>Les achats une fois modifiés dans V+ ne sont plus modifiable dans V2.
Ce script permet de les rendre à nouveau modifiables dans V2.</div>
<br/>
        <form method="post">
        
                <input type="submit" value="Liberer les achats" class="sansLabel" id="submit" /> 
        </form>  

</body>
</html>