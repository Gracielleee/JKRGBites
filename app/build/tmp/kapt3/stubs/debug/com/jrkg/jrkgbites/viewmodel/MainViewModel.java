package com.jrkg.jrkgbites.viewmodel;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.jrkg.jrkgbites.data.local.AppDatabase;
import com.jrkg.jrkgbites.data.repository.RestaurantRepository;
import com.jrkg.jrkgbites.data.UserPreferencesManager;
import com.jrkg.jrkgbites.domain.*;
import com.jrkg.jrkgbites.domain.service.AuthResult;
import com.jrkg.jrkgbites.domain.service.AuthService;
import com.jrkg.jrkgbites.model.Restaurant;
import com.jrkg.jrkgbites.model.RestaurantRating;
import com.jrkg.jrkgbites.model.User;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.StateFlow;
import com.jrkg.jrkgbites.data.repository.RestaurantRatingRepository;
import com.jrkg.jrkgbites.data.source.FirebaseAuthService;
import com.jrkg.jrkgbites.services.BiometricService;
import kotlinx.coroutines.flow.SharingStarted;
import com.jrkg.jrkgbites.R;
import com.jrkg.jrkgbites.data.repository.RouletteRepository;
import com.jrkg.jrkgbites.model.SpinSession;
import com.jrkg.jrkgbites.model.SubscriptionStatus;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u00ea\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001Bw\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013\u0012\u0006\u0010\u0014\u001a\u00020\u0015\u0012\u0006\u0010\u0016\u001a\u00020\u0017\u0012\u0006\u0010\u0018\u001a\u00020\u0019\u0012\u0006\u0010\u001a\u001a\u00020\u001b\u0012\u0006\u0010\u001c\u001a\u00020\u001d\u00a2\u0006\u0004\b\u001e\u0010\u001fJ\u0006\u00107\u001a\u000202J\u0006\u0010?\u001a\u000202J\u001c\u0010g\u001a\b\u0012\u0004\u0012\u00020i0h2\u0006\u0010j\u001a\u0002092\u0006\u0010k\u001a\u000209J$\u0010l\u001a\b\u0012\u0004\u0012\u00020i0h2\u0006\u0010j\u001a\u0002092\u0006\u0010k\u001a\u0002092\u0006\u0010m\u001a\u000209J\u0014\u0010n\u001a\b\u0012\u0004\u0012\u00020i0h2\u0006\u0010o\u001a\u000209J\u000e\u0010p\u001a\u000202H\u0086@\u00a2\u0006\u0002\u0010qJ\u0016\u0010r\u001a\u0002022\u0006\u0010s\u001a\u00020N2\u0006\u0010t\u001a\u00020uJ\u0006\u0010v\u001a\u000202J\u0006\u0010w\u001a\u000202J\u0006\u0010x\u001a\u000202J\u0006\u0010y\u001a\u000202J\u0006\u0010z\u001a\u000202J\u0006\u0010{\u001a\u000202J\u0016\u0010|\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010N0h2\u0006\u0010}\u001a\u000209J\u000e\u0010~\u001a\u0002022\u0006\u0010\u007f\u001a\u000209J\u000f\u0010\u0080\u0001\u001a\u0002022\u0006\u0010\u007f\u001a\u000209J\u0010\u0010\u0081\u0001\u001a\u0002022\u0007\u0010\u0082\u0001\u001a\u00020EJ\"\u0010\u0083\u0001\u001a\u0002022\u0006\u0010\u007f\u001a\u0002092\b\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0007\u0010\u0086\u0001\u001a\u000209J\u0011\u0010\u0087\u0001\u001a\u00020E2\b\u0010s\u001a\u0004\u0018\u00010NJ\u000f\u0010\u0088\u0001\u001a\u0002022\u0006\u0010\u007f\u001a\u000209J\u000f\u0010\u0089\u0001\u001a\u0002022\u0006\u0010s\u001a\u00020NJ\u000f\u0010\u008a\u0001\u001a\u0002022\u0006\u0010s\u001a\u00020NJ\u000f\u0010\u008b\u0001\u001a\u0002022\u0006\u0010s\u001a\u00020NJ\u0015\u0010\u008c\u0001\u001a\b\u0012\u0004\u0012\u00020E0h2\u0006\u0010j\u001a\u000209J\u0007\u0010\u0091\u0001\u001a\u000202J\u0012\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u008e\u0001H\u0086@\u00a2\u0006\u0002\u0010qJ\u0018\u0010\u0092\u0001\u001a\u0002022\u0006\u0010\u007f\u001a\u000209H\u0086@\u00a2\u0006\u0003\u0010\u0093\u0001J\u000f\u0010\u0094\u0001\u001a\u00020EH\u0086@\u00a2\u0006\u0002\u0010qJ\u0007\u0010\u0095\u0001\u001a\u00020EJ\u0010\u0010\u0096\u0001\u001a\u0002022\u0007\u0010\u0082\u0001\u001a\u00020EJ\u001b\u0010\u0097\u0001\u001a\u0002022\b\u0010\u0098\u0001\u001a\u00030\u0099\u00012\b\u0010\u009a\u0001\u001a\u00030\u0099\u0001J\u0016\u0010\u009b\u0001\u001a\u0011\u0012\u0005\u0012\u00030\u0099\u0001\u0012\u0005\u0012\u00030\u0099\u00010\u009c\u0001J\u0007\u0010\u00a0\u0001\u001a\u00020EJ\u0007\u0010\u00a1\u0001\u001a\u000202J\u0007\u0010\u00a2\u0001\u001a\u000202R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\u001a\u001a\u00020\u001b\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0011\u0010\u001c\u001a\u00020\u001d\u00a2\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0019\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010(0\'\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0016\u0010+\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0,X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010.\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0\'\u00a2\u0006\b\n\u0000\u001a\u0004\b/\u0010*R\u0014\u00100\u001a\b\u0012\u0004\u0012\u00020201X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u00103\u001a\b\u0012\u0004\u0012\u00020204\u00a2\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0016\u00108\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001090,X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010:\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001090\'\u00a2\u0006\b\n\u0000\u001a\u0004\b;\u0010*R\u0014\u0010<\u001a\b\u0012\u0004\u0012\u00020901X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010=\u001a\b\u0012\u0004\u0012\u00020904\u00a2\u0006\b\n\u0000\u001a\u0004\b>\u00106R\u0014\u0010@\u001a\b\u0012\u0004\u0012\u00020A0,X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010B\u001a\b\u0012\u0004\u0012\u00020A0\'\u00a2\u0006\b\n\u0000\u001a\u0004\bC\u0010*R\u0014\u0010D\u001a\b\u0012\u0004\u0012\u00020E0,X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010F\u001a\b\u0012\u0004\u0012\u00020E0\'\u00a2\u0006\b\n\u0000\u001a\u0004\bF\u0010*R\u0014\u0010G\u001a\b\u0012\u0004\u0012\u00020E0,X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010H\u001a\b\u0012\u0004\u0012\u00020E0\'\u00a2\u0006\b\n\u0000\u001a\u0004\bH\u0010*R\u0016\u0010I\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001090,X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010J\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001090\'\u00a2\u0006\b\n\u0000\u001a\u0004\bK\u0010*R\'\u0010L\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020N0M0\'8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bP\u0010Q\u001a\u0004\bO\u0010*R\'\u0010R\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020N0M0\'8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bT\u0010Q\u001a\u0004\bS\u0010*R\'\u0010U\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020N0M0\'8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bW\u0010Q\u001a\u0004\bV\u0010*R\'\u0010X\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020N0M0\'8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bZ\u0010Q\u001a\u0004\bY\u0010*R#\u0010[\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010N0\'8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b]\u0010Q\u001a\u0004\b\\\u0010*R\'\u0010^\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020N0M0\'8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b`\u0010Q\u001a\u0004\b_\u0010*R\'\u0010a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020N0M0\'8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bc\u0010Q\u001a\u0004\bb\u0010*R\u001d\u0010d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020e0M0\'\u00a2\u0006\b\n\u0000\u001a\u0004\bf\u0010*R\u0018\u0010\u008d\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u008e\u00010,X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u008f\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u008e\u00010\'\u00a2\u0006\t\n\u0000\u001a\u0005\b\u0090\u0001\u0010*R\u0014\u0010\u009d\u0001\u001a\u00020\u000f8F\u00a2\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001\u00a8\u0006\u00a3\u0001"}, d2 = {"Lcom/jrkg/jrkgbites/viewmodel/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "application", "Landroid/app/Application;", "restaurantPicker", "Lcom/jrkg/jrkgbites/domain/RestaurantPicker;", "swipeManager", "Lcom/jrkg/jrkgbites/domain/SwipeManager;", "searchManager", "Lcom/jrkg/jrkgbites/domain/SearchManager;", "ratingManager", "Lcom/jrkg/jrkgbites/domain/RatingManager;", "authManager", "Lcom/jrkg/jrkgbites/domain/AuthManager;", "prefsManager", "Lcom/jrkg/jrkgbites/data/UserPreferencesManager;", "sessionManager", "Lcom/jrkg/jrkgbites/domain/SessionManager;", "restaurantRepository", "Lcom/jrkg/jrkgbites/data/repository/RestaurantRepository;", "restaurantRatingRepository", "Lcom/jrkg/jrkgbites/data/repository/RestaurantRatingRepository;", "restaurantManager", "Lcom/jrkg/jrkgbites/domain/RestaurantManager;", "rouletteRepository", "Lcom/jrkg/jrkgbites/data/repository/RouletteRepository;", "adManager", "Lcom/jrkg/jrkgbites/domain/AdManager;", "subscriptionManager", "Lcom/jrkg/jrkgbites/domain/SubscriptionManager;", "<init>", "(Landroid/app/Application;Lcom/jrkg/jrkgbites/domain/RestaurantPicker;Lcom/jrkg/jrkgbites/domain/SwipeManager;Lcom/jrkg/jrkgbites/domain/SearchManager;Lcom/jrkg/jrkgbites/domain/RatingManager;Lcom/jrkg/jrkgbites/domain/AuthManager;Lcom/jrkg/jrkgbites/data/UserPreferencesManager;Lcom/jrkg/jrkgbites/domain/SessionManager;Lcom/jrkg/jrkgbites/data/repository/RestaurantRepository;Lcom/jrkg/jrkgbites/data/repository/RestaurantRatingRepository;Lcom/jrkg/jrkgbites/domain/RestaurantManager;Lcom/jrkg/jrkgbites/data/repository/RouletteRepository;Lcom/jrkg/jrkgbites/domain/AdManager;Lcom/jrkg/jrkgbites/domain/SubscriptionManager;)V", "getRouletteRepository", "()Lcom/jrkg/jrkgbites/data/repository/RouletteRepository;", "getAdManager", "()Lcom/jrkg/jrkgbites/domain/AdManager;", "getSubscriptionManager", "()Lcom/jrkg/jrkgbites/domain/SubscriptionManager;", "sessionState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/jrkg/jrkgbites/model/User;", "getSessionState", "()Lkotlinx/coroutines/flow/StateFlow;", "_startDestination", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/jrkg/jrkgbites/viewmodel/StartDestination;", "startDestination", "getStartDestination", "_navigateToMainEvent", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "", "navigateToMainEvent", "Lkotlinx/coroutines/flow/SharedFlow;", "getNavigateToMainEvent", "()Lkotlinx/coroutines/flow/SharedFlow;", "requestNavigateToMainAfterAuth", "_toastMessage", "", "toastMessage", "getToastMessage", "_lowRatingEvent", "lowRatingEvent", "getLowRatingEvent", "clearToastMessage", "_requiredAuthMethod", "Lcom/jrkg/jrkgbites/domain/AuthMethod;", "requiredAuthMethod", "getRequiredAuthMethod", "_isBiometricPreferenceEnabled", "", "isBiometricPreferenceEnabled", "_isKeepLoggedInEnabled", "isKeepLoggedInEnabled", "_pickedResult", "pickedResult", "getPickedResult", "allRestaurants", "", "Lcom/jrkg/jrkgbites/model/Restaurant;", "getAllRestaurants", "allRestaurants$delegate", "Lkotlin/Lazy;", "displayOrder", "getDisplayOrder", "displayOrder$delegate", "favoritesList", "getFavoritesList", "favoritesList$delegate", "neverAgainList", "getNeverAgainList", "neverAgainList$delegate", "selectedRestaurant", "getSelectedRestaurant", "selectedRestaurant$delegate", "nearbyRestaurants", "getNearbyRestaurants", "nearbyRestaurants$delegate", "deck", "getDeck", "deck$delegate", "allRestaurantRatings", "Lcom/jrkg/jrkgbites/model/RestaurantRating;", "getAllRestaurantRatings", "login", "Lkotlinx/coroutines/flow/Flow;", "Lcom/jrkg/jrkgbites/domain/service/AuthResult;", "email", "pass", "signUp", "preferredName", "signInWithGoogle", "idToken", "logout", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onSwipe", "restaurant", "direction", "Lcom/jrkg/jrkgbites/domain/SwipeDirection;", "resetDeck", "shuffleDeck", "resetRouletteSession", "undoSwipe", "refreshDeck", "clearSelectedRestaurant", "getRestaurantById", "id", "toggleFavorite", "restaurantId", "toggleNeverAgain", "setKeepLoggedIn", "enabled", "submitRating", "rating", "", "comment", "isUserOwner", "addToNeverAgainFromRating", "createRestaurant", "updateRestaurant", "deleteRestaurant", "sendPasswordResetEmail", "_rouletteSession", "Lcom/jrkg/jrkgbites/model/SpinSession;", "rouletteSession", "getRouletteSession", "refreshRouletteSession", "logRouletteSpin", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "watchRouletteAd", "isProximityEnabled", "setProximityEnabled", "updateUserLocation", "lat", "", "lng", "getUserLocation", "Lkotlin/Pair;", "userPrefsManager", "getUserPrefsManager", "()Lcom/jrkg/jrkgbites/data/UserPreferencesManager;", "isPremiumActive", "startFreeTrial", "subscribeUser", "app_debug"})
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
    private final com.jrkg.jrkgbites.data.repository.RestaurantRepository restaurantRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.repository.RestaurantRatingRepository restaurantRatingRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.RestaurantManager restaurantManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.repository.RouletteRepository rouletteRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.AdManager adManager = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.domain.SubscriptionManager subscriptionManager = null;
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
    private final kotlin.Lazy allRestaurants$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy displayOrder$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy favoritesList$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy neverAgainList$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy selectedRestaurant$delegate = null;
    
    /**
     * The single source of truth for all nearby restaurants.
     * Respects proximity settings and real-time location.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy nearbyRestaurants$delegate = null;
    
    /**
     * The reactive deck for the Picker (Home) view.
     * Filters nearby restaurants by excluding only "Never Again" and session-swiped items.
     * Favorites are NOT hidden here anymore.
     */
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy deck$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.RestaurantRating>> allRestaurantRatings = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.jrkg.jrkgbites.model.SpinSession> _rouletteSession = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.model.SpinSession> rouletteSession = null;
    
    public MainViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.RestaurantPicker restaurantPicker, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.SwipeManager swipeManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.SearchManager searchManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.RatingManager ratingManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.AuthManager authManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.UserPreferencesManager prefsManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.SessionManager sessionManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.repository.RestaurantRepository restaurantRepository, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.repository.RestaurantRatingRepository restaurantRatingRepository, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.RestaurantManager restaurantManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.repository.RouletteRepository rouletteRepository, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.AdManager adManager, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.SubscriptionManager subscriptionManager) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.jrkg.jrkgbites.data.repository.RouletteRepository getRouletteRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.jrkg.jrkgbites.domain.AdManager getAdManager() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.jrkg.jrkgbites.domain.SubscriptionManager getSubscriptionManager() {
        return null;
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
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getAllRestaurants() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getDisplayOrder() {
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
    
    /**
     * The single source of truth for all nearby restaurants.
     * Respects proximity settings and real-time location.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getNearbyRestaurants() {
        return null;
    }
    
    /**
     * The reactive deck for the Picker (Home) view.
     * Filters nearby restaurants by excluding only "Never Again" and session-swiped items.
     * Favorites are NOT hidden here anymore.
     */
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> getDeck() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.jrkg.jrkgbites.model.RestaurantRating>> getAllRestaurantRatings() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.jrkg.jrkgbites.domain.service.AuthResult> signInWithGoogle(@org.jetbrains.annotations.NotNull()
    java.lang.String idToken) {
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
    
    public final void resetRouletteSession() {
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
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.jrkg.jrkgbites.model.SpinSession> getRouletteSession() {
        return null;
    }
    
    public final void refreshRouletteSession() {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getRouletteSession(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.jrkg.jrkgbites.model.SpinSession> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object logRouletteSpin(@org.jetbrains.annotations.NotNull()
    java.lang.String restaurantId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object watchRouletteAd(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Boolean> $completion) {
        return null;
    }
    
    public final boolean isProximityEnabled() {
        return false;
    }
    
    public final void setProximityEnabled(boolean enabled) {
    }
    
    public final void updateUserLocation(double lat, double lng) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<java.lang.Double, java.lang.Double> getUserLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.jrkg.jrkgbites.data.UserPreferencesManager getUserPrefsManager() {
        return null;
    }
    
    public final boolean isPremiumActive() {
        return false;
    }
    
    public final void startFreeTrial() {
    }
    
    public final void subscribeUser() {
    }
}