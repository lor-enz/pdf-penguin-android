# Default PDF app

- Home shows a quiet hint below "Open PDF" while the app isn't the default PDF
  app (`defaultapp/DefaultPdfApp.kt`, checked on every resume). "Not now" hides it for
  7 days (`PdfDefaultTracker`, a timestamp in shared preferences), as
  without being the default the app is barely used. Settings shows the same
  banner (`defaultapp/DefaultHint.kt`) without "Not now", at the top of
  the screen, or a quiet line once the app is the default, so it's always
  found there. Android has no role for
  PDFs, so an app can't ask to become the default: only "Always" in the
  system's "Open with" dialog sets it, and only the default app's "Open by
  default" page clears it ("Clear default preferences", below the web
  links). With no default, "Make default" opens a small PDF of the app's own
  (`cache/default-check/`), so that dialog appears; the viewer closes as soon
  as it gets that file, and Home confirms in a snackbar once the app is the
  default. With another app as default, the hint says so without naming it,
  and "Clear other default" opens that app's "Open by default" page; back on
  Home the hint offers "Make default". The hint is a Material banner (tonal
  fill, info icon, action bottom right), not outlined: that read as one
  more recent entry. Seeing other apps needs the manifest's `<queries>` entry
  for viewing PDFs. To test it, set or clear defaults on the emulator with
  a plain VIEW intent (`open-pdf.sh` targets the app directly) and Drive's
  PDF viewer. Once the dialog suggests one app ("Open with …"), only that
  one gets "Always"; the others below it open just once. "Always" is disabled
  until an app is selected. `adb shell cmd package
  clear-package-preferred-activities kiwi.lorenz.pdfreader` clears the app's
  default again; Home only notices on its next resume.
