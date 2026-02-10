package com.jrkg.jrkgbites.adapter;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u0017B3\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0016\b\u0002\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n\u0018\u00010\t\u00a2\u0006\u0002\u0010\u000bJ\b\u0010\f\u001a\u00020\rH\u0016J\u0018\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\rH\u0016J\u0018\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\rH\u0016J\u0014\u0010\u0015\u001a\u00020\n2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/jrkg/jrkgbites/adapter/RestaurantAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/jrkg/jrkgbites/adapter/RestaurantAdapter$RestaurantViewHolder;", "context", "Landroid/content/Context;", "restaurantList", "", "Lcom/jrkg/jrkgbites/model/Restaurant;", "onItemClick", "Lkotlin/Function1;", "", "(Landroid/content/Context;Ljava/util/List;Lkotlin/jvm/functions/Function1;)V", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "updateList", "newList", "RestaurantViewHolder", "app_debug"})
public final class RestaurantAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.jrkg.jrkgbites.adapter.RestaurantAdapter.RestaurantViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.jrkg.jrkgbites.model.Restaurant> restaurantList;
    @org.jetbrains.annotations.Nullable()
    private final kotlin.jvm.functions.Function1<com.jrkg.jrkgbites.model.Restaurant, kotlin.Unit> onItemClick = null;
    
    public RestaurantAdapter(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<com.jrkg.jrkgbites.model.Restaurant> restaurantList, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super com.jrkg.jrkgbites.model.Restaurant, kotlin.Unit> onItemClick) {
        super();
    }
    
    public final void updateList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.jrkg.jrkgbites.model.Restaurant> newList) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.jrkg.jrkgbites.adapter.RestaurantAdapter.RestaurantViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.adapter.RestaurantAdapter.RestaurantViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0011\u0010\r\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\bR\u0011\u0010\u000f\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\u001b\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\bR\u0011\u0010\u001d\u001a\u00020\u001e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\b\u00a8\u0006#"}, d2 = {"Lcom/jrkg/jrkgbites/adapter/RestaurantAdapter$RestaurantViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/jrkg/jrkgbites/databinding/ItemRestaurantListCardBinding;", "(Lcom/jrkg/jrkgbites/databinding/ItemRestaurantListCardBinding;)V", "backCategory", "Landroid/widget/TextView;", "getBackCategory", "()Landroid/widget/TextView;", "backCuisine", "getBackCuisine", "backLevel", "getBackLevel", "backName", "getBackName", "backTags", "getBackTags", "cardBack", "Landroid/widget/LinearLayout;", "getCardBack", "()Landroid/widget/LinearLayout;", "cardFlipContainer", "Landroid/widget/FrameLayout;", "getCardFlipContainer", "()Landroid/widget/FrameLayout;", "cardFront", "getCardFront", "restaurantDescription", "getRestaurantDescription", "restaurantImage", "Landroid/widget/ImageView;", "getRestaurantImage", "()Landroid/widget/ImageView;", "restaurantName", "getRestaurantName", "app_debug"})
    public static final class RestaurantViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.jrkg.jrkgbites.databinding.ItemRestaurantListCardBinding binding = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.FrameLayout cardFlipContainer = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout cardFront = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.LinearLayout cardBack = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.ImageView restaurantImage = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView restaurantName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView restaurantDescription = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView backName = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView backCategory = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView backCuisine = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView backLevel = null;
        @org.jetbrains.annotations.NotNull()
        private final android.widget.TextView backTags = null;
        
        public RestaurantViewHolder(@org.jetbrains.annotations.NotNull()
        com.jrkg.jrkgbites.databinding.ItemRestaurantListCardBinding binding) {
            super(null);
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.FrameLayout getCardFlipContainer() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getCardFront() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.LinearLayout getCardBack() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.ImageView getRestaurantImage() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getRestaurantName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getRestaurantDescription() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getBackName() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getBackCategory() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getBackCuisine() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getBackLevel() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.widget.TextView getBackTags() {
            return null;
        }
    }
}