package com.shadow.context;

/**
 * @ClassName UserContext
 * @Description TODO
 * @Author CareShadow
 * @Date 2023/11/25 20:10
 * @Version 1.0
 **/
public final class UserContext {
    private static final ThreadLocal<String> user = new ThreadLocal<>();

    public static void add(String username) {
        user.set(username);
    }

    public static void remove() {
        user.remove();
    }

    public static String getCurrentUserName() {
        return user.get();
    }
}
