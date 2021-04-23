$(document).ready(function() {
    let id = 0;
    function serie(){
        var res = document.getElementById("serie-select").value;
        var search = document.getElementById("serie-search").value;
        var url;

        if(res == "title"){
            url = "http://localhost:8080/serie/byTitle?title="+search;
        }else if(res == "format"){
            url = "http://localhost:8080/serie/byFormat?format="+search;
        }else if(res == "auteur"){
            url = "http://localhost:8080/serie/byAuteur?auteur="+search;
        }else if(res == "type"){
            url = "http://localhost:8080/serie/byType?tyep="+search;
        }else if(res == "etat"){
            url = "http://localhost:8080/serie/byEtat?etat="+search;
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

                var div = $("<div></div>");// Create with jQuery

                var res = $("<div class='serie'></div>").text(result);
                var button = $("<input type='submit' value='favorissss' name='submit' id='test' class='addFavoris'/>");

                div.append(res);
                div.append(button);
                $("#res").append(div);
            })
            .catch(error => console.log('error', error));
    }

    function testq(){
        alert("testttttt");
        console.log('clicked : ');
    }

    $('#submit-id').click(serie);
    $('#test').click(testq);
});