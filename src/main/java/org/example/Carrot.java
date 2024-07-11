package org.example;

import java.util.ArrayList;
import java.util.List;

public class Carrot {
    public static void main(String[] args) {
        int[] carrotWeight = {5, 3, 4, 1, 2};
        int maxWeightPerTrip = 5;
        int maxTrips = 10;

        List carrotList = new ArrayList<>();

        for (int i = 0; i < carrotWeight.length; i++) {
            int length = maxWeightPerTrip / carrotWeight[i];
            for (int j = 0; j < length; j++) {
                System.out.print(carrotWeight[i] + " ");
                carrotList.add(carrotWeight[i]);
            }
        }

        System.out.println();

        int[] weights = new int[carrotList.size()];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (int) carrotList.get(i);
        }

        List<Integer> bestCombination = findBestCombination(weights, 5);
        System.out.println("Лучшая комбинация из морковок: " + bestCombination);
        System.out.println("За " + maxTrips + " ходок, заяц может принести: " +
                                        bestCombination.size() * maxTrips + " морковок");

    }

    public static List<Integer> findBestCombination(int[] weights, int maxWeight) {
        long start = System.nanoTime();
        List<Integer> bestCombination = new ArrayList<>();
        findCombination(weights, 0, new ArrayList<>(), 0, maxWeight, bestCombination);
        long duration = System.nanoTime() - start;
        System.out.println("Время выполнения: " + duration + " наносекунд");
        return bestCombination;
    }

    private static void findCombination(int[] weights, int startIndex, List<Integer> currentCombination, int currentWeight, int maxWeight, List<Integer> bestCombination) {
        if (currentWeight <= maxWeight && currentCombination.size() > bestCombination.size()) {
            bestCombination.clear();
            bestCombination.addAll(currentCombination);
        }

        for (int i = startIndex; i < weights.length; i++) {
            if (currentWeight + weights[i] <= maxWeight) {
                currentCombination.add(weights[i]);
                findCombination(weights, i + 1, currentCombination, currentWeight + weights[i], maxWeight, bestCombination);
                currentCombination.remove(currentCombination.size() - 1);
            }
        }
    }
}
