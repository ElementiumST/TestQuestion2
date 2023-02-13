package io.stark.testquestion.data.reposimpl

import io.stark.testquestion.data.datasource.NetworkApi
import io.stark.testquestion.data.models.PersonBody
import io.stark.testquestion.data.wrappers.PersonWrapper
import io.stark.testquestion.domain.models.PersonDomainBean
import io.stark.testquestion.domain.repos.PersonRepository
import org.json.JSONObject
import java.util.EmptyStackException

class PersonRepositoryImpl(
    private val networkApi: NetworkApi,
    private val personWrapper: PersonWrapper
) : PersonRepository {
    override suspend fun getPersonList(): List<PersonDomainBean> {
        val response = networkApi.getTestAndroidData().execute()
        val personBody = response.body() ?: throw IllegalArgumentException("response body is empty")
        return personWrapper.personBodyToPersonDomainBeanList(personBody)
    }

}