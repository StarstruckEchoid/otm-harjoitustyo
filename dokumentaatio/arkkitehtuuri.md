# Arkkitehtuurikuvaus
## Arkkitehtuuri

Sovelluksessa on neljä pakkausta:
* userinterface: käyttöliittymä
* domain: sovelluslogiikka
* dataaccessobject: levytapahtumat
* utilityclasses: apuluokat

Userinterface-pakkaus vuorovaikuttaa domainin kanssa. Domain ja dataaccessobject vuorovaikuttavat domainin pakkauksen dataservice kautta. Utilityclasses sisältää mihin vain pakkaukseen liittyviä apuluokkia, ja voi periaatteessa vuorovaikuttaa minkä tahansa pakkauksen kanssa.

Pakkauskaavio:
![Pakkauskaavio](/dokumentaatio/kuvat/pakkauskaavio.png)

## Käyttöliittymä

Käyttöliittymä sisältää useita eri näkymiä, mutta ne voi jakaa karkeasti kahteen päätyyppiin:
* lataus ja tallennus
  * lataus: esim. LoadUserScreen, LoadPlayerScreen.
  * tallennus: esim. NewUserScreen, NewPlayerScreen.
* pelinäkymä: LevelScreen

Nämä näkymät ovat erityisiä GameScreen-olioita, jotka palauttavat pääluokalle oman ulkonäkönsä Parent-oliona.
Pääluokka asettaa tämän olion oman Scene-olionsa juureksi. Pääluokka kutsuu tätä metodia joitain kymmeniä kertoja sekunnissa, mikä mahdollistaa pelinäkymän reaaliaikaisuuden. Lisäksi näppäinsyötteisiin reagoiminen ja oman tilan päivittäminen on jätetty GameScreenien omalle vastuulle.  
Pääluokka käskee GameScreenejä päivittämään itsensä joitain kertoja sekunnissa, mikä LevelScreenin tapauksessa saa pelin oliot liikkumaan ja muuten toimimaan.  
Sovelluslogiikka on pyritty eriyttämään käyttöliitymästä: koko käyttöliittymällä on yksi pääluokasta löytyvä DataService-olio, jonka metodeja kaikki levytapahtumia tekevät GameScreenit käyttävät yhteisesti.  
DataServicen attribuuttina on myös pelin taso, GameLevel, jota LevelScreen käyttää. LevelScreen ei itse päivitä pelin tilannetta, vaan ulkoistaa sen niin paljon kuin mahdollista GameLevelille itselleen.
LevelScreenistä mainittakoon, että myös pelitason piirtäminen on ulkoistettu omalle Renderer-luokalle, mikä tarkoittaa, että peligrafiikan muuttaminen nykyisestä kirjainmatriisiesityksestä johonkin hienompaan on periaatteessa vaivatonta.

## Sovelluslogiikka

Sovelluslogiikka perustuu rajapintaan GameLevel, jonka ympärillä kaikki pyörii.

GameLevelin tärkeimmät metodit ovat:
* movePlayer(Direction dir), joka liikuttaa pelaajaa yhden palikan tiettyyn suntaan.
* doGameTick(), joka päivittää pelitilanteen: liikuttaa hirviöitä, saa hirviöt lyömään, yms.
* playerAttack(Directior dir), joka saa pelaajan lyömään tiettyyn suuntaan.

Sovelluslogiikkaan kuuluu myös rajapinta DataService, joka hoitaa levyoperaatioita daojen kautta. DataServicen metodeja ovat mm.
* saveGame(), joka tallentaa pelitilanteen.
* fetchPlayers(), joka palauttaa listan tallennettujen pelaajien nimistä.
* fetchUsers(), joka palauttaa listan tallennettujen käyttäjien nimistä.
* setPlayer(String playerName), joka asettaa pelaajaksi pelaajan, jonka nimi on playerName. Lisäksi tallettaa pelaajan levylle, jos ei ole vielä olemassa.

...ja niin edelleen. Tarkoituksena on, että yksikään käyttöjärjestelmän luokka ei kutsu daoja suoraan, vaan aina DataServicen kautta. Näin myös varmistetaan, että kaikkialla ohjelmassa käytetään yhteensopivia daoja: Ohjelmassa kun oli vielä aikaisemmissa versioissa sekä ihmisen luettaviin tekstitiedostoihin erikoistuneita daoja, kuten TextFileGameSaveDao ja TextFileLevelDao, mutta myös binääritiedostoihin erikoituneita daoja kuten ByteFileGameSaveDao ja ByteFileLevelDao. Näitä taas oli todella huono käyttää ristiin.

## Päätoiminnallisuudet

Sekvenssikaavio: pelaaja painaa nappia ja liikkuu ylös.
![Sekvenssikaavio](/dokumentaatio/kuvat/Move_Player_Up.png)
