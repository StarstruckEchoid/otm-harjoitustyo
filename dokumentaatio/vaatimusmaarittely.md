# Vaatimusmäärittely

## Sovelluksen tarkoitus

Sovellus on ylhäältä päin kuvattu seikkailupeli. Pelissä liikutaan tasosta toiseen päihittäen vihollisia ja keräten samalla pisteitä.

## Käyttäjät

Pelillä on vain yksi käyttäjärooli: käyttäjä.

## Käyttöliittymäluonnos

Aloitusvalikko:  
![UI luonnos 1](/dokumentaatio/kuvat/uiLuonnos1.jpg)

Pelitilanne ja pelivalikko:  
![UI luonnos 2](/dokumentaatio/kuvat/uiLuonnos2.jpg)

## Pelin tarjoama toiminnallisuus

### Ennen pelinäkymään pääsemistä

* Käyttäjän on mahdollisuus luoda itselleen yksi tai useampi käyttäjätunnus.
* Jokainen käyttäjä voi luoda itselleen yhden tai useamman pelaajahahmon sekä ladata aikaisemmin luomiansa pelaajahahmoja.
* Käyttäjä voi ladata pelaajahahmojensa pelitilanteita.
* Käyttäjän on mahdollista päästä pelinäkymään.
* Käyttäjän on mahdollista lopettaa ohjelman suorittaminen.

### Pelinäkymässä
* Käyttäjä pääsee jatkamaan peliä siitä tilanteesta, johon hän sen viimeksi jätti.
* Käyttäjä voi liikkua ylös, alas, vasemmalle ja oikealle.
* Käyttäjä voi kerätä pisteitä.
* Käyttäjä voi taistella vihollisia vastaan. Sekä pelaajahahmo että viholliset ottavat toisiltaan vahinkoa ja voivat kuolla.
* Käyttäjä voi liikkua toisiin tasoihin siirtymällä tasolinkkien luokse.
* Käyttäjä voi vuorovaikuttaa maastoon ripoteltujen interaktiivisten esineiden, kuten ovien ja vipujen kanssa.
* Käyttäjä pääsee taukovalikkoon.

### Taukovalikossa
* Pelaaja pääsee takaisin pelinäkymään ja pelinäkymän pelitilanne on sama, kuin mihin se jätettiin.
* Pelaaja voi tallentaa pelitilanteensa.
* Pelaaja voi ladata aikaisemman pelitilanteen.
* Pelaaja pääse päävalikkoon.

## Lähitulevaisuuden jatkokehityskohteet
DungeonCrawlerin lähitulevaisuudessa on muutama ilmiselvä kehityskohde. Jos DungeonCrawlerin kehitys jatkuu kurssin jälkeen, nämä tullaan toteuttamaan ensimmäisenä.

* Tekstuureja käyttävä, graafisesti vaikuttavampi pelinäkymä.
  * Kuvatiedostoja käyttävä Rendererin toteuttava luokka ImageRenderer. Arviotu työmäärä 10 tuntia.
    * ImageService. Arvioitu työmäärä 10 tuntia.
      * ImageDao. Arvioitu työmäärä 10 tuntia.
  * Tekstuurit. Arvioitu työmäärä 30 tuntia.
* Pelitallennukset, jotka muistavat useamman kuin yhden kentän tilan. Arvioitu työmäärä 10 tuntia.

## Muita jatkokehitysideoita
Nämä ideat ovat kauempana tulevaisuudessa tai jopa puhdasta utopiaa.
* Muita kuin ylhäältä päin kuvattuja tasoja: esim. pulmia, vuoropohjaista taistelua, tms.
* Esineitä, kokemustasoja ja kauppoja
* Tehtäväloki
* Tarina
* Reaaliaikainen monipeli
