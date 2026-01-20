package com.xxcactussell.data.utils.mappers.personal

import com.xxcactussell.data.utils.mappers.date.toDomain
import com.xxcactussell.data.utils.mappers.dated.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.PersonalDetails.toDomain(): PersonalDetails = PersonalDetails(
    firstName = this.firstName,
    middleName = this.middleName,
    lastName = this.lastName,
    nativeFirstName = this.nativeFirstName,
    nativeMiddleName = this.nativeMiddleName,
    nativeLastName = this.nativeLastName,
    birthdate = this.birthdate.toDomain(),
    gender = this.gender,
    countryCode = this.countryCode,
    residenceCountryCode = this.residenceCountryCode
)

fun TdApi.PersonalDocument.toDomain(): PersonalDocument = PersonalDocument(
    files = this.files.map { it.toDomain() },
    translation = this.translation.map { it.toDomain() }
)

