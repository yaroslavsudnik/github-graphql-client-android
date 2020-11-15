package dev.sudnik.github.data.exp

class ExpEntity(private val exp: ExpDTO) {
    fun getExpValue() = exp.i.toString()
}