package org.ftcteam.internal;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpModeManager;

public class FtcSimpleBootstrap {
    public static void configure(boolean debug, @NonNull OpModeManager mgr,
                                 @IdRes int RelativeLayout, @Nullable Object... objects) {
        Bindings.setRelativeLayout(RelativeLayout);
        Bindings.setOpModeManager(mgr);

        boolean lastWasString = false;
        String lastString = "";
        if (objects != null) {
            for (Object o : objects) {
                if (!lastWasString) {
                    if (o instanceof String) {
                        lastString = (String) o;
                    } else {
                        if (debug) {
                            throw new IllegalArgumentException("Invalid mismatch of elements");
                        } else {
                            Log.i(FtcSimpleBootstrap.class.getSimpleName(),
                                    "Invalid mismatch of elements after \"" + lastString + "\"");
                        }
                    }
                }

                if (lastWasString) {
                    Bindings.register(lastString, o);
                }

                lastWasString = o instanceof String;
            }
        }

        AnnotationFtcRegister.loadOpModes(mgr);
    }

    public static void configure(@NonNull OpModeManager mgr, @IdRes int RelativeLayout, @Nullable Object... objects) {
        configure(false, mgr, RelativeLayout, objects);
    }
}
