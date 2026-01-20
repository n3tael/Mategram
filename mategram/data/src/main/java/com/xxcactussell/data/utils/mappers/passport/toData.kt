package com.xxcactussell.data.utils.mappers.passport

import com.xxcactussell.data.utils.mappers.address.toData
import com.xxcactussell.data.utils.mappers.identity.toData
import com.xxcactussell.data.utils.mappers.input.toData
import com.xxcactussell.data.utils.mappers.personal.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun DeletePassportElement.toData(): TdApi.DeletePassportElement = TdApi.DeletePassportElement(
    this.type.toData()
)

fun GetPassportAuthorizationForm.toData(): TdApi.GetPassportAuthorizationForm = TdApi.GetPassportAuthorizationForm(
    this.botUserId,
    this.scope,
    this.publicKey,
    this.nonce
)

fun GetPassportAuthorizationFormAvailableElements.toData(): TdApi.GetPassportAuthorizationFormAvailableElements = TdApi.GetPassportAuthorizationFormAvailableElements(
    this.authorizationFormId,
    this.password
)

fun GetPassportElement.toData(): TdApi.GetPassportElement = TdApi.GetPassportElement(
    this.type.toData(),
    this.password
)

fun PassportAuthorizationForm.toData(): TdApi.PassportAuthorizationForm = TdApi.PassportAuthorizationForm(
    this.id,
    this.requiredElements.map { it.toData() }.toTypedArray(),
    this.privacyPolicyUrl
)

fun PassportElement.toData(): TdApi.PassportElement = when(this) {
    is PassportElementPersonalDetails -> this.toData()
    is PassportElementPassport -> this.toData()
    is PassportElementDriverLicense -> this.toData()
    is PassportElementIdentityCard -> this.toData()
    is PassportElementInternalPassport -> this.toData()
    is PassportElementAddress -> this.toData()
    is PassportElementUtilityBill -> this.toData()
    is PassportElementBankStatement -> this.toData()
    is PassportElementRentalAgreement -> this.toData()
    is PassportElementPassportRegistration -> this.toData()
    is PassportElementTemporaryRegistration -> this.toData()
    is PassportElementPhoneNumber -> this.toData()
    is PassportElementEmailAddress -> this.toData()
}

fun PassportElementAddress.toData(): TdApi.PassportElementAddress = TdApi.PassportElementAddress(
    this.address.toData()
)

fun PassportElementBankStatement.toData(): TdApi.PassportElementBankStatement = TdApi.PassportElementBankStatement(
    this.bankStatement.toData()
)

fun PassportElementDriverLicense.toData(): TdApi.PassportElementDriverLicense = TdApi.PassportElementDriverLicense(
    this.driverLicense.toData()
)

fun PassportElementEmailAddress.toData(): TdApi.PassportElementEmailAddress = TdApi.PassportElementEmailAddress(
    this.emailAddress
)

fun PassportElementError.toData(): TdApi.PassportElementError = TdApi.PassportElementError(
    this.type.toData(),
    this.message,
    this.source.toData()
)

fun PassportElementErrorSource.toData(): TdApi.PassportElementErrorSource = when(this) {
    is PassportElementErrorSourceUnspecified -> this.toData()
    is PassportElementErrorSourceDataField -> this.toData()
    is PassportElementErrorSourceFrontSide -> this.toData()
    is PassportElementErrorSourceReverseSide -> this.toData()
    is PassportElementErrorSourceSelfie -> this.toData()
    is PassportElementErrorSourceTranslationFile -> this.toData()
    is PassportElementErrorSourceTranslationFiles -> this.toData()
    is PassportElementErrorSourceFile -> this.toData()
    is PassportElementErrorSourceFiles -> this.toData()
}

fun PassportElementErrorSourceDataField.toData(): TdApi.PassportElementErrorSourceDataField = TdApi.PassportElementErrorSourceDataField(
    this.fieldName
)

fun PassportElementErrorSourceFile.toData(): TdApi.PassportElementErrorSourceFile = TdApi.PassportElementErrorSourceFile(
    this.fileIndex
)

fun PassportElementErrorSourceFiles.toData(): TdApi.PassportElementErrorSourceFiles = TdApi.PassportElementErrorSourceFiles(
)

fun PassportElementErrorSourceFrontSide.toData(): TdApi.PassportElementErrorSourceFrontSide = TdApi.PassportElementErrorSourceFrontSide(
)

fun PassportElementErrorSourceReverseSide.toData(): TdApi.PassportElementErrorSourceReverseSide = TdApi.PassportElementErrorSourceReverseSide(
)

fun PassportElementErrorSourceSelfie.toData(): TdApi.PassportElementErrorSourceSelfie = TdApi.PassportElementErrorSourceSelfie(
)

fun PassportElementErrorSourceTranslationFile.toData(): TdApi.PassportElementErrorSourceTranslationFile = TdApi.PassportElementErrorSourceTranslationFile(
    this.fileIndex
)

fun PassportElementErrorSourceTranslationFiles.toData(): TdApi.PassportElementErrorSourceTranslationFiles = TdApi.PassportElementErrorSourceTranslationFiles(
)

fun PassportElementErrorSourceUnspecified.toData(): TdApi.PassportElementErrorSourceUnspecified = TdApi.PassportElementErrorSourceUnspecified(
)

fun PassportElementIdentityCard.toData(): TdApi.PassportElementIdentityCard = TdApi.PassportElementIdentityCard(
    this.identityCard.toData()
)

fun PassportElementInternalPassport.toData(): TdApi.PassportElementInternalPassport = TdApi.PassportElementInternalPassport(
    this.internalPassport.toData()
)

