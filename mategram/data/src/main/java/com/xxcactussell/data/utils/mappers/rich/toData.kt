package com.xxcactussell.data.utils.mappers.rich

import com.xxcactussell.data.utils.mappers.file.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun RichText.toData(): TdApi.RichText = when(this) {
    is RichTextPlain -> this.toData()
    is RichTextBold -> this.toData()
    is RichTextItalic -> this.toData()
    is RichTextUnderline -> this.toData()
    is RichTextStrikethrough -> this.toData()
    is RichTextFixed -> this.toData()
    is RichTextUrl -> this.toData()
    is RichTextEmailAddress -> this.toData()
    is RichTextSubscript -> this.toData()
    is RichTextSuperscript -> this.toData()
    is RichTextMarked -> this.toData()
    is RichTextPhoneNumber -> this.toData()
    is RichTextIcon -> this.toData()
    is RichTextReference -> this.toData()
    is RichTextAnchor -> this.toData()
    is RichTextAnchorLink -> this.toData()
    is RichTexts -> this.toData()
}

fun RichTextAnchor.toData(): TdApi.RichTextAnchor = TdApi.RichTextAnchor(
    this.name
)

fun RichTextAnchorLink.toData(): TdApi.RichTextAnchorLink = TdApi.RichTextAnchorLink(
    this.text.toData(),
    this.anchorName,
    this.url
)

fun RichTextBold.toData(): TdApi.RichTextBold = TdApi.RichTextBold(
    this.text.toData()
)

fun RichTextEmailAddress.toData(): TdApi.RichTextEmailAddress = TdApi.RichTextEmailAddress(
    this.text.toData(),
    this.emailAddress
)

fun RichTextFixed.toData(): TdApi.RichTextFixed = TdApi.RichTextFixed(
    this.text.toData()
)

fun RichTextIcon.toData(): TdApi.RichTextIcon = TdApi.RichTextIcon(
    this.document.toData(),
    this.width,
    this.height
)

fun RichTextItalic.toData(): TdApi.RichTextItalic = TdApi.RichTextItalic(
    this.text.toData()
)

fun RichTextMarked.toData(): TdApi.RichTextMarked = TdApi.RichTextMarked(
    this.text.toData()
)

fun RichTextPhoneNumber.toData(): TdApi.RichTextPhoneNumber = TdApi.RichTextPhoneNumber(
    this.text.toData(),
    this.phoneNumber
)

fun RichTextPlain.toData(): TdApi.RichTextPlain = TdApi.RichTextPlain(
    this.text
)

fun RichTextReference.toData(): TdApi.RichTextReference = TdApi.RichTextReference(
    this.text.toData(),
    this.anchorName,
    this.url
)

fun RichTextStrikethrough.toData(): TdApi.RichTextStrikethrough = TdApi.RichTextStrikethrough(
    this.text.toData()
)

fun RichTextSubscript.toData(): TdApi.RichTextSubscript = TdApi.RichTextSubscript(
    this.text.toData()
)

fun RichTextSuperscript.toData(): TdApi.RichTextSuperscript = TdApi.RichTextSuperscript(
    this.text.toData()
)

fun RichTextUnderline.toData(): TdApi.RichTextUnderline = TdApi.RichTextUnderline(
    this.text.toData()
)

fun RichTextUrl.toData(): TdApi.RichTextUrl = TdApi.RichTextUrl(
    this.text.toData(),
    this.url,
    this.isCached
)

fun RichTexts.toData(): TdApi.RichTexts = TdApi.RichTexts(
    this.texts.map { it.toData() }.toTypedArray()
)

