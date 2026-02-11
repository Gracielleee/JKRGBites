package com.jrkg.jrkgbites.domain;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0007JG\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0016\b\u0002\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015\u00a2\u0006\u0002\u0010\u0017J$\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\u001a\u001a\u00020\u0012H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/jrkg/jrkgbites/domain/SearchManager;", "", "restaurantRepository", "Lcom/jrkg/jrkgbites/data/RestaurantRepository;", "<init>", "(Lcom/jrkg/jrkgbites/data/RestaurantRepository;)V", "scope", "Lkotlinx/coroutines/CoroutineScope;", "_allRestaurants", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/jrkg/jrkgbites/model/Restaurant;", "init", "", "searchAndSort", "query", "", "sortBy", "Lcom/jrkg/jrkgbites/domain/SortOption;", "userLocation", "Lkotlin/Pair;", "", "radiusInMeters", "(Ljava/lang/String;Lcom/jrkg/jrkgbites/domain/SortOption;Lkotlin/Pair;Ljava/lang/Double;)Ljava/util/List;", "sort", "restaurants", "option", "app_debug"})
public final class SearchManager {
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.data.RestaurantRepository restaurantRepository = null;
    private kotlinx.coroutines.CoroutineScope scope;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.jrkg.jrkgbites.model.Restaurant>> _allRestaurants = null;
    
    public SearchManager(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.data.RestaurantRepository restaurantRepository) {
        super();
    }
    
    public final void init(@org.jetbrains.annotations.NotNull()
    kotlinx.coroutines.CoroutineScope scope) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.jrkg.jrkgbites.model.Restaurant> searchAndSort(@org.jetbrains.annotations.NotNull()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.domain.SortOption sortBy, @org.jetbrains.annotations.Nullable()
    kotlin.Pair<java.lang.Double, java.lang.Double> userLocation, @org.jetbrains.annotations.Nullable()
    java.lang.Double radiusInMeters) {
        return null;
    }
    
    private final java.util.List<com.jrkg.jrkgbites.model.Restaurant> sort(java.util.List<com.jrkg.jrkgbites.model.Restaurant> restaurants, com.jrkg.jrkgbites.domain.SortOption option) {
        return null;
    }
}