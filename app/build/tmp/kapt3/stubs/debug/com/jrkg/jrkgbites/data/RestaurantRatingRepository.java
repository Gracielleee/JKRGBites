package com.jrkg.jrkgbites.data;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f0\u000bJ\u0016\u0010\u000e\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u000b2\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\rH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\rH\u0082@\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lcom/jrkg/jrkgbites/data/RestaurantRatingRepository;", "", "restaurantRatingDao", "Lcom/jrkg/jrkgbites/data/RestaurantRatingDao;", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "<init>", "(Lcom/jrkg/jrkgbites/data/RestaurantRatingDao;Lcom/google/firebase/firestore/FirebaseFirestore;)V", "ratingsCollection", "Lcom/google/firebase/firestore/CollectionReference;", "getRatings", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/jrkg/jrkgbites/model/RestaurantRating;", "getRatingForRestaurant", "restaurantId", "", "insertRating", "", "rating", "(Lcom/jrkg/jrkgbites/model/RestaurantRating;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncRatingToRemote", "Companion", "app_debug"})
public final class RestaurantRatingRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantRatingDao restaurantRatingDao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String RATINGS_COLLECTION = "restaurant_ratings";
    @org.jetbrains.annotations.NotNull()
    private final com.google.firebase.firestore.CollectionReference ratingsCollection = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.data.RestaurantRatingRepository.Companion Companion = null;
    
    public RestaurantRatingRepository(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.RestaurantRatingDao restaurantRatingDao, @org.jetbrains.annotations.NotNull()
    com.google.firebase.firestore.FirebaseFirestore firestore) {
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
    
    /**
     * Inserts or updates a rating in the local Room database and mirrors the change to Firestore.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object insertRating(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.RestaurantRating rating, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Writes the given rating to Firestore using the restaurantId as the document key.
     */
    private final java.lang.Object syncRatingToRemote(com.jrkg.jrkgbites.model.RestaurantRating rating, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/jrkg/jrkgbites/data/RestaurantRatingRepository$Companion;", "", "<init>", "()V", "RATINGS_COLLECTION", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}