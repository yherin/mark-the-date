# mark-the-date

A historical quiz where the player guesses the time period of specific events from their description. A more accurate, faster guess awards more points.

# Specification

## Users:

- Player

## Use cases

- Read the introduction and start the new game.
- Choose an answer to a question.
- Play again with a new set of questions.
- Quit.


## Core functionality:

- Introduction during loading screen. __done__
- "One click start" that begins a new game. Little to no setup required from the player. __done__
- Countdown timer until the game ends.
- A quicker guess awards more points. __done__
- User presented with 4 possible answers. __done__
  - The remaining answers will be randomly generated (but will be 'sanity checked'). __done__
  - If one of the random answers is close to the correct answer, it will awards points in proportion to distance from the correct answer. __done__
- When selecting an answer, the next question will automatically show. __done__

# Implementation

## Data Structure

- Events are held in JSON within the .jar
- Data has been parsed from wikipedia.

## MVC - Model-Viewer-Controller architecture

- Implementation of GUI/data/logic has been modelled on the [Model–view–controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) architecture pattern.
- This aims to separate the core functions into three logical parts. Classes are divided in broad packages of `data`,`gui`,`logic` accordingly.



## Class diagrams.
### Data Model
![Data model diagram ()](https://github.com/yherin/mark-the-date/blob/master/documentation/mark-the-date-data.png)
### MVC implemenation diagram
![MVC diagram](https://github.com/yherin/mark-the-date/blob/master/documentation/mvc.png)

## Sequence diagrams.
![userClicksAnswer](https://github.com/yherin/mark-the-date/blob/master/documentation/userClicksAnswer.png)
![userClicksPlayAgain](https://github.com/yherin/mark-the-date/blob/master/documentation/userClicksPlayAgain.png)
