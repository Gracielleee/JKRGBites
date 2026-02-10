package com.jrkg.jrkgbites;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\b\u0010\u0015\u001a\u00020\u0012H\u0002J\b\u0010\u0016\u001a\u00020\u0012H\u0002J\b\u0010\u0017\u001a\u00020\u0012H\u0002J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0012H\u0016J\u001a\u0010!\u001a\u00020\u00122\u0006\u0010\"\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0017J\b\u0010#\u001a\u00020\u0012H\u0002J\b\u0010$\u001a\u00020\u0012H\u0002J\b\u0010%\u001a\u00020\u0012H\u0002J\u0010\u0010&\u001a\u00020\u00122\u0006\u0010\'\u001a\u00020\tH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/jrkg/jrkgbites/PickerFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/jrkg/jrkgbites/databinding/FragmentPickerBinding;", "binding", "getBinding", "()Lcom/jrkg/jrkgbites/databinding/FragmentPickerBinding;", "currentRestaurant", "Lcom/jrkg/jrkgbites/model/Restaurant;", "gestureDetector", "Landroidx/core/view/GestureDetectorCompat;", "isAnimating", "", "isNavigating", "viewModel", "Lcom/jrkg/jrkgbites/viewmodel/MainViewModel;", "animateCardOffScreen", "", "direction", "Lcom/jrkg/jrkgbites/domain/SwipeDirection;", "flipCard", "observeRestaurantDeck", "observeSelectedRestaurant", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "resetCardPositionWithAnimation", "resetCardVisuals", "setupGestureDetection", "updateCardUI", "restaurant", "app_debug"})
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