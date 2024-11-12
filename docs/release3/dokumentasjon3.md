I del 3 av prosjektet har vi gjort store forandringer; utviklet et rest-api og utvidet mye funksjonalitet i appen.

**UTVIDELSE AV FUNKSJONALITET**
En utvidelse vi valgte å gjøre var å koble sammen Treatments og Booking, slik at du booker de behandlingene som ble valgt på første side i appen. Dette medførte også at vi måtte implementere logikk for å booke flere sammenhengende Timeslots i BookingController. Vi har også endre Treatments-klassen slik at alle treatments har en duration på 60 min. I tillegg til å utvide fuksjonaliteten, har vi endret noe på formatet for booking av timer slik at det blir lettere å skrive inn ut i fra oversikten over ledige timer.

En annen utvidelse vi har gjort er vi har gjort at når en behandling hukes av inne i appen, kommer prisestimat og oversikten opp automatisk. På samme måte fjernes dette dersom man unchecker samme behandling. Dette ved at addTreatment-metoden og deleteTreatment-metoden kaller på handleCalculatePrice og handleShowOverview.

Vi har også lagt til en velkommen side for appen, for å gjøre den mer brukervennlig. Fra denne kan man enten gå til valg av behandlinger for å booke en time, eller så kan man gå inn på en side for tilbakemeldinger, som vi også har lagt til. På denne siden kan brukeren skrive en tilbakemelding om salongen, og/eller se tilbakemeldinger som er skrevet fra før av andre brukere. Til dette bruker vi filbehandling med en .txt-fil, der vi har noen tilbakemeldinger liggende fra før av.

**Her kommer en oversikt over nye klasser vi har implementert og hva disse gjør:**

**`TreatmentFileHandling.java`:**  
  Endret tidligere filhåndtering for behandlinger til å ha persistens med json. Nå brukes en json fil til å holde styr på de valgte behandlingene, som brukes av `BookingService` i **backend**. Klassen bruker TreatmentDeserializer- og TreatmentSerializer-klassene for filbehandlingen.

**`TreatmentDeserializer.java`:**  
  Klasse som hånterer hvordan data i forhold til behandlinger som er valgt hentes ut fra fil med json. Dette er en egendefinert deserializer som håndterer deserialisering av JSON-data til en liste med Treatment-objekter. Klassen arver fra Jackson-bibliotekets JsonDeserializer, og den er ment å brukes for å konvertere JSON-representasjonen av Treatments tilbake til Java-objekter.

**`TreatmentSerializer.java`:**  
  Klasse som håndterer hvordan data i forhold til behandliger som velges skrives til fil med json. Klasen brukes til å konvertere et Treatment-objekt til en JSON-representasjon. Klassen arver fra Jackson-bibliotekets JsonSerializer<Treatment>, noe som betyr at den spesifikt er ment for å serialisere TimeSlot-objekter til JSON-format.

**`FrontTreatmentService.java`:**  
  Klasse som brukes av TreatmentController i **ui** og er ansvarlig for sending av HTTP-forespørsler angående valg av behandlinger til **backend**-modul. Disse forespørslene håndteres av **backend** fra RestTreatmentController.java

**`RestTreatmentController.java`:**  
  Kontrollerklasse som tar imot HTTP-forspørsler angående behandlinger som er valgt fra FrontTreatmentService, og bruker TreatmentService for å håndtere disse forespørslene.

**`TreatmentService.java`:**  
  Klasse som håndterer skriving og lesing av Treatment-objekter til og fra fil med TreatmentFilehandling, og beregner pris på de ulike valgte behandlingene ved hjelp av PriceCalculator.

**`FrontBookingService.java`:**  
  Klasse som brukes av BookingController i **ui** og er ansvarlig for sending av HTTP-forespørsler angående booking til **backend**-modul. Disse forespørslene håndteres av **backend** fra RestBookingController.java

**`RestBookingController.java`:**  
  Kontrollerklasse som tar imot HTTP-forspørsler fra FrontBookingService i **ui**, og bruker BookingService i **backend** for å håndtere disse forespørslene.

**`BookingService.java`:**  
  Klasse som håndterer skriving og lesing av TimeSlot-objekter til og fra fil, og dermed også selve bookingen av TimeSlot-objekter ved hjelp av TimeSlot-klassen. Klassen bruker en instans av JsonFilehandling i for å legge til bookede TimeSlot objekter til fil.

**`ReviewController.java`:**  
  Kontrollerklassen som hånterer skriving av reviews, og å vise alle reviews. Kommuniserer med **backend**-modulen via FrontReviewService.

