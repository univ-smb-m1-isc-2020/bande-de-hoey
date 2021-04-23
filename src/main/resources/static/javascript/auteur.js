
let resFinal ;
$(document).ready(function() {

    function auteur(){
        var res = document.getElementById("auteur-select").value;
        var search = document.getElementById("id-search").value;
        var url =" http://localhost:8080/auteur/";
        //var url ="https://bande-de-hoey.oups.net/auteur/";

        if(res == "name"){
            url = url+"byName?name="+search;
        }else if(res == "serie"){
            url = url+"bySerie?serie="+search;
        }else if(res == "album"){
            url = url+"byAlbum?album="+search;
        }else{
            alert("error")
        }
        fetch(url, {
            method: 'GET',
            redirect: 'follow',
        })
            .then(response => response.json())
            .then(result => {
                console.log(result);
                var tr = $("<tr></tr>");

                var td = $("<td>Titre</td>").text(result["nom"]);
                tr.append(td);
                td = $("<td>Serie</td>").text(result["prenom"]);
                tr.append(td);
                td = $("<td>format</td>").text(result["series"]);
                tr.append(td);
                td = $("<td>type</td>").text(result["albums"]);
                tr.append(td);
                resFinal = result;
                td = $("<td>\<button id='but-test' onclick='addAlbumToFavoris(resFinal)' >add to favoris</button>\</td>");
                tr.append(td);
                $("#table").append(tr);
            })
            .catch(error => console.log('error', error));
    }
    $('#submit-id').click(auteur);
});

function addAlbumToFavoris(album){
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var body = JSON.stringify({
        "nom":album["nom"],
        "prenom":album["prenom"],
        "serie":album["serie"],
        "album":album["album"],
    });

    fetch("http://localhost:8080/utilisateur/addAuteurToFavoris", {
        method: 'POST',
        headers: myHeaders,
        body: body,
        redirect: 'follow',
    })
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}
