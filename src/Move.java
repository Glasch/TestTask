/**
 * Copyright (c) Anton on 24.03.2019.
 */
class Move {
    private PuzzleElement to;

    Move(PuzzleElement to) {
        this.to = to;
    }

    PuzzleElement getTo() {
        return to;
    }
}
