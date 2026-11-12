# Recent files

- Entries are cards filled in a grey (`LocalEntryColor`), without a border:
  the thin outline, doubled by the thumbnail's own, looked like a
  wireframe. Not the containers' Ice Blue (`surfaceContainer`), which made
  them look like the default app hint. A segmented list (one surface split
  by 2 dp gaps) was tried and looked less good. Beside the 54×72 dp
  thumbnail: the title, the file name, then the page last read and when it
  was last opened.
- Files opened from other apps lose their permission once closed, so the
  app keeps private copies for the recent files list. Cleanup (`CleanupRules`)
  has two limits, each chosen in Settings from a few presets, as no single
  default suits both short-lived files (tickets, documents to sign) and
  reference material (manuals, contracts). Age: an entry not opened for
  1 week, 1 month, 3 months (default), 6 months or 1 year leaves the list,
  picked files too (their permission is released), or the list would only
  grow. Storage: the
  copies and filled-in forms may take 100 MB, 500 MB (default, about a
  hundred everyday PDFs) or 2 GB (decimal, as Android shows sizes); above it
  the least recently opened go first, a copy with its entry, a picked file
  only losing its filled-in version. There's no limit on the number of
  entries. Stored in shared preferences (`cleanup`).
- Removing an entry hides it in the store and deletes its files only when
  the Undo snackbar is gone (also when the screen is left); after process
  death the startup cleanup finishes the job. Snackbars with an action
  default to indefinite in Material 3, so it uses `SnackbarDuration.Long`.
- The same content gets one entry (SHA-256, computed in the background
  after opening): the picked file wins and other entries, copies included,
  go. A file from another app that matches an unchanged picked entry opens
  through that entry.
- The recent files list marks protected entries with Material's padlock
  icons, not the animated `Lock`: open when the password is remembered (it
  opens without asking), closed otherwise. No animation on tap. An entry
  learns it's protected (`isProtected`) when it opens; entries saved before
  the flag count as protected if their password is remembered.
- Launcher shortcuts (`LauncherShortcuts`, started by `PdfPenguinApp`, so
  the store doesn't depend on them; long press on the app icon) open
  the four most recent entries, on top of Home so back leads to the list.
  They follow the store's list once it's loaded (`loadedEntries`; the empty
  list before that would wipe them). A shortcut pinned to the home screen
  is disabled once its entry leaves the list (also during Undo) and enabled
  again if it comes back. Icons show the thumbnail on Penguin Blue, or the PDF
  symbol when there's none (protected without remembered password). The
  shortcut intents name `MainActivity` and `ViewerActivity`, so pinned ones
  break if those are renamed.
