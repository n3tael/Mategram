package com.xxcactussell.data.utils.mappers.personal

import com.xxcactussell.data.utils.mappers.date.toData
import com.xxcactussell.data.utils.mappers.dated.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun PersonalDetails.toData(): TdApi.PersonalDetails = TdApi.PersonalDetails(
    this.firstName,
    this.middleName,
    this.lastName,
    this.nativeFirstName,
    this.nativeMiddleName,
    this.nativeLastName,
    this.birthdate.toData(),
    this.gender,
    this.countryCode,
    this.residenceCountryCode
)

fun PersonalDocument.toData(): TdApi.PersonalDocument = TdApi.PersonalDocument(
    this.files.map { it.toData() }.toTypedArray(),
    this.translation.map { it.toData() }.toTypedArray()
)

