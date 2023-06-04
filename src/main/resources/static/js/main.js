/**
 * Add a new list to the navbar
 */
const addNewList = () => {
    const listName = document.getElementById("listName")
    const errorMessage = document.getElementById("error-message-new-list");



    if (listName.value.length !== 0) {
        const radioButtons = document.querySelectorAll('input[name="inlineRadioOptions"]');

        let identifier = null;

        for (const rbtn of radioButtons) {
            if (rbtn.checked) {
                identifier = rbtn.value;
                break;
            }
        }

        axios.post("/list", {
            name: listName.value,
            access: identifier,
        }).then(async (res) => {
            // redirect if everything is ok
            if (res.data) {
                // close if everything is ok
                $('#exampleModal').hide()
                $('.modal-backdrop').remove()

                // clear input
                listName.value = "";
                errorMessage.hidden = true;

                window.location.href = "/"
            } else {
                // show error message
                const errorMessage = document.getElementById("error-message-new-list");

                errorMessage.hidden = false;
                errorMessage.innerText = "List could not be saved"
                errorMessage.style.color = "red";
                errorMessage.style.fontWeight = "bold";
            }
        }).catch((error) => {
            console.log(error)
        })

        return;
    }

    errorMessage.hidden = false;
    errorMessage.innerText = "The list must contain at least one character."
    errorMessage.style.color = "red";
    errorMessage.style.fontWeight = "bold";

}