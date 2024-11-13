
# Hårsalong Booking App

  [Link til eclipse](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2024/gr2415/gr2415)

Dette prosjektet er en bookingside for en frisørsalong, hvor brukere kan velge én eller flere behandlinger, få et anslag på pris, se tilgjengelige timer og booke en eller flere timer basert på behandlingen som er valgt. Appen vil hjelpe salongen med å digitalisere bookingprosessen og gi kundene en enkel måte å velge tjenester og planlegge sine frisørbesøk på.

# Mål med prosjektet

- **Effektiv booking:** Brukerne skal kunne se ledige timer og booke frisørbehandlinger direkte fra appen.
- **Prisoversikt:** Systemet skal kalkulere totalpris basert på valgte behandlinger.
- **Lagring:** Data om behandlinger, priser og bookinger skal lagres og være lett tilgjengelig for både salongen og kundene.

---

# Framgangsmåte - kjøring av app

Prosjektet bruker maven til bygging og kjøring.

For å bygge, kjør `mvn install` eller `mvn clean install` fra **hairdresserapp**-mappa. Man må altså gå inn i hairdresserapp ved å skrive `cd hairdresserapp` før man kjører `mvn clean install`. Dette vil kjøre alle tester og kvalitetssjekker.

For å kjøre appen med spring-boot, gå inn i **backend**-modulen ved å skrive `cd hairdresserapp/backend` i en terminal. Kjør kommandoen `mvn spring-boot:run` i denne terminalen. Da vil spring-boot applikasjonen starte opp. Deretter, for å kjøre og åpne den faktiske applikasjonen, åpne en ny terminal, og gå inn i **ui**-modulen ved å skrive `cd hairdresserapp/ui`. Herfra kjør kommandoen `mvn javafx:run`.

Merk at `mvn clean install` må kjøres på alle modulene før appen kjøres (fra hairdresserapp), for å ta hensyn til avhengighetene mellom dem.

# fremgangsmåte - vise diagrammene

For å kunne få opp diagrammene i VScode må du ha installeret to exstentions: Graphviz Interactive Preview og PlantUML Preview.
Du må så trykke deg inn i fila med koden for diagrammet.
så høyreklikker du og trykker på 'preview current UML code' og da vil diagrammet lastes opp i et preview-vindu.
Om dette ikke funker er det bilde av alle diagrammene i release.3 i mappen kalt for diagrams inne i picture-mappen.

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

# Funksjonalitet Implementert i Del 3

- **Valg av behandlinger:** Når brukeren trykker på en ønsket behandling, vises pris og oversikt over valgte behandlinger automatisk når checkboxen hukes av. Prisfelt og oversikt tilbakestilles også automatisk dersom checkboxen uncheckes.
- **Lagring til json-fil:** Treatments som velges lagres til fil med json, og blir i denne fila helt til appen lukkes.
- **Booking av flere behandlinger:** De to sidene med valg av behandlinger og booking har ikke tildigere vært avhengig av hverandre. Nå har vi lagt til funksjonalitet som gjør at det bookes et antall TimeSlots i forhold til antall behandlinger valgt. Hvis flere timer bookes samtidig, står disse timene som booket i oversikten etterpå.
- **Format på booking-felt:** Vi har gjort formatet på dato og tid som skal skrives av bruker inne i appen mer realistisk.
- **Velkommen-side:** En velkommen-side, som er den som åpnes når appen kjøres. Denne har tekst, som sier litt om salongen, og to knapper; en som tar brukeren til en tilbakemeldings-side, og en som tar brukeren til valg av behandlinger.
- **Tilbakemeldings-side:** En side der brukeren kan skrive tilbakemeldinger til salongen, og se tilbakemeldinger som er skrevet fra før.
- - **Testing:** Det er oppdatert tester ut fra ny funksjonalitet og REST-api. Tester for alle klasser i core og json, samt alle kontrollerene, untatt welcomeController.java, i ui testes til en viss grad

---

# Forutsetninger

Før du kjører applikasjonen, må du sørge for at følgende er installert:

- Java 8 eller nyere
- Maven eller Gradle (avhengig av byggeverktøy)
- IDE som støtter Java og FXML, f.eks. IntelliJ IDEA eller Eclipse

---

# Struktur

Prosjektet er organisert med følgende viktige klasser:

Core-modul:

- **`Treatment.java`:**  
  Representerer en behandling i frisørsalongen, med navn, pris og varighet. Klassen brukes til å vise detaljer om behandlinger og brukes i bookingprosessen.

- **`PriceCalculator.java`:**  
  Denne klassen tar inn én eller flere behandlinger (`Treatment`) og beregner totalprisen for en booking.

