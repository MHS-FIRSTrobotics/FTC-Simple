package org.ftcteam.internal;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;

import java.util.HashMap;

public final class Bindings {
    private static int cRelativeLayout;
    private static OpModeManager cOpModeManager;
    private static final HashMap<String, Object> cMap = new HashMap<>();

    static void setRelativeLayout(@IdRes int id) {
        cRelativeLayout = id;
    }

    static void register(@NonNull String key, @NonNull Object value) {
        if (key.equals("")) {
            throw new IllegalArgumentException();
        }

        cMap.put(key, value);
    }

    @IdRes
    public static int RelativeLayout() {
        return cRelativeLayout;
    }

    public static Object get(@NonNull String key) {
        if (isPresent(key)) {
            return cMap.get(key);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static <T> T get(T type, @NonNull String key) {
        try {
            return (T) get(key);
        } catch (ClassCastException ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    public static boolean isPresent(String key) {
        return cMap.containsKey(key);
    }

    static void setOpModeManager(@NonNull OpModeManager opModeManager) {
        cOpModeManager = opModeManager;
    }

    @NonNull
    public static OpModeManager OpModeManager() {
        return cOpModeManager;
    }
}
