package io.stark.testquestion.domain.models

data class PersonDomainBean(
    var image: String,
    var title: String,
    var rating: Int = 0,
)