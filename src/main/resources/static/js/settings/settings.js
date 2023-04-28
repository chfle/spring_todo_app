const onChangePassword = (username) => {
    const password = document.getElementById("inputPasswordNew");
    const passwordRepeat = document.getElementById("inputPasswordNewVerify");

    if (password.value.length < 8) {
        password.setCustomValidity("Password should have at least 8 characters")
        return;
    }else if (password.value !== passwordRepeat.value) {
        passwordRepeat.setCustomValidity("Passwords do not match");
        return;
    }

    // post request
    axios.put("/settings/password", {
        username: username,
        passwordOld: document.getElementById("inputPasswordOld").value,
        passwordNew: document.getElementById("inputPasswordNew").value
    }).then(async (res) => {
        if (res.data) {
            swal({
                icon: 'success',
                title: 'Password changed',
                text: 'Password has changed',
            })
        } else {
            swal({
                icon: 'error',
                title: 'Error',
                text: 'could not change password',
            })
        }
    }).catch((error) => {
        console.log(error)
    })
}