# Document info

- A document's title (from its metadata) is shown above its file name in
  the recent files list, the viewer's top bar and the sheets' headings;
  without one, only the file name shows, as before. Every title is shown,
  even ones that look generated ("Microsoft Word - …") or repeat the file
  name: the app doesn't judge a document's content, and the file name is
  always there too. The card in the app switcher, launcher shortcuts, the
  password screen, Save a copy and Share keep the file name. The viewer
  reads the title while the document opens (from the original, not the
  filled-in version), so the top bar doesn't change; entries store it
  (`title`), a protected one's only while its password is remembered (as
  with thumbnails). Entries saved before titles were stored
  (`isTitleRead` false) get theirs when Home starts.
- "Document info", in the viewer's and the recent entries' sheets, opens a
  sheet of its own with author, subject, pages, page size, file size,
  created, modified and the app it was made with; its heading shows the
  title. Every field shows; a missing one says "Not set", or "Protected by
  password" when it can't be read without the password, both grey and
  italic, set apart from the document's data. Files that need a password
  show their title and such in the viewer, and in the recents sheet only
  when the password is remembered (as with thumbnails); otherwise only pages
  and sizes are known. The sheet appears once the file is read: a
  `ModalBottomSheet` whose content grows later keeps its old height. Page
  sizes are named (A4, Letter, …) or given in mm, or inches in the US.
