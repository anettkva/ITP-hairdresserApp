
# informasjon om appen HairDresserApp

Appen vi har laget et et hjelpemiddel for å se priser på ulike behandlinger, samt se ledige timer basert på hvilke behandlinger man har valgt, og booke en time.

Vi har implementert logikken som skal til for at brukeren skal kunne velge hvilke behandlinger som ønskes, se en estimert pris basert på disse behandligene, og få en oversikt over behandlingene som er valgt. 

I tillegg kan brukeren booke en (eller flere) timer basert på behandlingene som er valgt ved å gå inn på en booking-side. Der kan hen skrive inn klokkeslett og dato som ønskes på et visst format. Brukeren kan også se en oversikt over ledige/bookede timer slik at hen kan booke en time i forhold til dette. Dersom brukeren skriver inn en time som allerede er booket, får brukeren informasjon om at en annen time må velges, fordi den ønskede timen er opptatt. Dersom bookingen er suksessfull, får brukeren beskjed om at timen ble booket.

I tillegg har vi en tilbakemeldings-side hvor brukeren kan se en oversikt over ulike tilbakemeldinger som er skrevet til salongen, samt skrive en tilbakemelding selv.

Vi har enn så brukt filhåndtering med json for å laste valgte behandlinger til fil, for så å vise en utskrift til skjerm med oversikt over de valgte behandlingenes pris.
Vi har også brukt filhåndtering med json for behandlingen av ulike bookinger, der bookede timer skrives til fil, slik at fila blir en oversikt over de timene som er booket.
For tilbakemeldingene har vi brukt filhåndtering med en .txt fil.

Appen bruker en backend-modul, som det sendes HTTP-forspørsler til fra ui-modulen.

# Brukerhistorie som forklarer appens bruk

Linea skal i en bursdagsfeiring til en venninne i helga, og i den anledning ønsker hun å klippe og style håret. Hun har hørt om en frisør som en venninne pleier å dra til, og vil sjekke om de har ledige timer, samt om de har priser som passer budsjettet hennes. Derfor besøker hun appen til frisøren, og velger ønsket behandling, og får da se pris, og en ledig time som passer for henne. Hun er fornøyd med prisestimatet, og ønsker å booke en time hos denne salongen. Hun booker en time basert på oversikten over ledige/bookede timer.
Linea blir kjempefornøyd med resultatet, og ønsker å skrive en tilbakemelding til frisørsalongen. Da går hun inn i appen, og skriver en tilbakemelding basert på det hun opplevde, slik at andre også kan se dette.

# Illustrerende skjermbilde

Illustrerende bilder av sidene i appen:
<div style="display: flex; flex-wrap: wrap; gap: 10px;">
  <img src="docs/release3/pictures/welcome.png" alt="Velkommen-side" width="300"/>
  <img src="docs/release3/pictures/treatments.png" alt="Behandlingsvalg-side" width="300"/>
  <img src="docs/release3/pictures/booking.png" alt="Booking-side" width="300"/>
  <img src="docs/release3/pictures/reviews.png" alt="Reviews-side" width="300"/>
</div>
