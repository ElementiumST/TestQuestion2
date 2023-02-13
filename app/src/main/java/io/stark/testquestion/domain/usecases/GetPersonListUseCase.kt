package io.stark.testquestion.domain.usecases

import io.stark.testquestion.domain.models.PersonDomainBean

interface GetPersonListUseCase {
    suspend fun execute(): List<PersonDomainBean>
}