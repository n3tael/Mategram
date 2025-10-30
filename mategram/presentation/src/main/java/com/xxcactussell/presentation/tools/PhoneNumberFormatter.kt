package com.xxcactussell.presentation.tools

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber

object PhoneNumberFormatter {
    private val phoneUtil = PhoneNumberUtil.getInstance()

    fun format(phoneNumberWithPlus: String?): String? {
        phoneNumberWithPlus?.length?.let {
            if (it <= 1) {
                return phoneNumberWithPlus
            }
        }
        return try {
            val numberProto: Phonenumber.PhoneNumber = phoneUtil.parse(phoneNumberWithPlus, null)
            phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
        } catch (_: NumberParseException) {
            phoneNumberWithPlus
        }
    }

    fun isValid(phoneNumberWithPlus: String): Boolean {
        if (phoneNumberWithPlus.length <= 1) {
            return false
        }
        return try {
            val numberProto = phoneUtil.parse(phoneNumberWithPlus, null)
            phoneUtil.isValidNumber(numberProto)
        } catch (_: NumberParseException) {
            false
        }
    }
}