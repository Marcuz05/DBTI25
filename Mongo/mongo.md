# MongoDB

## 1
### a)
JSON Dateien liegen unter:\
[`./src/main/resources/1-a-buch.json`](./src/main/resources/1-a-buch.json)\
[`./src/main/resources/1-a-leser.json`](./src/main/resources/1-a-leser.json)\
[`./src/main/resources/1-a-multi-buecher.json`](./src/main/resources/1-a-multi-buecher.json)\
[`./src/main/resources/1-a-multi-leser.json`](./src/main/resources/1-a-multi-leser.json)

#### 1-a-buch.json:
``` JSON
{
    "INVNR"  : "1",
    "AUTOR"  : "Marc-Uwe Kling",
    "TITEL"  : "Die Känguru Chroniken: Ansichten eines vorlauten Beuteltiers",
    "VERLAG" : "Ullstein-Verlag"
}
```
``` shell
$ db.buch.insertOne({
    "INVNR"  : "1",
    "AUTOR"  : "Marc-Uwe Kling",
    "TITEL"  : "Die Känguru Chroniken: Ansichten eines vorlauten Beuteltiers",
    "VERLAG" : "Ullstein-Verlag"
})
```

#### 1-a-leser.json:
``` JSON
{
    "LNR"     : "1",
    "NAME"    : "Friedrich Funke",
    "ADRESSE" : "Bahnhofstraße 17, 23758 Oldenburg"
}
```
``` shell
$ db.buch.insertOne({
    "LNR"     : "1",
    "NAME"    : "Friedrich Funke",
    "ADRESSE" : "Bahnhofstraße 17, 23758 Oldenburg"
})
```

#### 1-a-multi-buecher.json:
``` JSON
[
    {
        "INVNR"  : "2",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und der Stein der Weisen",
        "VERLAG" : "Carlsen Verlag"
    },
    {
        "INVNR"  : "3",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und die Kammer des Schreckens",
        "VERLAG" : "Carlsen Verlag"
    },
    {
        "INVNR"  : "4",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und der Gefangene von Askaban",
        "VERLAG" : "Carlsen Verlag"
    },
    {
        "INVNR"  : "5",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und der Feuerkelch",
        "VERLAG" : "Carlsen Verlag"
    },
    {
        "INVNR"  : "6",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und der Orden des Phönix",
        "VERLAG" : "Carlsen Verlag"
    },
    {
        "INVNR"  : "7",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und der Halbblutprinz",
        "VERLAG" : "Carlsen Verlag"
    },
    {
        "INVNR"  : "8",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und die Heiligtümer des Todes",
        "VERLAG" : "Carlsen Verlag"
    }
]
```
``` shell
$ db.buch.insertMany([
    {
        "INVNR"  : "2",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und der Stein der Weisen",
        "VERLAG" : "Carlsen Verlag"
    },
    {
        "INVNR"  : "3",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und die Kammer des Schreckens",
        "VERLAG" : "Carlsen Verlag"
    },
    {
        "INVNR"  : "4",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und der Gefangene von Askaban",
        "VERLAG" : "Carlsen Verlag"
    },
    {
        "INVNR"  : "5",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und der Feuerkelch",
        "VERLAG" : "Carlsen Verlag"
    },
    {
        "INVNR"  : "6",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und der Orden des Phönix",
        "VERLAG" : "Carlsen Verlag"
    },
    {
        "INVNR"  : "7",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und der Halbblutprinz",
        "VERLAG" : "Carlsen Verlag"
    },
    {
        "INVNR"  : "8",
        "AUTOR"  : "Joanne K. Rowling",
        "TITEL"  : "Harry Potter und die Heiligtümer des Todes",
        "VERLAG" : "Carlsen Verlag"
    }
])
```

#### 1-a-multi-leser.json:
``` JSON
[
    {
        "LNR"     : "2",
        "NAME"    : "Anna Müller",
        "ADRESSE" : "Hauptstraße 5, 10115 Berlin"
    },
    {
        "LNR"     : "3",
        "NAME"    : "Thomas Becker",
        "ADRESSE" : "Rosenweg 8, 80331 München"
    },
    {
        "LNR"     : "4",
        "NAME"    : "Laura Schmidt",
        "ADRESSE" : "Goethestraße 22, 50667 Köln"
    },
    {
        "LNR"     : "5",
        "NAME"    : "Peter Hoffmann",
        "ADRESSE" : "Feldstraße 12, 20095 Hamburg"
    },
    {
        "LNR"     : "6",
        "NAME"    : "Claudia Wagner",
        "ADRESSE" : "Gartenweg 3, 89073 Ulm"
    },
    {
        "LNR"     : "7",
        "NAME"    : "Michael Braun",
        "ADRESSE" : "Schillerstraße 45, 70173 Stuttgart"
    },
    {
        "LNR"     : "8",
        "NAME"    : "Katrin Neumann",
        "ADRESSE" : "Lindenallee 7, 04109 Leipzig"
    },
    {
        "LNR"     : "9",
        "NAME"    : "Johannes König",
        "ADRESSE" : "Marktplatz 1, 90402 Nürnberg"
    },
    {
        "LNR"     : "10",
        "NAME"    : "Susanne Fischer",
        "ADRESSE" : "Kirchweg 9, 55116 Mainz"
    },
    {
        "LNR"     : "11",
        "NAME"    : "Stefan Wolf",
        "ADRESSE" : "Berliner Allee 16, 40212 Düsseldorf"
    }
]
```
``` shell
$ db.leser.insertMany([
    {
        "LNR"     : "2",
        "NAME"    : "Anna Müller",
        "ADRESSE" : "Hauptstraße 5, 10115 Berlin"
    },
    {
        "LNR"     : "3",
        "NAME"    : "Thomas Becker",
        "ADRESSE" : "Rosenweg 8, 80331 München"
    },
    {
        "LNR"     : "4",
        "NAME"    : "Laura Schmidt",
        "ADRESSE" : "Goethestraße 22, 50667 Köln"
    },
    {
        "LNR"     : "5",
        "NAME"    : "Peter Hoffmann",
        "ADRESSE" : "Feldstraße 12, 20095 Hamburg"
    },
    {
        "LNR"     : "6",
        "NAME"    : "Claudia Wagner",
        "ADRESSE" : "Gartenweg 3, 89073 Ulm"
    },
    {
        "LNR"     : "7",
        "NAME"    : "Michael Braun",
        "ADRESSE" : "Schillerstraße 45, 70173 Stuttgart"
    },
    {
        "LNR"     : "8",
        "NAME"    : "Katrin Neumann",
        "ADRESSE" : "Lindenallee 7, 04109 Leipzig"
    },
    {
        "LNR"     : "9",
        "NAME"    : "Johannes König",
        "ADRESSE" : "Marktplatz 1, 90402 Nürnberg"
    },
    {
        "LNR"     : "10",
        "NAME"    : "Susanne Fischer",
        "ADRESSE" : "Kirchweg 9, 55116 Mainz"
    },
    {
        "LNR"     : "11",
        "NAME"    : "Stefan Wolf",
        "ADRESSE" : "Berliner Allee 16, 40212 Düsseldorf"
    }
])
```

