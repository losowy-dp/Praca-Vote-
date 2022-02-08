package com.example.vote_01.ExtraFuncrion

import android.util.Patterns

fun VerificationRegistration (firstName: String,lastName: String,email: String,passwordOne: String,passwordTwo:String):String
{
    if(firstName == "")
        return "Write First Name"
    if(lastName == "")
        return "Write Last Name"
    if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        return "Write email"
    if(passwordOne.length < 6)
        return "Password have need min 6 char"
    if(passwordOne != passwordTwo)
        return "Passwords are not the same"
    return ""
}