package com.example.threadlocalpoc.threadlocal;

public class MyThreadLocal {


    public static final ThreadLocal<String> userThreadLocal = new ThreadLocal<String> ();

    public static void setApiKey(String apiKey) {
        //logic to set api key
        //...
        userThreadLocal.set(apiKey);
    }

    public static String getApiKey() {
        return userThreadLocal.get();
    }

    public static void remove() {
        //logic to end a transaction
        //…
        userThreadLocal.remove();
    }
}

