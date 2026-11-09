# Create

- Images shared to the app (`SEND`/`SEND_MULTIPLE` with `image/*`) are
  made into a PDF (`create/`), one image per page in the order shared, no
  reorder screen. It opens in a card of its own and is kept like a copy
  of a file from another app (cleanup included). A snackbar says "PDF
  created" (and how many images couldn't be read) with Save, which opens
  the system's Save to dialog, as the PDF exists only in the app until
  then. Each page has its image's proportions and A4's width: the viewer
  fits the widest page, so with a fixed long side a tall screenshot showed
  narrow beside photos. `ImagePdfWriter` writes the PDF itself: JPEGs are
  embedded as they are (no quality loss, small files), turned by the
  page's matrix as EXIF says. Other formats are decoded (at most 4096 px)
  and PNG, GIF and BMP stay lossless (Flate), others (HEIC, WebP) become
  JPEGs; transparency becomes white. Images that can't be read are left
  out; with none left, the viewer shows "Couldn't make a PDF". Two PDFs of
  the same images differ (creation date), so each gets an entry.
- Several PDFs shared to the app (`SEND_MULTIPLE` with `application/pdf`;
  one is simply opened) are merged (`create/PdfMerger.kt`) in the order
  shared, no reorder screen, and kept and offered to save like a PDF of
  images ("Merged <date>.pdf", in English in every language: German's
  "Zusammengeführt" sounded terrible). `PdfMerger` copies each page with
  everything it uses, streams as they are (no quality lost), on top of
  `PdfFile`, which the metadata reader uses too; PdfBox was too large and
  slow, and rendering pages to images would lose text, links and forms.
  Named links become explicit ones, so names can't collide, and links with
  a bare `/Dest` get a "go to" action, so they work in the merged file
  (unlike in the original, see links in
  [Viewer](viewer.md)). Form fields are merged, a
  name an earlier file took getting a number (`name_2`), or equal forms
  would share their values; layers keep their state. The outline gets an
  entry per file, named by its file name without `.pdf`: the user knows
  the files by those, and titles are often generic ("untitled",
  "Microsoft Word - …"). Tags for screen readers, page labels and
  attachments are left out. A merge is all or nothing: if a file is
  damaged, or protected and no entry with the same content (hash) has its
  password remembered, nothing is made and the viewer names it (damaged
  ones first, no password helps them). Asking for each password would be a
  chore; opening the file once remembers it. A 200 MB file merges in a few
  seconds, as streams are copied in chunks.
- `scripts/test/make-merge-fixtures.py` writes the committed PDFs for
  `PdfMergerTest`
  (`src/test/resources/merge/`): what a merge has to carry over (inherited
  page attributes, named links, forms, layers). To check merged files
  beyond the tests, `qpdf --check` them and compare pages rendered by
  `pdftoppm` with the sources'.
- To share several images, push them to Download, select them in the Files
  app (long press the first, tap the others) and share from there: `am`
  can't send `SEND_MULTIPLE`. Files keeps the selection after sharing.
