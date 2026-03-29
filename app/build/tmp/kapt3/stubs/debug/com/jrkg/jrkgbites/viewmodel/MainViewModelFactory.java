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

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J%\u0010\u0006\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\nH\u0016\u00a2\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/jrkg/jrkgbites/viewmodel/MainViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "application", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"})
@kotlin.Suppress(names = {"UNCHECKED_CAST"})
public final class MainViewModelFactory implements androidx.lifecycle.ViewModelProvider.Factory {
    @org.jetbrains.annotations.NotNull()
    private final android.app.Application application = null;
    
    public MainViewModelFactory(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
    java.lang.Class<T> modelClass) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public <T extends androidx.lifecycle.ViewModel>T create(@org.jetbrains.annotations.NotNull()
    java.lang.Class<T> modelClass, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.viewmodel.CreationExtras extras) {
        return null;
    }
}