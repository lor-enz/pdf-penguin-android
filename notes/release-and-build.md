# Release and build

- The app was called Kiwi PDF Reader until that name turned out to be
  taken. The package and `applicationId` stay `kiwi.lorenz.pdfreader`
  (the developer's domain; users don't see it, and changing it would need
  a new Play listing), as does the test data's `kiwi`. The GitHub repo
  was renamed to `pdf-penguin-android` (October 2026); GitHub Pages
  doesn't redirect old URLs, so the privacy policy moved with it.
- Don't set `optimization.packageScope` in the release build. The template's
  `androidx/kotlin/kotlinx` scope made R8 produce a class extending a
  package-private class in `kotlin.collections` (`IllegalAccessError` at
  startup). R8 now optimizes everything (the default, `**`).
- Releases: raising `versionCode` in `version.properties` on `main` makes
  the GitHub Action run the CI checks (it calls `ci.yml`) and then upload a
  signed bundle to Play's internal track as a draft (see "Releasing" in the
  README). The Play upload action gets the service account's key, so it's
  pinned to a commit, not a tag. Bump `versionCode` only in its own `🔖`
  commit, and only when the user asks for a release.
- The workflows use `gradle/actions/setup-gradle@v6`, whose caching is a
  proprietary component under Gradle's Terms of Use. Accepted on purpose:
  the app's own code has to be open source, not every CI tool.
- Release signing reads the gitignored `keystore.properties` in the project
  root (same setup as the user's other apps). The upload key lives outside
  the repo. Without the file, release builds are unsigned (F-Droid signs
  them itself).
