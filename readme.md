
# Hårsalong Booking App

  [Link til eclipse](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2024/gr2415/gr2415)

Dette prosjektet er en bookingside for en frisørsalong, hvor brukere kan velge én eller flere behandlinger, få et anslag på pris, se tilgjengelige timer og booke en time. Appen vil hjelpe salongen med å digitalisere bookingprosessen og gi kundene en enkel måte å velge tjenester og planlegge sine frisørbesøk på.

# Mål med prosjektet

- **Effektiv booking:** Brukerne skal kunne se ledige timer og booke frisørbehandlinger direkte fra appen.
- **Prisoversikt:** Systemet skal kalkulere totalpris basert på valgte behandlinger.
- **Lagring:** Data om behandlinger, priser og bookinger skal lagres og være lett tilgjengelig for både salongen og kundene.

---

# Framgangsmåte - kjøring av app

Prosjektet bruker maven til bygging og kjøring.

For å bygge, kjør `mvn install` eller `mvn clean install` fra **hairdresserapp**-mappa Man må altså kjøre `cd ui` før man kjører `mvn clean install`. Dette vil kjøre alle tester og kvalitetssjekker.

Prosjektet må kjøres fra **ui**-modulen, ved å først kjøre `cd ui` og så `mvn javafx:run`.
Merk at man må først ha kjørt `mvn install` på modulene som **ui**-modulen er avhengig av (pr. nå **core**), for at det skal gå. Når man kjører `mvn clean install` i **hairdresserapp**-mappa er dette gjort.

# Funksjonalitet Implementert i Del 1

- **Behandlingsvalg:** Brukerne kan velge én eller flere behandlinger fra en liste av frisørtjenester.
- **Prisberegning:** Totalprisen for valgte behandlinger beregnes og vises i sanntid.
- **Brukergrensesnitt:** Et grafisk brukergrensesnitt (GUI) utviklet med FXML, hvor brukeren kan se tilgjengelige behandlinger og få prisanslag.
- **Lagring:** Data om behandlinger og priser lagres i en fil for fremtidig bruk.
- **Testing:** En testklasse for å sikre korrekt prisberegning.

---

# Funksjonalitet Implementert i Del 2

- **Booking:** Brukerne kan booke en time ved å trykke på "Go to booking" - knappen, skrive inn dato og klokkeslett for når timen skal være, og trykke "Book" for å booke denne.
- **Vise ledige/bookede timer:** Ledige og bookede timer vises når brukeren trykker på knappen "Show possible booking times" - knappen.
- **Brukergrensesnitt:** Et grafisk brukergrensesnitt (GUI) utviklet med FXML, hvor brukeren kan booke time, men også se hvilke timer som er ledige og ikke.
- **Lagring:** Data for bookede timer lagres i en json-fil. Dette brukes etterpå til å vise ledige/bookede timer i et felt. Lagrede data forblir selv om appen lukkes og åpnes igjen. 
- **Testing:** Det er lagd tester for alle klassene i core og json, og én test som tester litt av funksjonaliteten for TreatmentController i ui.

---

# Forutsetninger

Før du kjører applikasjonen, må du sørge for at følgende er installert:

- Java 8 eller nyere
- Maven eller Gradle (avhengig av byggeverktøy)
- IDE som støtter Java og FXML, f.eks. IntelliJ IDEA eller Eclipse

---

# Struktur

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

- **`TimeSlot.java`:**  
  Representerer en time som kan bookes. Denne har et starttidspunkt, et endetidspunkt og en boolean som forteller om timen er blitt booket eller ikke.

- **`WeeklyTimeSlots.java`:**  
  Hånterer flere TimeSlots, ved å lage en liste med alle tilgjengelige TimeSlot's for en uke fram i tid.

- **`BookingDeserializer.java`:**  
  Hånterer hvordan data hentes ut fra fil med json.

- **`BookingSerializer.java`:**  
  Håndterer hvordan data skrives til fil med json.

- **`JsonFileHandling.java`:**  
  Håndterer lagring og henting av data om booking av timer fra .json fil.

- **`BookingController.java`:**  
  Kontrollerklassen som hånterer valg knyttet til booking av timer i brukergrensesnittet. Bruker kan skrive inn ønsket dato og tidspunkt, booke timen, og se ulike timer som er bookede fra før/ledige. Koblet til FXML-grensesnittet Booking.fxml via `@FXML`-annotasjoner.

- **FXML-filer:**  
  Brukt for å definere layout og GUI-elementer i appen.
"""

---

# Diagram - arkitektur

![Diagram](../docs/release2/diagram/diagram.png)

---

# Save the updated content to a README.md file

file_path_minimal = "/mnt/data/README_minimal.md"

with open(file_path_minimal, "w") as readme_file:
    readme_file.write(readme_content_minimal)

file_path_minimal
