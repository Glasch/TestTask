import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Copyright (c) Anton on 21.03.2019.
 */
public class Resolver implements PuzzleResolver {
    static ArrayList <Integer> target = new ArrayList <>(Arrays.asList(1, 2, 3, 4, 0, 5, 6, 7));
    static LinkedBlockingQueue puzzles = new LinkedBlockingQueue();

    @Override
    public int[] resolve(int[] start) {
        return new int[0];
    }

    public static void main(String[] args) {
        Resolver resolver = new Resolver();
        ArrayList <Integer> input = new ArrayList <>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7));
        Puzzle puzzle = initPuzzle(input);
        if (puzzle.compareToTarget(target)) {
            System.out.println("FOUND");
            System.exit(0);
        }
        puzzles.add(puzzle);
        while (!puzzles.isEmpty()) {
            resolver.doPossibleMoves(puzzles);
        }
    }

    private void doPossibleMoves(Queue <Puzzle> puzzles) {
        for (Puzzle puzzle : puzzles) {
            puzzle.getPossibleMoves().clear();
            puzzle.findPossibleMoves();
            for (Move move : puzzle.getPossibleMoves()) {
                Puzzle puzzleState = initPuzzle(move, puzzle);
                if (puzzleState.compareToTarget(target)) {
                    System.out.println(puzzleState.getMoveTo());
                    System.exit(0);
                }
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



