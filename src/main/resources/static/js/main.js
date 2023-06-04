/**
 * Add a new list to the navbar
 */
const addNewList = () => {
    const listName = document.getElementById("listName")
    const errorMessage = document.getElementById("error-message-new-list");



    if (listName.value.length !== 0) {
        // TODO: backend request
        // close if everything is ok
        $('#exampleModal').hide()
        $('.modal-backdrop').remove()

        // clear input
        listName.value = "";
        errorMessage.hidden = true;
        return;
    }

    errorMessage.hidden = false;
    errorMessage.innerText = "The list must contain at least one character."
    errorMessage.style.color = "red";
    errorMessage.style.fontWeight = "bold";
}