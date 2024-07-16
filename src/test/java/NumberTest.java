import org.junit.jupiter.api.Test;

import java.util.*;

public class NumberTest {
    @Test
    public void test(){
        int[] arr = {1, 1, 2 ,2 ,5 ,6 ,7};
//        int [] arr2 = new int[5];
        ArrayList<Integer> arr2 = new ArrayList<>();
        int max = 0;
        for(int i = 1; i<=arr.length; i++){
            for(int a = 1; a<=arr.length; a++){
                if(i < a){
                    for(int b = 1; b<=arr.length; b++){
                        if(b != i && b != a){
                            arr2.add(arr[b-1]);
                        }
                    }
                    System.out.println(arr2);
                    //해당하는 배열이 어느 값에 속하는가
                    arr2.clear();
                }
            }
        }

    }
}
