package com.jrkg.jrkgbites.domain;

/**
 * Manages the state and logic for the swipeable "Tinder-style" card picker.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u0013J\u0006\u0010$\u001a\u00020\u001dJ\u000e\u0010%\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u0013J\u0006\u0010&\u001a\u00020\u001dR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0010R\u0016\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0010\u00a8\u0006\'"}, d2 = {"Lcom/jrkg/jrkgbites/domain/SwipeManager;", "", "restaurantRepository", "Lcom/jrkg/jrkgbites/data/RestaurantRepository;", "<init>", "(Lcom/jrkg/jrkgbites/data/RestaurantRepository;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "_allRestaurants", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/jrkg/jrkgbites/model/Restaurant;", "_deck", "deck", "Lkotlinx/coroutines/flow/StateFlow;", "getDeck", "()Lkotlinx/coroutines/flow/StateFlow;", "_favorites", "", "", "favorites", "getFavorites", "_neverAgain", "neverAgain", "getNeverAgain", "_selectedRestaurant", "selectedRestaurant", "getSelectedRestaurant", "init", "", "onSwipe", "restaurant", "direction", "Lcom/jrkg/jrkgbites/domain/SwipeDirection;", "addToNeverAgain", "restaurantId", "resetDeck", "removeFromNeverAgain", "clearSelectedRestaurant", "app_debug"})
public final class SwipeManager {
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantRepository restaurantRepository = null;
    private kotlinx.coroutines.CoroutineScope scope;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> _allRestaurants = null;
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
     * Processes a swipe action on a restaurant.
     * @param restaurant The restaurant that was swiped.
     * @param direction The direction of the swipe.
     */
    public final void onSwipe(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.Restaurant restaurant, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.SwipeDirection direction) {
    }
    
    /**
     * Adds a restaurant to the "Never Again" list.
     * This can be called from a swipe-down action or after a low rating.
     */
    public final void addToNeverAgain(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
    }
    
    /**
     * Resets the deck to its initial state, filtering out any restaurants in the "Never Again" list.
     * This can be called on app start or when the user wants to restart their session.
     */
    public final void resetDeck() {
    }
    
    /**
     * Allows a restaurant to be removed from the "Never Again" list.
     * This would be used if the user re-adds a restaurant from the search page.
     */
    public final void removeFromNeverAgain(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
    }
    
    /**
     * Clears the currently selected restaurant.
     * This would be called after a selected restaurant has been navigated to.
     */
    public final void clearSelectedRestaurant() {
    }
}