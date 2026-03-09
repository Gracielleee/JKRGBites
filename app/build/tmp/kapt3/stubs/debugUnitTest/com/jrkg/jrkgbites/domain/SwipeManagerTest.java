package com.jrkg.jrkgbites.domain;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\f\u001a\u00020\rH\u0007J\f\u0010\u000e\u001a\u00060\rj\u0002`\u000fH\u0007J\f\u0010\u0010\u001a\u00060\rj\u0002`\u000fH\u0007R\u0012\u0010\u0004\u001a\u00020\u00058\u0002@\u0002X\u0083.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/jrkg/jrkgbites/domain/SwipeManagerTest;", "", "<init>", "()V", "restaurantRepository", "Lcom/jrkg/jrkgbites/data/RestaurantRepository;", "swipeManager", "Lcom/jrkg/jrkgbites/domain/SwipeManager;", "testScope", "Lkotlinx/coroutines/test/TestScope;", "testRestaurant", "Lcom/jrkg/jrkgbites/model/Restaurant;", "setup", "", "onSwipe UP adds to favorites via repository", "Lkotlinx/coroutines/test/TestResult;", "onSwipe UP should update favoritesList state", "app_debugUnitTest"})
@kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
public final class SwipeManagerTest {
    @org.mockito.Mock()
    private com.jrkg.jrkgbites.data.RestaurantRepository restaurantRepository;
    private com.jrkg.jrkgbites.domain.SwipeManager swipeManager;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.test.TestScope testScope = null;
    @org.jetbrains.annotations.NotNull()
    private final com.jrkg.jrkgbites.model.Restaurant testRestaurant = null;
    
    public SwipeManagerTest() {
        super();
    }
    
    @org.junit.Before()
    public final void setup() {
    }
}