package com.jrkg.jrkgbites.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bH\'J\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u00a7@\u00a2\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u0016\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u000e\u0010\u0014\u001a\u00020\u0003H\u00a7@\u00a2\u0006\u0002\u0010\f\u00a8\u0006\u0015\u00c0\u0006\u0003"}, d2 = {"Lcom/jrkg/jrkgbites/data/NeverAgainRestaurantDao;", "", "insert", "", "neverAgainRestaurant", "Lcom/jrkg/jrkgbites/model/NeverAgainRestaurantId;", "(Lcom/jrkg/jrkgbites/model/NeverAgainRestaurantId;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllNeverAgainRestaurantIdsFlow", "Lkotlinx/coroutines/flow/Flow;", "", "", "getAllNeverAgainRestaurantIds", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isNeverAgain", "", "restaurantId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "delete", "deleteById", "id", "deleteAll", "app_debug"})
@androidx.room.Dao()
public abstract interface NeverAgainRestaurantDao {
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insert(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.NeverAgainRestaurantId neverAgainRestaurant, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT never_again_restaurant FROM never_again_restaurants")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<java.lang.String>> getAllNeverAgainRestaurantIdsFlow();
    
    @androidx.room.Query(value = "SELECT never_again_restaurant FROM never_again_restaurants")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getAllNeverAgainRestaurantIds(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<java.lang.String>> $completion);
    
    @androidx.room.Query(value = "SELECT EXISTS(SELECT 1 FROM never_again_restaurants WHERE never_again_restaurant = :restaurantId)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object isNeverAgain(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion);
    
    @androidx.room.Delete()
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.NeverAgainRestaurantId neverAgainRestaurant, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM never_again_restaurants WHERE never_again_restaurant = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM never_again_restaurants")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteAll(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}