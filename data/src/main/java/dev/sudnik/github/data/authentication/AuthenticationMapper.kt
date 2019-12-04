package dev.sudnik.github.data.authentication

import dev.sudnik.github.domain.model.Token

class AuthenticationMapper {
    fun transformToToken(dto: AuthenticationDTO) = Token(dto.accessToken, dto.tokenType)
}
