# Notes

The project's notes are in `notes/`, one per feature (links are relative to
it). Read a feature's note before working on it.

- [Viewer](viewer.md): the scroll view: content padding, filling the width
  or showing whole pages, the fast scroller, the ⋮ sheet, links, search.
  `ViewerScreen`, `DocumentView`, `ContentPadding`, `FastScrollAutoHide`,
  `DocumentSearch`, `SearchBar`.
- [Opening documents](opening.md): the cover, the splash screen, which URIs
  are accepted, diagnosing failures and retrying. `ReadyPages`,
  `FailureDiagnosis`, `FailureMessage`, opening in `ViewerViewModel`.
- [Activities and cards](activities-and-cards.md): Home and viewer
  activities, cards for files from other apps, one viewer per document, the
  tasks the store records, back. The activities, `recent/OpenTasks`.
- [Presentation mode](presentation.md): pager, keys, zoom, links, overlay,
  slider. `Presentation`, `PresentationOverlay`, `PageZoom`, `PageLinks`.
- [Table of contents](contents.md): the drawer and the section being read.
  `ContentsDrawer`.
- [Forms](forms.md): autosave, `androidx.pdf` form filling, the choice
  fields sheet. `FormAutosave`, `ChoiceFields`.
- [Password](password.md): the lock's animation, the password screen's
  layout, remembering passwords. `Lock`, `PasswordPrompt`.
- [Recent files](recent-files.md): private copies, cleanup limits, removing
  with Undo, one entry per content, padlock icons, launcher shortcuts.
  `recent/`, `home/LauncherShortcuts`.
- [Home](home.md): header and logo, the cog, landscape layout, when the list
  updates. `home/`.
- [Settings](settings.md): what it shows and in which order, the cleanup
  sliders, licenses, content width. `settings/`, `ui/ContentMaxWidth`.
- [Default PDF app](default-app.md): the hint on Home and in Settings, how
  defaults are set and cleared. `defaultapp/`.
- [Sheets](sheets.md): half-open state, scrolling, how actions close the
  sheet. `ui/ActionSheet`.
- [Document info](document-info.md): titles, the info sheet, page sizes.
  `info/`.
- [PDF reading](pdf-reading.md): reading a file's objects, metadata,
  encryption. `pdf/`.
- [Create](create.md): PDFs of shared images, merging shared PDFs. `create/`.
- [Print and share](print-share.md): printing, shared copies, Open with.
  `document/`.
- [androidx.pdf](androidx-pdf.md): beta status, minSdk and the system update,
  the isolated process. Read before updating the library or working around
  it. `util/SystemUpdate`, `PdfPenguinApp`.
- [Theme and icons](theme-and-icons.md): colors, launcher and themed icons.
  `ui/theme/`.
- [Languages](languages.md): English and German, terms. Read before adding
  or changing strings.
- [Release and build](release-and-build.md): the app's name, R8, releases,
  CI, signing.
- [Decided against](decided-against.md): OCR, content for the assistant,
  screen reader workarounds, a custom back animation, ideas for the recent
  files list. Read before proposing anything like them.
- [Known issues](known-issues.md): found, not yet fixed.
- [Planned changes](planned.md).
