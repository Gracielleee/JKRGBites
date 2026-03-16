package com.jrkg.jrkgbites.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tJ\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\t2\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u0016J\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000bH\u0086@\u00a2\u0006\u0002\u0010\u0018R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/jrkg/jrkgbites/data/RestaurantRatingRepository;", "", "restaurantRatingDao", "Lcom/jrkg/jrkgbites/data/RestaurantRatingDao;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "<init>", "(Lcom/jrkg/jrkgbites/data/RestaurantRatingDao;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "getRatingsLocal", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/jrkg/jrkgbites/model/RestaurantRating;", "getRatingForRestaurantLocal", "restaurantId", "", "syncRatingsFromRemote", "", "userId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitRating", "", "rating", "(Lcom/jrkg/jrkgbites/model/RestaurantRating;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeRating", "(Lcom/jrkg/jrkgbites/model/RestaurantRating;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "app_debug"})
public final class RestaurantRatingRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantRatingDao restaurantRatingDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "RestaurantRatingRepo";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String RESTAURANTS_COLLECTION = "restaurants";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String RATINGS_COLLECTION = "restaurant_ratings";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String USER_COLLECTION = "users";
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.data.RestaurantRatingRepository.Companion Companion = null;
    
    public RestaurantRatingRepository(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.RestaurantRatingDao restaurantRatingDao, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
        super();
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
    
    /**
     * Fetches all ratings for a specific user from Firestore and updates the local database.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object syncRatingsFromRemote(@org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion) {
        return null;
    }
    
    /**
     * Inserts locally first for instant UI response, then attempts to push to Firestore.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object submitRating(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.RestaurantRating rating, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object removeRating(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.RestaurantRating rating, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/jrkg/jrkgbites/data/RestaurantRatingRepository$Companion;", "", "<init>", "()V", "TAG", "", "RESTAURANTS_COLLECTION", "RATINGS_COLLECTION", "USER_COLLECTION", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}