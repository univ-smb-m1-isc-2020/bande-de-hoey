
function auteur(){
    var res = document.getElementById("auteur-select").value;
    var search = document.getElementById("auteur-search").value;
    var url;

    if(res == "name"){
        url = "http://localhost:8080/auteur/byName?name="+search;
    }else if(res == "album"){
        url = "http://localhost:8080/auteur/byAlbum?album="+search;
    }else if(res == "serie"){
        url = "http://localhost:8080/auteur/bySerie?serie="+search;
    }else{
        alert("error")
    }
    console.log(url)
    fetch(url, {
        method: 'GET',
        redirect: 'follow',
    })
        .then(response => response.text())
        .then(result => {
            console.log(result);
        })
        .catch(error => console.log('error', error));
}