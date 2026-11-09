# Product philosophy

- Simple means low friction, not few features. A feature belongs in the
  app if it adds value without adding anything the user has to notice,
  learn or decide. Example: thumbnails in the recent files list help a
  lot and cost the user nothing.
- Short feedback goes in a snackbar, never a toast: a copy was saved, a
  file couldn't be shared, no app opens a link, Undo after removing.
- Prefer sensible defaults over settings. Add a setting or customization
  only when no single default can work for most users.
- Before adding a feature, ask: does the user have to do or understand
  anything new? If yes, look for a way that works without it.
- Use standard Android and Compose behavior (e.g. `animateItem()` for list
  changes) instead of custom implementations, so the app feels native.
