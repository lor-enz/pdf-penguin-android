# Decided against

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
