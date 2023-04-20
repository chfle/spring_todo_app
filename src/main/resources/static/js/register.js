/**
 * Javascript file for the register page
 */

// validate user data and send post
const onRegister = () => {
    const name = document.getElementById("name")
    const email = document.getElementById("email")
    const password = document.getElementById("password")
    const passwordRepeat = document.getElementById("password-repeat")


    // check if passwords are matching
    if (password.value !== passwordRepeat.value) {
        passwordRepeat.setCustomValidity("Passwords do not match");
        return;
    }

    // post request
    axios.post("/register", {
        name: name.value,
        email: email.value,
        password: password.value,
    }).then(async (res) => {
        // redirect if everything is ok
        if (res.data) {
            window.location.href = "/login"
        } else {
            // show error message
            //TOdo: show error message
        }
    }).catch((error) => {
        console.log(error)
    })
}