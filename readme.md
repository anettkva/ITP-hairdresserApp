
# Hårsalong Booking App

- ![Link til eclipse](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2024/gr2415/gr2415)

Dette prosjektet er en bookingside for en frisørsalong, hvor brukere kan velge én eller flere behandlinger, få et anslag på pris, se tilgjengelige timer og booke en time. Appen vil hjelpe salongen med å digitalisere bookingprosessen og gi kundene en enkel måte å velge tjenester og planlegge sine frisørbesøk på.

### Mål med prosjektet

- **Effektiv booking:** Brukerne skal kunne se ledige timer og booke frisørbehandlinger direkte fra appen.
- **Prisoversikt:** Systemet skal kalkulere totalpris basert på valgte behandlinger.
- **Lagring:** Data om behandlinger, priser og bookinger skal lagres og være lett tilgjengelig for både salongen og kundene.

---

### Funksjonalitet
#### Implementert i Del 1
- **Behandlingsvalg:** Brukerne kan velge én eller flere behandlinger fra en liste av frisørtjenester.
- **Prisberegning:** Totalprisen for valgte behandlinger beregnes og vises i sanntid.
- **Brukergrensesnitt:** Et grafisk brukergrensesnitt (GUI) utviklet med FXML, hvor brukeren kan se tilgjengelige behandlinger og få prisanslag.
- **Lagring:** Data om behandlinger og priser lagres i en fil for fremtidig bruk.
- **Testing:** En testklasse for å sikre korrekt prisberegning.

---

### Forutsetninger

Før du kjører applikasjonen, må du sørge for at følgende er installert:

- Java 8 eller nyere
- Maven eller Gradle (avhengig av byggeverktøy)
- IDE som støtter Java og FXML, f.eks. IntelliJ IDEA eller Eclipse

---

### Struktur

Prosjektet er organisert med følgende viktige klasser:

- **`Treatment.java`:**  
  Representerer en behandling i frisørsalongen, med navn, pris og varighet. Klassen brukes til å vise detaljer om behandlinger og brukes i bookingprosessen.

- **`PriceCalculator.java`:**  
  Denne klassen tar inn én eller flere behandlinger (`Treatment`) og beregner totalprisen for en booking.

- **`TreatmentController.java`:**  
  Kontrollerklassen som håndterer valgene av behandlinger i brukergrensesnittet. Brukeren kan krysse av for tilgjengelige behandlinger, og totalpris beregnes i sanntid. Koblet til FXML-grensesnittet via `@FXML`-annotasjoner.

- **`HairdresserApp.java`:**  
  Hovedklassen som starter applikasjonen.

- **`FileHandling.java`:**  
  Håndterer lagring og henting av data om behandlinger og priser fra en fil.

- **FXML-filer:**  
  Brukt for å definere layout og GUI-elementer i appen.
"""

# Save the updated content to a README.md file
file_path_minimal = "/mnt/data/README_minimal.md"

with open(file_path_minimal, "w") as readme_file:
    readme_file.write(readme_content_minimal)

file_path_minimal

