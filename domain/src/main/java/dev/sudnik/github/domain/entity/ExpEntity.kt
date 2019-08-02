package dev.sudnik.github.domain.entity

import dev.sudnik.github.domain.model.Exp

class ExpEntity(val exp: Exp) {
    fun getExpValue() = exp.i.toString()
}