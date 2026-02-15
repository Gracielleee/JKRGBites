package com.jrkg.jrkgbites.adapter;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u00132\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0002\u0012\u0013B/\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0004\b\b\u0010\tJ\u0018\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u000eH\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/jrkg/jrkgbites/adapter/RestaurantRatingAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/jrkg/jrkgbites/model/RestaurantRating;", "Lcom/jrkg/jrkgbites/adapter/RestaurantRatingAdapter$RatingViewHolder;", "onRatingUpdate", "Lkotlin/Function1;", "", "onRatingDelete", "<init>", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "", "onBindViewHolder", "holder", "position", "RatingViewHolder", "Companion", "app_debug"})
public final class RestaurantRatingAdapter extends androidx.recyclerview.widget.ListAdapter<com.jrkg.jrkgbites.model.RestaurantRating, com.jrkg.jrkgbites.adapter.RestaurantRatingAdapter.RatingViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.jrkg.jrkgbites.model.RestaurantRating, kotlin.Unit> onRatingUpdate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.jrkg.jrkgbites.model.RestaurantRating, kotlin.Unit> onRatingDelete = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.jrkg.jrkgbites.model.RestaurantRating> DiffCallback = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.adapter.RestaurantRatingAdapter.Companion Companion = null;
    
    public RestaurantRatingAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.jrkg.jrkgbites.model.RestaurantRating, kotlin.Unit> onRatingUpdate, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.jrkg.jrkgbites.model.RestaurantRating, kotlin.Unit> onRatingDelete) {
        super(null);
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.jrkg.jrkgbites.adapter.RestaurantRatingAdapter.RatingViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.jrkg.jrkgbites.adapter.RestaurantRatingAdapter.RatingViewHolder holder, int position) {
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003R\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0004\n\u0002\u0010\u0006\u00a8\u0006\u0007"}, d2 = {"Lcom/jrkg/jrkgbites/adapter/RestaurantRatingAdapter$Companion;", "", "<init>", "()V", "DiffCallback", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\n"}, d2 = {"Lcom/jrkg/jrkgbites/adapter/RestaurantRatingAdapter$RatingViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "binding", "Lcom/jrkg/jrkgbites/databinding/ItemRestaurantRatingBinding;", "<init>", "(Lcom/jrkg/jrkgbites/databinding/ItemRestaurantRatingBinding;)V", "bind", "", "rating", "Lcom/jrkg/jrkgbites/model/RestaurantRating;", "app_debug"})
    public static final class RatingViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.jrkg.jrkgbites.databinding.ItemRestaurantRatingBinding binding = null;
        
        public RatingViewHolder(@org.jetbrains.annotations.NotNull()
        com.jrkg.jrkgbites.databinding.ItemRestaurantRatingBinding binding) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.jrkg.jrkgbites.model.RestaurantRating rating) {
        }
    }
}