# How to work here

- Commit atomically — one logical change per commit.
- Use gitmoji for commit messages, following the conventions in [`gitmoji.md`](../gitmoji.md).
- Commit only when the user asks. Propose the split first; every commit
  must build and pass the tests on its own.
- Work in milestones. After each one: build, run the unit tests, test on
  the emulator, then report to the user, who tests on a real phone.
- Test on an available emulator yourself, except when the user says they'll
  test (usually a small change): then stop after the build and unit tests.
- If the emulator or a device misbehaves during testing (hanging installs,
  very slow responses, unresponsive adb), stop and tell the user instead of
  waiting or working around it. It usually needs a manual restart.
- Only test on the emulator unless the user says otherwise; their own
  phone may be connected via adb too (target with `adb -s <serial>`).
- Give new UI elements a test tag (`ui/TestTags.kt`) in the same change
  that adds them, so they can be driven by adb (see [Testing](testing.md)).
- Packages are by feature, each with its screens and logic: `viewer`,
  `home`, `settings`, `defaultapp`, `info`, `recent`, `document` (open,
  print, share), `pdf` (reads what `androidx.pdf` can't: a file's objects, its
  metadata, the table of contents; `create` builds on it), `create` (makes PDFs of shared images, merges shared PDFs). `ui` holds only what several features share (sheets,
  dialogs, `TestTags`, theme), `util` small helpers. The root holds only
  `PdfPenguinApp` and the activities, which keep their names and package
  (manifest, launcher, pinned shortcuts). Put a new file in the package of
  its feature, or propose a new one when it fits none.
- Check the package of every file you touch: a file used by only one
  feature belongs in that feature's package, one used by several goes up to
  `ui` or `util`. If a move is advised, ask the user first, and move in a
  `🚚` commit of its own (moves only, so blame follows the rename).
- Record decisions and findings in the note of their feature (see the
  [index](index.md)), or in a new note added to the index, as they come up.
  Rules that apply everywhere go in [Rules](rules.md), the reasons in the
  feature's note. Update the notes and the readme in the commit they belong
  to (e.g. the milestones), not in a separate one.
