import java.util.ArrayList;
import java.util.Objects;

/**
 * Copyright (c) Anton on 24.03.2019.
 */
public class Puzzle {
    private ArrayList <PuzzleElement> puzzleElements = new ArrayList <>();
    private ArrayList <Move> possibleMoves = new ArrayList <>();
    private Move previousMove;
    private ArrayList<Integer> movesTo = new ArrayList <>();


    void initRules() {
        for (PuzzleElement puzzleElement : puzzleElements) {
            if (puzzleElements.indexOf(puzzleElement) == 0) {
                puzzleElement.setElementsToMove(puzzleElements.get(1), puzzleElements.get(2));
            } else if (puzzleElements.indexOf(puzzleElement) == 1) {
                puzzleElement.setElementsToMove(puzzleElements.get(0), puzzleElements.get(2), puzzleElements.get(3));
            } else if (puzzleElements.indexOf(puzzleElement) == 2) {
                puzzleElement.setElementsToMove(puzzleElements.get(0), puzzleElements.get(1), puzzleElements.get(5));
            } else if (puzzleElements.indexOf(puzzleElement) == 3) {
                puzzleElement.setElementsToMove(puzzleElements.get(1), puzzleElements.get(4), puzzleElements.get(6));
            } else if (puzzleElements.indexOf(puzzleElement) == 4) {
                puzzleElement.setElementsToMove(puzzleElements.get(3), puzzleElements.get(5));
            } else if (puzzleElements.indexOf(puzzleElement) == 5) {
                puzzleElement.setElementsToMove(puzzleElements.get(4), puzzleElements.get(2), puzzleElements.get(7));
            } else if (puzzleElements.indexOf(puzzleElement) == 6) {
                puzzleElement.setElementsToMove(puzzleElements.get(3), puzzleElements.get(7));
            } else if (puzzleElements.indexOf(puzzleElement) == 7) {
                puzzleElement.setElementsToMove(puzzleElements.get(6), puzzleElements.get(5));
            }
        }
    }

    public boolean compareToTarget(ArrayList <Integer> target) {
        for (int i = 0; i < 8; i++) {
            if (!Objects.equals(puzzleElements.get(i).getElementData(), target.get(i))) return false;
        }
        return true;
    }


    public ArrayList <PuzzleElement> getPuzzleElements() {
        return puzzleElements;
    }

    public ArrayList <Move> getPossibleMoves() {
        return possibleMoves;
    }

    public void findPossibleMoves() {
        for (PuzzleElement elementFrom : this.getPuzzleElements()) {
            if (elementFrom.getElementData() == 0) {
                for (PuzzleElement elementTo : elementFrom.getElementsToMove()) {
                    if (!isMovingBack(elementFrom, elementTo)) {
                        this.getPossibleMoves().add(new Move(elementFrom, elementTo));
                    }
                }
                return;
            }
        }
    }

    public ArrayList <Integer> getMoveTo() {
        return movesTo;
    }

    public void setMoveTo(ArrayList <Integer> moveTo) {
        this.movesTo = moveTo;
    }

    private boolean isMovingBack(PuzzleElement elementFrom, PuzzleElement elementToMove) {
        if (previousMove == null) return false;
        return previousMove.getTo().getElementData().equals(elementToMove.getElementData());
    }

    public Move getPreviousMove() {
        return previousMove;
    }

    public void setPreviousMove(Move previousMove) {
        this.previousMove = previousMove;
    }
}
