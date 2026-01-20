package com.xxcactussell.data.utils.mappers.page

import com.xxcactussell.data.utils.mappers.animation.toData
import com.xxcactussell.data.utils.mappers.chat.toData
import com.xxcactussell.data.utils.mappers.file.toData
import com.xxcactussell.data.utils.mappers.location.toData
import com.xxcactussell.data.utils.mappers.photo.toData
import com.xxcactussell.data.utils.mappers.rich.toData
import org.drinkless.tdlib.TdApi
import com.xxcactussell.domain.*

// Mappers: Domain -> TdApi

fun PageBlock.toData(): TdApi.PageBlock = when(this) {
    is PageBlockTitle -> this.toData()
    is PageBlockSubtitle -> this.toData()
    is PageBlockAuthorDate -> this.toData()
    is PageBlockHeader -> this.toData()
    is PageBlockSubheader -> this.toData()
    is PageBlockKicker -> this.toData()
    is PageBlockParagraph -> this.toData()
    is PageBlockPreformatted -> this.toData()
    is PageBlockFooter -> this.toData()
    is PageBlockDivider -> this.toData()
    is PageBlockAnchor -> this.toData()
    is PageBlockList -> this.toData()
    is PageBlockBlockQuote -> this.toData()
    is PageBlockPullQuote -> this.toData()
    is PageBlockAnimation -> this.toData()
    is PageBlockAudio -> this.toData()
    is PageBlockPhoto -> this.toData()
    is PageBlockVideo -> this.toData()
    is PageBlockVoiceNote -> this.toData()
    is PageBlockCover -> this.toData()
    is PageBlockEmbedded -> this.toData()
    is PageBlockEmbeddedPost -> this.toData()
    is PageBlockCollage -> this.toData()
    is PageBlockSlideshow -> this.toData()
    is PageBlockChatLink -> this.toData()
    is PageBlockTable -> this.toData()
    is PageBlockDetails -> this.toData()
    is PageBlockRelatedArticles -> this.toData()
    is PageBlockMap -> this.toData()
}

fun PageBlockAnchor.toData(): TdApi.PageBlockAnchor = TdApi.PageBlockAnchor(
    this.name
)

fun PageBlockAnimation.toData(): TdApi.PageBlockAnimation = TdApi.PageBlockAnimation(
    this.animation?.toData(),
    this.caption.toData(),
    this.needAutoplay
)

fun PageBlockAudio.toData(): TdApi.PageBlockAudio = TdApi.PageBlockAudio(
    this.audio?.toData(),
    this.caption.toData()
)

fun PageBlockAuthorDate.toData(): TdApi.PageBlockAuthorDate = TdApi.PageBlockAuthorDate(
    this.author.toData(),
    this.publishDate
)

fun PageBlockBlockQuote.toData(): TdApi.PageBlockBlockQuote = TdApi.PageBlockBlockQuote(
    this.text.toData(),
    this.credit.toData()
)

fun PageBlockCaption.toData(): TdApi.PageBlockCaption = TdApi.PageBlockCaption(
    this.text.toData(),
    this.credit.toData()
)

fun PageBlockChatLink.toData(): TdApi.PageBlockChatLink = TdApi.PageBlockChatLink(
    this.title,
    this.photo?.toData(),
    this.accentColorId,
    this.username
)

fun PageBlockCollage.toData(): TdApi.PageBlockCollage = TdApi.PageBlockCollage(
    this.pageBlocks.map { it.toData() }.toTypedArray(),
    this.caption.toData()
)

fun PageBlockCover.toData(): TdApi.PageBlockCover = TdApi.PageBlockCover(
    this.cover.toData()
)

fun PageBlockDetails.toData(): TdApi.PageBlockDetails = TdApi.PageBlockDetails(
    this.header.toData(),
    this.pageBlocks.map { it.toData() }.toTypedArray(),
    this.isOpen
)

fun PageBlockDivider.toData(): TdApi.PageBlockDivider = TdApi.PageBlockDivider(
)

fun PageBlockEmbedded.toData(): TdApi.PageBlockEmbedded = TdApi.PageBlockEmbedded(
    this.url,
    this.html,
    this.posterPhoto?.toData(),
    this.width,
    this.height,
    this.caption.toData(),
    this.isFullWidth,
    this.allowScrolling
)

fun PageBlockEmbeddedPost.toData(): TdApi.PageBlockEmbeddedPost = TdApi.PageBlockEmbeddedPost(
    this.url,
    this.author,
    this.authorPhoto?.toData(),
    this.date,
    this.pageBlocks.map { it.toData() }.toTypedArray(),
    this.caption.toData()
)

fun PageBlockFooter.toData(): TdApi.PageBlockFooter = TdApi.PageBlockFooter(
    this.footer.toData()
)

fun PageBlockHeader.toData(): TdApi.PageBlockHeader = TdApi.PageBlockHeader(
    this.header.toData()
)

fun PageBlockHorizontalAlignment.toData(): TdApi.PageBlockHorizontalAlignment = when(this) {
    is PageBlockHorizontalAlignmentLeft -> this.toData()
    is PageBlockHorizontalAlignmentCenter -> this.toData()
    is PageBlockHorizontalAlignmentRight -> this.toData()
}

