I del 2 av prosjektet har vi utvidet applikasjonen med funksjonalitet for å booke en time. Dette har blitt implementert ved å opprette en ny klasse, WeeklyTimeSlots, en ny kontroller, BookingController.java, samt en FXML-fil kalt booking.fxml. I tillegg har vi endret arkitekturen i prosjektet for bedre struktur og funksjonalitet.
Vi har også lagt til tester for TimeSlot-klassen og endret logikken i konstruktøren for TimeSlot for å gjøre det enklere å booke timer. Videre har vi implementert metoder for serialisering og deserialisering i JsonFileHandling, slik at vi kan hente og lagre informasjon om bookede timer til og fra en JSON-fil. Dette sikrer at bookede timer blir bevart og lastet inn på nytt ved oppstart av applikasjonen.

WeeklyTimeSlots
WeeklyTimeSlots er en hjelpeklasse som genererer en liste med gyldige tidspunkter for frisørsalongen for én uke. Klassen brukes i BookingController for å holde oversikt over alle tilgjengelige og bookede timer. Den har som formål å generere et sett med tidspunkter som kan vises til brukeren.

BookingController
BookingController er kontrollerklassen som håndterer interaksjonen mellom brukerens handlinger og systemets logikk for TimeSlots og WeeklyTimeSlots. Den benytter JsonFileHandling som en hjelpeklasse for å lese og skrive bookede timer til en JSON-fil. Hensikten med BookingController er å:
    1. Booke timer: Brukeren kan skrive inn ønsket tidspunkt i et TextField, og systemet vil sjekke om tiden er gyldig og ledig. Hvis timen blir booket, oppdateres et TextArea-felt med en melding som indikerer om bookingen var vellykket eller ikke.
    2. Oppdatere oversikt: Etter booking må brukeren trykke på en "Refresh TimeSlots"-knapp for å oppdatere oversikten i TextArea. Dette viser en oppdatert liste over ledige og bookede timer, basert på informasjonen i WeeklyTimeSlots og den lagrede JSON-filen.
    3. Hente bookede timer: Ved oppstart leses tidligere bookede timer fra JSON-filen, slik at brukeren får oppdatert oversikt over både nye og gamle bookinger.
