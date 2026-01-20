package com.xxcactussell.data.utils.mappers.countries

import com.xxcactussell.data.utils.mappers.country.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.Countries.toDomain(): Countries = Countries(
    countries = this.countries.map { it.toDomain() }
)

