package dev.sudnik.github.data.authentication

import dev.sudnik.basecleanandroid.data.BaseDataSource
import dev.sudnik.basecleanandroid.data.RetrofitHelper
import dev.sudnik.basecleanandroid.domain.RepositoryResponse
import dev.sudnik.github.data.IApi
import dev.sudnik.github.data.PropertyUtils
import dev.sudnik.github.domain.entity.AuthenticationEntity

class AuthenticationDataSource(var response: RepositoryResponse<AuthenticationEntity>) :
    BaseDataSource<AuthenticationEntity, AuthenticationDTO, IApi>(
        response,
        RetrofitHelper(GITHUB_BASE_URI).retrofit
    ) {

    suspend fun getToken(code: String) = loadData {
        api.accessToken(PropertyUtils().getClientId(), PropertyUtils().getClientSecret(), code)
    }

    override val successDataResponse: (AuthenticationDTO) -> Unit = {
        println("token ${AuthenticationMapper().transformToToken(it)}")
    }

    public override fun successDomainResponse(dto: AuthenticationDTO) =
        AuthenticationEntity(dto.tokenType)

    override val apiClazz: Class<IApi> = IApi::class.java

    companion object {
        private const val GITHUB_BASE_URI = "https://github.com"
    }
}
