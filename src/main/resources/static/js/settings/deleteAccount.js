/**
 * Javascript file for deleting user
 */


/**
 * Delete user
 */
const onDeleteAccount = (username) => {
    swal({
        title: "Are you sure you want to delete your account?",
        text: "Please note that deleting your account is a permanent action" +
            ", and all of your data, settings, and content associated with your account will be permanently " +
            "deleted from our system.",
        icon: "warning",
        width: 600,
        buttons: true,
        dangerMode: true,
    })
        // if user clicked ok, then delete user
        .then((willDelete) => {
            if (willDelete) {
                axios.delete("delete?username=" + username)
                    .then(async (res) => {
                        // redirect if everything is ok
                        if (!res.data) {
                            swal({
                                icon: 'error',
                                title: 'Error',
                                text: 'User could not be deleted! Please try again.',
                            })
                        } else {
                            location.href = "/logout"
                        }

                    }).catch((error) => {
                    swal({
                        icon: 'error',
                        title: 'Error',
                        text: 'User could not be deleted! Please try again.',
                    })
                })
            }
        });
}