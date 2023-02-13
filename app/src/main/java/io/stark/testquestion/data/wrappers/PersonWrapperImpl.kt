package io.stark.testquestion.data.wrappers

import io.stark.testquestion.data.models.PersonBody
import io.stark.testquestion.domain.models.PersonDomainBean
import org.json.JSONObject

class PersonWrapperImpl: PersonWrapper{
    override fun personBodyToPersonDomainBeanList(personBody: PersonBody): List<PersonDomainBean> {
        val personDomainBeanList = mutableListOf<PersonDomainBean>()
        var i = 0
        val jsonObject = JSONObject(personBody.raitings.toString())
        while (jsonObject.has("$i")) {
            val itemObjects = jsonObject.getJSONObject("$i")
            personDomainBeanList.add(
                PersonDomainBean(
                    itemObjects.getString("image"),
                    itemObjects.getString("title")
                )
            )
            i++
        }
        return personDomainBeanList
    }
}