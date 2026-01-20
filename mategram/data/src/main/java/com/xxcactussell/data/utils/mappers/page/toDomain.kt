package com.xxcactussell.data.utils.mappers.page

import com.xxcactussell.data.utils.mappers.animation.toDomain
import com.xxcactussell.data.utils.mappers.chat.toDomain
import com.xxcactussell.data.utils.mappers.file.toDomain
import com.xxcactussell.data.utils.mappers.location.toDomain
import com.xxcactussell.data.utils.mappers.photo.toDomain
import com.xxcactussell.data.utils.mappers.rich.toDomain
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: TdApi -> Domain

fun TdApi.PageBlock.toDomain(): PageBlock = when(this) {
    is TdApi.PageBlockTitle -> this.toDomain()
    is TdApi.PageBlockSubtitle -> this.toDomain()
    is TdApi.PageBlockAuthorDate -> this.toDomain()
    is TdApi.PageBlockHeader -> this.toDomain()
    is TdApi.PageBlockSubheader -> this.toDomain()
    is TdApi.PageBlockKicker -> this.toDomain()
    is TdApi.PageBlockParagraph -> this.toDomain()
    is TdApi.PageBlockPreformatted -> this.toDomain()
    is TdApi.PageBlockFooter -> this.toDomain()
    is TdApi.PageBlockDivider -> this.toDomain()
    is TdApi.PageBlockAnchor -> this.toDomain()
    is TdApi.PageBlockList -> this.toDomain()
    is TdApi.PageBlockBlockQuote -> this.toDomain()
    is TdApi.PageBlockPullQuote -> this.toDomain()
    is TdApi.PageBlockAnimation -> this.toDomain()
    is TdApi.PageBlockAudio -> this.toDomain()
    is TdApi.PageBlockPhoto -> this.toDomain()
    is TdApi.PageBlockVideo -> this.toDomain()
    is TdApi.PageBlockVoiceNote -> this.toDomain()
    is TdApi.PageBlockCover -> this.toDomain()
    is TdApi.PageBlockEmbedded -> this.toDomain()
    is TdApi.PageBlockEmbeddedPost -> this.toDomain()
    is TdApi.PageBlockCollage -> this.toDomain()
    is TdApi.PageBlockSlideshow -> this.toDomain()
    is TdApi.PageBlockChatLink -> this.toDomain()
    is TdApi.PageBlockTable -> this.toDomain()
    is TdApi.PageBlockDetails -> this.toDomain()
    is TdApi.PageBlockRelatedArticles -> this.toDomain()
    is TdApi.PageBlockMap -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PageBlockAnchor.toDomain(): PageBlockAnchor = PageBlockAnchor(
    name = this.name
)

fun TdApi.PageBlockAnimation.toDomain(): PageBlockAnimation = PageBlockAnimation(
    animation = this.animation?.toDomain(),
    caption = this.caption.toDomain(),
    needAutoplay = this.needAutoplay
)

fun TdApi.PageBlockAudio.toDomain(): PageBlockAudio = PageBlockAudio(
    audio = this.audio?.toDomain(),
    caption = this.caption.toDomain()
)

fun TdApi.PageBlockAuthorDate.toDomain(): PageBlockAuthorDate = PageBlockAuthorDate(
    author = this.author.toDomain(),
    publishDate = this.publishDate
)

fun TdApi.PageBlockBlockQuote.toDomain(): PageBlockBlockQuote = PageBlockBlockQuote(
    text = this.text.toDomain(),
    credit = this.credit.toDomain()
)

fun TdApi.PageBlockCaption.toDomain(): PageBlockCaption = PageBlockCaption(
    text = this.text.toDomain(),
    credit = this.credit.toDomain()
)

fun TdApi.PageBlockChatLink.toDomain(): PageBlockChatLink = PageBlockChatLink(
    title = this.title,
    photo = this.photo?.toDomain(),
    accentColorId = this.accentColorId,
    username = this.username
)

fun TdApi.PageBlockCollage.toDomain(): PageBlockCollage = PageBlockCollage(
    pageBlocks = this.pageBlocks.map { it.toDomain() },
    caption = this.caption.toDomain()
)

fun TdApi.PageBlockCover.toDomain(): PageBlockCover = PageBlockCover(
    cover = this.cover.toDomain()
)

fun TdApi.PageBlockDetails.toDomain(): PageBlockDetails = PageBlockDetails(
    header = this.header.toDomain(),
    pageBlocks = this.pageBlocks.map { it.toDomain() },
    isOpen = this.isOpen
)

fun TdApi.PageBlockDivider.toDomain(): PageBlockDivider = PageBlockDivider

fun TdApi.PageBlockEmbedded.toDomain(): PageBlockEmbedded = PageBlockEmbedded(
    url = this.url,
    html = this.html,
    posterPhoto = this.posterPhoto?.toDomain(),
    width = this.width,
    height = this.height,
    caption = this.caption.toDomain(),
    isFullWidth = this.isFullWidth,
    allowScrolling = this.allowScrolling
)

fun TdApi.PageBlockEmbeddedPost.toDomain(): PageBlockEmbeddedPost = PageBlockEmbeddedPost(
    url = this.url,
    author = this.author,
    authorPhoto = this.authorPhoto?.toDomain(),
    date = this.date,
    pageBlocks = this.pageBlocks.map { it.toDomain() },
    caption = this.caption.toDomain()
)

fun TdApi.PageBlockFooter.toDomain(): PageBlockFooter = PageBlockFooter(
    footer = this.footer.toDomain()
)

fun TdApi.PageBlockHeader.toDomain(): PageBlockHeader = PageBlockHeader(
    header = this.header.toDomain()
)

fun TdApi.PageBlockHorizontalAlignment.toDomain(): PageBlockHorizontalAlignment = when(this) {
    is TdApi.PageBlockHorizontalAlignmentLeft -> this.toDomain()
    is TdApi.PageBlockHorizontalAlignmentCenter -> this.toDomain()
    is TdApi.PageBlockHorizontalAlignmentRight -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PageBlockHorizontalAlignmentCenter.toDomain(): PageBlockHorizontalAlignmentCenter = PageBlockHorizontalAlignmentCenter

fun TdApi.PageBlockHorizontalAlignmentLeft.toDomain(): PageBlockHorizontalAlignmentLeft = PageBlockHorizontalAlignmentLeft

fun TdApi.PageBlockHorizontalAlignmentRight.toDomain(): PageBlockHorizontalAlignmentRight = PageBlockHorizontalAlignmentRight

fun TdApi.PageBlockKicker.toDomain(): PageBlockKicker = PageBlockKicker(
    kicker = this.kicker.toDomain()
)

fun TdApi.PageBlockList.toDomain(): PageBlockList = PageBlockList(
    items = this.items.map { it.toDomain() }
)

fun TdApi.PageBlockListItem.toDomain(): PageBlockListItem = PageBlockListItem(
    label = this.label,
    pageBlocks = this.pageBlocks.map { it.toDomain() }
)

fun TdApi.PageBlockMap.toDomain(): PageBlockMap = PageBlockMap(
    location = this.location.toDomain(),
    zoom = this.zoom,
    width = this.width,
    height = this.height,
    caption = this.caption.toDomain()
)

fun TdApi.PageBlockParagraph.toDomain(): PageBlockParagraph = PageBlockParagraph(
    text = this.text.toDomain()
)

fun TdApi.PageBlockPhoto.toDomain(): PageBlockPhoto = PageBlockPhoto(
    photo = this.photo?.toDomain(),
    caption = this.caption.toDomain(),
    url = this.url
)

fun TdApi.PageBlockPreformatted.toDomain(): PageBlockPreformatted = PageBlockPreformatted(
    text = this.text.toDomain(),
    language = this.language
)

fun TdApi.PageBlockPullQuote.toDomain(): PageBlockPullQuote = PageBlockPullQuote(
    text = this.text.toDomain(),
    credit = this.credit.toDomain()
)

fun TdApi.PageBlockRelatedArticle.toDomain(): PageBlockRelatedArticle = PageBlockRelatedArticle(
    url = this.url,
    title = this.title,
    description = this.description,
    photo = this.photo?.toDomain(),
    author = this.author,
    publishDate = this.publishDate
)

fun TdApi.PageBlockRelatedArticles.toDomain(): PageBlockRelatedArticles = PageBlockRelatedArticles(
    header = this.header.toDomain(),
    articles = this.articles.map { it.toDomain() }
)

fun TdApi.PageBlockSlideshow.toDomain(): PageBlockSlideshow = PageBlockSlideshow(
    pageBlocks = this.pageBlocks.map { it.toDomain() },
    caption = this.caption.toDomain()
)

fun TdApi.PageBlockSubheader.toDomain(): PageBlockSubheader = PageBlockSubheader(
    subheader = this.subheader.toDomain()
)

fun TdApi.PageBlockSubtitle.toDomain(): PageBlockSubtitle = PageBlockSubtitle(
    subtitle = this.subtitle.toDomain()
)

fun TdApi.PageBlockTable.toDomain(): PageBlockTable = PageBlockTable(
    caption = this.caption.toDomain(),
    cells = this.cells.map { row -> row.map { it.toDomain() } },
    isBordered = this.isBordered,
    isStriped = this.isStriped
)

fun TdApi.PageBlockTableCell.toDomain(): PageBlockTableCell = PageBlockTableCell(
    text = this.text?.toDomain(),
    isHeader = this.isHeader,
    colspan = this.colspan,
    rowspan = this.rowspan,
    align = this.align.toDomain(),
    valign = this.valign.toDomain()
)

fun TdApi.PageBlockTitle.toDomain(): PageBlockTitle = PageBlockTitle(
    title = this.title.toDomain()
)

fun TdApi.PageBlockVerticalAlignment.toDomain(): PageBlockVerticalAlignment = when(this) {
    is TdApi.PageBlockVerticalAlignmentTop -> this.toDomain()
    is TdApi.PageBlockVerticalAlignmentMiddle -> this.toDomain()
    is TdApi.PageBlockVerticalAlignmentBottom -> this.toDomain()
    else -> throw IllegalArgumentException("Unknown type: ${this.javaClass}")
}

fun TdApi.PageBlockVerticalAlignmentBottom.toDomain(): PageBlockVerticalAlignmentBottom = PageBlockVerticalAlignmentBottom

fun TdApi.PageBlockVerticalAlignmentMiddle.toDomain(): PageBlockVerticalAlignmentMiddle = PageBlockVerticalAlignmentMiddle

fun TdApi.PageBlockVerticalAlignmentTop.toDomain(): PageBlockVerticalAlignmentTop = PageBlockVerticalAlignmentTop

fun TdApi.PageBlockVideo.toDomain(): PageBlockVideo = PageBlockVideo(
    video = this.video?.toDomain(),
    caption = this.caption.toDomain(),
    needAutoplay = this.needAutoplay,
    isLooped = this.isLooped
)

fun TdApi.PageBlockVoiceNote.toDomain(): PageBlockVoiceNote = PageBlockVoiceNote(
    voiceNote = this.voiceNote?.toDomain(),
    caption = this.caption.toDomain()
)

