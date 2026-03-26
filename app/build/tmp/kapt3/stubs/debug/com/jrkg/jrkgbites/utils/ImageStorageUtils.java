package com.jrkg.jrkgbites.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005J$\u0010\r\u001a\u0004\u0018\u00010\u00012\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005J \u0010\u000f\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0007\u001a\u00020\u0005J\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/jrkg/jrkgbites/utils/ImageStorageUtils;", "", "<init>", "()V", "LOGO_PREFIX", "", "generateLogoFileName", "restaurantId", "getDrawableResourceByName", "", "context", "Landroid/content/Context;", "name", "getLogo", "restaurantName", "saveImageToInternalStorage", "uri", "Landroid/net/Uri;", "getLocalImageFile", "Ljava/io/File;", "fileName", "app_debug"})
public final class ImageStorageUtils {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String LOGO_PREFIX = "restaurant_logo_";
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.utils.ImageStorageUtils INSTANCE = null;
    
    private ImageStorageUtils() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String generateLogoFileName(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
        return null;
    }
    
    /**
     * Tries to find a matching drawable resource for a given restaurant name.
     * It tries several common naming patterns to increase the chance of a match.
     */
    public final int getDrawableResourceByName(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String name) {
        return 0;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getLogo(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String restaurantId, @org.jetbrains.annotations.Nullable()
    java.lang.String restaurantName) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.String saveImageToInternalStorage(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.File getLocalImageFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    java.lang.String fileName) {
        return null;
    }
}