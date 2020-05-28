**Benjamin Schichtholz, Matrikelnummer: 5097555, Studiengang: Informatik**

# Nim-Spiel
* * *

# Anleitung
* Beim Starten des Spiels kann der Spieler zwischen dem Testmodus (<b>t</b>: test mode) und dem Spielmodus (<b>p</b>: play) auswählen

## Test-Modus
*   Beim Testmodus werden in 40 Spielen mit zufällig generierten Kombinationen von   Spielsteinen Objekte der Klassen Nim gegen Objekte der Klasse NimPerfect gegeneinander ausgespielt

* Die Spiele geben Information über den beginnenden Spieler, die Konstellation der Spielsteine, den erwarteten Gewinner und den tatsächlichen Gewinner

* Am Ende vom Testmodus kann der Spieler zurück zur Auswahl zwischen Testmodus und Spielmodus zurückkehren (<b>ENTER</b>), oder das Programm beenden (<b>q / e</b>)

## Spielmodus
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

# Besonderheiten

* Die Benutzerinteraktion wird in der Klasse "UserInterface" realisiert
* Für den Testmodus wurde eine eigene Klasse "TestMode" implementiert
* Das Projekt ist auch bei GitHub unter https://github.com/benlayerwood/Nim veröffentlicht

# Aufzeichnung

<details>
    <summary>Spiel gegen Nim</summary>
    
    -----------------------------------------
    Welcome to the Game!
    Select the Game Mode
    -----------------------------------------
    1) Start Test mode [t]
    2) Start Playing [p]
    [t|p]: p
    
    ---------------
    Select Opponent
    ---------------
    1) Play against Nim [n]
    2) Play against NimPerfect [np]
    [n|np]: n
    
    You have chosen Nim!
    
    -----------
    Select Rows
    -----------
    Select preferred numbers in format "number1-number2-..." (example: 1-3-5)
    Type in "r" to randomly generate the Rows
    Type in "ENTER" to use default Rows (1-3-5-7)
    [Row numbers|r|ENTER]: 1-2-4-6
    
    -----------------------------
    I 
    I I 
    I I I I 
    I I I I I I 
    -----------------------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 0
    Type in your Move in the Format: row.number (example: 2.1)
    Move [row.number]: 4.1
    
    ---------------------------
    I 
    I I 
    I I I I 
    I I I I I 
    ---------------------------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    ---------------------------
    I 
    I I 
    I I I I 
    I I I I I 
    ---------------------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    ------------------------
    I 
    -
    I I I I 
    I I I I I 
    ------------------------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    ------------------------
    I 
    -
    I I I I 
    I I I I I 
    ------------------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 0
    Type in your Move in the Format: row.number (example: 2.1)
    Move [row.number]: 4.4
    
    ----------------
    I 
    -
    I I I I 
    I 
    ----------------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: u
    
    ------------------------
    I 
    -
    I I I I 
    I I I I I 
    ------------------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    ----------------------
    I 
    -
    I I I 
    I I I I I 
    ----------------------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    ----------------------
    I 
    -
    I I I 
    I I I I I 
    ----------------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    ----------------
    I 
    -
    I I I 
    I I 
    ----------------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    ----------------
    I 
    -
    I I I 
    I I 
    ----------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    ------------
    I 
    -
    I 
    I I 
    ------------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    ------------
    I 
    -
    I 
    I I 
    ------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    ---------
    I 
    -
    I 
    -
    ---------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    ---------
    I 
    -
    I 
    -
    ---------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 0
    Type in your Move in the Format: row.number (example: 2.1)
    Move [row.number]: 1.1
    
    --------
    -
    -
    I 
    -
    --------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    --------
    -
    -
    I 
    -
    --------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    -------
    -
    -
    -
    -
    -------
    
    The Game is over!
    Return to main menu: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: q
</details>

<details>
    <summary>Spiel gegen NimPerfect</summary>
    
    -----------------------------------------
    Welcome to the Game!
    Select the Game Mode
    -----------------------------------------
    1) Start Test mode [t]
    2) Start Playing [p]
    [t|p]: p
    
    ---------------
    Select Opponent
    ---------------
    1) Play against Nim [n]
    2) Play against NimPerfect [np]
    [n|np]: n
    
    You have chosen Nim!
    
    -----------
    Select Rows
    -----------
    Select preferred numbers in format "number1-number2-..." (example: 1-3-5)
    Type in "r" to randomly generate the Rows
    Type in "ENTER" to use default Rows (1-3-5-7)
    [Row numbers|r|ENTER]: r
    
    -------------------------
    I I 
    I I 
    I I I 
    I I I I 
    -------------------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    -----------------------
    I I 
    I I 
    I I I 
    I I I 
    -----------------------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    -----------------------
    I I 
    I I 
    I I I 
    I I I 
    -----------------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 0
    Type in your Move in the Format: row.number (example: 2.1)
    Move [row.number]: 4.3
    
    ------------------
    I I 
    I I 
    I I I 
    -
    ------------------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    ------------------
    I I 
    I I 
    I I I 
    -
    ------------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    ----------------
    I 
    I I 
    I I I 
    -
    ----------------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: u
    
    ------------------
    I I 
    I I 
    I I I 
    -
    ------------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    ----------------
    I 
    I I 
    I I I 
    -
    ----------------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    ----------------
    I 
    I I 
    I I I 
    -
    ----------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    ---------------
    -
    I I 
    I I I 
    -
    ---------------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    ---------------
    -
    I I 
    I I I 
    -
    ---------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    -------------
    -
    I I 
    I I 
    -
    -------------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    -------------
    -
    I I 
    I I 
    -
    -------------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 0
    Type in your Move in the Format: row.number (example: 2.1)
    Move [row.number]: 2.1
    
    -----------
    -
    I 
    I I 
    -
    -----------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    -----------
    -
    I 
    I I 
    -
    -----------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    ---------
    -
    I 
    I 
    -
    ---------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    ---------
    -
    I 
    I 
    -
    ---------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 1
    The computer is making the a move...
    
    --------
    -
    -
    I 
    -
    --------
    
    Continue: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: 
    
    --------
    -
    -
    I 
    -
    --------
    
    Make move manually (0) or let the computer make the move(1)
    [0|1]: 0
    Type in your Move in the Format: row.number (example: 2.1)
    Move [row.number]: 3.1
    
    -------
    -
    -
    -
    -
    -------
    
    The Game is over!
    Return to main menu: ENTER
    End Game: "q" or "e"
    Undo last move: "u"
    [ENTER|q|u]: q
