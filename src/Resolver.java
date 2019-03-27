import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Copyright (c) Anton on 21.03.2019.
 */
public class Resolver implements PuzzleResolver {
    private static final ArrayList <Integer> target = new ArrayList <>(Arrays.asList(1, 2, 3, 4, 0, 5, 6, 7));
    private LinkedBlockingQueue<Puzzle> puzzles = new LinkedBlockingQueue<>();
    private ArrayList <Integer> res;
    private boolean isResolved = false;
    private HashSet<Integer> checkedPuzzles = new HashSet <>();

    static ArrayList<Long> maxMemoryUsed = new ArrayList <>();

    @Override
    public int[] resolve(int[] start) {
        ArrayList <Integer> input = new ArrayList <>();
        for (int aStart : start) input.add(aStart);
        Puzzle puzzle = initPuzzle(input);
        if (puzzle.compareToTarget(target)) {
            return new int[0];
        }
        puzzles.add(puzzle);
        while (!isResolved) {
            doPossibleMoves(puzzles);
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < res.size(); i++) result[i] = res.get(i);
        return result;
    }

    private void doPossibleMoves(Queue <Puzzle> puzzles) {
        for (Puzzle puzzle : puzzles) {
            puzzle.getPossibleMoves().clear();
            puzzle.findPossibleMoves();
            for (Move move : puzzle.getPossibleMoves()) {
                Puzzle puzzleState = initPuzzle(move, puzzle);
                if (checkedPuzzles.contains(puzzleState.hashCode()))continue;
                if (puzzleState.compareToTarget(target)) {
                    isResolved = true;
                    res = puzzleState.getMoveTo();
                    return;
                }
                checkedPuzzles.add(puzzleState.hashCode());
                puzzleState.setPreviousMove(move);
                puzzles.add(puzzleState);
            }
            puzzles.remove(puzzle);
        }
    }

    private static Puzzle initPuzzle(ArrayList <Integer> input) {
        Puzzle puzzle = new Puzzle();
        for (Integer elementData : input) {
            puzzle.getPuzzleElements().add(new PuzzleElement(elementData));
        }
        puzzle.initRules();
        return puzzle;
    }

    private static Puzzle initPuzzle(Move move, Puzzle puzzle) {
        Puzzle puzzleState = new Puzzle();
        for (PuzzleElement element : puzzle.getPuzzleElements()) {
            if (element.getElementData() == 0) {
                puzzleState.getPuzzleElements().add(new PuzzleElement(move.getTo().getElementData()));
            } else if (Objects.equals(element.getElementData(), move.getTo().getElementData())) {
                puzzleState.getPuzzleElements().add(new PuzzleElement(0));
            } else
                puzzleState.getPuzzleElements().add(new PuzzleElement(element.getElementData()));
        }
        puzzleState.getMoveTo().addAll(puzzle.getMoveTo());
        puzzleState.getMoveTo().add(move.getTo().getElementData());
        puzzleState.initRules();
        return puzzleState;
    }
}