fun PageBlockHorizontalAlignmentCenter.toData(): TdApi.PageBlockHorizontalAlignmentCenter = TdApi.PageBlockHorizontalAlignmentCenter(
)

fun PageBlockHorizontalAlignmentLeft.toData(): TdApi.PageBlockHorizontalAlignmentLeft = TdApi.PageBlockHorizontalAlignmentLeft(
)

fun PageBlockHorizontalAlignmentRight.toData(): TdApi.PageBlockHorizontalAlignmentRight = TdApi.PageBlockHorizontalAlignmentRight(
)

fun PageBlockKicker.toData(): TdApi.PageBlockKicker = TdApi.PageBlockKicker(
    this.kicker.toData()
)

fun PageBlockList.toData(): TdApi.PageBlockList = TdApi.PageBlockList(
    this.items.map { it.toData() }.toTypedArray()
)

fun PageBlockListItem.toData(): TdApi.PageBlockListItem = TdApi.PageBlockListItem(
    this.label,
    this.pageBlocks.map { it.toData() }.toTypedArray()
)

fun PageBlockMap.toData(): TdApi.PageBlockMap = TdApi.PageBlockMap(
    this.location.toData(),
    this.zoom,
    this.width,
    this.height,
    this.caption.toData()
)

fun PageBlockParagraph.toData(): TdApi.PageBlockParagraph = TdApi.PageBlockParagraph(
    this.text.toData()
)

fun PageBlockPhoto.toData(): TdApi.PageBlockPhoto = TdApi.PageBlockPhoto(
    this.photo?.toData(),
    this.caption.toData(),
    this.url
)

fun PageBlockPreformatted.toData(): TdApi.PageBlockPreformatted = TdApi.PageBlockPreformatted(
    this.text.toData(),
    this.language
)

fun PageBlockPullQuote.toData(): TdApi.PageBlockPullQuote = TdApi.PageBlockPullQuote(
    this.text.toData(),
    this.credit.toData()
)

fun PageBlockRelatedArticle.toData(): TdApi.PageBlockRelatedArticle = TdApi.PageBlockRelatedArticle(
    this.url,
    this.title,
    this.description,
    this.photo?.toData(),
    this.author,
    this.publishDate
)

fun PageBlockRelatedArticles.toData(): TdApi.PageBlockRelatedArticles = TdApi.PageBlockRelatedArticles(
    this.header.toData(),
    this.articles.map { it.toData() }.toTypedArray()
)

fun PageBlockSlideshow.toData(): TdApi.PageBlockSlideshow = TdApi.PageBlockSlideshow(
    this.pageBlocks.map { it.toData() }.toTypedArray(),
    this.caption.toData()
)

fun PageBlockSubheader.toData(): TdApi.PageBlockSubheader = TdApi.PageBlockSubheader(
    this.subheader.toData()
)

fun PageBlockSubtitle.toData(): TdApi.PageBlockSubtitle = TdApi.PageBlockSubtitle(
    this.subtitle.toData()
)

fun PageBlockTable.toData(): TdApi.PageBlockTable = TdApi.PageBlockTable(
    this.caption.toData(),
    this.cells.map { row -> row.map { it.toData() }.toTypedArray() }.toTypedArray(),
    this.isBordered,
    this.isStriped
)

fun PageBlockTableCell.toData(): TdApi.PageBlockTableCell = TdApi.PageBlockTableCell(
    this.text?.toData(),
    this.isHeader,
    this.colspan,
    this.rowspan,
    this.align.toData(),
    this.valign.toData()
)

fun PageBlockTitle.toData(): TdApi.PageBlockTitle = TdApi.PageBlockTitle(
    this.title.toData()
)

fun PageBlockVerticalAlignment.toData(): TdApi.PageBlockVerticalAlignment = when(this) {
    is PageBlockVerticalAlignmentTop -> this.toData()
    is PageBlockVerticalAlignmentMiddle -> this.toData()
    is PageBlockVerticalAlignmentBottom -> this.toData()
}

fun PageBlockVerticalAlignmentBottom.toData(): TdApi.PageBlockVerticalAlignmentBottom = TdApi.PageBlockVerticalAlignmentBottom(
)

fun PageBlockVerticalAlignmentMiddle.toData(): TdApi.PageBlockVerticalAlignmentMiddle = TdApi.PageBlockVerticalAlignmentMiddle(
)

fun PageBlockVerticalAlignmentTop.toData(): TdApi.PageBlockVerticalAlignmentTop = TdApi.PageBlockVerticalAlignmentTop(
)

fun PageBlockVideo.toData(): TdApi.PageBlockVideo = TdApi.PageBlockVideo(
    this.video?.toData(),
    this.caption.toData(),
    this.needAutoplay,
    this.isLooped
)

fun PageBlockVoiceNote.toData(): TdApi.PageBlockVoiceNote = TdApi.PageBlockVoiceNote(
    this.voiceNote?.toData(),
    this.caption.toData()
)

