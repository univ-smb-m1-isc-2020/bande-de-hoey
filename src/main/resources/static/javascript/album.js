let incrIDbutton = 0;
let globalTab = [];
let resFinal;
const fav = "favoris";
const suiv = "suivis";
const coll = "collections";
$(document).ready(function() {

    function album(){
        var res = document.getElementById("album-select").value;
        var search = document.getElementById("id-search").value;
        var url ="http://localhost:8080/album/";
        //var url ="https://bande-de-hoey.oups.net/album/";

        if(res == "title"){
            url = url+"byTitle?title="+search;
        }else if(res == "isbn"){
            url = url+"byISBN?isbn="+search;
        }else if(res == "auteur"){
            url = url+"byAuteur?auteur="+search;
        }else if(res == "serie"){
            url = url+"bySerie?serie="+search;
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
                if (res == "all") {
                    result.forEach(result => {
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
                        td = $('<img />', {src : result["image"] +'.png'}, {width : "500"});
                        tr.append(td);
                        result["auteurs"].forEach( e =>{
                            td = $("<td>auteur</td>").text(e.nom);
                            tr.append(td);
                        });
                        td = $("<td>auteur</td>").text(result["auteurs"]);
                        tr.append(td);
                        resFinal = result;
                        globalTab.push(result);
                        globalTab.push(result);
                        globalTab.push(result);

                        var ids = incrIDbutton.toString();
                        td = $("<td>\<button onclick='addAlbum(globalTab[this.id],fav)' >add to favoris</button>\</td>");
                        td.children().attr('id', incrIDbutton)
                        incrIDbutton+=1;
                        tr.append(td);
                        td = $("<td>\<button  onclick='addAlbum(globalTab[this.id],suiv)' >add to suivis</button>\</td>");
                        td.children().attr('id', incrIDbutton)
                        incrIDbutton+=1;
                        tr.append(td);
                        td = $("<td>\<button onclick='addAlbum(globalTab[this.id],coll)' >add to collections</button>\</td>");
                        td.children().attr('id', incrIDbutton)
                        incrIDbutton+=1;
                        tr.append(td);
                        $("#table").append(tr);


                    })
                } else {
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
                    resFinal = result;

                    td = $("<td>\<button id='but-test' onclick='addAlbum(resFinal,fav)'>add to favoris</button>\</td>");
                    tr.append(td);
                    td = $("<td>\<button id='but-test' onclick='addAlbum(resFinal,suiv)'>Suivre</button>\</td>");
                    tr.append(td);
                    td = $("<td>\<button id='but-test' onclick='addAlbum(resFinal,coll)'>add to my collection</button>\</td>");
                    tr.append(td);
                    $("#table").append(tr)
                }
            })
            .catch(error => console.log('error', error));
    }
    $('#submit-id').click(album);
});

function addAlbum(album,to){
    console.log("album  :"+album["titre"]);
    var url = "http://localhost:8080/utilisateur/addAlbumTo";
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
        "titre":album["titre"],
        "serie":album["serie"],
        "format":album["format"],
        "type":album["type"],
        "isbn":album["isbn"],
        "ordre":album["ordre"],
        "image":album["image"],
        "auteurs":album["auteurs"],
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
