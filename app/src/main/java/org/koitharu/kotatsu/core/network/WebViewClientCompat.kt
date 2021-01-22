package org.koitharu.kotatsu.core.network

import android.annotation.TargetApi
import android.os.Build
import android.webkit.*

@Suppress("OverridingDeprecatedMember")
abstract class WebViewClientCompat : WebViewClient() {

	open fun shouldOverrideUrlCompat(view: WebView, url: String): Boolean {
		return false
	}

	open fun shouldInterceptRequestCompat(view: WebView, url: String): WebResourceResponse? {
		return null
	}

	open fun onReceivedErrorCompat(
		view: WebView,
		errorCode: Int,
		description: String?,
		failingUrl: String,
		isMainFrame: Boolean
	) {
	}

	@TargetApi(Build.VERSION_CODES.N)
	final override fun shouldOverrideUrlLoading(
		view: WebView,
		request: WebResourceRequest
	): Boolean = shouldOverrideUrlCompat(view, request.url.toString())

	final override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
		return shouldOverrideUrlCompat(view, url)
	}

	final override fun shouldInterceptRequest(
		view: WebView,
		request: WebResourceRequest
	): WebResourceResponse? = shouldInterceptRequestCompat(view, request.url.toString())

	final override fun shouldInterceptRequest(
		view: WebView,
		url: String
	): WebResourceResponse? = shouldInterceptRequestCompat(view, url)

	@TargetApi(Build.VERSION_CODES.M)
	final override fun onReceivedError(
		view: WebView,
		request: WebResourceRequest,
		error: WebResourceError
	) {
		onReceivedErrorCompat(
			view,
			error.errorCode,
			error.description?.toString(),
			request.url.toString(),
			request.isForMainFrame
		)
	}

	final override fun onReceivedError(
		view: WebView,
		errorCode: Int,
		description: String?,
		failingUrl: String
	) {
		onReceivedErrorCompat(view, errorCode, description, failingUrl, failingUrl == view.url)
	}

	@TargetApi(Build.VERSION_CODES.M)
	final override fun onReceivedHttpError(
		view: WebView,
		request: WebResourceRequest,
		error: WebResourceResponse
	) {
		onReceivedErrorCompat(
			view,
			error.statusCode,
			error.reasonPhrase,
			request.url
				.toString(),
			request.isForMainFrame
		)
	}
}