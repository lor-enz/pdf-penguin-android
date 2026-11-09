# Rules

These apply everywhere. The reasons are in the linked notes.

- Keep the app free of Google Play Services (open source, F-Droid).
- The package and `applicationId` stay `kiwi.lorenz.pdfreader`, as does the
  test data's `kiwi` ([Release and build](release-and-build.md)).
- `isSystemUpdateMissing` (`util/SystemUpdate.kt`) is the only feature
  check; don't add others ([androidx.pdf](androidx-pdf.md)).
- `PdfPenguinApp` does nothing in `androidx.pdf`'s isolated process
  ([androidx.pdf](androidx-pdf.md)).
- Don't set `optimization.packageScope` in the release build
  ([Release and build](release-and-build.md)).
- Bump `versionCode` only in its own `🔖` commit, and only when the user asks
  for a release ([Release and build](release-and-build.md)).
- The privacy policy for Google Play is `docs/privacy-policy.md`, served by
  GitHub Pages from `/docs`. Update it whenever the app starts storing,
  sending or requesting anything new (permissions, network, backups).
- Every runtime dependency is Apache 2.0; check the licenses list when adding
  one ([Settings](settings.md)).
- Add every new string in English and German; lint fails on a missing
  translation. German says "du" and "Gerät" ([Languages](languages.md)).
- Set every color role a component may use, or it falls back to Material's
  lavender ([Theme and icons](theme-and-icons.md)).
- All sheets are `ActionSheet`s ([Sheets](sheets.md)).
- Leave back to the system wherever the app would just close the activity; a
  `BackHandler` is only enabled while it has something to close
  ([Activities and cards](activities-and-cards.md)).
- Files from other apps are accepted only as `content://` URIs
  ([Opening](opening.md)).
- A document is open in one viewer only
  ([Activities and cards](activities-and-cards.md)).
- Protected documents get a thumbnail and title only while their password is
  remembered ([Password](password.md)).
- No special effort for screen readers (TalkBack): use standard components,
  label icon buttons, and don't break what works, but don't build
  workarounds for them ([Decided against](decided-against.md)).
