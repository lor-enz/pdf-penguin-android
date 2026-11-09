# Presentation mode

- Presentation mode ("Present" in the ⋮ sheet, `viewer/Presentation.kt`)
  shows one page at a time, full screen, as large as fits, on the app's dark
  background (`NightBackground`) in both themes. `PdfViewer` only scrolls
  continuously, so it's a `HorizontalPager` of pages rendered by
  `PdfDocument.getPageBitmapSource`. Swiping settles on a page like a photo
  gallery: the neighbors only show while swiping, with a gap between them.
  Keys turn pages too, so presentation clickers work: right, down, Page Down
  and space go forward, left, up and Page Up back, Home and End to the first
  and last page. No tapping the edges to turn pages, and volume keys are left
  alone. System bars are hidden (a swipe from the edge shows them for a
  moment; Android shows its own hint the first time) and the screen stays
  on. It starts at the page being read (the one at the table of contents'
  reading line) and Back returns to scrolling at the page shown last, which
  is saved as the last page. The view model holds the page (in saved state,
  so the mode survives process death). The scroll view stays composed
  below, hidden from screen readers, so it doesn't open anew afterwards.
- Presentation mode zooms (`viewer/PageZoom.kt`) with Telephoto's
  `Modifier.zoomable` (a library, as gestures inside a pager are tricky):
  pinch, double-tap (2.5× and back) and double-tap and hold, up to 5×. A
  drag decides at its start: if the page can pan that way, it pans and stops
  at the edge; only a drag that starts at the edge turns the page, so panning
  never does by accident (Telephoto's own behavior). Only the page shown
  zooms; its gesture area is the whole screen, so pinching beside a narrow
  page works, and the neighbors take no gestures. The pager moves its
  settled page to the target as soon as it starts snapping, so the page
  that zooms only changes once it has stopped: the page being left keeps its
  zoom while it slides away. Its zoom is dropped then, so it's whole again
  when it comes back. Keys still turn pages (Telephoto's shortcuts are off),
  and the zoom isn't saved, so rotation and process death show the whole
  page. While zooming the page is stretched; once it's still, the visible
  part is rendered again at the zoom (one screen's worth, a whole page at 5×
  would be far too large). To test on the emulator, double-tap with
  `cmd input tap x y; sleep 0.1; cmd input tap x y` in one `adb shell`:
  `input tap` is too slow for a double tap, and without the pause too fast.
- Links work in presentation mode too (`viewer/PageLinks.kt`): Telephoto
  reports single taps (once they can't start a double tap, about 300 ms, as
  in the scroll view), converted through the zoom to the page's points and
  looked up in `PdfDocument.getPageLinks`. A tap within 8 dp of a link
  counts, unless it's right on another one. A link within the document jumps
  to its page directly, without flicking through the pages in between, and
  shows it whole (position and zoom of the target are ignored). Other links
  open like the scroll view's (`openLink`), and a failure shows in the
  viewer's snackbar, which lies above presentation mode. Coming back from
  the app a link opened hides the system bars again. Bare `/Dest` links
  don't work here either: the library doesn't report them, and supporting
  them only here would make the two views differ.
- Presentation mode's overlay (`viewer/PresentationOverlay.kt`): a back
  arrow with the title and file name at the top (as in the viewer's top
  bar), the page number ("3 / 11") and a slider at the bottom. A tap that hits no link
  shows or hides it, zoomed in too; the system bars come and go with it,
  as in a photo gallery, which also makes Back easy to find. It shows for
  1 s when the mode starts, so the controls are seen, and hides 3 s after a
  tap (or longer if the accessibility timeout setting asks for it). It stays
  while swiping, so the number changes in front of the user, and the timer
  starts again once the swipe settles. A key that turns the page hides it
  at once (a clicker means presenting). With touch exploration on it always
  shows, as a screen reader can't tap to bring it up. Back still leaves the
  mode, and the overlay isn't saved. It's laid over the page, so nothing
  moves, on a scrim of `NightBackground` that fades from 90% at the
  screen's edge to nothing 160 dp beyond the text, along a smoothstep
  curve so it has no visible edge. A shorter, linear one left the file name
  and page number barely readable on a white page; one that stayed dark up
  to the text and faded only beyond it looked like a hard band.
  Its padding uses `systemBarsIgnoringVisibility`, so it doesn't move when
  the bars come and go.
- The overlay's slider turns the pages while it's dragged, not only once
  it's let go: a page is found by looking at it, and the number alone made
  that tedious. Each page jumps directly (`requestScrollToPage`) with a
  light tick (`SEGMENT_FREQUENT_TICK`, as the Settings sliders), and while
  it's held the pager renders only the page shown (no neighbors ahead), so
  rendering keeps up. A heavy page shows white until it has rendered. The
  overlay stays while the slider is held; grabbed while the overlay fades
  out (right after the mode starts), it brings it back, and once let go it
  stays the full 3 s. The track is slim (4 dp; Material's 16 dp competed
  with the page). It has a step per page (screen
  readers move one page) without drawing the ticks, and it never takes the
  focus, or a clicker's arrow keys would move it instead of turning the
  page. The number beside it keeps the width of the widest one, with
  tabular digits, so the slider's length doesn't change. A one-page
  document has no slider. To drag it on the emulator, use `input
  motionevent DOWN/MOVE/UP` in one `adb shell` within the overlay's 3 s.
