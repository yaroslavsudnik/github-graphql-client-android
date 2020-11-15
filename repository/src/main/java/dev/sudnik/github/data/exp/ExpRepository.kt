package dev.sudnik.github.data.exp

import dev.sudnik.basecleanandroid.domain.RepositoryResponse

interface ExpRepository {
    fun getExp(response: RepositoryResponse<ExpEntity>)
}
