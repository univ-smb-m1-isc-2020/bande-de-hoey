let resFinal;
const fav = "favoris";
const suiv = "suivis";
const coll = "collections";
$(document).ready(function() {
    function getAll(){
        var url = "http://localhost:8080/utilisateur/connectedUser";
        //var url ="https://bande-de-hoey.oups.net/utilisateur/connectedUser";
        fetch(url, {
            method: 'GET',
            redirect: 'follow',
        })
            .then(response => response.json())
            .then(result => {
                console.log(result["favoris"]["albums"]);
                console.log(result["suivis"]);
                console.log(result["collections"]);

                /***********************************************  favoris ************************************************************/
                //----------------------------------------------------- albums
                result["favoris"]["albums"].forEach(result =>{
                    var tr = $("<tr></tr>");
                    var td = $("<td>Titre</td>").text(result["titre"]);
                    tr.append(td);
                    td = $("<td>Serie</td>").text(result["serie"]);
                    tr.append(td);
                    td = $("<td>format</td>").text(result["format"]);
                    tr.append(td);
                    td = $("<td>type</td>").text(result["type"]);
                    tr.append(td);
                    td = $("<td>isbn</td>").text(result["isbn"]);
                    tr.append(td);
                    td = $("<td>ordre</td>").text(result["ordre"]);
                    tr.append(td);
                    td = $("<td>image</td>").text(result["image"]);
                    tr.append(td);
                    td = $("<td>auteur</td>").text(result["auteurs"]);
                    tr.append(td);

                    $("#resFavorisAlbum").append(tr)
                });
                //----------------------------------------------------- series
                result["favoris"]["series"].forEach(result =>{
                    var tr = $("<tr></tr>");

                    var td = $("<td>titre</td>").text(result["titre"]);
                    tr.append(td);
                    td = $("<td>Etat</td>").text(result["etat"]);
                    tr.append(td);
                    td = $("<td>albums</td>").text(result["albumes"]);
                    tr.append(td);
                    td = $("<td>auteurs</td>").text(result["auteurs"]);
                    tr.append(td);
                    td = $("<td>nb albums</td>").text(result["nbAlbum"]);
                    tr.append(td);
                    td = $("<td>format</td>").text(result["format"]);
                    tr.append(td);
                    td = $("<td>type</td>").text(result["type"]);
                    tr.append(td);

                    $("#resFavorisSerie").append(tr)
                });
                //----------------------------------------------------- auteurs
                result["favoris"]["auteurs"].forEach(result =>{
                    var tr = $("<tr></tr>");

                    var td = $("<td>Titre</td>").text(result["nom"]);
                    tr.append(td);
                    td = $("<td>Serie</td>").text(result["prenom"]);
                    tr.append(td);
                    td = $("<td>format</td>").text(result["series"]);
                    tr.append(td);
                    td = $("<td>type</td>").text(result["albums"]);
                    tr.append(td);

                    $("#resFavorisAuteur").append(tr)
                });
                /***********************************************  suivis ************************************************************/
                //----------------------------------------------------- albums
                result["suivis"]["albums"].forEach(result =>{
                    var tr = $("<tr></tr>");
                    var td = $("<td>Titre</td>").text(result["titre"]);
                    tr.append(td);
                    td = $("<td>Serie</td>").text(result["serie"]);
                    tr.append(td);
                    td = $("<td>format</td>").text(result["format"]);
                    tr.append(td);
                    td = $("<td>type</td>").text(result["type"]);
                    tr.append(td);
                    td = $("<td>isbn</td>").text(result["isbn"]);
                    tr.append(td);
                    td = $("<td>ordre</td>").text(result["ordre"]);
                    tr.append(td);
                    td = $("<td>image</td>").text(result["image"]);
                    tr.append(td);
                    td = $("<td>auteur</td>").text(result["auteurs"]);
                    tr.append(td);

                    $("#resSuivisAlbum").append(tr)
                });
                //----------------------------------------------------- series
                result["suivis"]["series"].forEach(result =>{
                    var tr = $("<tr></tr>");

                    var td = $("<td>titre</td>").text(result["titre"]);
                    tr.append(td);
                    td = $("<td>Etat</td>").text(result["etat"]);
                    tr.append(td);
                    td = $("<td>albums</td>").text(result["albumes"]);
                    tr.append(td);
                    td = $("<td>auteurs</td>").text(result["auteurs"]);
                    tr.append(td);
                    td = $("<td>nb albums</td>").text(result["nbAlbum"]);
                    tr.append(td);
                    td = $("<td>format</td>").text(result["format"]);
                    tr.append(td);
                    td = $("<td>type</td>").text(result["type"]);
                    tr.append(td);

                    $("#resSuivisSerie").append(tr)
                });
                //----------------------------------------------------- auteurs
                result["suivis"]["auteurs"].forEach(result =>{
                    var tr = $("<tr></tr>");

                    var td = $("<td>Titre</td>").text(result["nom"]);
                    tr.append(td);
                    td = $("<td>Serie</td>").text(result["prenom"]);
                    tr.append(td);
                    td = $("<td>format</td>").text(result["series"]);
                    tr.append(td);
                    td = $("<td>type</td>").text(result["albums"]);
                    tr.append(td);

                    $("#resSuivisAuteur").append(tr)
                });

                /***********************************************  collections ************************************************************/
                //----------------------------------------------------- albums
                result["collections"]["albums"].forEach(result =>{
                    var tr = $("<tr></tr>");
                    var td = $("<td>Titre</td>").text(result["titre"]);
                    tr.append(td);
                    td = $("<td>Serie</td>").text(result["serie"]);
                    tr.append(td);
                    td = $("<td>format</td>").text(result["format"]);
                    tr.append(td);
                    td = $("<td>type</td>").text(result["type"]);
                    tr.append(td);
                    td = $("<td>isbn</td>").text(result["isbn"]);
                    tr.append(td);
                    td = $("<td>ordre</td>").text(result["ordre"]);
                    tr.append(td);
                    td = $("<td>image</td>").text(result["image"]);
                    tr.append(td);
                    td = $("<td>auteur</td>").text(result["auteurs"]);
                    tr.append(td);

                    $("#resCollectionsAlbum").append(tr)
                });
                //----------------------------------------------------- series
                result["collections"]["series"].forEach(result =>{
                    var tr = $("<tr></tr>");

                    var td = $("<td>titre</td>").text(result["titre"]);
                    tr.append(td);
                    td = $("<td>Etat</td>").text(result["etat"]);
                    tr.append(td);
                    td = $("<td>albums</td>").text(result["albumes"]);
                    tr.append(td);
                    td = $("<td>auteurs</td>").text(result["auteurs"]);
                    tr.append(td);
                    td = $("<td>nb albums</td>").text(result["nbAlbum"]);
                    tr.append(td);
                    td = $("<td>format</td>").text(result["format"]);
                    tr.append(td);
                    td = $("<td>type</td>").text(result["type"]);
                    tr.append(td);

                    $("#resCollectionsSerie").append(tr)
                });
                //----------------------------------------------------- auteurs
                result["collections"]["auteurs"].forEach(result =>{
                    var tr = $("<tr></tr>");

                    var td = $("<td>Titre</td>").text(result["nom"]);
                    tr.append(td);
                    td = $("<td>Serie</td>").text(result["prenom"]);
                    tr.append(td);
                    td = $("<td>format</td>").text(result["series"]);
                    tr.append(td);
                    td = $("<td>type</td>").text(result["albums"]);
                    tr.append(td);

                    $("#resCollectionsAuteur").append(tr)
                });

            })
            .catch(error => console.log('error', error));
    }

    $('#submit-id').click(getAll);

});

