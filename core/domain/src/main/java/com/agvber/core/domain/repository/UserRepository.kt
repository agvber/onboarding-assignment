package com.agvber.core.domain.repository

interface UserRepository {

    /**
     *  유저 정보를 저장하는 함수
     *
     *  @param email 이메일
     *  @param name 사용자 이름
     *  @param password 사용자 비밀번호
     *
     */

    suspend fun storeUserInformation(
        email: String,
        name: String,
        password: String
    )
}