package com.jrkg.jrkgbites.domain;

import android.util.Log;
import com.jrkg.jrkgbites.data.RestaurantRepository;
import com.jrkg.jrkgbites.data.UserPreferencesManager;
import com.jrkg.jrkgbites.model.Restaurant;
import kotlinx.coroutines.flow.*;

/**
 * Manages the state and logic for the swipeable "Tinder-style" card picker.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B-\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u00a2\u0006\u0004\b\u000b\u0010\fJ\u000e\u0010.\u001a\u00020/2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u00100\u001a\u00020/2\u0006\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u00020\u001eJ\u0006\u00103\u001a\u00020/J\u000e\u00104\u001a\u00020/2\u0006\u00105\u001a\u00020\u0004J\u000e\u00106\u001a\u00020/2\u0006\u00105\u001a\u00020\u0004J\u0006\u00107\u001a\u00020/J\u0006\u00108\u001a\u00020/J\u0006\u00109\u001a\u00020/J\u0006\u0010:\u001a\u00020/J\u0006\u0010;\u001a\u00020/R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0019\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u001a0\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u001b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u001e0\u001d0\u001cX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020 X\u0082D\u00a2\u0006\u0002\n\u0000R\u0016\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\'\u0010$\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00158FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b&\u0010\'\u001a\u0004\b%\u0010\u0017R\'\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00158FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b*\u0010\'\u001a\u0004\b)\u0010\u0017R\'\u0010+\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u00158FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b-\u0010\'\u001a\u0004\b,\u0010\u0017\u00a8\u0006<"}, d2 = {"Lcom/jrkg/jrkgbites/domain/SwipeManager;", "", "userIdFlow", "Lkotlinx/coroutines/flow/Flow;", "", "restaurantRepository", "Lcom/jrkg/jrkgbites/data/RestaurantRepository;", "restaurantManager", "Lcom/jrkg/jrkgbites/domain/RestaurantManager;", "prefsManager", "Lcom/jrkg/jrkgbites/data/UserPreferencesManager;", "<init>", "(Lkotlinx/coroutines/flow/Flow;Lcom/jrkg/jrkgbites/data/RestaurantRepository;Lcom/jrkg/jrkgbites/domain/RestaurantManager;Lcom/jrkg/jrkgbites/data/UserPreferencesManager;)V", "currentUserId", "scope", "Lkotlinx/coroutines/CoroutineScope;", "_allRestaurants", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/jrkg/jrkgbites/model/Restaurant;", "allRestaurants", "Lkotlinx/coroutines/flow/StateFlow;", "getAllRestaurants", "()Lkotlinx/coroutines/flow/StateFlow;", "_displayOrder", "_sessionSwipedRestaurants", "", "_swipeHistory", "", "Lkotlin/Pair;", "Lcom/jrkg/jrkgbites/domain/SwipeDirection;", "MAX_SWIPE_HISTORY_SIZE", "", "_selectedRestaurant", "selectedRestaurant", "getSelectedRestaurant", "favoritesList", "getFavoritesList", "favoritesList$delegate", "Lkotlin/Lazy;", "neverAgainList", "getNeverAgainList", "neverAgainList$delegate", "deck", "getDeck", "deck$delegate", "init", "", "onSwipe", "restaurant", "direction", "undoLastSwipe", "toggleFavorite", "restaurantId", "toggleNeverAgain", "shuffleDeck", "updateDeck", "clearSessionSwipes", "clearSelectedRestaurant", "clearSwipeHistory", "app_debug"})
public final class SwipeManager {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> userIdFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantRepository restaurantRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.RestaurantManager restaurantManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.UserPreferencesManager prefsManager = null;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String currentUserId = "";
    private kotlinx.coroutines.CoroutineScope scope;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> _allRestaurants = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> allRestaurants = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> _displayOrder = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.Set<java.lang.String>> _sessionSwipedRestaurants = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<kotlin.Pair<com.jrkg.jrkgbites.model.Restaurant, com.jrkg.jrkgbites.domain.SwipeDirection>> _swipeHistory = null;
    private final int MAX_SWIPE_HISTORY_SIZE = 8;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.jrkg.jrkgbites.model.Restaurant> _selectedRestaurant = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.model.Restaurant> selectedRestaurant = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy favoritesList$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy neverAgainList$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy deck$delegate = null;
    
    public SwipeManager(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.flow.Flow<java.lang.String> userIdFlow, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.RestaurantRepository restaurantRepository, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.RestaurantManager restaurantManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.UserPreferencesManager prefsManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getAllRestaurants() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.model.Restaurant> getSelectedRestaurant() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getFavoritesList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getNeverAgainList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getDeck() {
        return null;
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope) {
    }
    
    public final void onSwipe(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.Restaurant restaurant, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.SwipeDirection direction) {
    }
    
    public final void undoLastSwipe() {
    }
    
    public final void toggleFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
    }
    
    public final void toggleNeverAgain(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
    }
    
    public final void shuffleDeck() {
    }
    
    public final void updateDeck() {
    }
    
    public final void clearSessionSwipes() {
    }
    
    public final void clearSelectedRestaurant() {
    }
    
    public final void clearSwipeHistory() {
    }
}