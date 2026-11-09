# Known issues

Found in a review of the app in landscape (October 2026), not yet fixed:

- Password screen in landscape: with the keyboard up, only about 43 dp
  are left above it (emulator), less than the field's 56 dp, so the
  field's label is cut off. No layout can fit more; the keyboard's ✓
  submits.
- Home without recent files ignores side cutouts: in landscape the
  default app hint reaches under the camera (the side-by-side layout with
  recent files keeps clear of it).
- Viewer in landscape: the status and top bars take about 28% of the
  height. Hiding the top bar while scrolling down would leave more room
  for the document.
- The viewer's ⋮ sheet doesn't fit in landscape: "Document info" and
  "PDF Penguin home" need scrolling.

Found on a tablet emulator (`Medium_Tablet`, 1280×800 dp, October 2026):

- The scroll view never gets keyboard focus: arrow keys move focus to the
  top bar (Space then presses Back and closes the viewer), Page Down and
  Space don't scroll, and Ctrl+F doesn't open search. `PdfView` handles
  arrows, Ctrl +/−/0, Ctrl+A and Ctrl+C itself once it's focused.
- Password screen in landscape: the lock and file name stay centered
  (not moved by the keyboard, on purpose), so while typing the tablet's
  keyboard hides them; only the shackle's top shows.

Found while making sheets slide away (October 2026):

- Share and Open with copy the document before the chooser appears
  (`sharedCopy`). With a large file (`heavy.pdf`) the sheet is gone and the
  viewer or Home sits there without any sign of progress until the chooser
  comes up. Sliding the sheet away wouldn't help: it would only add its
  animation to the wait.
