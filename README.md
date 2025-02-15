# HWR OOP Lecture Project - Bowling

This repository contains a student project created for an ongoing lecture on object-oriented programming with Java at 
HWR Berlin (summer term 2023).

> :warning: This code is for educational purposes only. Do not rely on it!

## Abstract

This application provides the logic of a bowling game. 
The player has to give the number of fallen pins to the program. 
The program then calculates the points and the bonus points for every round.
Multiple Players are supported.

### Problems
Test driven development is hard to grasp at first, but easier the more time you spend on it.
PITest doesn't always work on java20 yet.

## Feature List
- play a bowling game
- multiplayer and single player are supported
- robust error handling and input sanitization
- text-mode in- and output

[TODO]: <> (Add a new row to the table for every completed feature.)

| Number | Feature       | Tests       |
|--------|---------------|-------------|
| 1      | Throws        | 4           |
| 2      | Rounds        | 20          |
| 3      | Player        | 7           |
| 4      | Game          | 4           |
| 5      | IOAdapter     | 10          |
| 6      | TextUI        | 8           |
| 7      | Lifecycle     | 1/1 ignored |
| 8      | Persistence   | 3           |
| 9      | HighScores    | 4           |



## Additional Dependencies

[TODO]: <> (Add a new row to the table for every required dependency.)

| Number | Dependency Name                    | Dependency Description                                                    | Why is it necessary?         |
|--------|------------------------------------|---------------------------------------------------------------------------|------------------------------|
| 1      | Java-Compatible JVM implementation | A JVM implementation that supports reading Java Class Files from Javac17. | Provides runtime environment |
| 2      | Google GSON                        | A Simple JSON serialization library.                                      | Persistence                  |



## Contributing

### A test is written before the function gets implemented!