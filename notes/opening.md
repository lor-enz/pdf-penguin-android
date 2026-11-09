# Opening documents

- The viewer covers the document (background and a large PDF symbol,
  `DocumentPlaceholder`, as in the loading state; opening is too quick for a
  spinner to read as progress) until the visible pages, at most two, are rendered, and a
  reopened document has jumped to its saved page; then the cover fades out.
  `PdfViewer` only reports the first bitmap (`onFirstContentLoad`), so
  `rememberReadyPages` uses `PdfView`'s restricted per-page listener. After
  3 s (from opening, and again from the jump to the saved page) it shows
  anyway: a safety net in case pages never report ready, as white pages
  beat a hidden document. The jump only counts once the view reports the
  saved page as visible: it may scroll later, and until then its visible
  pages are the first ones, which have rendered.
- The splash screen is the system's default (the launcher icon on
  `windowBackground`), shown when the app starts and for every file from
  another app, as each opens a card of its own. There the cover continues
  it: the app icon at the splash's size (160 dp) and position (centered in
  the window), so the penguin greets the user until the document shows. Only
  then (`greetsWithAppIcon`): a password, a failure or another document ends
  it, and the viewer in the main task (from Home) always shows the PDF
  symbol. The penguin on every document from Home looked like the app
  restarting; the PDF symbol after the splash jumped in size and position.
  The theme sets `windowSplashScreenBehavior` to `icon_preferred`: by
  default only the launcher gets the icon, and a file opened from another
  app (a file manager) showed an empty screen until the cover drew it. `am
  start` from the shell does show the icon, so test that through an app
  (e.g. Files, Downloads). While the cover greets, the viewer removes the
  splash at once (`setOnExitAnimationListener`): the system's exit animation
  faded the icon out and the cover's in, so it seemed to reset. Home keeps
  the system's animation.
- Files from other apps are accepted only as `content://` URIs. A
  `file://` URI would be read with the app's own rights, which reach its
  private files; it gets the "not given access" error instead.
- Failures to open are diagnosed by reading the file's first bytes: no PDF
  header means not a PDF, a header (or an empty file) means damaged.
  Deleting a document revokes its URI grants, so a `SecurityException`
  can't tell "deleted" from "permission revoked": for files that opened
  before it counts as moved or deleted, otherwise as not given access.
  A failed file leaves the recent list, except when it opened before and
  still looks like a PDF (`TEMPORARY`): that's most likely a passing failure
  (the PDF service), and for a copy it's the only one there is. Such a file
  is retried once on its own after 500 ms, and only then offers "Try again"
  instead of "Open another PDF". To test it, truncate the stored copy
  (`files/recent/copies/` via `run-as`) of a file that opened before.
