# androidx.pdf

- `androidx.pdf` is beta, and the Compose `PdfViewer` is still
  `@ExperimentalPdfApi`. Fallback if it misbehaves: host
  `PdfViewerFragment` via `fragment-compose`.
- minSdk is 34 (Android 14), so the app installs on the user's OnePlus Pad
  Go. `androidx.pdf` doesn't need Android 15 but SDK extension 13, which
  Android 14 gets through Google Play system updates. Without it (a device
  behind on those updates, or one without Google Play) the library falls
  back to the old `PdfRenderer`: pages show (after the cover's 3 s safety
  net, as they never report ready), but links, search and form fields do
  nothing, and no password opens a protected file. Nothing crashes, and the
  app doesn't try to make up for it: `isSystemUpdateMissing`
  (`util/SystemUpdate.kt`) is the only feature check. Home shows a warning
  banner every time, without "Not now", and the viewer a snackbar each time
  a document opens (files from other apps never pass Home); the password
  screen says the update is needed instead of "wrong password". Shown
  often on purpose: it's rare, and otherwise the app just seems broken.
  Don't add other feature checks: with the update, Android 14 behaves like
  15.
- `androidx.pdf` renders in an isolated process of its own
  (`PdfDocumentServiceImpl`), which also runs `PdfPenguinApp.onCreate`. It
  can't reach the app's files or shared preferences (`getSharedPreferences`
  throws), so `PdfPenguinApp` does nothing there (`Process.isIsolated()`).
  Otherwise the service crashes and no document opens, while the viewer
  shows no error.
- Quirks of the library are recorded where they matter: [Viewer](viewer.md)
  (content padding, fast scroller, links), [Opening](opening.md) (the
  cover's per-page listener) and [Forms](forms.md) (form filling).
