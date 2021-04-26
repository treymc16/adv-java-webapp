/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

function validateForm() {
    var newPassword = document.forms["pwordForm"]["newpassword"].value;
    var oldPassword = document.forms["pwordForm"]["oldpassword"].value;
    if (newPassword == oldPassword) {
        alert("New password can't be the same as the old password!");
        return false;
    }
}
