package com.jrkg.jrkgbites.viewmodel;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u00c0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B_\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u00a2\u0006\u0004\b\u0018\u0010\u0019J\u0006\u0010+\u001a\u00020&J\u0006\u00103\u001a\u00020&J\u001c\u0010X\u001a\b\u0012\u0004\u0012\u00020Z0Y2\u0006\u0010[\u001a\u00020-2\u0006\u0010\\\u001a\u00020-J$\u0010]\u001a\b\u0012\u0004\u0012\u00020Z0Y2\u0006\u0010[\u001a\u00020-2\u0006\u0010\\\u001a\u00020-2\u0006\u0010^\u001a\u00020-J\u000e\u0010_\u001a\u00020&H\u0086@\u00a2\u0006\u0002\u0010`J\u0016\u0010a\u001a\u00020&2\u0006\u0010b\u001a\u00020B2\u0006\u0010c\u001a\u00020dJ\u0006\u0010e\u001a\u00020&J\u0006\u0010f\u001a\u00020&J\u0006\u0010g\u001a\u00020&J\u0006\u0010h\u001a\u00020&J\u0006\u0010i\u001a\u00020&J\u0016\u0010j\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010B0Y2\u0006\u0010k\u001a\u00020-J\u000e\u0010l\u001a\u00020&2\u0006\u0010m\u001a\u00020-J\u000e\u0010n\u001a\u00020&2\u0006\u0010m\u001a\u00020-J\u000e\u0010o\u001a\u00020&2\u0006\u0010p\u001a\u000209J\u001e\u0010q\u001a\u00020&2\u0006\u0010m\u001a\u00020-2\u0006\u0010r\u001a\u00020s2\u0006\u0010t\u001a\u00020-J\u0010\u0010u\u001a\u0002092\b\u0010b\u001a\u0004\u0018\u00010BJ\u000e\u0010v\u001a\u00020&2\u0006\u0010m\u001a\u00020-J\u000e\u0010w\u001a\u00020&2\u0006\u0010b\u001a\u00020BJ\u000e\u0010x\u001a\u00020&2\u0006\u0010b\u001a\u00020BJ\u000e\u0010y\u001a\u00020&2\u0006\u0010b\u001a\u00020BJ\u0014\u0010z\u001a\b\u0012\u0004\u0012\u0002090Y2\u0006\u0010[\u001a\u00020-R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001a\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001c0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0016\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\"\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010!0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001eR\u0014\u0010$\u001a\b\u0012\u0004\u0012\u00020&0%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\'\u001a\b\u0012\u0004\u0012\u00020&0(\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0016\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010\u001eR\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020-0%X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00101\u001a\b\u0012\u0004\u0012\u00020-0(\u00a2\u0006\b\n\u0000\u001a\u0004\b2\u0010*R\u0014\u00104\u001a\b\u0012\u0004\u0012\u0002050 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00106\u001a\b\u0012\u0004\u0012\u0002050\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b7\u0010\u001eR\u0014\u00108\u001a\b\u0012\u0004\u0012\u0002090 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010:\u001a\b\u0012\u0004\u0012\u0002090\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b:\u0010\u001eR\u0014\u0010;\u001a\b\u0012\u0004\u0012\u0002090 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010<\u001a\b\u0012\u0004\u0012\u0002090\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b<\u0010\u001eR\u0016\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010>\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b?\u0010\u001eR\'\u0010@\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0A0\u001b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bC\u0010\u001eR\'\u0010F\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0A0\u001b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bH\u0010E\u001a\u0004\bG\u0010\u001eR\'\u0010I\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0A0\u001b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bK\u0010E\u001a\u0004\bJ\u0010\u001eR\'\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0A0\u001b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bN\u0010E\u001a\u0004\bM\u0010\u001eR#\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010B0\u001b8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bQ\u0010E\u001a\u0004\bP\u0010\u001eR\u001d\u0010R\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020S0A0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\bT\u0010\u001eR\u001a\u0010U\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0A0 X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010V\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020B0A0\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\bW\u0010\u001e\u00a8\u0006{"}, d2 = {"Lcom/jrkg/jrkgbites/viewmodel/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "application", "Landroid/app/Application;", "restaurantPicker", "Lcom/jrkg/jrkgbites/domain/RestaurantPicker;", "swipeManager", "Lcom/jrkg/jrkgbites/domain/SwipeManager;", "searchManager", "Lcom/jrkg/jrkgbites/domain/SearchManager;", "ratingManager", "Lcom/jrkg/jrkgbites/domain/RatingManager;", "authManager", "Lcom/jrkg/jrkgbites/domain/AuthManager;", "prefsManager", "Lcom/jrkg/jrkgbites/data/UserPreferencesManager;", "sessionManager", "Lcom/jrkg/jrkgbites/domain/SessionManager;", "restaurantRepository", "Lcom/jrkg/jrkgbites/data/RestaurantRepository;", "restaurantRatingRepository", "Lcom/jrkg/jrkgbites/data/RestaurantRatingRepository;", "restaurantManager", "Lcom/jrkg/jrkgbites/domain/RestaurantManager;", "<init>", "(Landroid/app/Application;Lcom/jrkg/jrkgbites/domain/RestaurantPicker;Lcom/jrkg/jrkgbites/domain/SwipeManager;Lcom/jrkg/jrkgbites/domain/SearchManager;Lcom/jrkg/jrkgbites/domain/RatingManager;Lcom/jrkg/jrkgbites/domain/AuthManager;Lcom/jrkg/jrkgbites/data/UserPreferencesManager;Lcom/jrkg/jrkgbites/domain/SessionManager;Lcom/jrkg/jrkgbites/data/RestaurantRepository;Lcom/jrkg/jrkgbites/data/RestaurantRatingRepository;Lcom/jrkg/jrkgbites/domain/RestaurantManager;)V", "sessionState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/jrkg/jrkgbites/model/User;", "getSessionState", "()Lkotlinx/coroutines/flow/StateFlow;", "_startDestination", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/jrkg/jrkgbites/viewmodel/StartDestination;", "startDestination", "getStartDestination", "_navigateToMainEvent", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "navigateToMainEvent", "Lkotlinx/coroutines/flow/SharedFlow;", "getNavigateToMainEvent", "()Lkotlinx/coroutines/flow/SharedFlow;", "requestNavigateToMainAfterAuth", "_toastMessage", "", "toastMessage", "getToastMessage", "_lowRatingEvent", "lowRatingEvent", "getLowRatingEvent", "clearToastMessage", "_requiredAuthMethod", "Lcom/jrkg/jrkgbites/domain/AuthMethod;", "requiredAuthMethod", "getRequiredAuthMethod", "_isBiometricPreferenceEnabled", "", "isBiometricPreferenceEnabled", "_isKeepLoggedInEnabled", "isKeepLoggedInEnabled", "_pickedResult", "pickedResult", "getPickedResult", "deck", "", "Lcom/jrkg/jrkgbites/model/Restaurant;", "getDeck", "deck$delegate", "Lkotlin/Lazy;", "allRestaurants", "getAllRestaurants", "allRestaurants$delegate", "favoritesList", "getFavoritesList", "favoritesList$delegate", "neverAgainList", "getNeverAgainList", "neverAgainList$delegate", "selectedRestaurant", "getSelectedRestaurant", "selectedRestaurant$delegate", "allRestaurantRatings", "Lcom/jrkg/jrkgbites/model/RestaurantRating;", "getAllRestaurantRatings", "_searchResults", "searchResults", "getSearchResults", "login", "Lkotlinx/coroutines/flow/Flow;", "Lcom/jrkg/jrkgbites/domain/service/AuthResult;", "email", "pass", "signUp", "preferredName", "logout", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onSwipe", "restaurant", "direction", "Lcom/jrkg/jrkgbites/domain/SwipeDirection;", "resetDeck", "shuffleDeck", "undoSwipe", "refreshDeck", "clearSelectedRestaurant", "getRestaurantById", "id", "toggleFavorite", "restaurantId", "toggleNeverAgain", "setKeepLoggedIn", "enabled", "submitRating", "rating", "", "comment", "isUserOwner", "addToNeverAgainFromRating", "createRestaurant", "updateRestaurant", "deleteRestaurant", "sendPasswordResetEmail", "app_debug"})
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
    private final com.jrkg.jrkgbites.data.RestaurantRatingRepository restaurantRatingRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.RestaurantManager restaurantManager = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.model.User> sessionState = null;
    
    /**
     * Resolved once auth state has settled; used by Activity to set nav graph start destination.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.jrkg.jrkgbites.viewmodel.StartDestination> _startDestination = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.viewmodel.StartDestination> startDestination = null;
    
    /**
     * One-off event: request navigation to main after biometric (or other auth) success.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<kotlin.Unit> _navigateToMainEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<kotlin.Unit> navigateToMainEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _toastMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> toastMessage = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableSharedFlow<java.lang.String> _lowRatingEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.SharedFlow<java.lang.String> lowRatingEvent = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.jrkg.jrkgbites.domain.AuthMethod> _requiredAuthMethod = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.domain.AuthMethod> requiredAuthMethod = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isBiometricPreferenceEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isBiometricPreferenceEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isKeepLoggedInEnabled = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isKeepLoggedInEnabled = null;
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
    com.jrkg.jrkgbites.data.RestaurantRatingRepository restaurantRatingRepository, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.RestaurantManager restaurantManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.model.User> getSessionState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.viewmodel.StartDestination> getStartDestination() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<kotlin.Unit> getNavigateToMainEvent() {
        return null;
    }
    
    /**
     * Call after biometric (or other) auth success to trigger navigation to main.
     */
    public final void requestNavigateToMainAfterAuth() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getToastMessage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.SharedFlow<java.lang.String> getLowRatingEvent() {
        return null;
    }
    
    public final void clearToastMessage() {
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
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isKeepLoggedInEnabled() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.domain.service.AuthResult> signUp(@org.jetbrains.annotations.NotNull()
    java.lang.String email, @org.jetbrains.annotations.NotNull()
    java.lang.String pass, @org.jetbrains.annotations.NotNull()
    java.lang.String preferredName) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object logout(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
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
    
    public final void refreshDeck() {
    }
    
    public final void clearSelectedRestaurant() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.model.Restaurant> getRestaurantById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    public final void toggleFavorite(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
    }
    
    public final void toggleNeverAgain(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
    }
    
    public final void setKeepLoggedIn(boolean enabled) {
    }
    
    public final void submitRating(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId, int rating, @org.jetbrains.annotations.NotNull()
    java.lang.String comment) {
    }
    
    public final boolean isUserOwner(@org.jetbrains.annotations.Nullable()
    com.jrkg.jrkgbites.model.Restaurant restaurant) {
        return false;
    }
    
    public final void addToNeverAgainFromRating(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId) {
    }
    
    public final void createRestaurant(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.Restaurant restaurant) {
    }
    
    public final void updateRestaurant(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.Restaurant restaurant) {
    }
    
    public final void deleteRestaurant(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.model.Restaurant restaurant) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> sendPasswordResetEmail(@org.jetbrains.annotations.NotNull()
    java.lang.String email) {
        return null;
    }
}