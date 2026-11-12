# Decided against

- The recent files list (redesigned November 2026) has no date sections
  (they'd clutter it more than help), no reading progress bar (not
  important enough), no ⋮ button on entries (long press opens their
  sheet), no large card for the most recent document and no grid view.
- No list of all PDFs on the device (an "All files" tab on Home, considered
  November 2026). The only way to list other apps' PDFs is all files
  access (`MANAGE_EXTERNAL_STORAGE`): MediaStore shows an app only its own
  documents (`READ_EXTERNAL_STORAGE` never covered them from Android 11
  and does nothing from 13), and `DownloadManager` only its own downloads.
  Google Play reviews that permission and may reject a PDF reader for it,
  which rules it out; it would also be the app's first permission, and a
  broad one (writing and deleting too). Folders granted through
  `OPEN_DOCUMENT_TREE` need no permission, but Android 11+ refuses the
  storage root and Download itself, where most PDFs land, and adding
  folders is a setup step. The system picker (`OpenDocument`, limited to
  PDFs) already lists them all, with search, sorting and cloud storage.
- No OCR for scanned PDFs: the usual engine (ML Kit) needs Google Play
  Services, and Tesseract is too large. (`PdfViewer` does take an
  `ocrProvider`, should a suitable engine turn up.)
- No `onProvideAssistContent`. Tried (October 2026) handing the assistant
  the document as `clipData` with a read grant, so it could answer about
  the whole file. Tested on a Pixel 9 Pro (Android 17, Gemini): the system
  asks the viewer for it (Gemini, Circle to Search, the app switcher), but
  nothing uses it; the system never granted the URI, and Gemini's "+" →
  "Screen" attaches a screenshot. Over Drive's PDF viewer Gemini attaches
  the PDF itself, but Drive grants it to Gemini directly
  (`grantUriPermission`, `global` in `dumpsys activity permissions`), a
  private path between Google's apps. Web links (`setWebUri`) are the only
  other thing these use, and the app has none. The assistant works from the
  screenshot, which already includes our viewer, protected documents too.
- No special effort for screen readers (TalkBack): use standard
  components, label icon buttons, and don't break what works, but don't
  build workarounds for them. Tried (October 2026) opening the choice
  sheet from TalkBack's click by wrapping `PdfView`'s accessibility
  delegate, then dropped it: on a phone, form fields couldn't be filled
  with TalkBack at all, and it can't be tested
  through adb (`input` taps and keys bypass TalkBack and reach the app
  as plain touches), only by hand.
- No custom animation between Home and the viewer; back keeps Android's
  predictive back. Tried (November 2026) a slide to the right without
  zoom. Between two activities, Android shrinks the closing one to 90%
  while the finger is down, also with a custom animation
  (`overrideActivityTransition`), which only plays after release, and
  nothing turns that off (`CustomCrossActivityBackAnimation`,
  `MAX_SCALE`). With a custom close animation, Home also stays
  full-screen behind the shrunk viewer instead of beside it, so the
  gesture no longer shows where it lands. Apps that slide freely (Reverb,
  Claude) are a single activity. Avoiding the zoom means drawing the
  gesture in the app (a see-through viewer window, Home following it
  through shared state, a `PredictiveBackHandler` that's always on) or
  moving the viewer into Home's activity: built the first and it worked,
  but it changed how the app runs for an animation. Growing the viewer out
  of the tapped entry (`ActivityOptions.makeScaleUpAnimation`) and a
  colored background behind the gesture were tried too, and not liked.
