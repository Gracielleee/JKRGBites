package com.jrkg.jrkgbites.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0007\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u000b0\nH\'J\u0018\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\n2\u0006\u0010\r\u001a\u00020\u000eH\'\u00a8\u0006\u000f\u00c0\u0006\u0003"}, d2 = {"Lcom/jrkg/jrkgbites/data/RestaurantRatingDao;", "", "insert", "", "rating", "Lcom/jrkg/jrkgbites/model/RestaurantRating;", "(Lcom/jrkg/jrkgbites/model/RestaurantRating;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "update", "delete", "getAllRatings", "Lkotlinx/coroutines/flow/Flow;", "", "getLatestRatingForRestaurant", "restaurantId", "", "app_debug"})
@androidx.room.Dao()
public abstract interface RestaurantRatingDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.RestaurantRating rating, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Update()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object update(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.RestaurantRating rating, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.RestaurantRating rating, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM restaurant_ratings")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.jrkg.jrkgbites.model.RestaurantRating>> getAllRatings();
    
    @androidx.room.Query(value = "SELECT * FROM restaurant_ratings WHERE restaurantId = :restaurantId ORDER BY timestamp DESC LIMIT 1")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.model.RestaurantRating> getLatestRatingForRestaurant(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId);
}