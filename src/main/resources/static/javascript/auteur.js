let incrIDbutton = 0;
let globalTab = [];
let resFinal ;
const fav = "favoris";
const suiv = "suivis";
const coll = "collections";
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
        }else if(res == "all"){
            url = url+"all";
        }else{
            alert("error")
        }
        fetch(url, {
            method: 'GET',
            redirect: 'follow',
        })
            .then(response => response.json())
            .then(result => {
                if(res == "all"){
                    result.forEach(result => {
                        console.log(result);
                        resFinal = result;
                        globalTab.push(result);
                        globalTab.push(result);
                        globalTab.push(result);

                        var ids = incrIDbutton.toString();
                        td = $("<td>\<button onclick='addSerie(globalTab[this.id],fav)' >add to favoris</button>\</td>");
                        td.children().attr('id', incrIDbutton)
                        incrIDbutton+=1;
                        tr.append(td);
                        td = $("<td>\<button  onclick='addSerie(globalTab[this.id],suiv)' >add to suivis</button>\</td>");
                        td.children().attr('id', incrIDbutton)
                        incrIDbutton+=1;
                        tr.append(td);
                        td = $("<td>\<button onclick='addSerie(globalTab[this.id],coll)' >add to collections</button>\</td>");
                        td.children().attr('id', incrIDbutton)
                        incrIDbutton+=1;
                        tr.append(td);
                        $("#table").append(tr);
                    })
                }else {
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
                    td = $("<td>\<button id='but-test' onclick='addAlbum(resFinal,fav)' >add to favoris</button>\</td>");
                    tr.append(td);
                    td = $("<td>\<button id='but-test' onclick='addAlbum(resFinal,suiv)' >add to suivis</button>\</td>");
                    tr.append(td);
                    td = $("<td>\<button id='but-test' onclick='addAlbum(resFinal,coll)' >add to collection</button>\</td>");
                    tr.append(td);
                    $("#table").append(tr);
                }

            })
            .catch(error => console.log('error', error));
    }
    $('#submit-id').click(auteur);
});

function addAlbum(album,to){
    var url = "http://localhost:8080/utilisateur/addAuteurTo";
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
        "nom":album["nom"],
        "prenom":album["prenom"],
        "serie":album["serie"],
        "album":album["album"],
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
