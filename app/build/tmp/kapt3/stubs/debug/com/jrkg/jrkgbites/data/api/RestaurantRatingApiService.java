package com.jrkg.jrkgbites.data.api;

import retrofit2.http.*;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00042\b\b\u0001\u0010\u0007\u001a\u00020\u0004H\u00a7@\u00a2\u0006\u0002\u0010\bJ$\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0001\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u0018\u0010\f\u001a\u00020\r2\b\b\u0001\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010\u00a8\u0006\u0011\u00c0\u0006\u0003"}, d2 = {"Lcom/jrkg/jrkgbites/data/api/RestaurantRatingApiService;", "", "getRestaurantRatings", "", "Lcom/jrkg/jrkgbites/data/api/RestaurantRatingDto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createRestaurantRating", "rating", "(Lcom/jrkg/jrkgbites/data/api/RestaurantRatingDto;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createRestaurantRatings", "ratings", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteRestaurantRating", "", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface RestaurantRatingApiService {
    
    @retrofit2.http.GET(value = "restaurants/ratings")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRestaurantRatings(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.jrkg.jrkgbites.data.api.RestaurantRatingDto>> $completion);
    
    @retrofit2.http.POST(value = "restaurants/ratings")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createRestaurantRating(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.api.RestaurantRatingDto rating, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.jrkg.jrkgbites.data.api.RestaurantRatingDto> $completion);
    
    @retrofit2.http.POST(value = "restaurants/ratings")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createRestaurantRatings(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.List<com.jrkg.jrkgbites.data.api.RestaurantRatingDto> ratings, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.jrkg.jrkgbites.data.api.RestaurantRatingDto>> $completion);
    
    @retrofit2.http.DELETE(value = "restaurants/ratings/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteRestaurantRating(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}