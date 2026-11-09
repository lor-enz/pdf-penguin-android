# Settings

- Settings (cog on Home, `SettingsActivity`) shows what the app stores and
  information, in this order: the default PDF app hint at the top (it
  matters most), how long recent files are kept and the storage limit, then
  forget all passwords and clear recent files (both confirmed by a dialog,
  as there's no undo; open documents stay, as in cleanup; settings before
  one-off actions, the broadest last), version (tap copies it with the Android
  version and device for bug reports), source, issues, privacy policy and
  licenses. No "Open by default" link: that system page is about web links
  and can only clear defaults (see [Default PDF app](default-app.md)). Every runtime dependency is Apache 2.0, so the
  licenses screen lists them with one license text
  (`res/raw/apache_license.txt`); check the list when adding a dependency
  (`./gradlew :app:dependencies --configuration releaseRuntimeClasspath`).
  Google's `oss-licenses` plugin needs Play Services.
- The cleanup limits are chosen on the Settings screen itself, no dialog: a
  heading row explains each, a full-width Material `Slider` with a stop per
  preset (`StopSlider`) sits below it, and the stops' labels below that
  (tappable too, the selected one in the accent color). With a large font
  the labels would overlap, so `StopLabels` measures them: they show on one
  line where they fit (the default font size), else with the number above
  the unit ("3" above "months"), else only the selected one, following the
  thumb. On a Pixel 9 Pro the five age labels are stacked at 1.15× and only
  the selected one shows from 1.3×. The choice counts when the thumb is let
  go, so dragging past "1 week" deletes nothing. The thumb slides to a stop
  (200 ms) instead of jumping; that needs a `SliderState` with
  `shouldAutoSnap` off, as the plain `Slider(value, …)` snaps every value of
  the animation to the nearest stop. A choice that would remove something
  (`previewCleanup`) is held as `pendingLimits` behind a snackbar with Undo
  ("2 files removed from the list", or filled-in forms deleted when only
  those go), and applied once the snackbar ends or the screen is left, like
  removing an entry on Home. The Undo stays in Settings, not on Home: there
  it would come out of context, and it's easily missed. A dialog with a
  choice that applied on tapping was dropped as it deleted without any way
  back.
- In wide windows, Settings' content and Home's header (with the default
  app hint) are at most 640 dp wide (`ContentMaxWidth`, as wide as the
  sheets) and centered: across a tablet in landscape a line of text ran
  to about 150 characters. Settings still scrolls beside its content.
