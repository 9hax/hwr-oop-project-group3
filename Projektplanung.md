# Projektplanung 09.05.23

- Besprechung in Discord am 10.05.23 17:30 - 19:30

## Ansätze von Stefan

- Runde, in der Strike/Spare geworfen wurde, bekommt von den folgenden Runden die Punktzahl übermittelt und berechnet die Punkte selbst
- Punkte werden auch weitergegeben, selbst wenn kein Spare oder Strike. Es erfolgt nur keine Berechnung
- Jede Runde kann maximal 2x Bonuspunkte erhalten

### 10. Runde

- erstmal nicht beachten
- Würfe geben sich trotzdem die Bonuspunkte weiter, nehmen diese aber nicht auf
- für 3. Wurf muss mindestens ein Spare geworfen wurden sein

## Weitere Überlegungen am 29.5.2023
- maximale Spiellänge bis 42 - alle Runden mod10=0 sind 10te Runden
- Punkte werden weiter aufaddiert
  - danach ist Spiel beendet

## Nach Implementierung der Main-Methode stachen folgende Usability-Issues hervor:
- [x] Runden# nicht praktisch (Start bei 1 ist besser)
- [x] Wer ist dran?
- [x] UserInputs sanitizen
- [x] Exception Handling bei invalid throw combinations
- [ ] ScoreOutput nur nach Rundenende
- [x] Strike/Spare ausgeben
- [ ] das wars doch soweit (we are proud of our work)

## Vorgang
- Spiellogik
- UI
- Runde 10
- Gewinner ermitteln

## genereller Aufbau

- Spiel beinhaltet Spieler
- Spieler hat Runden
- Runden bestehen aus Würfen
- Würfe speichern Anzahl umgefallener Pins

# Übersicht über Klassen

## Wurf
### Attributes
int: Anzahl umgefallener Zieleinheiten
### Actions
- setFallenPins

## Runde
### Attributes
int: Zählervariable um Bonuspunkteberechnung zu limitieren
Liste mit 1-3 Würfen
Rundentyp (Strike, Spare, Normal)
### Actions
- Rundentyp aktualisieren
- Rundenpunktzahl berechnen

## Spieler
### Attributes
String: name
Liste mit 10 Runden
### Actions
- Gesamtpunktzahl berechnen
- Rundennummer ermitteln

## Spiel
### Attributes
Liste mit unbregenzter Anzahl an Spielern
Aktueller Spieler

### Actions
- Gewinner ermitteln
- Spiel initialisieren
- Rundennummer ermitteln


