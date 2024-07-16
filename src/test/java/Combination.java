import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    static int callCount = 0;

    @Test
    public void Combination(){
        int n = 7; // 전체 카드 수
        int r = 5; // 선택할 카드 수
        int[] cards = new int[n];

        // 카드 배열 초기화 (1부터 7까지)
        for (int i = 0; i < n; i++) {
            cards[i] = i + 1;
        }

        List<List<Integer>> combinations = new ArrayList<>();
        generateCombinations(cards, r, 0, new ArrayList<>(), combinations);
        System.out.println(combinations.size());

        // 결과 출력
        for (List<Integer> combination : combinations) {
            System.out.println(combination);
        }

//        System.out.println("Total combinations: " + combinations.size());
    }

    private static void generateCombinations(int[] cards, int r, int start, List<Integer> currentCombination, List<List<Integer>> combinations) {
        callCount++;
        if (currentCombination.size() == r) {
            //5개가 되었을떄 들어감
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }
        for (int i = start; i < cards.length; i++) {
            currentCombination.add(cards[i]);
            generateCombinations(cards, r, i + 1, currentCombination, combinations);
            currentCombination.remove(currentCombination.size() - 1); // 마지막 요소 제거
        }
    }
}
