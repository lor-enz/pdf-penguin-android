# Theme and icons

- Colors (`ui/theme`): Penguin Blue (`#00A6FB`) is the icon's background
  and the accent in dark mode. It's too light for text on a light
  background (2.6:1), so light mode uses Ocean Blue, the same hue darker.
  Filled buttons are Ocean Blue, in dark mode Deep Blue, as a blue bright
  enough for text glares as a filled area. The greys and dark backgrounds
  are tinted blue to match. The secondary roles reuse the blues. Errors
  (a wrong password) are orange instead of Material's red, `#F46036` in
  both themes. On the light background its text is only 3:1, accepted on
  purpose: a darker orange (`#B8462A`, 5:1) looked too dark. Every color role
  a component may use is set, or it falls back to Material's lavender
  (snackbars use the inverse colors; in presentation mode, whose background is
  `NightBackground` in both themes, they take dark mode's, or in light mode
  they'd vanish into it). `outline` is for field borders (3:1),
  `outlineVariant` for decorative ones (thumbnails). The recent files
  entries are filled with Mist (`LocalEntryColor`), a grey tinted blue, in
  dark mode `NightSurface`. In dark mode bottom sheets are darker than the
  background (`NightSheet` as `surfaceContainerLow`), in light mode they
  keep the background color.
- The logo sources are in `logos-and-marketing-images/`. The themed icon's
  glyph (`ic_launcher_monochrome`) is scaled to 62%, the size of Google's
  own themed glyphs; at 70% (as the foreground) it looked heavier beside
  them. To check it, `Emu_Pixel_9_Pro_with_Google`'s Pixel Launcher themes
  the home screen (Wallpaper & style → Icons), not the app drawer.