Für den Sachverhalt, dass ein Leser ein Buch ausleiht gibt es zwei Möglichkeiten:
1. Es gibt eine weitere Collection "entliehen" in der die LNR des Lesers und die INVNR des Buches gespeichert werden, sowie das Rückgabedatum. Bsp:
    ``` JSON
    {
        "LNR"            : "1",
        "INVNR"          : "1",
        "RUECKGABEDATUM" : "2025-10-31"
    }
    ```
2. Die Daten über den Ausleihzusatnd des Buches, werden in diesem gespeichert (da es ja nur einmal zur Zeit ausgeliehen werden kann). Bsp:
    ``` JSON
    {
        "INVNR"          : "1",
        "AUTOR"          : "Marc-Uwe Kling",
        "TITEL"          : "Die Känguru Chroniken: Ansichten eines vorlauten Beuteltiers",
        "VERLAG"         : "Ullstein-Verlag",
        "LNR"            : "1",
        "RUECKGABEDATUM" : "2025-10-31"
    }
    ```
3. Die ausgeliehenen Bücher werden im Leser in einer Liste gespeichert. Bsp:
    ``` JSON
    {
        "LNR"       : "1",
        "NAME"      : "Friedrich Funke",
        "ADRESSE"   : "Bahnhofstraße 17, 23758 Oldenburg",
        "ENTLIEHEN" : [
            {
                "INVNR"          : "1",
                "RUECKGABEDATUM" : "2025-10-31"
            }
        ]
    }
    ```

Im folgenden wird die erste Variante ("entliehen-Collection mit Beziehung") genutzt.

``` shell
$ db.entliehen.insertMany([
    {
        "LNR"            : "1",
        "INVNR"          : "1",
        "RUECKGABEDATUM" : "2025-10-31"
    },
    {
        "LNR"            : "2",
        "INVNR"          : "2",
        "RUECKGABEDATUM" : "2025-11-01"
    },
    {
        "LNR"            : "2",
        "INVNR"          : "3",
        "RUECKGABEDATUM" : "2025-11-02"
    },
    {
        "LNR"            : "6",
        "INVNR"          : "4",
        "RUECKGABEDATUM" : "2025-11-03"
    },
    {
        "LNR"            : "6",
        "INVNR"          : "5",
        "RUECKGABEDATUM" : "2025-11-04"
    },
    {
        "LNR"            : "6",
        "INVNR"          : "7",
        "RUECKGABEDATUM" : "2025-11-06"
    }
])
```

### b)

``` shell
$ db.buch.find({ "AUTOR": "Marc-Uwe Kling" })
```

### c)

``` shell
$ db.buch.count()                  # deprecated (sollte nicht mehr verwendet werden)
$ db.buch.countDocuments()         # empfohlener Befehl
$ db.buch.estimatedDocumentCount() # eventuell ungenau, falls Index nicht aktuell, aber viel schneller
```

### d)
Ermitteln Sie bitte alle Leser, die mehr als ein
Buch ausgeliehen haben, absteigend sortiert nach Anzahl der entliehenen Bucher.
Im Folgenden wird die Lösung mit der erste Variante ("entliehen-Collection mit Beziehung") gezeigt.

``` shell
$ db.entliehen.aggregate([
    { $group: { _id: "$LNR", count: { $sum: 1 } } },
    { $match: { count: { $gt: 1 } } },
    { $sort: { count: -1 } },
    { $lookup: {
        from: "leser",
        localField: "_id",
        foreignField: "LNR",
        as: "leserInfo"
    }},
    { $project: {
        _id: 0,
        LNR: "$_id",
        AnzahlBuecher: "$count",
        LeserName: { $arrayElemAt: ["$leserInfo.NAME", 0] }
    }}
])
```

### e)
Lassen Sie bitte Friedrich Funke das Känguru-Buch ausleihen und wieder zuruckgeben.

``` shell
$ db.
```
