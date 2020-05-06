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
    
           <c:if test="${!empty session}">
         <div id="menu">
<c:import url="/${menupage}" var="file" scope="page" />
    ${file}
    </div> 
</c:if>
  
<body>
 <fieldset>
                <legend>Bienvenu(e) <c:out value="${session.login}"/></legend>
                <div>
<img class ="imageprofil" src="<c:out value='${session.emplacementImageProfil}'/>"  title="profil" alt="Votre photo"/>

<a href="<c:out value='${effacermoncomptepage}'/>" title="effacermoncompte" >

<img src="<c:out value='${iconesupprimer}'/>" title ="supprimer" alt="Effacer mon compte" id="supprimercompte" />

</a>

</div>
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