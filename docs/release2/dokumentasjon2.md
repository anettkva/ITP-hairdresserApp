I del 2 av prosjektet har vi utvidet applikasjonen med funksjonalitet for å booke en time. Dette har blitt implementert ved å opprette en ny klasse, WeeklyTimeSlots, en ny kontroller, BookingController.java, samt en FXML-fil kalt booking.fxml. I tillegg har vi endret arkitekturen i prosjektet for bedre struktur og funksjonalitet.
Vi har også lagt til tester for TimeSlot-klassen og endret logikken i konstruktøren for TimeSlot for å gjøre det enklere å booke timer. Videre har vi implementert metoder for serialisering og deserialisering i JsonFileHandling, slik at vi kan hente og lagre informasjon om bookede timer til og fra en JSON-fil. Dette sikrer at bookede timer blir bevart og lastet inn på nytt ved oppstart av applikasjonen.

WEEKLYTIMESLOTS
WeeklyTimeSlots er en hjelpeklasse som genererer en liste med gyldige tidspunkter for frisørsalongen for én uke. Klassen brukes i BookingController for å holde oversikt over alle tilgjengelige og bookede timer. Den har som formål å generere et sett med tidspunkter som kan vises til brukeren.

BOOKINGKONTROLLER
BookingController er kontrollerklassen som håndterer interaksjonen mellom brukerens handlinger og systemets logikk for TimeSlots og WeeklyTimeSlots. Den benytter JsonFileHandling som en hjelpeklasse for å lese og skrive bookede timer til en JSON-fil. Hensikten med BookingController er å:
    1. Booke timer: Brukeren kan skrive inn ønsket tidspunkt i et TextField, og systemet vil sjekke om tiden er gyldig og ledig. Hvis timen blir booket, oppdateres et TextArea-felt med en melding som indikerer om bookingen var vellykket eller ikke.
    2. Oppdatere oversikt: Etter booking må brukeren trykke på en "Refresh TimeSlots"-knapp for å oppdatere oversikten i TextArea. Dette viser en oppdatert liste over ledige og bookede timer, basert på informasjonen i WeeklyTimeSlots og den lagrede JSON-filen.
    3. Hente bookede timer: Ved oppstart leses tidligere bookede timer fra JSON-filen, slik at brukeren får oppdatert oversikt over både nye og gamle bookinger.

BOOKINGSERIALIZER:
BookingSerializer som brukes til å konvertere et TimeSlot-objekt til en JSON-representasjon. Klassen arver fra Jackson-bibliotekets JsonSerializer<TimeSlot>, noe som betyr at den spesifikt er ment for å serialisere TimeSlot-objekter til JSON-format.

BOOKINGDESERIALIZER:
Denne klassen, BookingDeserializer, er en egendefinert deserializer som håndterer deserialisering av JSON-data til en liste med TimeSlot-objekter. Klassen arver fra Jackson-bibliotekets JsonDeserializer, og den er ment å brukes for å konvertere JSON-representasjonen av TimeSlots tilbake til Java-objekter.

JSONFILEHANDLING:
Denne klassen JsonFilehandling håndterer lesing, skriving og nullstilling av JSON-filer som inneholder en liste over TimeSlot-objekter brukt til booking. Klassen bruker Jackson-biblioteket for å håndtere JSON-operasjoner. Formålet med klassen er å håndtere skriving til fil og lasting fra fil, ved å bruke BookingSerializer og BookingDeserializer. Nå en ny time bookes så skrives denne til fil, og filen brukes for å vise hvilke timer som kan bookes i en oversikt av timer, samt å sørge for at man ikke får booket en time som allerede er booket. 


TESTING
Vi har prioritert høy dekningsgrad for testklassene TreatmentTest.java, TimeSlotTest.java, og WeeklyTimeSlotsTest.java. Disse testene dekker viktige metoder og logikk i klassene, og bidrar til å sikre at applikasjonen fungerer som forventet.
Vi har også testdekningsgrad på filhåndtering også, både i Json og i filhåndtering i core.
I tillegg jobber vi med tester i ui, men vi har valgt å prioritere logikk og metoder før disse testene. Dette gjør at vi kan sikre at kjernefunksjonaliteten er solid. Vi har kun én enkel test i ui, men planlegger å skrive mer dekkende og grundige tester videre i prosjektet.

Arbeidsvaner og Arbeidsflyt:
Som arbeidsvaner har vi forholdt oss til å bruke Milestone funksjonen på git, med tilhørende issues. Vi starter med å lage en milstone for den gjeldende innleveringen, så lager vi issues for hver oppgave vi må gjøre for å fullføre milestonen. På hvert issue forklarer vi hva som skal løses slik at det er oversiktlig for alle i gruppen hva oppgaven går ut på og hva som skal gjøres. Vi prøver å lage så mange issues som mulig for å oppfylle oppgavekriteriene, mens vi samtidig fortløpende legger til flere issues underveis. Når vi jobber inne i et issue lager vi commitmeldinger for endringen som blir gjort. Når vi pusher branchen med det gjeldend issuet, lager vi en merge request som en på medgruppa må se over og merge. Dette er for at vi skal passe på at kodekvaliteten er bra og unngå mest mulig individuelle feil. Denne arbeidsvanen med Git har gitt oss en felles arbeidsmetode som bidrar til en god arbeidsflyt. Vi begynte ettehvert å innføre co-authored-by metoden der vi sitter to og to sammen, for å få en mer effektiv og reflektert arbeidsprossess, og ikke minst sikre bedre kodekvalitet. Denne metoden bidrar også til arbeidsflyt ettersom at det å jobbe i par gir en bedre kontiunitet i arbeidsprossesen, når man kan hjelpe hverandre fortløpende.


Refleksjon rundt dokumentmetafor vs implisitt lagring:
Valget mellom dokumentmetafor og implisitt lagring handler om brukerens kontroll over lagringsprossesen. Dokumentmetaforer gir brukeren eksplisitt kontroll ved å måtte lagre manuelt, noe som kan gi mer trygghet men også øker risikoen for glemt lagring og datatap. Implisitt lagring, derimot, lagrer endringer automatisk uten at brukeren trenger å tenke på det, noe som gir en sømløs brukeropplevelse. For mange moderne applikasjoner, hvor enkelthet og brukervennlighet er viktig, er implisitt lagring det foretrukne valget for å unngå mennesklig feil og sikre kontinuerlig datalagring. Det er nettopp derfor vi har valgt å ha implisitt lagring som utgangspunkt i lagringsprossesen.
