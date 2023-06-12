/**
 * Formate une date au format "dd/MM/yyyy".
 *
 * @param {Date} date
 * @returns {string}
 */
export function formatDate(date) {
    const yyyy = date.getFullYear();
    const MM = pad(date.getMonth());
    const dd = pad(date.getDate());
    return `${dd}/${MM}/${yyyy}`;
}

/**
 * Formate une date au format "HH:mm:ss".
 *
 * @param {Date} date
 * @returns {string}
 */
export function formatTime(date) {
    const HH = pad(date.getHours());
    const mm = pad(date.getMinutes());
    const ss = pad(date.getSeconds());
    return `${HH}:${mm}:${ss}`;
}

/**
 * Formate une date au format "dd/MM/yyyy <sep> HH:mm:ss" où <sep> est un caractère (ou une chaine de caractères)
 * de séparation.
 *
 * @param {Date} date La date à formaté.
 * @param {string} sep - Un séparateur entre la date et l'heure.
 * @returns {string} La date au format complet date+heure avec une chaine de séparation.
 */
export function formatFull(date, sep) {
    const d = formatDate(date);
    const t = formatTime(date);
    return `${d}${sep}${t}`;
}

/**
 * Formate une date au format "dd/MM/yyyy à HH:mm:ss".
 *
 * @param {Date} date La date à formaté.
 * @returns {string} La date au format complet date+heure avec une chaine de séparation.
 */
export function formatFullFR(date) {
    return formatFull(date, " à ");
}

/**
 * Ajoute un zéro au début pour les chiffres inférieurs à 10.
 * @param value La valeur à laquelle ajouter un zéro.
 * @returns {string} La nouvelle valeur avec un zéro au début si besoin.
 */
function pad(value) {
    return value + value < 10 ? '0' : ''
}