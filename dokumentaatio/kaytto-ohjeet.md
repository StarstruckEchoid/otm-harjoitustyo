# Käyttöohjeet
<details>
  <summary><h2>Lataaminen, asentaminen ja käynnistäminen.</h2></summary>

Etsi [viimeisin julkaisu](https://github.com/StarstruckEchoid/otm-harjoitustyo/releases) ja seuraa siinä annettuja ohjeita.
</details>

<details>
  <summary><h2>Käyttöliittymässä navigoiminen.</h2></summary>

Käyttöliittymän muissa ikkunoissa kuin peli-ikkunassa liikutaan pääosin nuolinäppäinten, lähinnä UP ja DOWN, sekä näppäinten ESC ja ENTER avulla.

<details>
  <summary><h3>Päävalikko</h3></summary>

Peli alkaa aina päävalikosta.
![Päävalikko](/dokumentaatio/kuvat/MainMenuScreen.PNG)
EXIT sulkee ohjelman; START vie [käyttäjän valintaan](#käyttäjän-valitseminen). Molemmat vaihtoehdot valitaan nuolinäppäimillä ja ENTER:illä. Näppäin ESC ei tee päävalikossa mitään: pelin käyttöliittymä on ikkunoiden puu, ja päävalikko on sen juuri.
</details>
<details>
  <summary><h3>Käyttäjän valitseminen</h3></summary>

Seuraava ikkuna on käyttäjänvalintaikkuna.
![Käyttäjän valinta](/dokumentaatio/kuvat/LoadUserScreen.PNG)
Tässä esimerkissä pelissä on jo käyttäjä 'VEX', ja sen valitseminen vie suoraan [pelaajan valitsemiseen](#pelaajan-valitseminen), mutta kun peli käynnistetään ensi kertaa, on tietenkin ainoa vaihtoehto '\<new user\>', joka vie [uuden käyttäjän luonti-ikkunaan](#käyttäjän-luominen).
</details>
<details>
  <summary><h3>Käyttäjän luominen</h3></summary>

Käyttäjän luonnissa käyttöliittymälle syötetään haluttu käyttäjänimi ja lopuksi painetaan Enter. Jos nimi on epätyhjä, siirrytään eteenpäin [pelaajan valintaan](#pelaajan-valitseminen).
![Käyttäjän luominen 1](/dokumentaatio/kuvat/NewUserScreen.PNG)
![Käyttäjän luominen 2](/dokumentaatio/kuvat/NewUserScreen_filled.PNG)
</details>
<details>
  <summary><h3>Pelaajan valitseminen</h3></summary>

Pelaajan valitseminen on päällisin puolin identtinen tapahtuma käyttäjän valinnan kanssa. Tässä esimerkissä on taas valmiina jo kaksi pelaajaa, 'ART' ja 'BBB', mutta ensimmäisellä käynnistyksellä jälleen kerran ainoa vaihtoehto olisi '\<new player\>', joka vie [uuden pelaajan luomiseen](#pelaajan-luominen). Valmiin pelaajan valinta vie [tallennetun pelin lataamisikkunaan](#pelin-lataaminen).
![Pelaajan valinta](/dokumentaatio/kuvat/LoadPlayerScreen.PNG)
</details>
<details>
  <summary><h3>Pelaajan luominen</h3></summary>

Kun luodaan uusi pelaaja, tullaan samalla luoneeksi uusi peli, sillä kuhunkin pelaajahahmoon liittyy tietenkin ainakin yksi pelitallennus. Pelaajan luonnissa pelaajalle annetaan nimi (kirjainnäppäimillä) ja sille valitaan hahmoluokka (nuolinäppäimet LEFT ja RIGHT). Kun molemmat on valittu, uusi peli käynnistyy välittömästi.

![Pelaajan luominen 1](/dokumentaatio/kuvat/NewPlayerScreen.PNG)
![Pelaajan luominen 2](/dokumentaatio/kuvat/NewPlayerScreen_filled.PNG)
</details>
<details>
  <summary><h3>Pelinäkymä</h3></summary>

Sovelluksen suola, itse peli!
![Pelinäkymä](/dokumentaatio/kuvat/LevelScreen.PNG)
Pelaajan hahmoa symboloi merkki '@'. Pelissä liikutaan [WASD](https://en.wikipedia.org/wiki/Arrow_keys#WASD_keys):illa ylös, alas, vasemmalle ja oikealle. Nuolinäppäimet eivät kuitenkaan ole turhat, sillä niillä pelaaja lyö kuhunkin ilmansuuntaan. Lyönnin kantama on 1 palikka. Pelaaja lyö myös palikoita, jotka ovat pelaajan kanssa päällekäin.  
Lyönti tekee vahinkoa vihollishahmoille. Viholliset tunnistaa siitä, että ne lähtevät liikkumaan pelaajaa kohti pelaajan ollessa tarpeeksi lähellä ja tekevät pelaajalle vahinkoa päästessään iholle.  
ENTER-napilla pelaaja vuorovaikuttaa tason interaktiivisten esineiden kanssa (esimerkiksi vivun symboli on 't') tai siirtyy tasolinkkejä pitkin toisiin tasoihin. Tasolinkkejä symboloi yleisimmin merkki '\['.  
ESCAPE-napilla pelaaja siirtyy [taukovalikkoon](#taukovalikko).
</details>
<details>
  <summary><h3>Taukovalikko</h3></summary>

Taukovalikossa on neljä vaihtoehtoa: Continue, Save Game, Load Game, ja Quit Game.
![Taukovalikko](/dokumentaatio/kuvat/PauseMenuScreen.PNG)
Continue vie takaisin [pelinäkymään](#pelinäkymä), eikä tämä prosessi eroa mitenkään ESCAPE-näppäimen painamisesta.  
Save Game vie [tallennusvalikkoon](#tallennusvalikko).  
Load Game vie [latausvalikkoon](#latausvalikko).  
Quit Game vie takaisin [päävalikkoon](#päävalikko).
</details>
<details>
  <summary><h3>Tallennusvalikko</h3></summary>

Tallennusvalikossa voi nimensä mukaisesti tallentaa pelitilanteen.
![Tallennusvalikko](/dokumentaatio/kuvat/SaveGameScreen.PNG)
Vaihtoehdon '\<new save\>' valitseminen tallentaa pelitilanteen ja vie sitten takaisin [pelinäkymään](#pelinäkymä).
Kirjoittamisen hetkellä valikossa osoittimen voi viedä aikaisempien pelitallennusten päälle, mutta niiden valinta ei tee mitään. Tulevaisuudessa tämän olisi tarkoitus ylikirjoittaa kyseinen tallennus.
</details>
<details>
  <summary><h3>Latausvalikko</h3></summary>

Latausvalikossa voi ladata aikaisemmin tallennetun pelitilanteen.
![Latausvalikko](/dokumentaatio/kuvat/LoadGameScreen.PNG)
Valikossa on aina vähintään yksi tallennus, sillä uuden pelin luomisen yhteydessä peli tallennetaan kerran.  
Pelin lataaminen lataa tallennuksen pelitilanteen ja vie sitten takaisin [pelinäkymään](#pelinäkymä).
</details>
<details>
  <summary><h3>Peli ohi -näkymä</h3></summary>

Peli voi päättyä, ja tällä hetkellä ainoa tilanne, jossa näin käy, on kun pelaaja kuolee, eli hänen elämäpisteet laskevat nollaan.
![Peli ohi](/dokumentaatio/kuvat/GameOverScreen.PNG)
Peli ohi -näkymästä minkä tahansa napin painaminen johtaa pelaajan siirtymiseen takaisin [päävalikkoon](#päävalikko).
</details>
<details>
  <summary><h3>Virhenäkymä</h3></summary>

Silloin tällöin pelissä voi tapahtua virheitä, joista pelin logiikka ei selviä itse, vaan suoritus täytyy keskeyttää. Tällöin käyttäjä ohjataan virhenäkymään.
![Virhenäkymä](/dokumentaatio/kuvat/ErrorScreen.PNG)
Virhenäkymä näyttää pelaajalle lyhyen virheilmoituksen. Minkä tahansa napin painaminen vie pelaajan takaisin [päävalikkoon](#päävalikko).
Tyypillisin syy joutua virhenäkymään on IO-poikkeus, joka voi aiheutua esimerkiksi siitä, että levels-kansiossa ei ole haettua tasotiedostoa tai siitä, että käyttäjälle tai pelaajalle on yritetty antaa kielletty nimi, kuten CON, NUL, tai AUX.
</details>
</details>
