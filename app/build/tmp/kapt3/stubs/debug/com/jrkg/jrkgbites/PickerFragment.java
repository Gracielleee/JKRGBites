package com.jrkg.jrkgbites;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J\u001a\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00132\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0017J\b\u0010\u001d\u001a\u00020\u001bH\u0002J\u0010\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 H\u0002J\b\u0010!\u001a\u00020\u001bH\u0002J\b\u0010\"\u001a\u00020\u001bH\u0002J\b\u0010#\u001a\u00020\u001bH\u0002J\u0010\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\fH\u0002J\b\u0010&\u001a\u00020\u001bH\u0002J\b\u0010\'\u001a\u00020\u001bH\u0002J\b\u0010(\u001a\u00020\u001bH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/jrkg/jrkgbites/PickerFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "_binding", "Lcom/jrkg/jrkgbites/databinding/FragmentPickerBinding;", "binding", "getBinding", "()Lcom/jrkg/jrkgbites/databinding/FragmentPickerBinding;", "viewModel", "Lcom/jrkg/jrkgbites/viewmodel/MainViewModel;", "currentRestaurant", "Lcom/jrkg/jrkgbites/model/Restaurant;", "gestureDetector", "Landroidx/core/view/GestureDetectorCompat;", "isAnimating", "", "isNavigating", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "setupGestureDetection", "animateCardOffScreen", "direction", "Lcom/jrkg/jrkgbites/domain/SwipeDirection;", "resetCardVisuals", "resetCardPositionWithAnimation", "observeRestaurantDeck", "updateCardUI", "restaurant", "flipCard", "observeSelectedRestaurant", "onDestroyView", "app_debug"})
public final class PickerFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.jrkg.jrkgbites.databinding.FragmentPickerBinding _binding;
    private com.jrkg.jrkgbites.viewmodel.MainViewModel viewModel;
    @org.jetbrains.annotations.Nullable()
    private com.jrkg.jrkgbites.model.Restaurant currentRestaurant;
    private androidx.core.view.GestureDetectorCompat gestureDetector;
    private boolean isAnimating = false;
    private boolean isNavigating = false;
    
    public PickerFragment() {
        super();
    }
    
    private final com.jrkg.jrkgbites.databinding.FragmentPickerBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupGestureDetection() {
    }
    
    private final void animateCardOffScreen(com.jrkg.jrkgbites.domain.SwipeDirection direction) {
    }
    
    private final void resetCardVisuals() {
    }
    
    private final void resetCardPositionWithAnimation() {
    }
    
    private final void observeRestaurantDeck() {
    }
    
    private final void updateCardUI(com.jrkg.jrkgbites.model.Restaurant restaurant) {
    }
    
    private final void flipCard() {
    }
    
    private final void observeSelectedRestaurant() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}