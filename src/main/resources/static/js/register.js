/**
 * Javascript file for the register page
 */

// validate user data and send post
const onRegister = () => {
    const name = document.getElementById("name")
    const email = document.getElementById("email")
    const password = document.getElementById("password")
    const passwordRepeat = document.getElementById("password-repeat")
    const validRegex =/^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    // check if passwords are matching
    if (name.value.length === 0){
        name.setCustomValidity("Name should not be empty");
        return;
    } else if (email.value.length === 0 && !email.value.match(validRegex)) {
        email.setCustomValidity("Email is not valid");
        return;
    }else if (password.value !== passwordRepeat.value) {
        passwordRepeat.setCustomValidity("Passwords do not match");
        return;
    } else if (password.value.length < 8) {
        password.setCustomValidity("Password should have at least 8 characters")
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