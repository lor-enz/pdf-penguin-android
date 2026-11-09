# PDF reading

- `androidx.pdf` reads no metadata, so `PdfMetadataReader` reads the info
  dictionary and the first page's size itself, through `PdfFile`: xref
  tables and streams, updates, object streams, Flate with PNG predictors,
  and a scan for objects when the offsets are wrong. `PdfFile` also walks
  the page tree (with what pages inherit), the outline and named
  destinations, for the metadata reader and the merger alike. `PdfSecurity`
  decrypts the standard encryption (RC4, AES-128, AES-256). Files with only
  a permissions password open without asking and decrypt with the empty
  password, so they show everything.
- `scripts/test/make-metadata-fixtures.py` writes the committed PDFs for
  `PdfMetadataReaderTest` (`src/test/resources/metadata/`), one per way a
  PDF can store its metadata. Run it again after changing it.
