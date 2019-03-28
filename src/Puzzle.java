import java.util.ArrayList;
import java.util.Objects;

/**
 * Copyright (c) Anton on 24.03.2019.
 */
public class Puzzle {
    private ArrayList <PuzzleElement> puzzleElements = new ArrayList <>();
    private ArrayList <Move> possibleMoves = new ArrayList <>();
    private Move previousMove;
    private ArrayList <Integer> movesTo = new ArrayList <>();

    void initRules() {
        puzzleElements.get(0).setElementsToMove(puzzleElements.get(1), puzzleElements.get(2));
        puzzleElements.get(1).setElementsToMove(puzzleElements.get(0), puzzleElements.get(2), puzzleElements.get(3));
        puzzleElements.get(2).setElementsToMove(puzzleElements.get(0), puzzleElements.get(1), puzzleElements.get(5));
        puzzleElements.get(3).setElementsToMove(puzzleElements.get(1), puzzleElements.get(4), puzzleElements.get(6));
        puzzleElements.get(4).setElementsToMove(puzzleElements.get(3), puzzleElements.get(5));
        puzzleElements.get(5).setElementsToMove(puzzleElements.get(4), puzzleElements.get(2), puzzleElements.get(7));
        puzzleElements.get(6).setElementsToMove(puzzleElements.get(3), puzzleElements.get(7));
        puzzleElements.get(7).setElementsToMove(puzzleElements.get(6), puzzleElements.get(5));
    }

    boolean compareToTarget(ArrayList <Integer> target) {
        for (int i = 0; i < 8; i++) {
            if (!Objects.equals(puzzleElements.get(i).getElementData(), target.get(i))) return false;
        }
        return true;
    }

    ArrayList <PuzzleElement> getPuzzleElements() {
        return puzzleElements;
    }

    ArrayList <Move> getPossibleMoves() {
        return possibleMoves;
    }

    void findPossibleMoves() {
        for (PuzzleElement elementFrom : this.getPuzzleElements()) {
            if (elementFrom.getElementData() == 0) {
                for (PuzzleElement elementTo : elementFrom.getElementsToMove()) {
                    if (!isMovingBack(elementTo)) {
                        this.getPossibleMoves().add(new Move(elementTo));
                    }
                }
                return;
            }
        }
    }

    @Override
    public int hashCode() {
        return puzzleElements.get(0).getElementData() * 10000000
                + puzzleElements.get(1).getElementData() * 1000000
                + puzzleElements.get(2).getElementData() * 100000
                + puzzleElements.get(3).getElementData() * 10000
                + puzzleElements.get(4).getElementData() * 1000
                + puzzleElements.get(5).getElementData() * 100
                + puzzleElements.get(6).getElementData() * 10
                + puzzleElements.get(7).getElementData()
                ;
    }

    ArrayList <Integer> getMoveTo() {
        return movesTo;
    }

    private boolean isMovingBack(PuzzleElement elementToMove) {
        return previousMove != null && previousMove.getTo().getElementData().equals(elementToMove.getElementData());
    }

    void setPreviousMove(Move previousMove) {
        this.previousMove = previousMove;
    }
}
