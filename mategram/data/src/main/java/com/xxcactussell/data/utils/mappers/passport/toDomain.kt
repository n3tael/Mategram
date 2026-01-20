package com.xxcactussell.data.utils.mappers.passport

import com.xxcactussell.data.utils.mappers.address.toDomain
import com.xxcactussell.data.utils.mappers.identity.toDomain
import com.xxcactussell.data.utils.mappers.input.toDomain
import com.xxcactussell.data.utils.mappers.personal.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.DeletePassportElement.toDomain(): DeletePassportElement = DeletePassportElement(
    type = this.type.toDomain()
)

fun TdApi.GetPassportAuthorizationForm.toDomain(): GetPassportAuthorizationForm = GetPassportAuthorizationForm(
    botUserId = this.botUserId,
    scope = this.scope,
    publicKey = this.publicKey,
    nonce = this.nonce
)

fun TdApi.GetPassportAuthorizationFormAvailableElements.toDomain(): GetPassportAuthorizationFormAvailableElements = GetPassportAuthorizationFormAvailableElements(
    authorizationFormId = this.authorizationFormId,
    password = this.password
)

fun TdApi.GetPassportElement.toDomain(): GetPassportElement = GetPassportElement(
    type = this.type.toDomain(),
    password = this.password
)

fun TdApi.PassportAuthorizationForm.toDomain(): PassportAuthorizationForm = PassportAuthorizationForm(
    id = this.id,
    requiredElements = this.requiredElements.map { it.toDomain() },
    privacyPolicyUrl = this.privacyPolicyUrl
)

