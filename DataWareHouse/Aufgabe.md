# Aufgabe 3: Data-Warehouse und Sternschema

In dieser Übung gehen wir zurück zu relationalen Datenbanken und wollen uns mit dem Data-Warehouse-Gedanken auseinandersetzen.

## Data-Warehouse und Sternschema

Die bisher betrachtete `firma`-Datenbank enthält fast ausschließlich statische Stammdaten (Personal, Gehaltsstufen, Maschinen, Prämienarten etc.), aber keine echten Transaktionen über die Zeit.
Ein Data-Warehouse lebt jedoch von zeitbezogenen Transaktionsdaten, auf die man Faktenanalysen anwenden kann.

Dies wollen wir in einfacher Form ergänzen und konzentrieren uns auf Gehaltszahlungen.

Betrachten wir also die zusätzliche Tabelle:

``` SQL
CREATE TABLE gehaltszahlung (
    gz_id INT AUTO_INCREMENT PRIMARY KEY,
    pnr INT NOT NULL,
    zahlungsdatum DATE NOT NULL,
    gehalt_betrag INT NOT NULL,
    bemerkung VARCHAR(100),
    FOREIGN KEY (pnr) REFERENCES personal(pnr)
);
```

die beschreibt, wann eine Gehaltszahlung in welcher Höhe an welchen Mitarbeiter gezahlt wird.
Beispielzahlungen finden sich im File [`gehaltszahlungen.sql`](./gehaltszahlungen.sql).
Bitte laden Sie diese Daten in die Firmendatenbank (MariaDB aus Übung 1, im CodeSpace oder in Ihrer eigenen Installation).

## Mit Gehaltsdaten arbeiten

Machen Sie sich bitte mit den Daten in der Tabelle `gehaltszahlung` vertraut.
Befragen Sie dann bitte MariaDB nach folgenden Sachverhalten:

1. ### Weihnachtsgeldzahlungen

    Schreiben Sie bitte eine SQL-Anweisung, die ermittelt, welchen Mitarbeitern Weihnachtsgeld gezahlt wurde und welchen nicht.

    ``` SQL
    SELECT DISTINCT
        p.pnr,
        p.name,
        p.vorname,
        wg.bekommt_weihnachtsgeld
    FROM
        personal p
    LEFT JOIN (
        SELECT
            pnr,
            1 AS bekommt_weihnachtsgeld
        FROM
            gehaltszahlung
        WHERE
            bemerkung LIKE 'Weihnachtsgeld%'
    ) AS wg ON p.pnr = wg.pnr;
    ```

2. ### Urlaubsgeldzahlungen

    Schreiben Sie bitte eine SQL-Anweisung, die ermittelt, in welchen Monaten Urlaubsgeld gezahlt wird.

    ``` SQL
    SELECT DISTINCT
        MONTHNAME(zahlungsdatum) AS urlaubsgeld_monat
    FROM
        gehaltszahlung
    WHERE
        bemerkung LIKE 'Urlaubsgeld%'
    ORDER BY
        urlaubsgeld_monat;
    ```

Zur besseren Auswertung der Gehaltszahlungen wollen wir nun ein Stern-Schema anlegen.
Der Einfachheit halber legen wir dies direkt in der `firma`-Datenbank an und nicht in einer ExtraDatenbank auf einem OLAP-Datenbankserver.

## Stern-Schema

Zur ganz grundsätzlichen Darstellung von Zeiträumen verwenden wird die Tabelle:

``` SQL
CREATE TABLE dim_zeit (
    zeit_id INT AUTO_INCREMENT PRIMARY KEY,
    datum DATE NOT NULL,
    jahr SMALLINT,
    monat TINYINT,
    quartal TINYINT,
    wochentag_name VARCHAR(10)
);
```

Da Gehaltszahlungen im Zentrum unserer Überlegungen stehen sollen, definieren wir die Faktentabelle des Stern-Schemas zur Darstellung der Gehaltszahlungen durch:

``` SQL
CREATE TABLE fact_gehalt (
    fact_id INT AUTO_INCREMENT PRIMARY KEY,
    mitarbeiter_id INT NOT NULL,
    zeit_id INT NOT NULL,
    betrag INT NOT NULL,
    bemerkung VARCHAR(100),
    FOREIGN KEY (mitarbeiter_id) REFERENCES dim_mitarbeiter(mitarbeiter_id),
    FOREIGN KEY (zeit_id) REFERENCES dim_zeit(zeit_id)
);
```

