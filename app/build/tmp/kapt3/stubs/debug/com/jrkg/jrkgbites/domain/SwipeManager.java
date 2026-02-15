package com.jrkg.jrkgbites.domain;

/**
 * Manages the state and logic for the swipeable "Tinder-style" card picker.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010%\u001a\u00020&2\u0006\u0010\u0006\u001a\u00020\u0007J\u0006\u0010\'\u001a\u00020&J\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u000b0\nJ\u0016\u0010)\u001a\u00020&2\u0006\u0010*\u001a\u00020\u000b2\u0006\u0010+\u001a\u00020\u0016J\u0016\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020\u0012H\u0086@\u00a2\u0006\u0002\u0010.J\u0006\u0010/\u001a\u00020&J\u0006\u00100\u001a\u00020&J\u0006\u00101\u001a\u00020&J\u0006\u00102\u001a\u00020&J\u0006\u00103\u001a\u00020&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0013\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00160\u00150\u0014X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0017\u001a\u00020\u0018X\u0082D\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000fR\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000fR\u001a\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\r\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u000fR\u0016\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\r\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010\u000f\u00a8\u00064"}, d2 = {"Lcom/jrkg/jrkgbites/domain/SwipeManager;", "", "restaurantRepository", "Lcom/jrkg/jrkgbites/data/RestaurantRepository;", "<init>", "(Lcom/jrkg/jrkgbites/data/RestaurantRepository;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "_allRestaurants", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/jrkg/jrkgbites/model/Restaurant;", "allRestaurants", "Lkotlinx/coroutines/flow/StateFlow;", "getAllRestaurants", "()Lkotlinx/coroutines/flow/StateFlow;", "_sessionSwipedRestaurants", "", "", "_swipeHistory", "", "Lkotlin/Pair;", "Lcom/jrkg/jrkgbites/domain/SwipeDirection;", "MAX_SWIPE_HISTORY_SIZE", "", "_deck", "deck", "getDeck", "_favorites", "favorites", "getFavorites", "_neverAgain", "neverAgain", "getNeverAgain", "_selectedRestaurant", "selectedRestaurant", "getSelectedRestaurant", "init", "", "updateDeck", "filterDeck", "onSwipe", "restaurant", "direction", "addToNeverAgain", "restaurantId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "clearSessionSwipes", "undoLastSwipe", "shuffleDeck", "clearSelectedRestaurant", "clearSwipeHistory", "app_debug"})
public final class SwipeManager {
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantRepository restaurantRepository = null;
    private kotlinx.coroutines.CoroutineScope scope;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> _allRestaurants = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> allRestaurants = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Set<java.lang.String>> _sessionSwipedRestaurants = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<kotlin.Pair<com.jrkg.jrkgbites.model.Restaurant, com.jrkg.jrkgbites.domain.SwipeDirection>> _swipeHistory = null;
    private final int MAX_SWIPE_HISTORY_SIZE = 8;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> _deck = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> deck = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Set<java.lang.String>> _favorites = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.String>> favorites = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Set<java.lang.String>> _neverAgain = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.String>> neverAgain = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.jrkg.jrkgbites.model.Restaurant> _selectedRestaurant = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.model.Restaurant> selectedRestaurant = null;
    
    public SwipeManager(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.RestaurantRepository restaurantRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getAllRestaurants() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getDeck() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.String>> getFavorites() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.Set<java.lang.String>> getNeverAgain() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.model.Restaurant> getSelectedRestaurant() {
        return null;
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope) {
    }
    
    /**
     * Updates the deck by applying current filters.
     */
    public final void updateDeck() {
    }
    
    /**
     * Filters the deck based on flags and the current session's swipes.
     */
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.jrkg.jrkgbites.model.Restaurant> filterDeck() {
        return null;
    }
    
    /**
     * Processes a swipe action on a restaurant.
     * @param restaurant The restaurant that was swiped.
     * @param direction The direction of the swipe.
     */
    public final void onSwipe(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.Restaurant restaurant, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.SwipeDirection direction) {
    }
    
    /**
     * Adds a restaurant to the "Never Again" list by its ID.
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object addToNeverAgain(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * Method to clear session-level swipes (when user wants to reset the deck during the session)
     */
    public final void clearSessionSwipes() {
    }
    
    public final void undoLastSwipe() {
    }
    
    /**
     * Shuffles the current filtered deck.
     */
    public final void shuffleDeck() {
    }
    
    /**
     * Clears the selected restaurant.
     */
    public final void clearSelectedRestaurant() {
    }
    
    public final void clearSwipeHistory() {
    }
}