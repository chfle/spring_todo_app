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

/**
 * delete profile pic by username
 * @param username username of the user
 */
const onDeleteProfilePic = (username) => {
    // ask user if he is sure
    swal({
        title: "Are you sure you want to delete your profile pic?",
        text: "Deleting your Profile pic will resolve in removing it permanently",
        icon: "warning",
        width: 600,
        buttons: true,
        dangerMode: true,
    })
        // if user clicked ok, then delete user
        .then((willDelete) => {
            if (willDelete) {
                axios.delete("/profile/deleteProfilePic?username=" + username)
                    .then(async (res) => {
                        // redirect if everything is ok
                        if (!res.data) {
                            swal({
                                icon: 'error',
                                title: 'Error',
                                text: 'Profile pic could not be deleted! Please try again.',
                            })
                        } else {
                            location.reload();
                        }

                    }).catch((error) => {
                    swal({
                        icon: 'error',
                        title: 'Error',
                        text: 'Profile pic could not be deleted! Please try again.',
                    })
                })
            }
        });
}
