package dev.sudnik.template.data.exp

import dev.sudnik.template.domain.model.Exp

class ExpMapper {
    fun transformToExp(dto: ExpDTO) = Exp(dto.i)
}