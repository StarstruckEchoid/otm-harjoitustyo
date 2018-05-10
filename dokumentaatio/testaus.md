# Testauksesta
Ohjelmaa on testattu automatisoidusta JUnitilla toteutetuin yksikkötesteillä, ja muutamalla integraatiotestillä. Ohjelman käyttöliittymää on lisäksi testattu käsipelin.  
Aivan kaikilla luokilla ei ole omaa testiluokkaansa, (koska vuorokaudessa on vain rajallinen määrä tunteja), mutta kaikki luokat tulevat kuitenkin ainakin olennaisilta osin testatuksi viimeistään integraatiotestien kautta.

## Yksikkö- ja integraatiotestit
Sovellus- ja integraatiotestit suoritetaan JUnit-pohjaisten testiluokkien kautta. Sekä yksikkö-, että integraatiotestit löytyvät samoista testiluokista. Integraatiotestit löytyvät sen luokan mukaan nimetyn testiluokan alta, jonka alle niiden koetaan vahvimmin kuuluvan.

### domain
[Sovelluslogiikan testit](/DungeonCrawler/src/test/java/otmkurssiprojekti/domain) ovat pitkälti perinteisiä yksikkötestejä JUnitilla toteutettuna. Testit pyrkivät testaamaan mahdollisimman hyvin tasan yhden metodin tasan yhtä haaraa kerrallaan.  
Integraatiotestausta tapahtuu lähinnä abstraktien luokkien yhteydessä, sillä usein näissä luokissa käytetään testattavana jotain abstraktin luokan aliluokkaa. Lisäksi moni testiluokka, jonka tarvitsee jostain syystä generoida [GameLevel](/DungeonCrawler/src/main/java/otmkurssiprojekti/domain/level/GameLevel.java)-olio, käyttää tähän [TextFileGameLevels](DungeonCrawler/src/main/java/otmkurssiprojekti/utilityclasses/TextFileGameLevels.java)in metodeja.

### dataaccessobject
Levyoperaatioita tekeviä luokkia, eli daoja [testataan](/DungeonCrawler/src/test/java/otmkurssiprojekti/dataaccessobject) myös JUnitilla. Koska käytetyt daot perustuvat kansioihin ja niistä löytyviin tiedostoihin, luodaan testattavien luokkien tarvitsema data pitkälti Files-apuluokan metodeilla createTempFile ja createTempDirectory.

### userinterface
Käyttöliittymän luokkia ei testata lainkaan automatisoidusti, koska se menee tämän kurssin oppimistavoitteiden ulkopuolelle. Käyttöliittymää on kuitenkin testattu käsipelin niin kattavasti kuin mahdollista, ottaen huomioon ilmeisten käyttäjän kulkemien polkujen lisäksi myös epätavallisemmat yhdistelmät.  
On kuitenkin totta, että käyttöliittymässä on 12 näkymää ja näiden välillä on 22 suunnattua kaarta, jotka muodostavat myös syklejä, joten käyttöliittymän joka polun tutkiminen ei ole mahdollista.

### utilityclasses
[Apuluokkien testit](/DungeonCrawler/src/test/java/otmkurssiprojekti/utilityclasses) ovat hyvin suoraviivaisia yksikkötestejä, kuitenkin sillä huomautuksella, että kukin apuluokka käyttää jossain vaiheessa projektin muita luokkia - nämä luokat ovat kuitenkin tehty auttamaan nimenomaan tämän projektin luokkia joidenkin asioiden tekemisessä - joten integraatiotestejä ei päästä näissä karkuun.