- **`TreatmentDeserializer.java`:**  
  Hånterer hvordan data i forhold til behandlinger som er valgt hentes ut fra fil med json.

- **`TreatmentSerializer.java`:**  
  Håndterer hvordan data i forhold til behandliger som velges skrives til fil med json.

- **`TreatmentFileHandling.java`:**  
  Håndterer lagring og henting av data om behandlinger og priser fra en fil.

- **`TimeSlot.java`:**  
  Representerer en time som kan bookes. Denne har et starttidspunkt, et endetidspunkt og en boolean som forteller om timen er blitt booket eller ikke.

- **`WeeklyTimeSlots.java`:**  
  Hånterer flere TimeSlots, ved å lage en liste med alle tilgjengelige TimeSlot's for en uke fram i tid.

- **`BookingDeserializer.java`:**  
  Hånterer hvordan data i forhold til en booking hentes ut fra fil med json.

- **`BookingSerializer.java`:**  
  Håndterer hvordan data om en booking som bookes skrives til fil med json.

- **`JsonFileHandling.java`:**  
  Håndterer lagring og henting av data om booking av timer fra .json fil.

Ui-modul:

- **`HairdresserApp.java`:**  
  Hovedklassen som starter applikasjonen.

- **`TreatmentController.java`:**  
  Kontrollerklassen som håndterer valgene av behandlinger i brukergrensesnittet. Brukeren kan krysse av for tilgjengelige behandlinger, og totalpris beregnes i sanntid. Koblet til FXML-grensesnittet via `@FXML`-annotasjoner.

- **`FrontTreatmentService.java`:**  
  Klasse som er ansvarlig for sending av HTTP-forespørsler angående valg av behandlinger til **backend**-modul. Disse forespørslene håndteres av **backend** fra RestTreatmentController.java

- **`BookingController.java`:**  
  Kontrollerklassen som hånterer valg knyttet til booking av timer i brukergrensesnittet. Bruker kan skrive inn ønsket dato og tidspunkt, booke timen, og se ulike timer som er bookede fra før/ledige. Koblet til FXML-grensesnittet Booking.fxml via `@FXML`-annotasjoner. Kommuniserer med **backend**-modulen via FrontBookingService.java.

- **`FrontBookingService.java`:**  
  Klasse som er ansvarlig for sending av HTTP-forespørsler angående booking til **backend**-modul. Disse forespørslene håndteres av **backend** fra RestBookingController.java

- **`ReviewController.java`:**  
  Kontrollerklassen som hånterer skriving av reviews, og å vise alle reviews. Kommuniserer med **backend**-modulen via FrontReviewService.java.


- **`FrontReviewService.java`:**  
  Klasse som er ansvarlig for sending av HTTP-forespørsler angående reviews til **backend**-modul. Disse forespørslene håndteres av **backend** fra RestReviewController.java

- **`WelcomeController.java`:**  
  Kontrollerklassen som håndterer visning av velkommen-siden, og håndterer trykking av knapper for å komme til booking og reviews.

- **FXML-filer:**  
  Brukt for å definere layout og GUI-elementer i appen.

Backend-modul:

- **`RestBookingController.java`:**  
  Kontrollerklasse som tar imot HTTP-forspørsler, og bruker BookingService.java for å håndtere disse forespørslene.

- **`BookingService.java`:**  
  Klasse som håndterer skriving og lesing av TimeSlot-objekter til og fra fil, og derfmed også selve bookingen av TimeSlot-objektene i TimeSlot.java.

- **`RestTreatmentController.java`:**  
  Kontrollerklasse som tar imot HTTP-forspørsler angående behandlinger som er valgt, og bruker TreatmentService.java for å håndtere disse forespørslene.

- **`TreatmentService.java`:**  
  Klasse som håndterer skriving og lesing av Treatment-objekter til og fra fil med TreatmentFilehandling.java, og beregner pris på de ulike valgte behandlingene ved hjelp av PriceCalculator.java.

- **`RestReviewController.java`:**  
  Kontrollerklasse som tar imot HTTP-forspørsler angående reviews, og bruker ReviewService.java for å håndtere disse forespørslene.

- **`ReviewService.java`:**  
  Klasse som håndterer skriving av reviews til fil og lesing av reviews fra fil med ReviewFilehandling.java.

---

# Diagram - arkitektur

![Diagram](../docs/release2/diagram/diagram.png)

---

# Save the updated content to a README.md file

file_path_minimal = "/mnt/data/README_minimal.md"

with open(file_path_minimal, "w") as readme_file:
    readme_file.write(readme_content_minimal)

file_path_minimal
