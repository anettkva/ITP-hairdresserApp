
# Hårsalong Booking App

  [Link til eclipse](https://che.stud.ntnu.no/#https://gitlab.stud.idi.ntnu.no/it1901/groups-2024/gr2415/gr2415)

HairdresserApp er en app som er utviklet i faget 'Informatikk i prosjektarbeid 1' ved NTNU. Produktet er en bookingside hos en fiktiv frisørsalong. Hovedfokuset har blitt lagt i funksjonalitet og testing, der ulike krav for prosjektet har blitt gitt iterasjonsvis. Appen lar brukere velge ønskende behandlinger, gir et prisoverslag i sanntid, viser ledige timer og booke time. I tillegg kan brukerne skrive og lese anmeldelser. Appen skal hjelpe frisørsalongen med digitalisering, og sikre en mer flytende brukeropplevelse for kunder.


# Mål med applikasjonen

- **Effektiv booking:** Brukerne skal kunne se ledige timer og booke frisørbehandlinger direkte fra appen.
- **Prisoversikt:** Systemet skal kalkulere totalpris basert på valgte behandlinger.
- **Lagring:** Data om behandlinger, priser og bookinger skal lagres og være lett tilgjengelig for både salongen og kundene.
- **Tilbakemeldinger:** Brukerne skal kunne se tilbakemeldinger som er skrevet for appen, og også skrive tilbakemeldinger selv.

---

# Hvordan kjøre applikasjonen
Kjøring via terminal (utviklingsmodus)
  1. **Kompilering**:
     ```
     cd hairdresserapp
     mvn clean install
     ```

  2. **Start backend** (Spring Boot) i en ny terminal:
     ```
     cd backend
     mvn spring-boot:run
     ```

  3. **Start frontend** (JavaFX):
     ```
     cd ../ui
     mvn javafx:run
     ```

**Merk** Backend må kjøre samtidig som frontend for full funksjonalitet


# Hvordan bygge og distibruere appen (shippable product)

Kjør følgende kommandoer i terminalen:
  ```
  cd hairdresserapp
  mvn clean install
  mvn clean package

  jpackage --input ui/target \
            --dest ui/target/installer \
            --name HairdresserApp \
            --main-class ui.HairdresserApp \
            --main-jar modules-ui-0.0.1-SNAPSHOT.jar \
            --type dmg \
            --icon HairdresserApp.icns \
            --module-path ui/target/lib \
            --add-modules javafx.controls,javafx.fxml,java.net.http
  ```
**Merk:** Tilpass parameteren --type basert på ditt operativsystem (dmg for Mac, exe eller msi for Windows, og deb eller rpm for Linux).

For å kjøre den distibruerte versjonen må backend kjøre parallelt:
```
  cd backend
  mvn spring-boot:run
```

# Hvordan vise diagrammer i VSCode:

  - Installer utvidelsen **Graphviz Interactive Preview** og **PlantUML Preview**
  - Åpne UML-fil, høyreklikk og velg **Preview current UML code**
  - Alternativt ligger diagrammene som bilder i mappen pictured/diagrams under realease.3



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
- **Testing:** Det er oppdatert tester ut fra ny funksjonalitet og REST-api. Tester for alle klasser i core og json, samt alle kontrollerene, untatt welcomeController.java, i ui testes til en viss grad. I backend tester vi service-klassene. 
- **Brukervennlighet:** Vi har gjort appen mer brukervennlig ved å legge inn mer feedback når brukeren klikker seg rundt. I tillegg har vi lagt til en mer detaljert beskrivelse av hvordan man skal booke en time.

---

# Forutsetninger

Følgende programvare må være installert for å kjøre prosjektet:

- Java 8 eller nyere
- Maven
- IDE: IntelliJ IDEA, Eclipse eller lignende som støtter Java og FXML

---

# Teknologier og rammeverk brukt
- **JavaFX og FXML** for brukergrensesnitt
- **Spring Boot** for backend og REST API
- **Maven** for bygge- og pakkesystem
- **JSON** for persistent lagring
- **JUnit** for testing

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

![Klassediagram](../docs/release3/pictures/diagrams/Klassediagram.png)

---

# Save the updated content to a README.md file

file_path_minimal = "/mnt/data/README_minimal.md"

with open(file_path_minimal, "w") as readme_file:
    readme_file.write(readme_content_minimal)

file_path_minimal
