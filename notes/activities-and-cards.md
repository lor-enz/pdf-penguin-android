# Activities and cards

- Home (`MainActivity`) and viewer are separate activities, each with its
  own view model. `MainActivity` keeps its name because launcher shortcuts
  point to it. Documents from the app's own list and picker open in
  `ViewerActivity` in the main task; documents from other apps open in
  `ExternalViewerActivity` (`documentLaunchMode="always"`), each with a card
  of its own named after the file. Back closes such a card and returns to
  the app that opened it. A document is open in one viewer only: opening it
  again, from anywhere, switches to the viewer that has it (forms in two
  viewers would overwrite each other).
- The store records which entry each task shows, so cleanup spares every
  open document, also after process death, and a card can reopen its copy
  after its intent's permission is gone. Returning to a card counts as
  opening the document. An app update or force-stop removes the cards.
- Back is left to the system wherever the app would just close the
  activity, so the back gesture's preview (predictive back) shows what's
  behind: Home behind a viewer, the other app behind a card of its own
  (`autoRemoveFromRecents` removes the card, no `finishAndRemoveTask`
  needed). A `BackHandler` is only enabled while it has something to close
  (search, presentation mode); one that's always on hides the preview.
  `enableOnBackInvokedCallback` is set, as only Android 16 opts in through
  `targetSdk`. On Android 14 the preview is behind a developer option
  ("Predictive back animations"), and it needs gesture navigation.
