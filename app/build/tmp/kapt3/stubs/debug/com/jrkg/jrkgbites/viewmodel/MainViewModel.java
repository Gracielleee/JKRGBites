package com.jrkg.jrkgbites.viewmodel;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a8\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001BW\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u00a2\u0006\u0004\b\u0016\u0010\u0017J\u0006\u0010\u001f\u001a\u00020 J\u001c\u0010F\u001a\b\u0012\u0004\u0012\u00020H0G2\u0006\u0010I\u001a\u00020\u001a2\u0006\u0010J\u001a\u00020\u001aJ\u0006\u0010K\u001a\u00020 J\u0016\u0010L\u001a\u00020 2\u0006\u0010M\u001a\u0002002\u0006\u0010N\u001a\u00020OJ\u0006\u0010P\u001a\u00020 J\u0006\u0010Q\u001a\u00020 J\u0006\u0010R\u001a\u00020 J\u0006\u0010S\u001a\u00020 J\u0006\u0010T\u001a\u00020 J\u0016\u0010U\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001000G2\u0006\u0010V\u001a\u00020\u001aJ\u000e\u0010W\u001a\u00020 2\u0006\u0010X\u001a\u00020\u001aJ\u001e\u0010Y\u001a\u00020 2\u0006\u0010X\u001a\u00020\u001a2\u0006\u0010Z\u001a\u00020[2\u0006\u0010\\\u001a\u00020\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0019\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020%0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001eR\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020)0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010\u001eR\u0016\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010\u001eR\'\u0010.\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000/0\u001c8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b2\u00103\u001a\u0004\b1\u0010\u001eR\'\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000/0\u001c8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b6\u00103\u001a\u0004\b5\u0010\u001eR\'\u00107\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000/0\u001c8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b9\u00103\u001a\u0004\b8\u0010\u001eR\'\u0010:\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000/0\u001c8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b<\u00103\u001a\u0004\b;\u0010\u001eR#\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001000\u001c8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b?\u00103\u001a\u0004\b>\u0010\u001eR\u001d\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020A0/0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\bB\u0010\u001eR\u001a\u0010C\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000/0\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010D\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002000/0\u001c\u00a2\u0006\b\n\u0000\u001a\u0004\bE\u0010\u001e\u00a8\u0006]"}, d2 = {"Lcom/jrkg/jrkgbites/viewmodel/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "application", "Landroid/app/Application;", "restaurantPicker", "Lcom/jrkg/jrkgbites/domain/RestaurantPicker;", "swipeManager", "Lcom/jrkg/jrkgbites/domain/SwipeManager;", "searchManager", "Lcom/jrkg/jrkgbites/domain/SearchManager;", "ratingManager", "Lcom/jrkg/jrkgbites/domain/RatingManager;", "authManager", "Lcom/jrkg/jrkgbites/domain/AuthManager;", "prefsManager", "Lcom/jrkg/jrkgbites/data/UserPreferencesManager;", "sessionManager", "Lcom/jrkg/jrkgbites/domain/SessionManager;", "restaurantRepository", "Lcom/jrkg/jrkgbites/data/RestaurantRepository;", "restaurantManager", "Lcom/jrkg/jrkgbites/domain/RestaurantManager;", "<init>", "(Landroid/app/Application;Lcom/jrkg/jrkgbites/domain/RestaurantPicker;Lcom/jrkg/jrkgbites/domain/SwipeManager;Lcom/jrkg/jrkgbites/domain/SearchManager;Lcom/jrkg/jrkgbites/domain/RatingManager;Lcom/jrkg/jrkgbites/domain/AuthManager;Lcom/jrkg/jrkgbites/data/UserPreferencesManager;Lcom/jrkg/jrkgbites/domain/SessionManager;Lcom/jrkg/jrkgbites/data/RestaurantRepository;Lcom/jrkg/jrkgbites/domain/RestaurantManager;)V", "_toastMessage", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "toastMessage", "Lkotlinx/coroutines/flow/StateFlow;", "getToastMessage", "()Lkotlinx/coroutines/flow/StateFlow;", "clearToastMessage", "", "sessionState", "Lcom/jrkg/jrkgbites/model/User;", "getSessionState", "_requiredAuthMethod", "Lcom/jrkg/jrkgbites/domain/AuthMethod;", "requiredAuthMethod", "getRequiredAuthMethod", "_isBiometricPreferenceEnabled", "", "isBiometricPreferenceEnabled", "_pickedResult", "pickedResult", "getPickedResult", "deck", "", "Lcom/jrkg/jrkgbites/model/Restaurant;", "getDeck", "deck$delegate", "Lkotlin/Lazy;", "allRestaurants", "getAllRestaurants", "allRestaurants$delegate", "favoritesList", "getFavoritesList", "favoritesList$delegate", "neverAgainList", "getNeverAgainList", "neverAgainList$delegate", "selectedRestaurant", "getSelectedRestaurant", "selectedRestaurant$delegate", "allRestaurantRatings", "Lcom/jrkg/jrkgbites/model/RestaurantRating;", "getAllRestaurantRatings", "_searchResults", "searchResults", "getSearchResults", "login", "Lkotlinx/coroutines/flow/Flow;", "Lcom/jrkg/jrkgbites/domain/service/AuthResult;", "email", "pass", "logout", "onSwipe", "restaurant", "direction", "Lcom/jrkg/jrkgbites/domain/SwipeDirection;", "resetDeck", "shuffleDeck", "undoSwipe", "refreshDeck", "clearSelectedRestaurant", "getRestaurantById", "id", "toggleFavorite", "restaurantId", "submitRating", "rating", "", "comment", "app_debug"})
public final class MainViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final android.app.Application application = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.RestaurantPicker restaurantPicker = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.SwipeManager swipeManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.SearchManager searchManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.RatingManager ratingManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.AuthManager authManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.UserPreferencesManager prefsManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.SessionManager sessionManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantRepository restaurantRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.RestaurantManager restaurantManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _toastMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> toastMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.model.User> sessionState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.jrkg.jrkgbites.domain.AuthMethod> _requiredAuthMethod = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.domain.AuthMethod> requiredAuthMethod = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isBiometricPreferenceEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isBiometricPreferenceEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _pickedResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> pickedResult = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy deck$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy allRestaurants$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy favoritesList$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy neverAgainList$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy selectedRestaurant$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.RestaurantRating>> allRestaurantRatings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> _searchResults = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> searchResults = null;
    
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.RestaurantPicker restaurantPicker, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.SwipeManager swipeManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.SearchManager searchManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.RatingManager ratingManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.AuthManager authManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.UserPreferencesManager prefsManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.SessionManager sessionManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.RestaurantRepository restaurantRepository, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.RestaurantManager restaurantManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getToastMessage() {
        return null;
    }
    
    public final void clearToastMessage() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.model.User> getSessionState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.domain.AuthMethod> getRequiredAuthMethod() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isBiometricPreferenceEnabled() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getPickedResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getDeck() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getAllRestaurants() {
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
    public final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.model.Restaurant> getSelectedRestaurant() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.RestaurantRating>> getAllRestaurantRatings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getSearchResults() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.domain.service.AuthResult> login(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String pass) {
        return null;
    }
    
    public final void logout() {
    }
    
    public final void onSwipe(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.Restaurant restaurant, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.SwipeDirection direction) {
    }
    
    public final void resetDeck() {
    }
    
    public final void shuffleDeck() {
    }
    
    public final void undoSwipe() {
    }
    
    /**
     * Re-applies current session filters to the deck. Useful when
     * returning to the picker so that previously swiped restaurants
     * (including RIGHT swipes) are not shown again.
     */
    public final void refreshDeck() {
    }
    
    public final void clearSelectedRestaurant() {
    }
    
    /**
     * Exposes a Flow for a single restaurant by ID so that
     * UI layers (e.g., RestaurantDetailsFragment) don't depend
     * on the current swipe deck contents.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.model.Restaurant> getRestaurantById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    public final void toggleFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
    }
    
    public final void submitRating(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId, int rating, @org.jetbrains.annotations.NotNull()
    java.lang.String comment) {
    }
}