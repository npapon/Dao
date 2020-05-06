<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>HOMEPAGE</title>

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
     
        
            <fieldset>
                <legend>Bienvenu sur Mediahub Script +</legend>
                <br/>
               <div class="home"> Créer un compte</div>
                <br/>
                <a href="<c:out value='${inscriptionpage}'/>" title ="inscription" alt="inscription"/>S'inscrire
</a>
<br/>
<br/>       
<div class="home">Vous avez déjà un compte</div>
                <br/>
                             <a href="<c:out value='${connexionpage}'/>" title ="connexion" alt="connexion"/>Se connecter
</a>
                            
            </fieldset>
       
</body>
</html>