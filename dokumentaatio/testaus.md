# Testauksesta
Ohjelmaa on testattu automatisoidusta JUnitilla toteutetuin yksikkötesteillä, ja muutamalla integraatiotestillä. Ohjelman käyttöliittymää on lisäksi testattu käsipelin.  
Aivan kaikilla luokilla ei ole omaa testiluokkaansa, (koska aika on rajalline resurssi), mutta kaikki luokat tulevat kuitenkin ainakin olennaisilta osin testatuksi viimeistään integraatiotestien kautta.

## Yksikkö- ja integraatiotestit
Sovellus- ja integraatiotestit suoritetaan JUnit-pohjaisten testiluokkien kautta. Sekä yksikkö-, että integraatiotestit löytyvät samoista testiluokista. Integraatiotestit löytyvät sen luokan mukaan nimetyn testiluokan alta, jonka alle niiden koetaan vahvimmin kuuluvan.

### domain
[Sovelluslogiikan testit](/DungeonCrawler/src/test/java/otmkurssiprojekti/domain) ovat pitkälti perinteisiä yksikkötestejä JUnitilla toteutettuna. Testit pyrkivät testaamaan mahdollisimman hyvin tasan yhden metodin tasan yhtä haaraa kerrallaan.  
Integraatiotestausta tapahtuu lähinnä abstraktien luokkien yhteydessä, sillä usein näissä luokissa käytetään testattavana jotain abstraktin luokan aliluokkaa. Lisäksi moni testiluokka, jonka tarvitsee jostain syystä generoida [GameLevel](/DungeonCrawler/src/main/java/otmkurssiprojekti/domain/level/GameLevel.java)-olio, käyttää tähän [TextFileGameLevels](/DungeonCrawler/src/main/java/otmkurssiprojekti/utilityclasses/TextFileGameLevels.java)in metodeja.

### dataaccessobject
Levyoperaatioita tekeviä luokkia, eli daoja [testataan](/DungeonCrawler/src/test/java/otmkurssiprojekti/dataaccessobject) myös JUnitilla. Koska käytetyt daot perustuvat kansioihin ja niistä löytyviin tiedostoihin, luodaan testattavien luokkien tarvitsema data pitkälti Files-apuluokan metodeilla createTempFile ja createTempDirectory.

### utilityclasses
[Apuluokkien testit](/DungeonCrawler/src/test/java/otmkurssiprojekti/utilityclasses) ovat hyvin suoraviivaisia yksikkötestejä, kuitenkin sillä huomautuksella, että kukin apuluokka käyttää jossain vaiheessa projektin muita luokkia - nämä luokat ovat kuitenkin tehty auttamaan nimenomaan tämän projektin luokkia joidenkin asioiden tekemisessä - joten integraatiotestejä ei päästä näissä karkuun.  

## Testikattavuus
Kirjoitushetkellä projektilla on 224 testiä, ja niiden rivikattavuus on 94% ja haarakattavuus 83% - haarakattavuutta tosin laskee se, että monen luokan equals-metodissa on paljon haaroja, ja näistä suuri osa on täysin triviaaleja, esim. null-testejä. Testeissä ei oteta huomion käyttöliittymää. Testauksessa ei oteta huomioon [domain.gameobjects.archetypes](/DungeonCrawler/src/main/java/otmkurssiprojekti/domain/gameobject/archetypes)-pakkausta, sillä se sisältää pelkkiä enumeja, joissa taas ei ole käytännössä lainkaan mielekästä testattavaa.
![Jacoco](/dokumentaatio/kuvat/jacoco_report.PNG)

## Järjestelmätestaus

### userinterface
Käyttöliittymän luokkia ei testata lainkaan automatisoidusti, koska se menee tämän kurssin oppimistavoitteiden ulkopuolelle. Käyttöliittymää on kuitenkin testattu käsipelin niin kattavasti kuin mahdollista, ottaen huomioon ilmeisten käyttäjän kulkemien polkujen lisäksi myös epätavallisemmat yhdistelmät.  
On kuitenkin totta, että käyttöliittymässä on 12 näkymää ja näiden välillä on 22 suunnattua kaarta, jotka muodostavat myös syklejä, joten käyttöliittymän joka polun tutkiminen ei ole mahdollista.

### Käyttöjärjestelmät
Sovellus on tuotannon varrella todettu toimivaksi ainakin Windows 10- ja Ubuntu-käyttöjärjestelmissä.

### Toiminnallisuudet
Sovellus täyttää sille [määrittelydokumentissa](/dokumentaatio/vaatimusmaarittely.md) asetetut toiminnallisuusvaatimukset.

## Laatuongelmat
Sovelluksen näyttämät virheviestit eivät ole kovin hyödyllisiä käyttäjän syöttäessä käyttäjän tai pelaajan nimeksi kiellettyjä merkkijonoja, kuten Windows 10-käyttöjärjestelmässä NUL tai CON.
