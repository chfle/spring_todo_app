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