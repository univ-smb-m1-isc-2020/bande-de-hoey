function Series(titre, etat, auteur, formate, genre){
    this.titre = titre;
    this.etat = etat;
    this.auteur = auteur;
    this.formate = formate;
    this.genre = genre;
}

const serie1 = new Series('One piece', 'en cours', 'oda', 'manga', 'shonen');
const serie2 = new Series('The Amazing Spiderman(2015)', 'fini', 'Axel Alonzo/Devin Lewis', 'comics', 'action');

let series = [];
series.push(serie1, serie2);

function populateTableList(){
    let listOfSeries ='';

    series.forEach(serie =>
        listOfSeries +=
            `<tr className="text-center">
                <td><a href="">${serie.titre}</a></td>
                <td><a href="">${serie.etat}</a></td>
                <td><a href="">${serie.auteur}</a></td>
                <td><a href="">${serie.formate}</a></td>
                <td><a href="">${serie.genre}</a></td>
            </tr>`
    )
    document.getElementById('serieList').innerHTML = listOfSeries;

    //https://www.youtube.com/watch?v=s2bTANyJNV0
}