</details>

<details>
    <summary>Test-Modus</summary>
    
    -----------------------------------------
    Welcome to the Game!
    Select the Game Mode
    -----------------------------------------
    1) Start Test mode [t]
    2) Start Playing [p]
    [t|p]: t
    
    Game 1:
    Nim starts!
    Rows: 4 4 6 
    Nim should win!
    Nim has won!
    
    Game 2:
    NimPerfect starts!
    Rows: 2 2 3 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 3:
    NimPerfect starts!
    Rows: 2 4 4 5 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 4:
    Nim starts!
    Rows: 5 5 5 6 
    Nim should win!
    Nim has won!
    
    Game 5:
    Nim starts!
    Rows: 2 3 4 
    Nim should win!
    Nim has won!
    
    Game 6:
    Nim starts!
    Rows: 3 5 5 6 
    Nim should win!
    Nim has won!
    
    Game 7:
    Nim starts!
    Rows: 2 3 5 6 6 
    Nim should win!
    Nim has won!
    
    Game 8:
    NimPerfect starts!
    Rows: 4 4 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 9:
    NimPerfect starts!
    Rows: 2 2 4 5 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 10:
    NimPerfect starts!
    Rows: 3 4 5 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 11:
    NimPerfect starts!
    Rows: 3 3 4 6 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 12:
    NimPerfect starts!
    Rows: 2 2 4 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 13:
    Nim starts!
    Rows: 3 3 4 6 
    Nim should win!
    Nim has won!
    
    Game 14:
    Nim starts!
    Rows: 2 2 3 4 
    Nim should win!
    Nim has won!
    
    Game 15:
    NimPerfect starts!
    Rows: 2 3 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 16:
    NimPerfect starts!
    Rows: 3 5 6 
    NimPerfect should win!
    Nim has won!
    
    Game 17:
    NimPerfect starts!
    Rows: 4 5 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 18:
    NimPerfect starts!
    Rows: 2 3 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 19:
    NimPerfect starts!
    Rows: 2 2 3 4 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 20:
    NimPerfect starts!
    Rows: 2 4 5 5 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 21:
    Nim starts!
    Rows: 2 6 6 6 
    Nim should win!
    Nim has won!
    
    Game 22:
    Nim starts!
    Rows: 2 3 4 6 6 
    Nim should win!
    Nim has won!
    
    Game 23:
    NimPerfect starts!
    Rows: 3 4 4 5 6 
    NimPerfect should win!
    Nim has won!
    
    Game 24:
    Nim starts!
    Rows: 3 4 4 
    Nim should win!
    Nim has won!
    
    Game 25:
    Nim starts!
    Rows: 3 6 6 
    Nim should win!
    Nim has won!
    
    Game 26:
    NimPerfect starts!
    Rows: 3 3 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 27:
    NimPerfect starts!
    Rows: 3 3 6 6 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 28:
    Nim starts!
    Rows: 2 4 5 5 
    Nim should win!
    Nim has won!
    
    Game 29:
    NimPerfect starts!
    Rows: 3 4 5 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 30:
    NimPerfect starts!
    Rows: 2 4 4 5 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 31:
    Nim starts!
    Rows: 2 4 4 4 4 
    Nim should win!
    Nim has won!
    
    Game 32:
    NimPerfect starts!
    Rows: 3 3 4 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 33:
    NimPerfect starts!
    Rows: 2 4 4 5 5 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 34:
    Nim starts!
    Rows: 2 2 5 6 
    Nim should win!
    Nim has won!
    
    Game 35:
    Nim starts!
    Rows: 2 3 4 5 5 
    Nim should win!
    Nim has won!
    
    Game 36:
    NimPerfect starts!
    Rows: 4 4 6 6 6 
    NimPerfect should win!
    NimPerfect has won!
    
    Game 37:
    Nim starts!
    Rows: 2 2 4 5 
    Nim should win!
    Nim has won!
    
    Game 38:
    Nim starts!
    Rows: 2 3 5 6 
    Nim should win!
    Nim has won!
    
    Game 39:
    NimPerfect starts!
    Rows: 2 3 3 4 6 
    NimPerfect should win!
    Nim has won!
    
    Game 40:
    Nim starts!
    Rows: 3 5 6 6 
    Nim should win!
    Nim has won!
    
    Test Completed!
    Go back to main menu: ENTER
    End Game: "q" or "e"
    [ENTER|q|e]: q    
</details>