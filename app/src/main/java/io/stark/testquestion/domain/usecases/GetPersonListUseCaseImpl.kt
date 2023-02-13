package io.stark.testquestion.domain.usecases

import io.stark.testquestion.domain.models.PersonDomainBean
import io.stark.testquestion.domain.repos.PersonRepository

class GetPersonListUseCaseImpl(
    private val personRepository: PersonRepository
): GetPersonListUseCase {
    override suspend fun execute(): List<PersonDomainBean> {
        return personRepository.getPersonList()
            .map { it.apply { rating = 5 } }

    }
}