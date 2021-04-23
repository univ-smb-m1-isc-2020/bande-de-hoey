$(document).ready(function() {
    let id = 0;
    function serie(){
        var res = document.getElementById("serie-select").value;
        var search = document.getElementById("serie-search").value;
        var url = "http://localhost:8080/serie/";
        search="serie1";
        if(res == "title"){
            url = url+"byTitle?title="+search;
        }else if(res == "format"){
            url = url+"byFormat?format="+search;
        }else if(res == "auteur"){
            url = url+"byAuteur?auteur="+search;
        }else if(res == "type"){
            url = url+"byType?tyep="+search;
        }else if(res == "etat"){
            url = url+"byEtat?etat="+search;
        }else{
            alert("error");
        }

        fetch(url, {
            method: 'GET',
            redirect: 'follow',
        })
            .then(response => response.json())
            .then(result => {
                console.log(result);
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
                var button = $("<button value='test' class='addFavoris'>testss</button>");
                resFinal = result;
                td = $("<td>\<button id='but-test' value='3' onclick='addSerieToFavoris(resFinal)' >add to favoris</button>\</td>");
                tr.append(td);

                $("#table").append(tr);


            })
            .catch(error => console.log('error', error));
    }

    $('#submit-id').click(serie);

});

function addSerieToFavoris(serie){
    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var body = JSON.stringify({
        "etat":serie["etat"],
        "titre":serie["titre"],
        "type":serie["type"],
        "nbAlbum":serie["nbAlbum"],
        "format":serie["format"],
    });

    fetch("http://localhost:8080/utilisateur/addSerie", {
        method: 'POST',
        headers: myHeaders,
        body: body,
        redirect: 'follow',
    })
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}
