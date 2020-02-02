package org.koitharu.kotatsu.ui.details

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.OneExecutionStateStrategy
import moxy.viewstate.strategy.StateStrategyType
import org.koitharu.kotatsu.core.model.Manga
import org.koitharu.kotatsu.core.model.MangaHistory

interface MangaDetailsView : MvpView {

	@StateStrategyType(AddToEndSingleStrategy::class)
	fun onMangaUpdated(manga: Manga)

	@StateStrategyType(AddToEndSingleStrategy::class)
	fun onLoadingStateChanged(isLoading: Boolean)

	@StateStrategyType(OneExecutionStateStrategy::class)
	fun onError(e: Exception)

	@StateStrategyType(AddToEndSingleStrategy::class)
	fun onHistoryChanged(history: MangaHistory?)
}