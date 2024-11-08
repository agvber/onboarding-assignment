package com.agvber.core.domain.entity

import org.junit.Before
import org.junit.Test

class SignupEntityUnitTest {

    private lateinit var signupEntity: SignupEntity

    @Before
    fun setup() {
        signupEntity = SignupEntity(
            "example@gmail.com", "minjun", "12345678"
        )
    }

    @Test
    fun `이메일 형식이 옳바른지 검증합니다`() {
        signupEntity.checkEmailFormat()
    }

    @Test
    fun `이름 형식이 옳바른지 검증합니다`() {
        signupEntity.checkNameFormat()
    }

    @Test
    fun `패스워드 형식이 옳바른지 검증합니다`() {
        signupEntity.checkPasswordFormat()
    }
}