fun TdApi.PassportElement.toDomain(): PassportElement = when(this) {
    is TdApi.PassportElementPersonalDetails -> this.toDomain()
    is TdApi.PassportElementPassport -> this.toDomain()
    is TdApi.PassportElementDriverLicense -> this.toDomain()
    is TdApi.PassportElementIdentityCard -> this.toDomain()
    is TdApi.PassportElementInternalPassport -> this.toDomain()
    is TdApi.PassportElementAddress -> this.toDomain()
    is TdApi.PassportElementUtilityBill -> this.toDomain()
    is TdApi.PassportElementBankStatement -> this.toDomain()
    is TdApi.PassportElementRentalAgreement -> this.toDomain()
    is TdApi.PassportElementPassportRegistration -> this.toDomain()
    is TdApi.PassportElementTemporaryRegistration -> this.toDomain()
    is TdApi.PassportElementPhoneNumber -> this.toDomain()
    is TdApi.PassportElementEmailAddress -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PassportElementAddress.toDomain(): PassportElementAddress = PassportElementAddress(
    address = this.address.toDomain()
)

fun TdApi.PassportElementBankStatement.toDomain(): PassportElementBankStatement = PassportElementBankStatement(
    bankStatement = this.bankStatement.toDomain()
)

fun TdApi.PassportElementDriverLicense.toDomain(): PassportElementDriverLicense = PassportElementDriverLicense(
    driverLicense = this.driverLicense.toDomain()
)

fun TdApi.PassportElementEmailAddress.toDomain(): PassportElementEmailAddress = PassportElementEmailAddress(
    emailAddress = this.emailAddress
)

fun TdApi.PassportElementError.toDomain(): PassportElementError = PassportElementError(
    type = this.type.toDomain(),
    message = this.message,
    source = this.source.toDomain()
)

fun TdApi.PassportElementErrorSource.toDomain(): PassportElementErrorSource = when(this) {
    is TdApi.PassportElementErrorSourceUnspecified -> this.toDomain()
    is TdApi.PassportElementErrorSourceDataField -> this.toDomain()
    is TdApi.PassportElementErrorSourceFrontSide -> this.toDomain()
    is TdApi.PassportElementErrorSourceReverseSide -> this.toDomain()
    is TdApi.PassportElementErrorSourceSelfie -> this.toDomain()
    is TdApi.PassportElementErrorSourceTranslationFile -> this.toDomain()
    is TdApi.PassportElementErrorSourceTranslationFiles -> this.toDomain()
    is TdApi.PassportElementErrorSourceFile -> this.toDomain()
    is TdApi.PassportElementErrorSourceFiles -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PassportElementErrorSourceDataField.toDomain(): PassportElementErrorSourceDataField = PassportElementErrorSourceDataField(
    fieldName = this.fieldName
)

fun TdApi.PassportElementErrorSourceFile.toDomain(): PassportElementErrorSourceFile = PassportElementErrorSourceFile(
    fileIndex = this.fileIndex
)

fun TdApi.PassportElementErrorSourceFiles.toDomain(): PassportElementErrorSourceFiles = PassportElementErrorSourceFiles

fun TdApi.PassportElementErrorSourceFrontSide.toDomain(): PassportElementErrorSourceFrontSide = PassportElementErrorSourceFrontSide

fun TdApi.PassportElementErrorSourceReverseSide.toDomain(): PassportElementErrorSourceReverseSide = PassportElementErrorSourceReverseSide

fun TdApi.PassportElementErrorSourceSelfie.toDomain(): PassportElementErrorSourceSelfie = PassportElementErrorSourceSelfie

fun TdApi.PassportElementErrorSourceTranslationFile.toDomain(): PassportElementErrorSourceTranslationFile = PassportElementErrorSourceTranslationFile(
    fileIndex = this.fileIndex
)

fun TdApi.PassportElementErrorSourceTranslationFiles.toDomain(): PassportElementErrorSourceTranslationFiles = PassportElementErrorSourceTranslationFiles

fun TdApi.PassportElementErrorSourceUnspecified.toDomain(): PassportElementErrorSourceUnspecified = PassportElementErrorSourceUnspecified

fun TdApi.PassportElementIdentityCard.toDomain(): PassportElementIdentityCard = PassportElementIdentityCard(
    identityCard = this.identityCard.toDomain()
)

fun TdApi.PassportElementInternalPassport.toDomain(): PassportElementInternalPassport = PassportElementInternalPassport(
    internalPassport = this.internalPassport.toDomain()
)

fun TdApi.PassportElementPassport.toDomain(): PassportElementPassport = PassportElementPassport(
    passport = this.passport.toDomain()
)

fun TdApi.PassportElementPassportRegistration.toDomain(): PassportElementPassportRegistration = PassportElementPassportRegistration(
    passportRegistration = this.passportRegistration.toDomain()
)

fun TdApi.PassportElementPersonalDetails.toDomain(): PassportElementPersonalDetails = PassportElementPersonalDetails(
    personalDetails = this.personalDetails.toDomain()
)

fun TdApi.PassportElementPhoneNumber.toDomain(): PassportElementPhoneNumber = PassportElementPhoneNumber(
    phoneNumber = this.phoneNumber
)

fun TdApi.PassportElementRentalAgreement.toDomain(): PassportElementRentalAgreement = PassportElementRentalAgreement(
    rentalAgreement = this.rentalAgreement.toDomain()
)

fun TdApi.PassportElementTemporaryRegistration.toDomain(): PassportElementTemporaryRegistration = PassportElementTemporaryRegistration(
    temporaryRegistration = this.temporaryRegistration.toDomain()
)

fun TdApi.PassportElementType.toDomain(): PassportElementType = when(this) {
    is TdApi.PassportElementTypePersonalDetails -> this.toDomain()
    is TdApi.PassportElementTypePassport -> this.toDomain()
    is TdApi.PassportElementTypeDriverLicense -> this.toDomain()
    is TdApi.PassportElementTypeIdentityCard -> this.toDomain()
    is TdApi.PassportElementTypeInternalPassport -> this.toDomain()
    is TdApi.PassportElementTypeAddress -> this.toDomain()
    is TdApi.PassportElementTypeUtilityBill -> this.toDomain()
    is TdApi.PassportElementTypeBankStatement -> this.toDomain()
    is TdApi.PassportElementTypeRentalAgreement -> this.toDomain()
    is TdApi.PassportElementTypePassportRegistration -> this.toDomain()
    is TdApi.PassportElementTypeTemporaryRegistration -> this.toDomain()
    is TdApi.PassportElementTypePhoneNumber -> this.toDomain()
    is TdApi.PassportElementTypeEmailAddress -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PassportElementTypeAddress.toDomain(): PassportElementTypeAddress = PassportElementTypeAddress

fun TdApi.PassportElementTypeBankStatement.toDomain(): PassportElementTypeBankStatement = PassportElementTypeBankStatement

fun TdApi.PassportElementTypeDriverLicense.toDomain(): PassportElementTypeDriverLicense = PassportElementTypeDriverLicense

fun TdApi.PassportElementTypeEmailAddress.toDomain(): PassportElementTypeEmailAddress = PassportElementTypeEmailAddress

fun TdApi.PassportElementTypeIdentityCard.toDomain(): PassportElementTypeIdentityCard = PassportElementTypeIdentityCard

fun TdApi.PassportElementTypeInternalPassport.toDomain(): PassportElementTypeInternalPassport = PassportElementTypeInternalPassport

fun TdApi.PassportElementTypePassport.toDomain(): PassportElementTypePassport = PassportElementTypePassport

fun TdApi.PassportElementTypePassportRegistration.toDomain(): PassportElementTypePassportRegistration = PassportElementTypePassportRegistration

fun TdApi.PassportElementTypePersonalDetails.toDomain(): PassportElementTypePersonalDetails = PassportElementTypePersonalDetails

fun TdApi.PassportElementTypePhoneNumber.toDomain(): PassportElementTypePhoneNumber = PassportElementTypePhoneNumber

fun TdApi.PassportElementTypeRentalAgreement.toDomain(): PassportElementTypeRentalAgreement = PassportElementTypeRentalAgreement

fun TdApi.PassportElementTypeTemporaryRegistration.toDomain(): PassportElementTypeTemporaryRegistration = PassportElementTypeTemporaryRegistration

fun TdApi.PassportElementTypeUtilityBill.toDomain(): PassportElementTypeUtilityBill = PassportElementTypeUtilityBill

fun TdApi.PassportElementUtilityBill.toDomain(): PassportElementUtilityBill = PassportElementUtilityBill(
    utilityBill = this.utilityBill.toDomain()
)

fun TdApi.PassportElements.toDomain(): PassportElements = PassportElements(
    elements = this.elements.map { it.toDomain() }
)

fun TdApi.PassportElementsWithErrors.toDomain(): PassportElementsWithErrors = PassportElementsWithErrors(
    elements = this.elements.map { it.toDomain() },
    errors = this.errors.map { it.toDomain() }
)

fun TdApi.PassportRequiredElement.toDomain(): PassportRequiredElement = PassportRequiredElement(
    suitableElements = this.suitableElements.map { it.toDomain() }
)

fun TdApi.PassportSuitableElement.toDomain(): PassportSuitableElement = PassportSuitableElement(
    type = this.type.toDomain(),
    isSelfieRequired = this.isSelfieRequired,
    isTranslationRequired = this.isTranslationRequired,
    isNativeNameRequired = this.isNativeNameRequired
)

fun TdApi.SendPassportAuthorizationForm.toDomain(): SendPassportAuthorizationForm = SendPassportAuthorizationForm(
    authorizationFormId = this.authorizationFormId,
    types = this.types.map { it.toDomain() }
)

fun TdApi.SetPassportElement.toDomain(): SetPassportElement = SetPassportElement(
    element = this.element.toDomain(),
    password = this.password
)

fun TdApi.SetPassportElementErrors.toDomain(): SetPassportElementErrors = SetPassportElementErrors(
    userId = this.userId,
    errors = this.errors.map { it.toDomain() }
)

