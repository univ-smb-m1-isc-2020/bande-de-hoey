let resFinal;
const fav = "favoris";
const suiv = "suivis";
const coll = "collections";
$(document).ready(function() {
    function getAll(){
        var url = "http://localhost:8080/utilisateur/connectedUser";
        //var url ="https://bande-de-hoey.oups.net/utilisateur/connectedUser";

        console.log('teeeest ok button');
        fetch(url, {
            method: 'GET',
            redirect: 'follow',
        })
            .then(response => response.json())
            .then(result => {
                console.log(result);
                var tr = $("<tr></tr>");

                /*var td = $("<td>titre</td>").text(result["titre"]);
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
                resFinal = result;
                td = $("<td>\<button id='but-test'  onclick='addSerie(resFinal,fav)' >add to favoris</button>\</td>");
                tr.append(td);
                td = $("<td>\<button id='but-test'  onclick='addSerie(resFinal,suiv)' >add to suivis</button>\</td>");
                tr.append(td);
                td = $("<td>\<button id='but-test'  onclick='addSerie(resFinal,coll)' >add to collections</button>\</td>");
                tr.append(td);*/
                $("#table").append(tr);
            })
            .catch(error => console.log('error', error));
    }

    $('#submit-id').click(getAll());

});

