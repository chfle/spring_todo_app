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

/**
 * Add modal body for the edit list modal
 */
const editListButtonBody = (listName, access, userId, listUserID) => {
    let modalBody = document.getElementById("modal-body-edit-list");

    // user can edit list
    if (userId === listUserID) {

        modalBody.innerHTML = `
        <div class="form-group">
                  <label for="listName" style="display: none">New List</label>
                  <input type="text" class="form-control" id="listName" placeholder="List name" value="${listName}">
        </div>
        <div class="form-group">
                   <div class="form-check form-check-inline">
                         <input  class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio1-edit-list" value="PRIVATE" />
                         <label class="form-check-label" for="inlineRadio1-edit-list">private</label>
                   </div>
                   <div class="form-check form-check-inline">
                         <input class="form-check-input" type="radio" name="inlineRadioOptions" id="inlineRadio2-edit-list" value="PUBLIC" />
                         <label class="form-check-label" for="inlineRadio2-edit-list">public</label>
                   </div>
        </div>
    `

        if (access === 'private') {
            document.getElementById("inlineRadio1-edit-list").checked = true;
            document.getElementById("inlineRadio2-edit-list").checked = false;
        } else if (access === 'public') {
            document.getElementById("inlineRadio1-edit-list").checked = false;
            document.getElementById("inlineRadio2-edit-list").checked = true;
        }

    } else {
        modalBody.innerHTML = `
            <p style="font-weight: bold">You are not the owner of this list.</p>
        `

        document.getElementById('save-edit-list').disabled = true;
    }
}