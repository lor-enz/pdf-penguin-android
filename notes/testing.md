# Testing

- Unit tests run on the JVM. `RecentFilesStoreTest` uses Robolectric (simulating
  Android 15, see `src/test/resources/robolectric.properties`) and a fake
  documents provider (`TestFilesProvider`), so store logic is tested without a
  device; add a test there when changing the store. `ViewerViewModelTest` covers
  how the viewer opens documents (passwords, filled-in forms, failures, other
  cards) with the real store, a fake `PdfLoader` and fake password encryption
  (Robolectric has no Android Keystore); add a test there when changing that.
- Use only already running emulators. If none are running or they are misbehaving, tell the user.
  There are three: `Emu_Pixel_9a_No_Google` (Android 16) for everyday
  testing, and `Medium_Phone` (Android 14 without Google Play, SDK extension
  7, which it never gets past) for devices without the system update `androidx.pdf` needs (see
  [androidx.pdf](androidx-pdf.md)). Test changes to that warning, or to anything that may
  behave differently there, on the second too. `Emu_Pixel_9_Pro_with_Google`
  (Android 16) has Google Play services, for what needs Google's apps (the
  assistant, Drive's PDF viewer); its assistant has no account.
- Only one emulator runs at a time, and RAM and storage are limited. If a
  test needs another configuration, ask the user for it instead of working
  around it, and keep such requests few.
- Helpers in `scripts/test/` use the running emulator, never a connected
  phone unless `ANDROID_SERIAL` names it. With several devices connected,
  pick one with `ANDROID_SERIAL` (`adb devices`; `adb -s <serial> emu avd
  name` tells which is which). Install with `install.sh`, not
  `./gradlew installDebug`, which installs on every connected device.
  - `make-test-pdfs.py` writes test files to `build/test-pdfs/` (the
    protected ones use password `kiwi`). `heavy.pdf` (about 200 MB, a
    large image on every page) renders slowly, e.g. to test the viewer's
    cover.
  - `make-metadata-fixtures.py` and `make-merge-fixtures.py` write the
    committed PDFs for the unit tests of reading and merging; see
    [PDF reading](pdf-reading.md) and [Create](create.md).
  - `prepare-device.sh` turns animations off, which keeps screenshots
    stable. Run it before testing; an emulator booted from an older
    snapshot has them on again.
  - `open-pdf.sh [--fresh] [--no-grant] <file>` opens a file the way
    another app would (push to Download, media store URI, VIEW intent),
    in a card of its own. `--fresh` stops the app and clears its data: a
    file opened before otherwise reopens at its last page, and one that's
    still open switches to its card.
  - `ui.sh list|tap|hold|text|type|wait|back|home|shot` reads and drives
    the screen through `uiautomator`, finding elements by test tag, text
    or description. `home` opens the app like its launcher icon; a plain
    `am start -n` would stack a second home screen.
  - `app.sh cards|focus|top|kill` lists the cards with the document each
    shows, switches between them, and simulates process death (`am kill`
    doesn't work, and force-stop removes the document cards).
  - `store.sh show|age` lists the recent files list and makes an entry
    older, e.g. to test cleanup. Cleanup runs when the home screen is
    created, after a document opens and when a viewer closes.
- Test tags (`ui/TestTags.kt`) show up as resource ids because the root
  sets `testTagsAsResourceId` (`setAppContent`). Popups, sheets and dialogs
  are windows of their own and need `Modifier.exposeTestTags()` again.
- Page content is a single accessibility node, so links in a document are
  tapped by coordinates.
  - `form-values.sh [--password <pw>] <entry name | /device/path>` prints
    the field values of an entry's stored filled-in form, or of a PDF on
    the device, and whether it needs a password.
- With a text field focused, the first back only closes the keyboard.
- Form fields are tapped by coordinates. A document that reopens at its
  last position may sit lower on screen, so take a screenshot first. A tap
  only counts after about 300 ms (the view waits for a double tap), so
  leave the app no sooner than that after one.
- Fast swipes fling to the end of the document; use slow ones.
- To check animations, set `animator_duration_scale` to 1 (the splash screen
  needs all three animation scales; 5 slows them down) and record with
  `screenrecord --size 720x1606` (the full size fails); run
  `prepare-device.sh` again afterwards.
