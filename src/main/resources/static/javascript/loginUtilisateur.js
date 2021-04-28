
function connexion(){
    var mail = document.getElementById("mail").value;
    var mdp = document.getElementById("mdp").value;

    var myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    var body = JSON.stringify({"mail":mail,"mdp":mdp});
    var res;
    fetch("http://localhost:8080/utilisateur/login?mail="+mail+"&mdp="+mdp, {
        method: 'GET',
        redirect: 'follow',
    })
        .then(response => response.text())
        .then(result => {
            if(result == "true"){
                window.location.replace("http://localhost:63342/gestion-bd/templates/home.html");
            }else{
                alert("this account doesn't exist, try to change the mail or the password")
            }
        })
        .catch(error => console.log('error', error));
}