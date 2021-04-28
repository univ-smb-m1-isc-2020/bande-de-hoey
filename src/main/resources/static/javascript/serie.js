let resFinal;
let globalTab = [];
const fav = "favoris";
const suiv = "suivis";
const coll = "collections";
$(document).ready(function() {

    let incrIDbutton = 0;


    function serie(){

        var res = document.getElementById("serie-select").value;
        var search = document.getElementById("serie-search").value;
        var url = "http://localhost:8080/serie/";
        //var url ="https://bande-de-hoey.oups.net/serie/";
        console.log(url);

        if(res == "title"){
            url = url+"byTitle?title="+search;
        }else if(res == "format"){
            url = url+"byFormat?format="+search;
        }else if(res == "auteur"){
            url = url+"byAuteur?auteur="+search;
        }else if(res == "type"){
            url = url+"byType?type="+search;
        }else if(res == "etat"){
            url = url+"byEtat?etat="+search;
        }else if(res == "all"){
            url = url+"all";
        }else{
            alert("error");
        }

        fetch(url, {
            method: 'GET',
            redirect: 'follow',
        })
            .then(response => response.json())
            .then(result => {
                if(res != "title"){
                    console.log("hoey :" +result);
                    result.forEach(result => {
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

                        resFinal = result;
                        globalTab.push(result);

                        td = $("<td>\<button onclick='addSerie(resFinal,fav,)' >add to favoris</button>\</td>");
                        td.attr('id', 'but-test' + incrIDbutton);
                        incrIDbutton+=1;
                        tr.append(td);
                        td = $("<td>\<button id='but-test'  onclick='addSerie(resFinal,suiv,)' >add to suivis</button>\</td>");
                        td.attr('id', 'but-test' + incrIDbutton);
                        incrIDbutton+=1;
                        tr.append(td);
                        td = $("<td>\<button id='but-test'  onclick='addSerie(resFinal,coll,)' >add to collections</button>\</td>");
                        td.attr('id', 'but-test' + incrIDbutton);
                        incrIDbutton+=1;
                        tr.append(td);
                        $("#table").append(tr);
                    })
                    console.log(globalTab);
                }else{
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
                    resFinal = result;
                    td = $("<td>\<button id='but-test'  onclick='addSerie(resFinal,fav)' >add to favoris</button>\</td>");
                    tr.append(td);
                    td = $("<td>\<button id='but-test'  onclick='addSerie(resFinal,suiv)' >add to suivis</button>\</td>");
                    tr.append(td);
                    td = $("<td>\<button id='but-test'  onclick='addSerie(resFinal,coll)' >add to collections</button>\</td>");
                    tr.append(td);
                    $("#table").append(tr);
                }
            })
            .catch(error => console.log('error', error));
    }

    $('#submit-id').click(serie);

});

function addSerie(serie,to){
    console.log( globalTab[Number.parseInt( ($(this).attr('id')).slice(-1), 10)] );

    var url = "http://localhost:8080/utilisateur/addSerieTo";
    if(to=='favoris'){
        url = url+"Favoris";
    }else if(to =='suivis'){
        url = url+"Suivis";
    }else{
        url = url+"Collections";
    }

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var body = JSON.stringify({
        "etat":serie["etat"],
        "titre":serie["titre"],
        "type":serie["type"],
        "nbAlbum":serie["nbAlbum"],
        "format":serie["format"],
    });

    fetch(url, {
        method: 'POST',
        headers: myHeaders,
        body: body,
        redirect: 'follow',
    })
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}
