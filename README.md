# Fiks SvarUt klient
![GitHub License](https://img.shields.io/github/license/ks-no/fiks-svarut-klient)
[![Maven Central](https://img.shields.io/maven-central/v/no.ks.fiks/fiks-svarut-klient)](https://search.maven.org/artifact/no.ks.fiks/fiks-svarut-klient)
![GitHub Release Date](https://img.shields.io/github/release-date/ks-no/fiks-svarut-klient.svg)
![GitHub Last Commit](https://img.shields.io/github/last-commit/ks-no/fiks-svarut-klient.svg)

Klient for REST-APIer som benytter autentisering via Maskinporten, som beskrevet [her](https://developers.fiks.ks.no/felles/integrasjoner/).

API-dokumentasjon finnes [her](https://developers.fiks.ks.no/tjenester/svarut/restv2/).

### Major-versjoner

| Klientversjon | JDK-versjon | Jetty klientversjon |
|---------------|-------------|---------------------|
| 1.x.x         | 11          | 9                   |
| 2.x.x         | 17          | 11                  |
| 3.x.x         | 17          | 12                  |

#### Maven dependency

```xml
    <dependencies>
       <dependency>
            <groupId>no.ks.fiks</groupId>
            <artifactId>fiks-svarut-klient</artifactId>
            <version>x.x.x</version>
       </dependency>
    </dependencies>
```

### Java-support

Klienten er skrevet i Kotlin og bruker blant annet genererte data-klasser. Enkelte av disse har mange felter, men de fleste av disse er ikke påkrevd.
For å gjøre dette enklere å sette kun de feltene man ønsker fra Java er det laget noen builder-klasser som kan benyttes slik:
```java
Forsendelse forsendelse = new ForsendelseBuilder()
        .mottaker(new AdresseBuilder()
                .navn("Test Testesen")
                .build()
        )
        .tittel("Tittel på forsendelsen")
        .build();
```
