import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) Anton on 24.03.2019.
 */
class PuzzleElement {
    private Integer elementData;
    private final List <PuzzleElement> elementsToMove = new ArrayList <>();

    PuzzleElement(Integer element) {
        this.elementData = element;
    }

    void setElementsToMove(PuzzleElement firstElement, PuzzleElement secondElement) {
        elementsToMove.add(firstElement);
        elementsToMove.add(secondElement);
    }

    void setElementsToMove(PuzzleElement firstElement, PuzzleElement secondElement, PuzzleElement thirdElement) {
        elementsToMove.add(firstElement);
        elementsToMove.add(secondElement);
        elementsToMove.add(thirdElement);
    }

    Integer getElementData() {
        return elementData;
    }

    List <PuzzleElement> getElementsToMove() {
        return elementsToMove;
    }
}
