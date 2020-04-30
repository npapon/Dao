<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>CONNEXION</title>

</head>
 <c:if test="${!empty session}">
<c:import url="/deconnexion" var="file" scope="page" />
    ${file}
    </c:if>
<body>
        <form method="post">
        
            <fieldset>
                <legend>Se connecter</legend>
                <br/>
               
                <label for="login">Login <span class="requis">*</span></label>
                <input type="text" id="login" name="login" value="<c:out value="${cookielogin.value}"/>" size="32" maxlength="32" />
                <div class="erreur" id="erreurlogin"></div>
                <br />
                <br />
                               
                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="<c:out value="${cookiemotdepasse.value}"/>" size="56" maxlength="56" />
                 <div class="erreur" id="erreurmotdepasse"></div>      
                <br />
                <br />
                
                
                <input type="submit" value="Connexion" class="sansLabel" id="submit" /> 
                <input type="reset" value="Effacer saisie"  id="reset"/>          
                <br />
                <div class="erreur"><c:out value="${connexionform.connexionAutorisee}"/></div>
                            
            </fieldset>
        </form>  
<script src="<c:url value='/js/connexion.js'/>" type="text/javascript"></script>
</body>
</html>