package com.jrkg.jrkgbites.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u001c\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\fH\'J\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\tH\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\rH\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u000e\u0010\u0017\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0018\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tH\u0096@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u0019\u00c0\u0006\u0003"}, d2 = {"Lcom/jrkg/jrkgbites/data/FavoriteRestaurantDao;", "", "insert", "", "favoriteRestaurant", "Lcom/jrkg/jrkgbites/model/FavoriteRestaurantId;", "(Lcom/jrkg/jrkgbites/model/FavoriteRestaurantId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertAll", "favoriteRestaurants", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllFavoriteRestaurantIdsFlow", "Lkotlinx/coroutines/flow/Flow;", "", "getAllFavoriteRestaurantIds", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isFavorited", "", "restaurantId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "deleteById", "id", "deleteAll", "clearAndInsert", "app_debug"})
@androidx.room.Dao()
public abstract interface FavoriteRestaurantDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.FavoriteRestaurantId favoriteRestaurant, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.jrkg.jrkgbites.model.FavoriteRestaurantId> favoriteRestaurants, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT favorite_restaurant FROM favorite_restaurants")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getAllFavoriteRestaurantIdsFlow();
    
    @androidx.room.Query(value = "SELECT favorite_restaurant FROM favorite_restaurants")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllFavoriteRestaurantIds(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM favorite_restaurants WHERE favorite_restaurant = :restaurantId)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isFavorited(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.FavoriteRestaurantId favoriteRestaurant, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM favorite_restaurants WHERE favorite_restaurant = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM favorite_restaurants")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @org.jetbrains.annotations.Nullable()
    public default java.lang.Object clearAndInsert(@org.jetbrains.annotations.NotNull()
    java.util.List<com.jrkg.jrkgbites.model.FavoriteRestaurantId> favoriteRestaurants, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 3, xi = 48)
    public static final class DefaultImpls {
        
        @org.jetbrains.annotations.Nullable()
        @java.lang.Deprecated()
        public static java.lang.Object clearAndInsert(@org.jetbrains.annotations.NotNull()
        com.jrkg.jrkgbites.data.FavoriteRestaurantDao $this, @org.jetbrains.annotations.NotNull()
        java.util.List<com.jrkg.jrkgbites.model.FavoriteRestaurantId> favoriteRestaurants, @org.jetbrains.annotations.NotNull()
        kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
            return null;
        }
    }
}