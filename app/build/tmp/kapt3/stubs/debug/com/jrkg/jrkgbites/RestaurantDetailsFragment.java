package com.jrkg.jrkgbites;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u000fH\u0002J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0013H\u0016J\u001a\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\u0010\u0010#\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"Lcom/jrkg/jrkgbites/RestaurantDetailsFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/jrkg/jrkgbites/databinding/FragmentRestaurantDetailsBinding;", "args", "Lcom/jrkg/jrkgbites/RestaurantDetailsFragmentArgs;", "getArgs", "()Lcom/jrkg/jrkgbites/RestaurantDetailsFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "binding", "getBinding", "()Lcom/jrkg/jrkgbites/databinding/FragmentRestaurantDetailsBinding;", "currentRestaurantId", "", "viewModel", "Lcom/jrkg/jrkgbites/viewmodel/MainViewModel;", "displayRestaurantDetails", "", "restaurant", "Lcom/jrkg/jrkgbites/model/Restaurant;", "observeExistingRating", "restaurantId", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupRatingSection", "app_debug"})
public final class RestaurantDetailsFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.jrkg.jrkgbites.databinding.FragmentRestaurantDetailsBinding _binding;
    private com.jrkg.jrkgbites.viewmodel.MainViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    private final androidx.navigation.NavArgsLazy args$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String currentRestaurantId;
    
    public RestaurantDetailsFragment() {
        super();
    }
    
    private final com.jrkg.jrkgbites.databinding.FragmentRestaurantDetailsBinding getBinding() {
        return null;
    }
    
    private final com.jrkg.jrkgbites.RestaurantDetailsFragmentArgs getArgs() {
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
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void displayRestaurantDetails(com.jrkg.jrkgbites.model.Restaurant restaurant) {
    }
    
    private final void setupRatingSection(com.jrkg.jrkgbites.model.Restaurant restaurant) {
    }
    
    private final void observeExistingRating(java.lang.String restaurantId) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}