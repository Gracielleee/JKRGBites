package com.jrkg.jrkgbites.domain;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007Jd\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00a2\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u000bH\u0002J \u0010\u001a\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u00132\u0006\u0010\u001c\u001a\u00020\u000bH\u0002J\u0016\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010 \u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u0016\u0010!\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u001fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/jrkg/jrkgbites/domain/RestaurantManager;", "", "restaurantDao", "Lcom/jrkg/jrkgbites/data/RestaurantDao;", "restaurantRepository", "Lcom/jrkg/jrkgbites/data/RestaurantRepository;", "<init>", "(Lcom/jrkg/jrkgbites/data/RestaurantDao;Lcom/jrkg/jrkgbites/data/RestaurantRepository;)V", "addRestaurant", "", "name", "", "category", "cuisine", "level", "location", "lat", "lng", "photoBitmap", "Landroid/graphics/Bitmap;", "tags", "", "context", "Landroid/content/Context;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap;Ljava/util/List;Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateStringId", "savePhotoToInternalStorage", "bitmap", "filename", "toggleFavorite", "restaurantId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "toggleNeverAgain", "addToNeverAgain", "app_debug"})
public final class RestaurantManager {
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantDao restaurantDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantRepository restaurantRepository = null;
    
    public RestaurantManager(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.RestaurantDao restaurantDao, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.RestaurantRepository restaurantRepository) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addRestaurant(@org.jetbrains.annotations.NotNull()
    java.lang.String name, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String cuisine, @org.jetbrains.annotations.NotNull()
    java.lang.String level, @org.jetbrains.annotations.NotNull()
    java.lang.String location, @org.jetbrains.annotations.NotNull()
    java.lang.String lat, @org.jetbrains.annotations.NotNull()
    java.lang.String lng, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap photoBitmap, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> tags, @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.String generateStringId() {
        return null;
    }
    
    private final void savePhotoToInternalStorage(android.content.Context context, android.graphics.Bitmap bitmap, java.lang.String filename) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object toggleNeverAgain(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addToNeverAgain(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}