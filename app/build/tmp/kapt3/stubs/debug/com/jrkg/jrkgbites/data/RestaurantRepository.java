package com.jrkg.jrkgbites.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u000b0\u0006J\u000e\u0010\f\u001a\u00020\rH\u0082@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0016\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u001c\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\u0016\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/jrkg/jrkgbites/data/RestaurantRepository;", "", "restaurantDao", "Lcom/jrkg/jrkgbites/data/RestaurantDao;", "(Lcom/jrkg/jrkgbites/data/RestaurantDao;)V", "getRestaurantById", "Lkotlinx/coroutines/flow/Flow;", "Lcom/jrkg/jrkgbites/model/Restaurant;", "id", "", "getRestaurants", "", "hasData", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadRestaurantsFromAsset", "context", "Landroid/content/Context;", "refreshRestaurants", "", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "searchRestaurantsByName", "query", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class RestaurantRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantDao restaurantDao = null;
    
    public RestaurantRepository(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.RestaurantDao restaurantDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getRestaurants() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.model.Restaurant> getRestaurantById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    private final java.lang.Object hasData(kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object refreshRestaurants(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.util.List<com.jrkg.jrkgbites.model.Restaurant> loadRestaurantsFromAsset(android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object searchRestaurantsByName(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.jrkg.jrkgbites.model.Restaurant>> $completion) {
        return null;
    }
}