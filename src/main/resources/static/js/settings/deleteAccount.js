/**
 * Javascript file for deleting user
 */

const onDeleteAccount = () => {
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
        .then((willDelete) => {
            if (willDelete) {
               // TODO: delete account and redirect to register page
            }
        });
}