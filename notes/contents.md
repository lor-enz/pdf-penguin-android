# Table of contents

- The table of contents (the PDF's outline) opens in a drawer from the left
  (`viewer/ContentsDrawer.kt`, Material's `ModalNavigationDrawer`), by a
  button left of ⋮ that only shows when the document has one. It opens only
  by that button: swiping in from the edge is the system's back gesture.
  `androidx.pdf` can't read outlines, so `PdfMetadataReader` does (only when
  asked, `read(withOutline)`, as it goes through every page): explicit and
  named destinations (name tree or old `/Dests` dictionary) and "go to"
  actions; links to websites or other files lead nowhere. The viewer reads
  it while the document opens, in parallel, so the button never pops in.
  Branches start open or closed as the author left them. A jump puts the
  target's top right below the top bar (`PdfViewerState.scrollToPosition`
  centers its point, so it's shifted by half the view). The section being
  read is the last that starts above a line a third down the visible area
  (a quarter missed chapter headings below a page's top margin); it's
  marked, and its closed parents open, as soon as the drawer starts to
  open (it's composed off screen), not once it's open: that came late. To
  test it, `contents.pdf` from `make-test-pdfs.py` draws a rule where each
  entry leads.
