package com.jrkg.jrkgbites;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.jrkg.jrkgbites.data.local.RestaurantDao;
import com.jrkg.jrkgbites.data.local.RestaurantRatingDao;
import com.jrkg.jrkgbites.data.local.FavoriteRestaurantDao;
import com.jrkg.jrkgbites.data.local.NeverAgainRestaurantDao;
import com.jrkg.jrkgbites.model.Restaurant;
import com.jrkg.jrkgbites.model.RestaurantRating;
import com.jrkg.jrkgbites.model.FavoriteRestaurantId;
import com.jrkg.jrkgbites.model.NeverAgainRestaurantId;
import com.jrkg.jrkgbites.utils.Converters;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&J\b\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u000bH&\u00a8\u0006\r"}, d2 = {"Lcom/jrkg/jrkgbites/AppDatabase;", "Landroidx/room/RoomDatabase;", "<init>", "()V", "restaurantDao", "Lcom/jrkg/jrkgbites/data/local/RestaurantDao;", "restaurantRatingDao", "Lcom/jrkg/jrkgbites/data/local/RestaurantRatingDao;", "favoriteRestaurantDao", "Lcom/jrkg/jrkgbites/data/local/FavoriteRestaurantDao;", "neverAgainRestaurantDao", "Lcom/jrkg/jrkgbites/data/local/NeverAgainRestaurantDao;", "Companion", "app_debug"})
@androidx.room.Database(entities = {com.jrkg.jrkgbites.model.Restaurant.class, com.jrkg.jrkgbites.model.RestaurantRating.class, com.jrkg.jrkgbites.model.FavoriteRestaurantId.class, com.jrkg.jrkgbites.model.NeverAgainRestaurantId.class}, version = 5, exportSchema = false)
@androidx.room.TypeConverters(value = {com.jrkg.jrkgbites.utils.Converters.class})
public abstract class AppDatabase extends androidx.room.RoomDatabase {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.jrkg.jrkgbites.AppDatabase INSTANCE;
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.AppDatabase.Companion Companion = null;
    
    public AppDatabase() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.jrkg.jrkgbites.data.local.RestaurantDao restaurantDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.jrkg.jrkgbites.data.local.RestaurantRatingDao restaurantRatingDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.jrkg.jrkgbites.data.local.FavoriteRestaurantDao favoriteRestaurantDao();
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.jrkg.jrkgbites.data.local.NeverAgainRestaurantDao neverAgainRestaurantDao();
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bR\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/jrkg/jrkgbites/AppDatabase$Companion;", "", "<init>", "()V", "INSTANCE", "Lcom/jrkg/jrkgbites/AppDatabase;", "getDatabase", "context", "Landroid/content/Context;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.jrkg.jrkgbites.AppDatabase getDatabase(@org.jetbrains.annotations.NotNull()
        android.content.Context context) {
            return null;
        }
    }
}