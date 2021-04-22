let resFinal = ""
function albums(){
    var res = document.getElementById("album-select").value;
    var search = document.getElementById("id-search").value;
    var url;

    if(res == "title"){
        url = "http://localhost:8080/album/byTitle?title="+search;
    }else if(res == "isbn"){
        url = "http://localhost:8080/album/byISBN?isbn="+search;
    }else if(res == "auteur"){
        url = "http://localhost:8080/album/byAuteur?auteur="+search;
    }else if(res == "serie"){
        url = "http://localhost:8080/album/bySerie?serie="+search;
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