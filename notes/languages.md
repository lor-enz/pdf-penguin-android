# Languages

- The app is in English and German (`values-de`). The German addresses
  the user with "du", as Android does. The device is "device" ("Gerät"),
  never "phone", as the app runs on tablets too. "Remember" is
  "speichern" (forgetting a password is "entfernen", since "Passwort
  vergessen" reads as "forgot password"), the recent files list is "Zuletzt
  geöffnet". Prefer short words over long compounds ("Document info" is
  "Eigenschaften", not "Dokumenteigenschaften"). The tagline refers to the app:
  "Nimm einfach die Blaue." The app name isn't translated (`translatable="false"`). The
  system's per-app language setting lists both languages
  (`generateLocaleConfig`, default locale in `res/resources.properties`).
  Add every new string to both files; lint fails on a missing translation.
