package dev.sudnik.github.data.exp

import dev.sudnik.github.domain.model.Exp

class ExpMapper {
    fun transformToExp(dto: ExpDTO) = Exp(dto.i)
}