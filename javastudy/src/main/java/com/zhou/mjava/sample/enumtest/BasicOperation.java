package com.zhou.mjava.sample.enumtest;

import com.sun.tools.corba.se.idl.constExpr.Minus;

/**
 * @author liqingzhou on 18/10/18
 */
public enum BasicOperation implements Operation {
    PLUS("+") {
        public double apply(double x, double y) {
            return x+y;
        }
    },
    MINUS("-"){

        public double apply(double x, double y) {
            return 0;
        }
    },
    TIMES("*"){
        @Override
        public double apply(double x, double y) {
            return 0;
        }
    };

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

}
