package io.stark.testquestion.domain.repos

import io.stark.testquestion.domain.models.PersonDomainBean

interface PersonRepository {
    suspend fun getPersonList(): List<PersonDomainBean>
}