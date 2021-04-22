
function getValueLastName() {
    var input = document.getElementById("nom").value;
    alert(input);
}

function getValueFirstName() {
    var input = document.getElementById("nom").value;
    alert(input);
}
function getValuePseudo() {
    var input = document.getElementById("pseudo").value;
    alert(input);
}
function getValueMail() {
    var input = document.getElementById("pseudo").value;
    alert(input);
}
function getValueMDP() {
    var input = document.getElementById("mdp").value;
    alert(input);
}
function getValueMDP2() {
    var input = document.getElementById("mdp2").value;
    alert(input);
}

function sendValueToJava() {
    var prenom = document.getElementById("prenom").value;
    var nom = document.getElementById("nom").value;
    var mdp = document.getElementById("mdp").value;
    var mdp2 = document.getElementById("mdp2").value;
    var pseudo = document.getElementById("pseudo").value;
    var mail = document.getElementById("mail").value;

    if( mdp != mdp2){
        alert("invalid")
    }else{
        var myHeaders = new Headers();
        myHeaders.append("Content-Type", "application/json");

        var body = JSON.stringify({
                "nom":nom,
                "prenom":prenom,
                "mail":mail,
                "pseudo":pseudo,
                "mdp":mdp});

        fetch("http://localhost:8080/utilisateur/inscription", {
            method: 'POST',
            headers: myHeaders,
            body: body,
            redirect: 'follow',
        })
            .then(response => response.text())
            .then(result => console.log(result))
            .catch(error => console.log('error', error));
    }

}

