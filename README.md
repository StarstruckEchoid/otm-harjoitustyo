# Dungeon Crawler
Dungeon Crawler on ylhäältäpäin kuvattu seikkailupeli. Pelin ykistyiskohdista voi lukea tarkemmin [vaatimusmäärittelystä](/dokumentaatio/vaatimusmaarittely.md).

## Dokumentaatio
* [Vaatimusmäärittely](/dokumentaatio/vaatimusmaarittely.md)
* [Arkkitehtuuri](/dokumentaatio/arkkitehtuuri.md)
* [Työaikakirjanpito](/dokumentaatio/tuntikirjanpito.md)
* [Käyttöohjeet](/dokumentaatio/kaytto-ohjeet.md)
* [Testausdokumentaatio](/dokumentaatio/testaus.md)

## Testauksesta
Sovellusta voi testata terminaalista komennolla

    mvn test
Testikattavuusraportin voi luoda komennolla

    mvn jacoco:report

Sovelluksen tyylin voi tarkistaa komennolla

    mvn jxr:jxr checkstyle:checkstyle
    
Javadocin voi generoida komennolla

    mvn javadoc:javadoc

## Ohjelman generointi ja suoritus

Jarin voi generoida kansiossa DungeonCrawler komennolla

    mvn package

Jarin voi suorittaa samassa pakkauksessa komennolla

    java -jar target/DungeonCrawler-1.0-SNAPSHOT.jar 

## [Julkaisut](https://github.com/StarstruckEchoid/otm-harjoitustyo/releases)
* [Kolmas valmis julkaisu, ja tämän kurssin loppupalautus, DungeonCrawler 1.2.](https://github.com/StarstruckEchoid/otm-harjoitustyo/releases/tag/11.5.2018)
* [Pelin toinen valmis julkaisu, DungeonCrawler 1.1](https://github.com/StarstruckEchoid/otm-harjoitustyo/releases/tag/10.5.2018)
* [Pelin ensimmäinen valmis julkaisu, DungeonCrawler 1.0](https://github.com/StarstruckEchoid/otm-harjoitustyo/releases/tag/7.5.2018).
