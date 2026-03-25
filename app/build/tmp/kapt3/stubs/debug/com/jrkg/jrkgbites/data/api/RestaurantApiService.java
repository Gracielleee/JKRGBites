package com.jrkg.jrkgbites.data.api;

import com.jrkg.jrkgbites.model.Restaurant;
import retrofit2.http.*;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u00020\u00042\b\b\u0001\u0010\f\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u00020\u000f2\b\b\u0001\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010\u0012\u001a\u00020\u00112\b\b\u0001\u0010\f\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0013J\u0018\u0010\u0014\u001a\u00020\u000f2\b\b\u0001\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0014\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010\u0017\u001a\u00020\u00162\b\b\u0001\u0010\f\u001a\u00020\u0016H\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u0018\u0010\u0019\u001a\u00020\u000f2\b\b\u0001\u0010\b\u001a\u00020\tH\u00a7@\u00a2\u0006\u0002\u0010\n\u00a8\u0006\u001a\u00c0\u0006\u0003"}, d2 = {"Lcom/jrkg/jrkgbites/data/api/RestaurantApiService;", "", "getRestaurants", "", "Lcom/jrkg/jrkgbites/data/api/RestaurantDto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRestaurant", "Lcom/jrkg/jrkgbites/model/Restaurant;", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createRestaurant", "restaurant", "(Lcom/jrkg/jrkgbites/data/api/RestaurantDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRestaurant", "", "getFavoriteRestaurants", "Lcom/jrkg/jrkgbites/data/api/FavoriteRestaurantDto;", "addFavorite", "(Lcom/jrkg/jrkgbites/data/api/FavoriteRestaurantDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeFavorite", "getNeverAgainRestaurants", "Lcom/jrkg/jrkgbites/data/api/NeverAgainRestaurantDto;", "addNeverAgain", "(Lcom/jrkg/jrkgbites/data/api/NeverAgainRestaurantDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "removeNeverAgain", "app_debug"})
public abstract interface RestaurantApiService {
    
    @retrofit2.http.GET(value = "restaurants")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRestaurants(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.jrkg.jrkgbites.data.api.RestaurantDto>> $completion);
    
    @retrofit2.http.GET(value = "restaurants/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRestaurant(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.jrkg.jrkgbites.model.Restaurant> $completion);
    
    @retrofit2.http.POST(value = "restaurants")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createRestaurant(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.api.RestaurantDto restaurant, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.jrkg.jrkgbites.data.api.RestaurantDto> $completion);
    
    @retrofit2.http.DELETE(value = "restaurants/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRestaurant(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.GET(value = "restaurants/favorites")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getFavoriteRestaurants(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.jrkg.jrkgbites.data.api.FavoriteRestaurantDto>> $completion);
    
    @retrofit2.http.POST(value = "restaurants/favorite")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addFavorite(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.api.FavoriteRestaurantDto restaurant, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.jrkg.jrkgbites.data.api.FavoriteRestaurantDto> $completion);
    
    @retrofit2.http.DELETE(value = "restaurants/favorite/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeFavorite(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.GET(value = "restaurants/neveragains")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getNeverAgainRestaurants(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.jrkg.jrkgbites.data.api.NeverAgainRestaurantDto>> $completion);
    
    @retrofit2.http.POST(value = "restaurants/neveragain")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object addNeverAgain(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.api.NeverAgainRestaurantDto restaurant, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.jrkg.jrkgbites.data.api.NeverAgainRestaurantDto> $completion);
    
    @retrofit2.http.DELETE(value = "restaurants/neveragain/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object removeNeverAgain(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}