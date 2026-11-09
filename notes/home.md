# Home

- Home's header shows the logo above the app name: the launcher icon's
  foreground (`ic_launcher_foreground`, scaled back up from the 70% that
  fits the icon masks) on a Penguin Blue circle, as the white page would
  vanish on the light background. It gives the tagline ("the blue one") its
  meaning.
- Home has no top bar, so scrolling shows only the list (a strip in the
  background color covers the status bar). The settings cog sits in the
  top right corner, just below the status bar, and scrolls away with the
  header. Its icon is 24 dp from the right edge, like the sheets' content
  (their icons, the heading and the action labels); 16 dp hugged the edge,
  and a gap as large as the status bar looked odd on phones with a tall one
  (Pixel). When the window is wider than tall (at least 600 dp, e.g. a phone
  in landscape) and there are recent files, the header sits on the left and
  the list on the right: stacked, the header filled the screen and the list
  started below it, so it looked empty. The cog then sits in the header's top
  left corner, level with "Recent" (24 dp from the left edge): beside
  "Recent" it looked like an option of the list, and it scrolled away with it.
- Home collects the recent files only while resumed, so opening an entry
  (which moves it to the top) doesn't reorder the list as the viewer slides
  in. Home takes the new order when it's back.
