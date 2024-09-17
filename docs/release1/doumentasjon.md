I denne delen av prosjektet har vi utviklet et system for en frisørsalong og opprettet en passende mappestruktur for organiseringen av prosjektet. Vi har implementert klasser og metoder for Treatment, PriceCalculator, TreatmentController, HairdresserApp og FileHandling. Videre har vi også laget et grensesnitt i FXML som håndterer funksjonaliteten for release 1. I tillegg har vi opprettet en testklasse for PriceCalculator.

Treatment.java er en klasse som oppretter et Treatment-objekt med navn, pris og varighet. Disse representerer ulike behandlinger som tilbys i frisørsalongen. Klassen er ment for å hente ut informasjon om behandlingene slik at man kan booke time og se detaljer om hver behandling. Klassen inneholder også getter- og setter-metoder samt en konstruktør.

PriceCalculator.java tar inn instanser av Treatment-klassen for å beregne totalprisen for en booking, enten det er for én behandling eller en kombinasjon av flere.

TreatmentController er en kontrollerklasse som i denne releasen, med hjelp av fxml, gjør det mulig å krysse av for tilgjengelige behandlinger og se den totale prisen.Klassen er koblet til fxml-elementene ved hjelp av @fxml-annotasjonen, som binder GUI-komonenter som sjekkboks til tilsvarende Java-variabler og betoder i kontrollerklassen. Klassen håndterer også noe filbehandling, som vi planlegger å forbedre og videreutvikle i kommende releaser.

HairdresserApp.java inneholder koden som gjør det mulig å kjøre applikasjonen.

FileHandling er en klasse som håndterer lagring av behandlingene og deres tilhørende priser til en fil.