Sie verbindet die angefallenen Gehaltszahlungen mit den Mitarbeitern und dem Zeitpunkt der Zahlung.
Beide Tabellen finden sich im File [`sternschema_gehalt.sql`](./sternschema_gehalt.sql).

`fact_gehalt` verweist über seine Fremdschlüssel `zeit_id` und `mitarbeiter_id` auf die beiden Dimensionstabellen des Stern-Schemas `dim_zeit` (siehe oben) und `dim_mitarbeiter`.

3. ### Dimensionstabelle für Mitarbeiter

    Überlegen Sie bitte, wie die Dimensionstabelle `dim_mitarbeiter` aussieht und schreiben Sie bitte eine passendes `CREATE TABLE` Anweisung.
    Sie soll Informationen zum Mitarbeiter (Personalnummer, Vorname, Name) seiner Gehaltsstufe, Abteilung und Krankenkasse enthalten.

    ``` SQL
    CREATE TABLE dim_mitarbeiter (
        mitarbeiter_id INT AUTO_INCREMENT PRIMARY KEY,
        pnr INT NOT NULL,
        vorname VARCHAR(50) NOT NULL,
        name VARCHAR(50) NOT NULL,
        gehaltsstufe_id INT NOT NULL,
        abteilung_id INT NOT NULL,
        krankenkasse_id INT NOT NULL,
        FOREIGN KEY (pnr) REFERENCES personal(pnr),
        FOREIGN KEY (gehaltsstufe_id) REFERENCES gehaltsstufen(id),
        FOREIGN KEY (abteilung_id) REFERENCES abteilungen(id),
        FOREIGN KEY (krankenkasse_id) REFERENCES krankenkassen(id)
    );
    ```

4. ### Schema–Diagramm

    Wie sieht das Diagramm für das resultierende Stern-Schema aus.
    Bitte skizzieren Sie es für sich (gerne auch einfach mit Bleistift auf Papier).

    ``` SQL

    ```

5. ### Stern-Schema übernehmen

    Bitte übernehmen Sie das Stern-Schema in die Datenbank `firma`.
    Normalerweise würde das Stern-Schema in einer getrennten OLAP-Datenbank angelegt werden.
    Hier vereinfachen wir.

    ``` SQL

    ```

## Extract Transfer Load

Wir wollen nun die bestehenden Firmendaten über Mitarbeitern, Gehaltsstufen und Gehaltszahlungen in einem — hier nur ganz einfach vorliegenden — ETL-Prozess in das Stern-Schema überführen.
Schreiben Sie bitte basierend auf Ihren CRUD-Java-Definitionen aus Übung 1 ein Java-Programm, das den ETL-Prozess durchführt, indem er

1. Daten aus den Tabellen `mitarbeiter` und `gehaltszahlung` extrahiert,

2. die gelesenen Daten geeignet transformiert und dann

3. die transformierten Daten in die Tabellen des Stern-Schema `fact_gehalt`, `dim_zeit` und `dim_mitarbeiter` lädt.

Überprüfen Sie Ihr ETL-Ergebnis bitte mit geeigneten SQL-Anweisungen für die einzelnen Tabellen.

## Business Intelligence

Um nun gezielt die Gehälter zu analysieren, wollen wir das Stern-Schema befragen.
Bitte formulieren Sie jeweils geeignet SQL-Anweisungen, die die Tabellen `fact_gehalt`, `dim_zeit` und `dim_mitarbeiter` ansprechen und sie miteinander verbinden.

1. Wie hoch sind die Gehaltszahlungen (exklusive und inklusive Urlaubs- und Weihnachtsgeld) nach Monaten aufgeschlüsselt?

2. Welche Entwicklung lässt sich dabei erkennen?

3. Wie stellen sich aggregierten Gehaltszahlungen Quartals-weise dar?

4. Was beobachten Sie, wenn Sie die Gehaltszahlungen nach Abteilungen betrachten?
    Sind eventuelle Gehaltsanpassungen auf bestimmte Abteilungen beschränkt?

5. Fragen Sie bitte nach weiteren interessanten Sachverhalten über die Gehaltszahlungen.

6. In welchem Monat fand die Gehaltserhöhung statt und wie hoch viel sie aus?
