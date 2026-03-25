package com.jrkg.jrkgbites.utils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jrkg.jrkgbites.R;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001:\u0001\u0012B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003JE\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\u0002\u0010\u0011\u00a8\u0006\u0013"}, d2 = {"Lcom/jrkg/jrkgbites/utils/ToastUtils;", "", "<init>", "()V", "showCustomToast", "", "context", "Landroid/content/Context;", "message", "", "type", "Lcom/jrkg/jrkgbites/utils/ToastUtils$ToastType;", "durationMs", "", "gravity", "", "yOffset", "(Landroid/content/Context;Ljava/lang/String;Lcom/jrkg/jrkgbites/utils/ToastUtils$ToastType;Ljava/lang/Long;II)V", "ToastType", "app_debug"})
public final class ToastUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.utils.ToastUtils INSTANCE = null;
    
    private ToastUtils() {
        super();
    }
    
    /**
     * Shows a custom toast with a specific type and position.
     * @param durationMs If provided, will cancel the toast after this duration (e.g., 1000ms).
     * @param gravity The position of the toast on screen (default is BOTTOM).
     */
    public final void showCustomToast(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String message, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.utils.ToastUtils.ToastType type, @org.jetbrains.annotations.Nullable()
    java.lang.Long durationMs, int gravity, int yOffset) {
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007\u00a8\u0006\b"}, d2 = {"Lcom/jrkg/jrkgbites/utils/ToastUtils$ToastType;", "", "<init>", "(Ljava/lang/String;I)V", "SUCCESS", "ERROR", "INFO", "WARNING", "app_debug"})
    public static enum ToastType {
        /*public static final*/ SUCCESS /* = new SUCCESS() */,
        /*public static final*/ ERROR /* = new ERROR() */,
        /*public static final*/ INFO /* = new INFO() */,
        /*public static final*/ WARNING /* = new WARNING() */;
        
        ToastType() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.jrkg.jrkgbites.utils.ToastUtils.ToastType> getEntries() {
            return null;
        }
    }
}