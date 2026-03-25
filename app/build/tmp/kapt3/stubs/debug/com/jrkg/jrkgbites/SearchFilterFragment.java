package com.jrkg.jrkgbites;

import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.transition.TransitionManager;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.jrkg.jrkgbites.databinding.FragmentSearchFilterBinding;
import com.jrkg.jrkgbites.model.Restaurant;
import com.jrkg.jrkgbites.services.FilterService;
import com.jrkg.jrkgbites.viewmodel.MainViewModel;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u001a\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00122\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u001aH\u0002J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u0010 \u001a\u00020\u001aH\u0002J \u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00122\u0006\u0010$\u001a\u00020\u0012H\u0002J\u0016\u0010%\u001a\u00020\u001a2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020(0\'H\u0002J,\u0010)\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\r0\'2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0018\u0010,\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020.H\u0002J\u0010\u0010/\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0010\u00100\u001a\u00020\r2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\b\u00101\u001a\u00020\u001aH\u0016R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\u00020\u00058BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00062"}, d2 = {"Lcom/jrkg/jrkgbites/SearchFilterFragment;", "Landroidx/fragment/app/Fragment;", "<init>", "()V", "_binding", "Lcom/jrkg/jrkgbites/databinding/FragmentSearchFilterBinding;", "binding", "getBinding", "()Lcom/jrkg/jrkgbites/databinding/FragmentSearchFilterBinding;", "viewModel", "Lcom/jrkg/jrkgbites/viewmodel/MainViewModel;", "initialCategories", "", "", "initialCuisines", "initialLevels", "initialTags", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "", "view", "clearAllSelections", "resetChipGroup", "group", "Lcom/google/android/material/chip/ChipGroup;", "setupExpandableSections", "setupToggle", "header", "content", "arrow", "populateAllChipGroups", "restaurants", "", "Lcom/jrkg/jrkgbites/model/Restaurant;", "populateChipGroup", "strings", "selectedSet", "uncheckOthers", "currentChip", "Lcom/google/android/material/chip/Chip;", "uncheckAllChip", "getCheckedChipTexts", "onDestroyView", "app_debug"})
public final class SearchFilterFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.jrkg.jrkgbites.databinding.FragmentSearchFilterBinding _binding;
    private com.jrkg.jrkgbites.viewmodel.MainViewModel viewModel;
    @org.jetbrains.annotations.NotNull()
    private java.util.Set<java.lang.String> initialCategories;
    @org.jetbrains.annotations.NotNull()
    private java.util.Set<java.lang.String> initialCuisines;
    @org.jetbrains.annotations.NotNull()
    private java.util.Set<java.lang.String> initialLevels;
    @org.jetbrains.annotations.NotNull()
    private java.util.Set<java.lang.String> initialTags;
    
    public SearchFilterFragment() {
        super();
    }
    
    private final com.jrkg.jrkgbites.databinding.FragmentSearchFilterBinding getBinding() {
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
    
    private final void clearAllSelections() {
    }
    
    private final void resetChipGroup(com.google.android.material.chip.ChipGroup group) {
    }
    
    private final void setupExpandableSections() {
    }
    
    private final void setupToggle(android.view.View header, android.view.View content, android.view.View arrow) {
    }
    
    private final void populateAllChipGroups(java.util.List<com.jrkg.jrkgbites.model.Restaurant> restaurants) {
    }
    
    private final void populateChipGroup(com.google.android.material.chip.ChipGroup group, java.util.List<java.lang.String> strings, java.util.Set<java.lang.String> selectedSet) {
    }
    
    private final void uncheckOthers(com.google.android.material.chip.ChipGroup group, com.google.android.material.chip.Chip currentChip) {
    }
    
    private final void uncheckAllChip(com.google.android.material.chip.ChipGroup group) {
    }
    
    private final java.lang.String getCheckedChipTexts(com.google.android.material.chip.ChipGroup group) {
        return null;
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}