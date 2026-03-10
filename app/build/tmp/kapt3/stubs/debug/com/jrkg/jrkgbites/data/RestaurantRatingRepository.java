package com.jrkg.jrkgbites.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007J\u0016\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00072\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/jrkg/jrkgbites/data/RestaurantRatingRepository;", "", "restaurantRatingDao", "Lcom/jrkg/jrkgbites/data/RestaurantRatingDao;", "<init>", "(Lcom/jrkg/jrkgbites/data/RestaurantRatingDao;)V", "getRatings", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/jrkg/jrkgbites/model/RestaurantRating;", "getRatingForRestaurant", "restaurantId", "", "insertRating", "", "rating", "(Lcom/jrkg/jrkgbites/model/RestaurantRating;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class RestaurantRatingRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantRatingDao restaurantRatingDao = null;
    
    public RestaurantRatingRepository(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.RestaurantRatingDao restaurantRatingDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.jrkg.jrkgbites.model.RestaurantRating>> getRatings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.model.RestaurantRating> getRatingForRestaurant(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertRating(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.RestaurantRating rating, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}