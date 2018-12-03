package com.zhou.mjava.sample.enumtest;

/**
 * @author liqingzhou on 18/10/18
 */
public enum ExtendedOperation implements Operation {
    EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x,y);
        }
    },
    MOD("%"){

        public double apply(double x, double y) {
            return x%y;
        }
    },
    TIMES("*"){
        @Override
        public double apply(double x, double y) {
            return 0;
        }
    };

    private final String symbol;

    ExtendedOperation(String symbol) {
        this.symbol = symbol;
    }

}
