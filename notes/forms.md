# Forms

- Form edits are autosaved (`FormAutosave`) as a full filled-in PDF. When
  the original changes (hash), the filled-in version is dropped; otherwise
  reopening the original opens it without asking. Share, Print and Save a
  copy use it. It counts toward the copies' limits, and a picked file then
  only loses its filled-in version. A filled-in protected form needs the
  same password. Edits to a document without a recent entry only live in
  memory, and are written out just to share or print them.
- `androidx.pdf` form filling, as of beta01: the app applies each edit
  (`EditablePdfDocument.applyEdit`), the viewer only reports it.
  `PdfViewer` sets the document before enabling form filling, so the first
  pages load without their fields and taps on them do nothing; the viewer
  enables it once the first content has loaded, which reloads the fields.
  Closing a `PdfWriteHandle` closes the whole document, so it's never
  closed. The view's own drop-down dialog (a Material Components dialog)
  crashes without a Material theme: the XML theme is based on Material 3
  (Compose doesn't need it) and colors it via `materialAlertDialogTheme`.
  Screen readers still get that dialog (see below).
- Drop-downs and list boxes open a sheet of the app's own
  (`viewer/ChoiceFields.kt`) instead of the view's dialog, which needed a
  second tap ("Confirm"), dropped the choice when tapped beside, didn't say
  which field it was for and (beta01) showed no selection at all. The
  sheet's heading names the field (its tooltip, `/TU`, or else its name,
  `form1[0].page1[0].country[0]` cut to "Country"), the selected option is
  in the accent color with a check, and a long list opens with it in view.
  One option is picked by tapping it, which closes the sheet; a list box
  that takes several ticks each right away, with no Done. A drop-down that
  takes text of its own has an "Other" field below the options (like
  "Other: ___" on paper; picking one is the usual way). `PdfView` has no
  hook, so a touch listener on it takes quick taps on such fields (found in
  the fields of the pages seen, read as they come into view): the view gets
  a cancel instead of the lifted finger. Drags, long presses and second
  fingers still go to it. As the view never sees that tap, a text field
  being edited is closed with its "Done" action (its text is applied as
  it's typed). The tap counts at once, without waiting for a double tap.
  Screen readers activate fields through the view's own nodes, so they
  still get the view's dialog. `choices.pdf` from `make-test-pdfs.py` has
  every kind of choice field.
