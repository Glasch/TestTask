import junit.framework.TestCase;
import org.apache.commons.collections4.iterators.PermutationIterator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Copyright (c) Anton on 27.03.2019.
 */
public class ResolverUTest extends TestCase {

    public void testAllPossibleVariantsMemoryAndSpeed() {
        int j = 0;
        HashSet <ArrayList <Integer>> allPossiblePuzzles = findAllPossiblePuzzles(new ArrayList <>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 0)));
        for (ArrayList <Integer> in : allPossiblePuzzles) {
            Resolver resolver = new Resolver();
            int[] start = new int[in.size()];
            for (int i = 0; i < in.size(); i++) {
                start[i] = in.get(i);
            }
            int[] res = resolver.resolve(start);
            ArrayList <Integer> result = new ArrayList <>();
            for (int re : res) result.add(re);
            checkResolve(in, result);
            System.out.println(++j);
        }
    }

    private void checkResolve(ArrayList <Integer> in, ArrayList <Integer> res) {
        ArrayList <Integer> resolve = new ArrayList <>(res);
        ArrayList <Integer> input = new ArrayList <>(in);
        ArrayList <Integer> target = new ArrayList <>(Arrays.asList(1, 2, 3, 4, 0, 5, 6, 7));
        for (Integer integer : resolve) {
            int integerIndex = input.indexOf(integer);
            int zeroIndex = input.indexOf(0);
            input.set(integerIndex, 0);
            input.set(zeroIndex, integer);
        }
        assertEquals(target, input);
    }

    private HashSet <ArrayList <Integer>> findAllPossiblePuzzles(List <Integer> integers) { // org.apache.commons:commons-collections4:4.0
        HashSet <ArrayList <Integer>> allPossiblePuzzles = new HashSet <>();
        PermutationIterator <Integer> iterator = new PermutationIterator <>(integers);
        while (iterator.hasNext()) {
            allPossiblePuzzles.add(new ArrayList<>(iterator.next()));
        }
        return allPossiblePuzzles;
    }

}
