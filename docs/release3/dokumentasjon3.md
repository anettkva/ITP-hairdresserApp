I del 3 av prosjektet har vi gjort to store forandringer; utviklet et rest-api og utvidet funksjonaliteten for booking. 

UTVIDELSE AV FUNKSJONALITET
Utvidelensen vi valgte å gjøre var å koble sammen Treatments og Booking, slik at du booker de treatments som ble valgt på første side i appen. Dette medførte også at vi måtte implementere logikk for å booke flere sammenhengende Timeslots i BookingController. Vi har også endre Treatments-klassen slik at alle treatments har en duration på 60 min. I tillegg til å utvide fuksjonaliteten, har vi endret noe på formatet for booking av timer slik at det blir lettere å skrive inn ut i fra oversikten over ledige timer. 


REST-API
....
(beskrive nye klasser, format for forespørsler)


ARBEIDSVANER
Vi har fortsatt forholdt oss til Milestone funksjonen i git, med tilhørende issues. Vi har også hatt mer fokus på å skrive commit-meldinger på likt format med issuenummer og conventional commit. Store deler av release 3 har vi jobbet i par. Dette fordi vi det var vanskelig å jobbe parrallet med store deler av release 3, og derfor ville vi også unngå en del dødtid ved å vente på hverandre. Samtidig kunne vi bedre kodekvaliteten, ved å codereview hverandre sin kode kontinuerlig under prosessen. 


TESTER
Da vi har endret en del funksjonalitet, har vi samtidig måtte endre på en del tester. Dette har vi gjort under selve prosessen for å kunne teste den nye funksjonaliteten samtidig for å sikre en medle arbeidsflyt for mengden testing. 