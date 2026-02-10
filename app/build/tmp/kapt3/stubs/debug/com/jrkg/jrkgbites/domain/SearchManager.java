package com.jrkg.jrkgbites.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nJG\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u00a2\u0006\u0002\u0010\u0016J$\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\u0019\u001a\u00020\u0011H\u0002R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/jrkg/jrkgbites/domain/SearchManager;", "", "restaurantRepository", "Lcom/jrkg/jrkgbites/data/RestaurantRepository;", "(Lcom/jrkg/jrkgbites/data/RestaurantRepository;)V", "_allRestaurants", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/jrkg/jrkgbites/model/Restaurant;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "init", "", "searchAndSort", "query", "", "sortBy", "Lcom/jrkg/jrkgbites/domain/SortOption;", "userLocation", "Lkotlin/Pair;", "", "radiusInMeters", "(Ljava/lang/String;Lcom/jrkg/jrkgbites/domain/SortOption;Lkotlin/Pair;Ljava/lang/Double;)Ljava/util/List;", "sort", "restaurants", "option", "app_debug"})
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