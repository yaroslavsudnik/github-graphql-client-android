package dev.sudnik.template.domain.entity

import dev.sudnik.template.domain.model.Exp

class ExpEntity(val exp: Exp) {
    fun getExpValue() = exp.i.toString()
}