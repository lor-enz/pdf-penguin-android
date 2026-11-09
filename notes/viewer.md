# Viewer

- `PdfViewer` applies `contentPadding` only when its view is created,
  which is why the top bar is laid over the document. A recreated activity
  (after rotating) creates the view before the status bar's inset is known,
  so the pages started under the top bar; `ContentPadding` sets the view's
  padding again whenever it changes (the view reads it as it lays out and
  draws).
- The viewer fills the width, except in a window wider than tall and at
  least 480 dp tall (a tablet in landscape): there it zooms out until the
  page being read fits between the bars, as filling the width showed only
  a third of a page, in large text. A phone in landscape is less tall, and
  a whole page would be too small there. Checked when the document opens
  and when the window changes size; once the user pinches or double-taps
  (zoom changes while a gesture runs), their zoom stays. Turned back to
  portrait, the width is filled again, at the page that was in the middle
  before (`shownPage`, saved): the view first shows the whole page small
  in the tall window, where neither the reading line nor the middle
  reliably falls on it (page 5 came back as 4, page 1 as 2).
  `PdfViewerState.scrollToPage` zooms to fill the width, so a whole page is shown with
  `scrollToPosition` at its center instead (saved page, back from
  presentation mode). A double tap goes from the whole page to the width.
- `PdfView` keeps the fast scroller always visible whenever any
  accessibility service runs (password managers etc.), not only screen
  readers. `FastScrollAutoHide` switches it back to fading out (library
  default: 1.3 s + 0.2 s fade) unless touch exploration is on, via the
  restricted `fastScrollVisibility`. Drop it once `androidx.pdf` fixes this.
  To reproduce on the emulator, install a dummy accessibility service.
- The viewer's tools live in a bottom sheet behind the ⋮ button, so the
  top bar stays calm as tools are added. Its last item leads to Home: the
  app's own viewer just closes (Home is behind it), a card from another app
  stays and Home comes up in the main task, closing any viewer there.
- Web, mail and phone links go through `onUrlLinkClicked`: only http(s),
  mailto and tel are opened (the library's default would open any scheme).
  In-document links are
  handled by `PdfView` itself: it centers the target in the viewport (so it
  never hides under the top bar), and links with a bare `/Dest` array do
  nothing; only `/GoTo` actions work, including named destinations (LaTeX,
  Word). Nothing to hook into for either, as of beta01.
- Searching a document without any text says so instead of "0 / 0"; the
  check reads pages until the first one with text and runs once per
  document.
