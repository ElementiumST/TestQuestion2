package io.stark.testquestion.data.wrappers

import io.stark.testquestion.data.models.PersonBody
import io.stark.testquestion.domain.models.PersonDomainBean

interface PersonWrapper {
    fun personBodyToPersonDomainBeanList(personBody: PersonBody): List<PersonDomainBean>
}