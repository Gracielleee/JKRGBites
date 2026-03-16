package com.jrkg.jrkgbites.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\tH\u0086@\u00a2\u0006\u0002\u0010\u0012J\u0012\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u00150\u0014J\u0016\u0010\u0016\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u00142\u0006\u0010\u0011\u001a\u00020\tJ\u0016\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0019J\u0016\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001c"}, d2 = {"Lcom/jrkg/jrkgbites/data/RestaurantRatingRepository;", "", "restaurantRatingDao", "Lcom/jrkg/jrkgbites/data/RestaurantRatingDao;", "<init>", "(Lcom/jrkg/jrkgbites/data/RestaurantRatingDao;)V", "syncRatings", "", "userId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitRating", "", "restaurantRating", "Lcom/jrkg/jrkgbites/model/RestaurantRating;", "(Lcom/jrkg/jrkgbites/model/RestaurantRating;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRestaurantRatingByCascade", "restaurantId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRatingsLocal", "Lkotlinx/coroutines/flow/Flow;", "", "getRatingForRestaurantLocal", "insertRatingLocal", "rating", "(Lcom/jrkg/jrkgbites/model/RestaurantRating;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeRatingLocal", "Companion", "app_debug"})
public final class RestaurantRatingRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantRatingDao restaurantRatingDao = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "RestaurantRatingRepository";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String RESTAURANTS_COLLECTION = "restaurants";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String RATINGS_COLLECTION = "restaurantRatings";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String USER_COLLECTION = "users";
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.data.RestaurantRatingRepository.Companion Companion = null;
    
    public RestaurantRatingRepository(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.RestaurantRatingDao restaurantRatingDao) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncRatings(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object submitRating(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.RestaurantRating restaurantRating, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object deleteRestaurantRatingByCascade(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.jrkg.jrkgbites.model.RestaurantRating>> getRatingsLocal() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.model.RestaurantRating> getRatingForRestaurantLocal(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertRatingLocal(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.RestaurantRating rating, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeRatingLocal(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.RestaurantRating rating, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/jrkg/jrkgbites/data/RestaurantRatingRepository$Companion;", "", "<init>", "()V", "TAG", "", "RESTAURANTS_COLLECTION", "RATINGS_COLLECTION", "USER_COLLECTION", "generateCompositeKey", "restaurantId", "userId", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String generateCompositeKey(@org.jetbrains.annotations.NotNull()
        java.lang.String restaurantId, @org.jetbrains.annotations.NotNull()
        java.lang.String userId) {
            return null;
        }
    }
}