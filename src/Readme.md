**Benjamin Schichtholz, Matrikelnummer: 5097555, Studiengang: Informatik**

# Nim-Spiel
* * *

#Anleitung
* Beim Starten des Spiels kann der Spieler zwischen dem Testmodus (<b>t</b>: test mode) und dem Spielmodus (<b>p</b>: play) auswählen

##Test-Modus
*   Beim Testmodus werden in 40 Spielen mit zufällig generierten Kombinationen von   Spielsteinen Objekte der Klassen Nim gegen Objekte der Klasse NimPerfect gegeneinander ausgespielt

* Die Spiele geben Information über den beginnenden Spieler, die Konstellation der Spielsteine, den erwarteten Gewinner und den tatsächlichen Gewinner

* Am Ende vom Testmodus kann der Spieler zurück zur Auswahl zwischen Testmodus und Spielmodus zurückkehren (<b>ENTER</b>), oder das Programm beenden (<b>q / e</b>)

##Spielmodus
* Beim Spielmodus kann der Spieler den Gegner auswählen: Nim (<b>n</b>) oder NimPerfect (<b>np</b>)

* Danach kann der Spieler entscheiden, mit welcher Konstellation an Spielsteinen er spielen will:
    + Um die Spielsteine selbst zu definieren, wird die Anzahl der Steine pro Reihe - getrennt mit einem Bindestrich - eingegeben : <b>Zahl1-Zahl2-Zahl3-...</b>
    + Bei der Eingabe von <b>r</b> wird das Spielfeld zufällig generiert
    + Wird die <b>ENTER</b>-Taste gedrückt, wird das Default-Spielfeld (1-3-5-7) gewählt

* Danach beginnt das eigentliche Spiel

* Bei jedem Spielzug kann der Spieler entscheiden, ob er selbst den Zug ausführen will (<b>0</b>) oder ob der Computer den bestmöglichen Zug ausführen soll (<b>1</b>)

* Um selbst einen Zug auszuführen, wird die Reihe und Anzahl der Steine in folgendem Format eingegeben: <b>[REIHE].[ANZAHL]</b> (z.B. <b>2.1</b>)
    + Ist das Eingabeformat falsch, kommt eine Fehlermeldung und der Spieler kann erneut eine Eingabe tätigen

* Nach dem Ausführen des Zuges kann der Spieler entscheiden, ob das Spiel fortgesetzt werden soll (<b>ENTER</b>, ob das Spiel beendet werden soll (<b>q / e</b>) ob der letzte Zug rückgängig gemacht werden soll (<b>u</b>)
    + Ist das Spiel zu Ende, kann der Spieler mit <b>ENTER</b> zur Auswahl zwischen Spiel- und Testmodus zurückkehren, oder die anderen Optionen wahrnemen (beenden/rückgängig)


#Besonderheiten

#Aufzeichnung