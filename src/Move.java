/**
 * Copyright (c) Anton on 24.03.2019.
 */
public class Move {
    private PuzzleElement from;
    private PuzzleElement to;

    public Move(PuzzleElement from, PuzzleElement to) {
        this.from = from;
        this.to = to;
    }

    public PuzzleElement getFrom() {
        return from;
    }

    public PuzzleElement getTo() {
        return to;
    }


}
