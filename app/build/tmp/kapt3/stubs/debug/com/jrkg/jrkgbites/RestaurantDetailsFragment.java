package com.jrkg.jrkgbites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.jrkg.jrkgbites.databinding.FragmentRestaurantDetailsBinding;
import com.jrkg.jrkgbites.model.Restaurant;
import com.jrkg.jrkgbites.utils.ImageStorageUtils;
import com.jrkg.jrkgbites.utils.ToastUtils;
import com.jrkg.jrkgbites.viewmodel.MainViewModel;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u000b\n\u0002\u0010 \n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u001a\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00142\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0010\u0010\u001e\u001a\u00020\u001c2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u0010!\u001a\u00020\u001c2\u0006\u0010\"\u001a\u00020 H\u0002J\u0010\u0010#\u001a\u00020\u001c2\u0006\u0010$\u001a\u00020 H\u0002J\u0010\u0010%\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\'H\u0002J\u0010\u0010(\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\'H\u0002J \u0010)\u001a\u00020\u001c2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020\u0012H\u0002J\u0010\u0010.\u001a\u00020\u001c2\u0006\u0010&\u001a\u00020\'H\u0002J\u0010\u0010/\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u0012H\u0002J\b\u00101\u001a\u00020\u001cH\u0002J\u0010\u00102\u001a\u00020\u001c2\u0006\u00100\u001a\u00020\u0012H\u0002J\b\u00103\u001a\u00020\u001cH\u0002J\b\u00104\u001a\u00020\u001cH\u0002J\u0016\u00105\u001a\u00020\u001c2\f\u00106\u001a\b\u0012\u0004\u0012\u00020\u001207H\u0002J\b\u00108\u001a\u00020\u001cH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00069"}, d2 = {"Lcom/jrkg/jrkgbites/RestaurantDetailsFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "_binding", "Lcom/jrkg/jrkgbites/databinding/FragmentRestaurantDetailsBinding;", "binding", "getBinding", "()Lcom/jrkg/jrkgbites/databinding/FragmentRestaurantDetailsBinding;", "viewModel", "Lcom/jrkg/jrkgbites/viewmodel/MainViewModel;", "args", "Lcom/jrkg/jrkgbites/RestaurantDetailsFragmentArgs;", "getArgs", "()Lcom/jrkg/jrkgbites/RestaurantDetailsFragmentArgs;", "args$delegate", "Landroidx/navigation/NavArgsLazy;", "currentRestaurantId", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "updateFavoriteButton", "isFavorited", "", "updateNeverAgainButton", "isNeverAgain", "updateIsPublicText", "isPublic", "willHideUnathorizedButtons", "restaurant", "Lcom/jrkg/jrkgbites/model/Restaurant;", "displayRestaurantDetails", "openExternalMap", "lat", "", "lng", "name", "setupRatingSection", "observeExistingRating", "restaurantId", "showDeleteConfirmationDialog", "showNeverAgainDialog", "showPremiumUnlockDialog", "showTagPicker", "saveTags", "tags", "", "onDestroyView", "app_debug"})
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
    
    private final void updateFavoriteButton(boolean isFavorited) {
    }
    
    private final void updateNeverAgainButton(boolean isNeverAgain) {
    }
    
    private final void updateIsPublicText(boolean isPublic) {
    }
    
    private final void willHideUnathorizedButtons(com.jrkg.jrkgbites.model.Restaurant restaurant) {
    }
    
    private final void displayRestaurantDetails(com.jrkg.jrkgbites.model.Restaurant restaurant) {
    }
    
    private final void openExternalMap(double lat, double lng, java.lang.String name) {
    }
    
    private final void setupRatingSection(com.jrkg.jrkgbites.model.Restaurant restaurant) {
    }
    
    private final void observeExistingRating(java.lang.String restaurantId) {
    }
    
    private final void showDeleteConfirmationDialog() {
    }
    
    private final void showNeverAgainDialog(java.lang.String restaurantId) {
    }
    
    private final void showPremiumUnlockDialog() {
    }
    
    private final void showTagPicker() {
    }
    
    private final void saveTags(java.util.List<java.lang.String> tags) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}