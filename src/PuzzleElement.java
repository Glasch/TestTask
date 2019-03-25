import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) Anton on 24.03.2019.
 */
public class PuzzleElement {
  private Integer elementData;
  private final List<PuzzleElement> elementsToMove = new ArrayList <>();

    public PuzzleElement(Integer element) {
        this.elementData = element;
    }

    public void setElementsToMove(PuzzleElement firstElement, PuzzleElement secondElement){
        elementsToMove.add(firstElement);
        elementsToMove.add(secondElement);
    }


    public void setElementsToMove(PuzzleElement firstElement, PuzzleElement secondElement, PuzzleElement thirdElement){
        elementsToMove.add(firstElement);
        elementsToMove.add(secondElement);
        elementsToMove.add(thirdElement);
    }

    public Integer getElementData() {
        return elementData;
    }

    public void setElementData(Integer elementData) {
        this.elementData = elementData;
    }

    public List <PuzzleElement> getElementsToMove() {
        return elementsToMove;
    }

}
