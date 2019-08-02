package dev.sudnik.github.domain.repository

import dev.sudnik.basecleanandroid.domain.OnCallback
import dev.sudnik.github.domain.entity.ExpEntity

interface ExpRepository {
    fun getExp(callback: OnCallback<ExpEntity>)
}
