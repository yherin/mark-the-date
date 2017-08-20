# mark-the-date

A historical quiz where the player guesses the time period of specific events from their description. A more accurate, faster guess awards more points.

# Code status

## On pressing 'Run Project':
- Questions are chosen randomly and shown in a basic UI, with answer buttons and a question timer.
- The buttons record the answer and load the next question.
- Score is logged to `System.out`.
- After 15 questions, score is shown. User can either select 'quit' or 'play again', which loads a set of questions different from the previous set.

## Issues
- Score is missing from final screen (JTextArea) not yet added.

## Code quality
- The code documentation (Javadoc) is not as good as I would like.
- Single-Responsibility-Principle is followed fairly well. Most classes have only 1 public method (excl. some getters), so they have only 1 responsibility.

# Specification

## Users:

Player

## User actions

- Start a new game.
- See the current time remaining (no action required).
- ~~Select answers by radio button.~~ Select answers by 'normal'/JButtons.
- Select answers to questions until the time runs out.
- View their score.
- Play again with a new set of questions.


## Core functionality:

- Introduction during loading screen.
- "One click start" that begins a new game. Little to no setup required from the player. __done__
- Countdown timer until the game ends.
- A quicker guess awards more points. __almsot done__
- User presented with 4 possible answers. __done__
  - One will be correct. Awards 500 points (reduced by time taken).
  - The remaining answers will be randomly generated (but will be 'sanity checked'). __done__
  - If one of the random answers is close to the correct answer, it will awards points in proportion to distance from the correct answer. __done__
- When selecting an answer, the next question will automatically show. __done__

## 'Nice to have' functionality

- Select answers by keyboard or by mouse.
- Persistent high score.
- Tags that sort events into categories.
- Pictures for each question
- Summary at the end, display right/wrong answers.

# Implementation

## Data Structure

- Events are held in JSON within the .jar
- Data has been parsed from wikipedia.

## MVC - Model-Viewer-Controller architecture

- Implementation of GUI/data/logic has been modelled on the [Model–view–controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) architecture pattern.
- This aims to separate the core functions into three logical parts. Classes are divided in broad packages of `data`,`gui`,`logic` accordingly.



## Class diagrams.
### Sadly not yet complete class diagram.
![Data model diagram ()](https://github.com/yherin/mark-the-date/blob/master/documentation/mark-the-date-data.png)
### MVC implemenation diagram with relevant methods.
![MVC diagram](https://github.com/yherin/mark-the-date/blob/master/documentation/mvc.png)
