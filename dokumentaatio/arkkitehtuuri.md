# Arkkitehtuurikuvaus

Sovelluksessa on neljä pakkausta:
* userinterface: käyttöliittymä
* domain: sovelluslogiikka
* dataaccessobject: levytapahtumat
* utilityclasses: apuluokat

Userinterface-pakkaus vuorovaikuttaa domainin ja dataaccessobjectin kanssa. Domain ja dataaccessobject eivät vuorovaikuta toistensa kanssa. Utilityclasses sisältää mihin vain pakkaukseen liittyviä apuluokkia, ja voi vuorovaikuttaa minkä tahansa pakkauksen kanssa.

Pakkauskaavio:
![Pakkauskaavio](/dokumentaatio/pakkauskaavio.png)

Luokkakaavio:
![Luokkakaavio](/dokumentaatio/luokkakaavio.png)

Sekvenssikaavio: pelaaja painaa nappia ja liikkuu ylös.
![Sekvenssikaavio](/dokumentaatio/Move_Player_Up.png)
