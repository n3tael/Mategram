package com.xxcactussell.data.utils.mappers.rich

import com.xxcactussell.data.utils.mappers.file.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.RichText.toDomain(): RichText = when(this) {
    is TdApi.RichTextPlain -> this.toDomain()
    is TdApi.RichTextBold -> this.toDomain()
    is TdApi.RichTextItalic -> this.toDomain()
    is TdApi.RichTextUnderline -> this.toDomain()
    is TdApi.RichTextStrikethrough -> this.toDomain()
    is TdApi.RichTextFixed -> this.toDomain()
    is TdApi.RichTextUrl -> this.toDomain()
    is TdApi.RichTextEmailAddress -> this.toDomain()
    is TdApi.RichTextSubscript -> this.toDomain()
    is TdApi.RichTextSuperscript -> this.toDomain()
    is TdApi.RichTextMarked -> this.toDomain()
    is TdApi.RichTextPhoneNumber -> this.toDomain()
    is TdApi.RichTextIcon -> this.toDomain()
    is TdApi.RichTextReference -> this.toDomain()
    is TdApi.RichTextAnchor -> this.toDomain()
    is TdApi.RichTextAnchorLink -> this.toDomain()
    is TdApi.RichTexts -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.RichTextAnchor.toDomain(): RichTextAnchor = RichTextAnchor(
    name = this.name
)

fun TdApi.RichTextAnchorLink.toDomain(): RichTextAnchorLink = RichTextAnchorLink(
    text = this.text.toDomain(),
    anchorName = this.anchorName,
    url = this.url
)

fun TdApi.RichTextBold.toDomain(): RichTextBold = RichTextBold(
    text = this.text.toDomain()
)

fun TdApi.RichTextEmailAddress.toDomain(): RichTextEmailAddress = RichTextEmailAddress(
    text = this.text.toDomain(),
    emailAddress = this.emailAddress
)

fun TdApi.RichTextFixed.toDomain(): RichTextFixed = RichTextFixed(
    text = this.text.toDomain()
)

fun TdApi.RichTextIcon.toDomain(): RichTextIcon = RichTextIcon(
    document = this.document.toDomain(),
    width = this.width,
    height = this.height
)

fun TdApi.RichTextItalic.toDomain(): RichTextItalic = RichTextItalic(
    text = this.text.toDomain()
)

fun TdApi.RichTextMarked.toDomain(): RichTextMarked = RichTextMarked(
    text = this.text.toDomain()
)

fun TdApi.RichTextPhoneNumber.toDomain(): RichTextPhoneNumber = RichTextPhoneNumber(
    text = this.text.toDomain(),
    phoneNumber = this.phoneNumber
)

fun TdApi.RichTextPlain.toDomain(): RichTextPlain = RichTextPlain(
    text = this.text
)

fun TdApi.RichTextReference.toDomain(): RichTextReference = RichTextReference(
    text = this.text.toDomain(),
    anchorName = this.anchorName,
    url = this.url
)

fun TdApi.RichTextStrikethrough.toDomain(): RichTextStrikethrough = RichTextStrikethrough(
    text = this.text.toDomain()
)

fun TdApi.RichTextSubscript.toDomain(): RichTextSubscript = RichTextSubscript(
    text = this.text.toDomain()
)

fun TdApi.RichTextSuperscript.toDomain(): RichTextSuperscript = RichTextSuperscript(
    text = this.text.toDomain()
)

fun TdApi.RichTextUnderline.toDomain(): RichTextUnderline = RichTextUnderline(
    text = this.text.toDomain()
)

fun TdApi.RichTextUrl.toDomain(): RichTextUrl = RichTextUrl(
    text = this.text.toDomain(),
    url = this.url,
    isCached = this.isCached
)

fun TdApi.RichTexts.toDomain(): RichTexts = RichTexts(
    texts = this.texts.map { it.toDomain() }
)

