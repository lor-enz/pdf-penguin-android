package kiwi.lorenz.pdfreader.viewer

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView

/**
 * Keeps the document view's padding up to date. `PdfViewer` applies its `contentPadding` only
 * when it creates the view, and a recreated activity (after rotating) creates it before the
 * status bar's inset is known, so the pages started under the top bar (beta01). The view reads
 * its padding as it lays out and draws, so setting it later moves the pages down.
 */
@Composable
fun ContentPadding(contentPadding: PaddingValues) {
    val root = LocalView.current
    val density = LocalDensity.current
    val layoutDirection = LocalLayoutDirection.current
    DisposableEffect(root, contentPadding, density, layoutDirection) {
        // Posted, so PdfViewer has created its view.
        val apply = Runnable {
            val view = root.findPdfView() ?: return@Runnable
            with(density) {
                val left = contentPadding.calculateLeftPadding(layoutDirection).roundToPx()
                val top = contentPadding.calculateTopPadding().roundToPx()
                val right = contentPadding.calculateRightPadding(layoutDirection).roundToPx()
                val bottom = contentPadding.calculateBottomPadding().roundToPx()
                if (view.paddingLeft != left || view.paddingTop != top ||
                    view.paddingRight != right || view.paddingBottom != bottom
                ) {
                    view.setPadding(left, top, right, bottom)
                }
            }
        }
        root.post(apply)
        onDispose { root.removeCallbacks(apply) }
    }
}
