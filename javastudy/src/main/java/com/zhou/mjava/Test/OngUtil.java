package com.zhou.mjava.Test;

/**
 * @author liqingzhou on 18/3/8
 */

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class OngUtil {

    private static final int[] intSize = {9, 99, 999, 9999, 99999, 999999};

    private static final char ZERO = '0';

    public static final int MAX_SUFFIX_LENGTH = 6;

    public static void checkSuffix(int suffix, int suffixLength) {
        Preconditions.checkArgument(suffixLength >= 0 && suffixLength <= MAX_SUFFIX_LENGTH,
                "suffix length should in [0, " + MAX_SUFFIX_LENGTH + "]");
        if (suffixLength > 0) {
            Preconditions.checkArgument(suffix >= 0, "suffix should not less than 0");
            Preconditions.checkArgument(size(suffix) <= suffixLength,
                    "suffix length should not large than %s", suffixLength);
        }
    }

    private static int size(int codeSize) {
        for (int i = 0; i < intSize.length; i++) {
            if (codeSize <= intSize[i]) {
                return i + 1;
            }
        }
        return Integer.MAX_VALUE;
    }

    public static String fixSize(int num, int numSize) {
        return numSize == 0 ? "" : Strings.padStart(String.valueOf(num), numSize, ZERO);
    }

}
