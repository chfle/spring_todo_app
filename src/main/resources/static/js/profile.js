const onUpdateProfile = () => {
    // put request
    axios.put("/profile", {
        username: document.getElementById("profile-username").value,
        email: document.getElementById("profile-email").value
    }).then(async (res) => {
        if (res.data) {
          location.href = "/logout"
        } else {
            swal({
                icon: 'error',
                title: 'Error',
                text: 'Could not update profile',
            })
        }
    }).catch((error) => {
        console.log(error)
    })
}

/**
 * Check if the uploaded file has the right format
 * @param file
 * @returns {boolean}
 */
function isFileImage(file) {
    const acceptedImageTypes = ['image/gif', 'image/jpeg', 'image/png'];

    return acceptedImageTypes.includes(file)
}