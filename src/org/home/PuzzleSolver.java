package org.home;

import java.util.*;

public class PuzzleSolver {
    private final int k;
    private final Collection<Integer> originalList;

    public PuzzleSolver(int k, Collection<Integer> originalList) {
        this.k = k;
        if (originalList.size() < 2) {
            throw new RuntimeException("Original list should contain at least 2 elements");
        }
        this.originalList = originalList;
    }

    public int maxLength() {
        Map<Integer, Integer> countByModK = countByModK();
        int count = 0;
        List<Integer> excludedMods = new ArrayList<>();
        for (Integer modK: countByModK.keySet()) {
            if (excludedMods.contains(modK)) {
                continue;
            }
            if (modK == 0 || modK + modK == k) {
                count += 1;
            }
            else if (countByModK.containsKey(k - modK)) {
                count += Math.max(countByModK.get(modK), countByModK.get(k - modK));
                int modToIgnore = modK;
                if (countByModK.get(modK) >= countByModK.get(k - modK)) {
                    modToIgnore = k - modK;
                }
                excludedMods.add(modToIgnore);
            }
            else {
                count += countByModK.get(modK);
            }
        }
        return count;
    }

    private Map<Integer, Integer> countByModK() {
        Map<Integer, Integer> countByModK = new HashMap<>();
        for (Integer elem : originalList) {
            int modK = elem % k;
            if (!countByModK.containsKey(modK)) {
                countByModK.put(modK, 0);
            }
            countByModK.put(modK, countByModK.get(modK) + 1);
        }
        return countByModK;
    }
}