fun PassportElementPassport.toData(): TdApi.PassportElementPassport = TdApi.PassportElementPassport(
    this.passport.toData()
)

fun PassportElementPassportRegistration.toData(): TdApi.PassportElementPassportRegistration = TdApi.PassportElementPassportRegistration(
    this.passportRegistration.toData()
)

fun PassportElementPersonalDetails.toData(): TdApi.PassportElementPersonalDetails = TdApi.PassportElementPersonalDetails(
    this.personalDetails.toData()
)

fun PassportElementPhoneNumber.toData(): TdApi.PassportElementPhoneNumber = TdApi.PassportElementPhoneNumber(
    this.phoneNumber
)

fun PassportElementRentalAgreement.toData(): TdApi.PassportElementRentalAgreement = TdApi.PassportElementRentalAgreement(
    this.rentalAgreement.toData()
)

fun PassportElementTemporaryRegistration.toData(): TdApi.PassportElementTemporaryRegistration = TdApi.PassportElementTemporaryRegistration(
    this.temporaryRegistration.toData()
)

fun PassportElementType.toData(): TdApi.PassportElementType = when(this) {
    is PassportElementTypePersonalDetails -> this.toData()
    is PassportElementTypePassport -> this.toData()
    is PassportElementTypeDriverLicense -> this.toData()
    is PassportElementTypeIdentityCard -> this.toData()
    is PassportElementTypeInternalPassport -> this.toData()
    is PassportElementTypeAddress -> this.toData()
    is PassportElementTypeUtilityBill -> this.toData()
    is PassportElementTypeBankStatement -> this.toData()
    is PassportElementTypeRentalAgreement -> this.toData()
    is PassportElementTypePassportRegistration -> this.toData()
    is PassportElementTypeTemporaryRegistration -> this.toData()
    is PassportElementTypePhoneNumber -> this.toData()
    is PassportElementTypeEmailAddress -> this.toData()
}

fun PassportElementTypeAddress.toData(): TdApi.PassportElementTypeAddress = TdApi.PassportElementTypeAddress(
)

fun PassportElementTypeBankStatement.toData(): TdApi.PassportElementTypeBankStatement = TdApi.PassportElementTypeBankStatement(
)

fun PassportElementTypeDriverLicense.toData(): TdApi.PassportElementTypeDriverLicense = TdApi.PassportElementTypeDriverLicense(
)

fun PassportElementTypeEmailAddress.toData(): TdApi.PassportElementTypeEmailAddress = TdApi.PassportElementTypeEmailAddress(
)

fun PassportElementTypeIdentityCard.toData(): TdApi.PassportElementTypeIdentityCard = TdApi.PassportElementTypeIdentityCard(
)

fun PassportElementTypeInternalPassport.toData(): TdApi.PassportElementTypeInternalPassport = TdApi.PassportElementTypeInternalPassport(
)

fun PassportElementTypePassport.toData(): TdApi.PassportElementTypePassport = TdApi.PassportElementTypePassport(
)

fun PassportElementTypePassportRegistration.toData(): TdApi.PassportElementTypePassportRegistration = TdApi.PassportElementTypePassportRegistration(
)

fun PassportElementTypePersonalDetails.toData(): TdApi.PassportElementTypePersonalDetails = TdApi.PassportElementTypePersonalDetails(
)

fun PassportElementTypePhoneNumber.toData(): TdApi.PassportElementTypePhoneNumber = TdApi.PassportElementTypePhoneNumber(
)

fun PassportElementTypeRentalAgreement.toData(): TdApi.PassportElementTypeRentalAgreement = TdApi.PassportElementTypeRentalAgreement(
)

fun PassportElementTypeTemporaryRegistration.toData(): TdApi.PassportElementTypeTemporaryRegistration = TdApi.PassportElementTypeTemporaryRegistration(
)

fun PassportElementTypeUtilityBill.toData(): TdApi.PassportElementTypeUtilityBill = TdApi.PassportElementTypeUtilityBill(
)

fun PassportElementUtilityBill.toData(): TdApi.PassportElementUtilityBill = TdApi.PassportElementUtilityBill(
    this.utilityBill.toData()
)

fun PassportElements.toData(): TdApi.PassportElements = TdApi.PassportElements(
    this.elements.map { it.toData() }.toTypedArray()
)

fun PassportElementsWithErrors.toData(): TdApi.PassportElementsWithErrors = TdApi.PassportElementsWithErrors(
    this.elements.map { it.toData() }.toTypedArray(),
    this.errors.map { it.toData() }.toTypedArray()
)

fun PassportRequiredElement.toData(): TdApi.PassportRequiredElement = TdApi.PassportRequiredElement(
    this.suitableElements.map { it.toData() }.toTypedArray()
)

fun PassportSuitableElement.toData(): TdApi.PassportSuitableElement = TdApi.PassportSuitableElement(
    this.type.toData(),
    this.isSelfieRequired,
    this.isTranslationRequired,
    this.isNativeNameRequired
)

fun SendPassportAuthorizationForm.toData(): TdApi.SendPassportAuthorizationForm = TdApi.SendPassportAuthorizationForm(
    this.authorizationFormId,
    this.types.map { it.toData() }.toTypedArray()
)

fun SetPassportElement.toData(): TdApi.SetPassportElement = TdApi.SetPassportElement(
    this.element.toData(),
    this.password
)

fun SetPassportElementErrors.toData(): TdApi.SetPassportElementErrors = TdApi.SetPassportElementErrors(
    this.userId,
    this.errors.map { it.toData() }.toTypedArray()
)

