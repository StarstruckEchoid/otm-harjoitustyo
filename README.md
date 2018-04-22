# Dungeon Crawler
Dungeon Crawler (nimi saattaa vielä muuttua) on ylhäältäpäin kuvattu seikkailupeli. Pelin ykistyiskohdista voi lukea tarkemmin [vaatimusmäärittelystä](/dokumentaatio/vaatimusmaarittely.md).

## Dokumentaatio
* [Vaatimusmäärittely](/dokumentaatio/vaatimusmaarittely.md)
* [Arkkitehtuuri](/dokumentaatio/arkkitehtuuri.md)
* [Työaikakirjanpito](/dokumentaatio/tuntikirjanpito.md)

## Testauksesta
Sovellusta voi testata terminaalista komennolla

    mvn test
Testikattavuusraportin voi luoda komennolla

    mvn jacoco:report

Sovelluksen tyylin voi tarkistaa komennolla

    mvn jxr:jxr checkstyle:checkstyle

## Ohjelman generointi ja suoritus

Jarin voi generoida kansiossa DungeonCrawler komennolla

    mvn package

Jarin voi suorittaa komennolla

   java -jar target/DungeonCrawler-1.0-SNAPSHOT.jar 

