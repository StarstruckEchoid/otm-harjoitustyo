# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on ylhäältä päin kuvattu seikkailupeli. Pelissä liikutaan ympäri pelikarttaa ja tutkitaan välillä myös luolastoja.

## Käyttäjät

Pelillä on vain yksi käyttäjärooli: käyttäjä.

## Käyttöliittymäluonnos

Aloitusvalikko:  
![UI luonnos 1](/uiLuonnos1.jpg)

Pelitilanne ja pelivalikko:  
![UI luonnos 2](/uiLuonnos2.jpg)

## Pelin tarjoama toiminnallisuus

### Päävalikossa

* Käyttäjän on mahdollisuus luoda itselleen yksi tai useampi pelaajatunnus.
* Jokainen pelaaja voi luoda itselleen yhden tai useamman pelin sekä ladata aikaisemmin luomiansa pelejä.
  * Sana 'peli' viittaa tässä hahmoon ja pelitilanteeseen. Pelitilanne taas koostuu useasta eri kentän tilasta.
* Pelaaja voi käynnistää jonkun omista peleistään, poistuen samalla päävalikosta pelitilaan.
* Käyttäjän on mahdollisuus lopettaa peli.

### Pelissä

* Käyttäjä pääsee jatkamaan peliä siitä tilanteesta, johon hän sen viimeksi jätti.
* Käyttäjä voi tallentaa pelitilanteensa.
* Käyttäjä voi ladata jonkun toisen pelitilanteen.
* Käyttäjä voi poistua pelistä päävalikkoon.

#### Ylhäältä päin kuvatuissa tasoissa

* Käyttäjä voi liikkua ylös, alas, vasemmalle ja oikealle.
* Käyttäjä voi kerätä pisteitä.
* Käyttäjä voi liikkua toisiin tasoihin siirtymällä tasolinkkien luokse.

#### Muissa tasoissa

* Käyttäjä voi siirtyä tasosta toiseen tasoon suoritettuaan tason.

## Jatkokehitysideoita

* Tasohyppelytasoja
* Pulmatasoja
* Vuoropohjaista taistelua
* Esineitä, kokemustasoja ja kauppoja
* Tehtäväloki
* Reaaliaikainen monipeli
