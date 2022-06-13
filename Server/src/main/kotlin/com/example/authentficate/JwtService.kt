package com.example.authentficate

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm

class JwtService {
    private val issuer = "Server"
    private val jwtSecret = System.getenv("JWT_SECRET")
    private val algorithm = Algorithm.HMAC256(jwtSecret)

    val varifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()
}