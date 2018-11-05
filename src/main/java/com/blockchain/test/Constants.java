package com.blockchain.test;

/**
 * 常量
 */
public interface Constants {

    // zero fill with 4 chars...
    String ZERO_FILL_TEMPLATE = "%04d";

    String ZERO_FILL = "%.2f";

    String PASSWORD_SALT = "_lkb";

    class Concurrent {

        public static Long CURRENT_WORKER = 1L;
        public static Long WORKERS = 1L;

    }

}