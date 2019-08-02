package dev.sudnik.template.domain.repository

import dev.sudnik.basecleanandroid.domain.OnCallback
import dev.sudnik.template.domain.entity.ExpEntity

interface ExpRepository {
    fun getExp(callback: OnCallback<ExpEntity>)
}
