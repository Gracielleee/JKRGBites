package com.jrkg.jrkgbites.domain;

/**
 * Manages the logic related to submitting restaurant ratings.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J.\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u000fH\u0086@\u00a2\u0006\u0002\u0010\u0014J\u0016\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u00072\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0017"}, d2 = {"Lcom/jrkg/jrkgbites/domain/RatingManager;", "", "restaurantRatingRepository", "Lcom/jrkg/jrkgbites/data/RestaurantRatingRepository;", "<init>", "(Lcom/jrkg/jrkgbites/data/RestaurantRatingRepository;)V", "allRatings", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/jrkg/jrkgbites/model/RestaurantRating;", "getAllRatings", "()Lkotlinx/coroutines/flow/Flow;", "submitRating", "", "restaurantId", "", "rating", "", "comment", "userId", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRatingForRestaurant", "Companion", "app_debug"})
public final class RatingManager {
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantRatingRepository restaurantRatingRepository = null;
    
    /**
     * The star rating below which a restaurant is prompted to be added to never again.
     */
    public static final int RATING_THRESHOLD = 2;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.jrkg.jrkgbites.model.RestaurantRating>> allRatings = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.domain.RatingManager.Companion Companion = null;
    
    public RatingManager(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.RestaurantRatingRepository restaurantRatingRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.jrkg.jrkgbites.model.RestaurantRating>> getAllRatings() {
        return null;
    }
    
    /**
     * Submits a rating for a given restaurant and returns whether it is considered "low".
     *
     * @param restaurantId The ID of the restaurant being rated.
     * @param rating The star rating given by the user (e.g., 1, 2, 3, 4, 5).
     * @param comment The user's comment for the rating.
     * @return true if the rating is below [RATING_THRESHOLD], false otherwise.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object submitRating(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId, int rating, @org.jetbrains.annotations.NotNull()
    java.lang.String comment, @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    /**
     * Retrieves the latest stored rating for a specific restaurant.
     * @param restaurantId The ID of the restaurant.
     * @return A Flow emitting the latest RestaurantRating or null if not found.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.model.RestaurantRating> getRatingForRestaurant(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
        return null;
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/jrkg/jrkgbites/domain/RatingManager$Companion;", "", "<init>", "()V", "RATING_THRESHOLD", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}