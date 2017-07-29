# mark-the-date

A historical quiz where the player guesses the time period of specific events from their description. A more accurate, faster guess awards more points.

## Users:

Player

## User actions

- Start a new game.
- See the current time remaining (no action required).
- Select answers by radio button.
- Select answers to questions until the time runs out.
- View their score.
- Play again with a new set of questions.


## Core functionality:

- Introduction during loading screen.
- "One click start" that begins a new game. Little to no setup required from the player.
- Countdown timer until the game ends.
- A quicker guess awards more points.
- User presented with 4 possible answers.
  - One will be correct. Awards 50 points (reduced by time taken).
  - The remaining answers will be randomly generated (but will be 'sanity checked').
  - If one of the random answers is close to the correct answer, it will awards points in proportion to distance from the correct answer.
- When selecting an answer, the next question will automatically show.

## 'Nice to have' functionality

- Select answers by keyboard or by mouse.
- Persistent high score.
- Tags that sort events into categories.
- Pictures for each question
- Summary at the end, display right/wrong answers.

## Structure

- Events are held in JSON within the .jar
- Data has been parsed from wikipedia.