**`FrontReviewService.java`:**  
  Klassen som er ansvarlig for sending av HTTP-forespørsler angående reviews til **backend**-modul. Disse forespørslene håndteres av **backend** fra RestReviewController.

**`RestReviewController.java`:**  
  Kontrollerklasse som tar imot HTTP-forspørsler angående reviews, og bruker ReviewService for å håndtere disse forespørslene.

**`ReviewService.java`:**  
  Klasse som håndterer skriving av reviews til fil og lesing av reviews fra fil med ReviewFilehandling.

**`WelcomeController.java`:**  
  Kontrollerklassen som håndterer visning av velkommen-siden, og håndterer trykking av knapper for å komme til booking og reviews.

**REST-API**
Vi har i denne delen av prosjektet bygget et rest-api rundt kjernelogikken. Vi har dermed måttet gjort endringer i ui-modulens kontrollere for at prosjektet skal kunne kjøres med dette rest-apiet som vi har laget en egen **backend**-modul til.

I ui har vi, for alle fxml-filer i prosjektet, én FrontService-klasse i tillegg til kontrolleren. Kontrollerne bruker nå sin respektive FrontService-klasse til å sende HTTP-forespørsler til **backend**-modulen. Ellers er logikken i kontrollerne ganske lik som før, bare at de ikke håndterer kommunikasjon med **core**-modulen som tidligere. Nå går alt gjennom backend.

Ut fra hva slags logikk vi har, er det ulike forespørsler som er nødt til å støttes. Her er er oversikt over forespørsler knyttet til de ulike kontrollerene:

Forespørsler angående behandlinger:

- GET-forespørsel for å få tak i valgte behandlinger, med metoden getChosenTreatments, som bruker TreatmentFilehandling.
- POST-forespørsel for å legge til (velge) en behandling, med metoden addTreatment, som bruker TreatmentFilehandling. 
- DELETE-forespørsel for å slette en spesifikk allerede valgt behandling, med metoden deleteTreatment, som bruker TreatmentFilehandling.
- POST-forespørsel for å endre på / regne ut totalpris, med metoden calculateTotalPrice, som bruker PriceCalculator.

Forespørsler angående booking:

- GET-forespørsel for å få tak i timer som allerede er booket, med metoden getBookedSlots, som bruker JsonFilehandling.
- GET-forespørsel for å få tak i alle timer (for en uke), med metoden getAllTimeSlots, som bruker WeeklyTimeSlots.
- POST-forespørsel for å booke en time, med metoden bookSlot, som bruker TimeSlot og JsonFilehandling.

Forespørsler angående tilbakemeldinger:

- GET-forespørsel for å få tak i reviews som allerede er skrevet, med metoden getReviews, som bruker ReviewFilehandling.
- POST-forespørsel for å legge til en tilbakemelding, med metoden addReview, som bruker ReviewFilehandling.

Når backend mottar disse forespørslene i sine rest-kontrollere, bruker kontrollerene igjen sin respektive service klasse som ligger i **backend**. Service-klassen bruker logikken i core for å returnere/gjøre det funksjonen er ment å gjøre. I terminalen for spring-boot har vi lagt til logging, slik at man ser når de ulike forespørslene sendes når man gjør ulike ting i appen.

**ARBEIDSVANER**
Vi har fortsatt forholdt oss til Milestone funksjonen i git, med tilhørende issues. Vi har også hatt mer fokus på å skrive commit-meldinger på likt format med issuenummer og conventional commit. Store deler av release 3 har vi jobbet i par. Dette fordi vi det var vanskelig å jobbe parrallet med store deler av release 3, og derfor ville vi også unngå en del dødtid ved å vente på hverandre. Samtidig kunne vi bedre kodekvaliteten, ved å codereview hverandre sin kode kontinuerlig under prosessen.

**TESTER**
Da vi har endret en del funksjonalitet, har vi samtidig måtte endre på en del tester. Dette har vi gjort under selve prosessen for å kunne teste den nye funksjonaliteten samtidig for å sikre en medle arbeidsflyt for mengden testing.

Vi har i tillegg til å endre en del på tester vi hadde fra før, laget nye tester for:

- FrontReviewService
- FrontTreatmentService
- ReviewControllerTest
- TreatmentControllerTest
- FrontBookingService
- BookingController
  
Vi har også en klasse, JavaFxTestInitializer som hjelper med å lage javafx-felter, slik at man kan mocke disse.