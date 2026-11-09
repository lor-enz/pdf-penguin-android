# Password

- The password screen's padlock (`viewer/Lock.kt`) is drawn in Compose, like
  Material's lock icon, so its shackle can move: once the password is
  right it lifts until the short leg is free and swings half a turn around
  the long one, which stays in the body (seen from the front, the shackle
  narrows and widens again on the other side; the open lock isn't
  centered). It shakes (with the system's reject vibration) when the
  password is wrong. All timings are in `LockMotion`. The view
  model holds the opened document back (`NeedsPassword.unlocked`) until the
  lock has opened, then it fades in. The password is only known to be right
  once the document opened, so the animation can't start any earlier.
  When the window is wider than tall (at least 600 dp), the lock and file
  name sit on the left and the field, checkbox and button on the right.
  Stacked, a phone in landscape has room for the field only once the
  keyboard is up (not even all of it), so the rest was out of sight. There,
  submitting closes the keyboard, or it would cover the lock opening or
  shaking and the wrong password message; tapping the field brings it back.
- "Remember password" is ticked by default. Protected documents get no
  thumbnail unless their password is remembered (privacy).
