<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="medical.m2i.model.*" %>
<%@ page import="medical.m2i.dao.VilleDao" %>

<% Ville v = new Ville();
ArrayList<Ville> lv = new ArrayList<Ville>();
%>

	<%
		v = ( Ville ) request.getAttribute("villeparam"); 
	%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Formulaire d'édition ville</title>
</head>

<body>
	<%@ include file="fragments/header.jsp" %>
    <main class="container">
        <h1>Formulaire d'édition ville</h1>
        <hr>
        <div>
        </div>
        <form name="monForm" action="" method="post">
            <div class="form-line mb-4">
                <label class="form-label">Pays *</label>
                <input class="form-control" type="text" name="pays" placeholder="Votre pays" required value="<%= v.getPays() %>">
            </div>
            <div class="form-line mb-4">
                <label>Nom de la ville *</label>
                <input class="form-control" type="text" name="nom" placeholder="Nom de la ville" required value="<%= v.getNom() %>">
            </div>

            <div class="form-line mb-4">
                <label class="form-label">Code Postal *</label>
                <input class="form-control" type="number" name="code_postal" placeholder="code postal " required value="<%= v.getCode_postal() %>">
            </div>


     <%--        <div class="form-line mb-4">
                <label class="form-label">Pays *</label>
                <select class="form-select" name="pays" required id="pays" onchange="load()">
                   	<option <% if( v.getPays().equals( "France" ) ) out.println("selected");  %>>France</option>
                    <option <% if( v.getPays().equals( "Belgique" ) ) out.println("selected");  %>>Belgique</option>
                    <option <% if( v.getPays().equals( "Etats Unis" ) ) out.println("selected");  %>>Etats Unis</option>
                </select>
            </div>

            <div class="form-line mb-4">
                <label class="form-label">Ville *</label>
                <div id="cities-content" style="width: 59%;float: right">
                    <select class="form-select" name="ville" required id="ville">
                    	<%
                    	lv = ( ArrayList<Ville> ) request.getAttribute("villeville");
                    	
                    	for( Ville v : lv ){
                    		%>
                    		<option <% if( v.getVille().equals( v.getNom() ) ) out.println("selected");  %>><%= v.getNom() %></option>
                    		<%
                    	}
                    	%>
                    </select>
                </div>
            </div> --%>

            <div class="form-line mb-4">
                <button type="submit" class="btn btn-primary">Envoyer</button>
            </div>

        </form>
    </main>
    <%@ include file="fragments/footer.jsp" %>
</body>

</html>