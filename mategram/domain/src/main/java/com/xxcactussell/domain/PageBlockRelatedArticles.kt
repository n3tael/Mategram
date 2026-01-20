package com.xxcactussell.domain

data class PageBlockRelatedArticles(
    val header: RichText,
    val articles: List<PageBlockRelatedArticle>
) : PageBlock
