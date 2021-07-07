package com.wecure.doctor

import android.util.Patterns
import java.util.regex.Pattern

object FieldValidators {

    //check if email pattern is vaild
    fun isValidEmail(email: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    //check if email contains a digit
    fun isStringContainNumber(text: String): Boolean {
        val pattern = Pattern.compile(".*\\d.*")
        val matcher = pattern.matcher(text)
        return matcher.matches()
    }
    //check if email contains lower and upper case
    fun isStringLowerAndUpperCase(text: String): Boolean {
        val lowerCasePattern = Pattern.compile(".*[a-z].*")
        val upperCasePattern = Pattern.compile(".*[A-Z].*")
        val lowerCasePatterMatcher = lowerCasePattern.matcher(text)
        val upperCasePatterMatcher = upperCasePattern.matcher(text)
        return if (!lowerCasePatterMatcher.matches()) {
            false
        } else upperCasePatterMatcher.matches()
    }
    //check if email contains special character
    fun isStringContainSpecialCharacter(text: String): Boolean {
        val specialCharacterPattern = Pattern.compile("[^a-zA-Z0-9 ]")
        val specialCharacterMatcher = specialCharacterPattern.matcher(text)
        return specialCharacterMatcher.find()
    }
}
