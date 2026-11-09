# Sheets

- Sheets skip the half-open state (`skipPartiallyExpanded`): a recreated
  sheet (e.g. on switching to dark mode) returns to it, even after being
  dragged up, and the info sheet is meant to be read in full anyway. All
  sheets are `ActionSheet`s, whose content scrolls when it doesn't fit (e.g.
  in landscape; `ModalBottomSheet` would cut it off). The navigation bar's
  inset is inside the scrolling content, or it would be a strip hiding what
  scrolls past it, and the content fills the width, so the sheet scrolls
  beside narrow content too.
- An action closes its sheet at once by default (`SheetClosing.Instant`):
  most bring up something else (a chooser, another sheet), which covers the
  cut. Those whose result shows behind the sheet slide it away like a swipe
  down (`SheetClosing.Slide`), and run once it's gone: "Remove from list"
  (the entry is seen leaving; it would also take its sheet with it), "Forget
  password" (its only feedback is the entry's padlock closing, and its
  thumbnail and title going) and the choices of form fields (which apply
  right away, so the field shows the new value as the sheet slides off).
