<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MON PROFIL</title>
</head>
<body>
</head>
 <c:if test="${!empty session}">
<c:import url="/${deconnexionboutonpage}" var="file" scope="page" />
    ${file}
    </c:if>
<body>
    
 <fieldset>
                <legend>Bienvenu <c:out value="${session.login}"/></legend>
<img class ="imageprofil" src="<c:out value='${session.emplacementImageProfil}'/>"  title="profil" alt="Votre photo"/>
</fieldset>
<form method="post" enctype="multipart/form-data">

 <fieldset>
                <legend>Charger sa photo</legend>
        

                <label for="imageprofil">Charger la photo <span class="requis">*</span></label>
                <input type="file" id="imageprofil" name="imageprofil" />
                 <span class="succes"><c:out value="${session.login}"/></span>
                 <div class="erreur" id="erreurimageprofil"></div>
                <br />
                
                <input id ="submit" type="submit" value="Envoyer" class="sansLabel" />
                <br />

</fieldset>
</form>
   <script src="<c:url value='/js/profil.js'/>" type="text/javascript"></script>
</body>
</html>