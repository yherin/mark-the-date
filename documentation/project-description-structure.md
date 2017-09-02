# mark-the-date

A historical quiz where the player guesses the time period of specific events from their description. A more accurate, faster guess awards more points.

# How to play
- Open the .jar file.
 - On Linux you will most likely need do to `chmod +x mark-the-date-XXX.jar`.
- Read the description. Click continue and start playing!

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

# Implementation and description of application structure.

## Data Structure

- Game data is held in JSON format within the .jar
- Data has been parsed from wikipedia.

## MVC - Model-Viewer-Controller design pattern

- Implementation of GUI/data/logic has been modelled on the [Model–view–controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) architecture pattern.
- This aims to separate the core functions into three logical parts. Classes are divided in broad packages of `data`,`gui`,`logic` accordingly.

## A note on test coverage.

- Test coverage is slightly below the desired 90%. This is due to the recent implementation of
'user friendly' error messages which displays UI elements. These are not easily tested. Previously
the line coverage was higher than 90%.

## Application 'walkthrough'

## Setup
1. Main method creates *GamePlayer* which begins the setup.
2. Helper thread which handles showing possible setup errors graphically is invoked.
3. Setup begins on main thread.
  - Loader subclasses load data from files to JSONObjects.
  - Creator classes (*EventCreator*, *QuestionCreator* etc.) process the data.
4. All of the setup data is then encapsulated in a *Quiz* object, which is a member attribute of *QuizMaster*. *QuizMaster* forms the view element of the MVC design pattern in my application.
5. GUI components are created and held within a HashMap accessible by static method. *GameWindow* (MVC view component) is created, holding references to these components.
6. Controller *UserInputController* is created. ActionListeners are added to GUI components.
7. As setup has finished, setup helper thread is killed. GUI thread is invoked.
8. GUI is loaded, game is ready to play.

## Gameplay
1. Currently the game will continue until the player provides answers to 15 questions. They can then play again or quit.
2. If they choose to play again, *UserInputController* calls the `fetchNewQuiz` method offered by *QuizMaster* which generates a new quiz using questions that were not yet asked. The exception to this is that if all questions have been asked, then the same question can appear twice.
3. In principle, the player can play continuously.

## Class diagrams.
### Data Model
![Data model diagram ()](https://github.com/yherin/mark-the-date/blob/master/documentation/mark-the-date-data.png)
### MVC implemenation diagram
![MVC diagram](https://github.com/yherin/mark-the-date/blob/master/documentation/mvc.png)

## Sequence diagrams.
![userClicksAnswer](https://github.com/yherin/mark-the-date/blob/master/documentation/userClicksAnswer.png)
![userClicksPlayAgain](https://github.com/yherin/mark-the-date/blob/master/documentation/userClicksPlayAgain.png)
