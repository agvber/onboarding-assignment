package com.agvber.core.domain.entity

class SignupEntity(
    private val email: String,
    private val name: String,
    private val password: String
) {

    private val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}\$".toRegex()
    private val passwordLengthRange = 8..12

    fun checkEmailFormat() {
        require(emailRegex.matches(email)) {
            EMAIL_INVALID_MESSAGE
        }
    }

    fun checkNameFormat() {
        require(name.isNotBlank()) {
            NAME_INVALID_MESSAGE
        }
    }

    fun checkPasswordFormat() {
        require(password.length in passwordLengthRange) {
            PASSWORD_INVALID_MESSAGE
        }
    }

    companion object {

        const val EMAIL_INVALID_MESSAGE = "email_error"
        const val NAME_INVALID_MESSAGE = "name_error"
        const val PASSWORD_INVALID_MESSAGE = "password_error"
    }
}