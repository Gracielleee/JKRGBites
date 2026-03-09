package com.jrkg.jrkgbites.services;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\u0018\u0000 \u00042\u00020\u0001:\u0002\u0004\u0005B\u0007\u00a2\u0006\u0004\b\u0002\u0010\u0003\u00a8\u0006\u0006"}, d2 = {"Lcom/jrkg/jrkgbites/services/FilterService;", "", "<init>", "()V", "Companion", "FilterGroup", "app_debug"})
public final class FilterService {
    @org.jetbrains.annotations.NotNull()
    public static final com.jrkg.jrkgbites.services.FilterService.Companion Companion = null;
    
    public FilterService() {
        super();
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005J\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005J\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005J\u001a\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005J\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005J$\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00052\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u00a8\u0006\u0010"}, d2 = {"Lcom/jrkg/jrkgbites/services/FilterService$Companion;", "", "<init>", "()V", "generateCategoryFilter", "", "", "restaurantList", "Lcom/jrkg/jrkgbites/model/Restaurant;", "generateCuisineFilter", "generateLevelFilter", "generateLocationFilter", "generateTagsFilter", "generateFilter", "filterName", "Lcom/jrkg/jrkgbites/services/FilterService$FilterGroup;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> generateCategoryFilter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.jrkg.jrkgbites.model.Restaurant> restaurantList) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> generateCuisineFilter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.jrkg.jrkgbites.model.Restaurant> restaurantList) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> generateLevelFilter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.jrkg.jrkgbites.model.Restaurant> restaurantList) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> generateLocationFilter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.jrkg.jrkgbites.model.Restaurant> restaurantList) {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<java.lang.String> generateTagsFilter(@org.jetbrains.annotations.NotNull()
        java.util.List<com.jrkg.jrkgbites.model.Restaurant> restaurantList) {
            return null;
        }
        
        private final java.util.List<java.lang.String> generateFilter(java.util.List<com.jrkg.jrkgbites.model.Restaurant> restaurantList, com.jrkg.jrkgbites.services.FilterService.FilterGroup filterName) {
            return null;
        }
    }
    
    @kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t\u00a8\u0006\n"}, d2 = {"Lcom/jrkg/jrkgbites/services/FilterService$FilterGroup;", "", "<init>", "(Ljava/lang/String;I)V", "Category", "Cuisine", "Level", "Location", "Tags", "Added_by", "app_debug"})
    public static enum FilterGroup {
        /*public static final*/ Category /* = new Category() */,
        /*public static final*/ Cuisine /* = new Cuisine() */,
        /*public static final*/ Level /* = new Level() */,
        /*public static final*/ Location /* = new Location() */,
        /*public static final*/ Tags /* = new Tags() */,
        /*public static final*/ Added_by /* = new Added_by() */;
        
        FilterGroup() {
        }
        
        @org.jetbrains.annotations.NotNull()
        public static kotlin.enums.EnumEntries<com.jrkg.jrkgbites.services.FilterService.FilterGroup> getEntries() {
            return null;
        }
    }
}