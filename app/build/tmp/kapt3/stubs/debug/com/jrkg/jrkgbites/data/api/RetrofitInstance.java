package com.jrkg.jrkgbites.data.api;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c6\u0002\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0006\u001a\u00020\u00078FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR\u001b\u0010\u0011\u001a\u00020\u00128FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0015\u0010\u000b\u001a\u0004\b\u0013\u0010\u0014\u00a8\u0006\u0016"}, d2 = {"Lcom/jrkg/jrkgbites/data/api/RetrofitInstance;", "", "<init>", "()V", "BASE_URL", "", "userAPI", "Lcom/jrkg/jrkgbites/data/api/UserApiService;", "getUserAPI", "()Lcom/jrkg/jrkgbites/data/api/UserApiService;", "userAPI$delegate", "Lkotlin/Lazy;", "restaurantApi", "Lcom/jrkg/jrkgbites/data/api/RestaurantApiService;", "getRestaurantApi", "()Lcom/jrkg/jrkgbites/data/api/RestaurantApiService;", "restaurantApi$delegate", "ratingApi", "Lcom/jrkg/jrkgbites/data/api/RestaurantRatingApiService;", "getRatingApi", "()Lcom/jrkg/jrkgbites/data/api/RestaurantRatingApiService;", "ratingApi$delegate", "app_debug"})
public final class RetrofitInstance {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BASE_URL = "https://jsonplaceholder.typicode.com/";
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy userAPI$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy restaurantApi$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy ratingApi$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.data.api.RetrofitInstance INSTANCE = null;
    
    private RetrofitInstance() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.jrkg.jrkgbites.data.api.UserApiService getUserAPI() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.jrkg.jrkgbites.data.api.RestaurantApiService getRestaurantApi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.jrkg.jrkgbites.data.api.RestaurantRatingApiService getRatingApi() {
        return null;
    }
}