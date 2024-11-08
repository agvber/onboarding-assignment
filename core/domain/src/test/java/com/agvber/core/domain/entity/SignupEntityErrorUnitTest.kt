package com.agvber.core.domain.entity

import org.junit.Assert
import org.junit.Before
import org.junit.Test

class SignupEntityErrorUnitTest {

    private lateinit var signupEntity: SignupEntity

    @Before
    fun setup() {
        signupEntity = SignupEntity(
            "example@gmail", "", "123"
        )
    }

    @Test
    fun `잘못된 이메일 형식의 예외 처리를 테스트 합니다`() {
        Assert.assertThrows(
            SignupEntity.EMAIL_INVALID_MESSAGE,
            IllegalArgumentException::class.java
        ) {
            signupEntity.checkEmailFormat()
        }
    }

    @Test
    fun `잘못된 이름 형식의 예외 처리를 테스트 합니다`() {
        Assert.assertThrows(
            SignupEntity.NAME_INVALID_MESSAGE,
            IllegalArgumentException::class.java
        ) {
            signupEntity.checkNameFormat()
        }
    }

    @Test
    fun `잘못된 패스워드 형식이 예외 처리를 테스트 합니다`() {
        Assert.assertThrows(
            SignupEntity.PASSWORD_INVALID_MESSAGE,
            IllegalArgumentException::class.java
        ) {
            signupEntity.checkPasswordFormat()
        }
    }
}