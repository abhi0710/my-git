package com.abhi0710.a.d.t.l;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import org.apache.commons.lang3.StringUtils;

public class LargestRegtangleInHistogram {


    public static void main(String[] args) {

        int n = 6;
        int hist[] = generateIntsArrays(n);

        System.out.println(StringUtils.join(hist, ','));

        int maxArea = 0 ;

        for (int i = 0 ; i < n ; i++) {
            int minHeight = hist[i];
            for (int j = i; j < n ; j++) {
                minHeight = Math.min(minHeight, hist[j
                    ]);

                maxArea = Math.max(maxArea, minHeight * (j - i + 1));

            }
        }
        System.out.println(maxArea);
    }

    private static int minHeight(int[] hist, int i, int j) {
        int minHeight =hist[i];

        while(++i <= j) {
            minHeight = Math.min(minHeight, hist[i]);
        }

        return minHeight;
    }

    private static int[] generateIntsArrays(int n) {
        int array[] = new int[n];

        while (--n >= 0)
            array[n] = ThreadLocalRandom.current().nextInt(1,20);

        return array;
    }
}