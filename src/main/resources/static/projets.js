const BASEURL_WEBSERVICE_PROJETS = "/api/projets";
const BASEURL_FRAGMENT_PROJETS = "/fragments/projets";
const BASEURL_WEBSERVICE_VOTES = "/api/votes";

const BTN_CREATE = "btn-create-polling";
const BTN_REFRESH = "btn-refresh-pollings";
const DIV_PROJETS = "projets";
const FORM_CREATE_PROJET = "form-creation-PROJET";

document.onreadystatechange = () => {
    if (document.readyState === "complete") {
        let btnRefreshPollings = document.getElementById(BTN_REFRESH);
        let formCreatePolling = document.getElementById(FORM_CREATE_PROJET);
        btnRefreshPollings.addEventListener("click", refreshAllPROJETS);
        formCreatePolling.addEventListener("submit", createNewPolling);
    }
}

/**
 * Recharge la liste des PROJETS.
 * @param {MouseEvent} event
 */
function refreshAllPROJETS(event) {

    let pageCourante = document.querySelector(".page-courante").textContent;
    pageCourante -= 1;

    fetch(BASEURL_FRAGMENT_PROJETS + '?page=' + pageCourante)
        .then(result => result.text())
        .then(text => {
            let div = document.getElementById(DIV_PROJETS);
            let documentFragment = document.createRange().createContextualFragment(text);
            div.innerHTML = documentFragment.firstChild.innerHTML;
        })
    ;
}

/**
 * Ajoute un nouveau PROJET dans la liste sans devoir tout recharger.
 * @param {PROJET} PROJET
 */
function updateListWith(PROJET) {
    console.log("Mise à jour de la page avec le nouveau PROJET : ");
    console.log(PROJET);

    fetch(BASEURL_FRAGMENT_PROJETS + '/' + PROJET.id)
        .then(response => response.text())
        .then(text => {
            const div = document.getElementById(DIV_PROJETS);
            let documentFragment = document.createRange().createContextualFragment(text);
            div.prepend(documentFragment.firstChild);
        });
}

/**
 * Envoie les données du PROJET vers l'URL "/api/PROJETS" avec méthode POST.
 *
 * @param {Event} event L'évènement de click.
 */
function createNewPolling(event) {

    // Pour éviter d'envoyer une requête POST par défaut via le navigateur
    // on désactive le comportement par défaut du bouton submit car on va
    // gérer nous-même la requête en mode 'fetch'
    event.preventDefault();

    // On transforme les champs du formulaire au format JSON
    let form = document.getElementById(FORM_CREATE_PROJET);
    let formData = new FormData(form);
    let json = JSON.stringify(Object.fromEntries(formData.entries()));

    console.debug("Sending data to server : \n" + json);

    // Envoi des données au WebService
    fetch(BASEURL_WEBSERVICE_PROJETS, {
        method: 'POST',
        body: json,
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(response => {
        if (response.ok) {
            // La requête s'est bien passée
            console.log("PROJET créé avec succès !");
            response.json().then(PROJET => updateListWith(PROJET));
        } else {
            // La requête a échoué
            console.error("Erreur lors de la création du PROJET !");
            response.json().then(err => console.error(err));
        }
    }).catch(error => {
        console.error("Erreur lors de la requête :", error);
    });
}

