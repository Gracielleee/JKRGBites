package com.jrkg.jrkgbites.viewmodel;

/**
 * Resolved start destination for the navigation graph.
 * Used by the Activity to set the graph programmatically and avoid login flash.
 */
@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002\u00a2\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005\u00a8\u0006\u0006"}, d2 = {"Lcom/jrkg/jrkgbites/viewmodel/StartDestination;", "", "<init>", "(Ljava/lang/String;I)V", "LOGIN", "MAIN", "app_debug"})
public enum StartDestination {
    /*public static final*/ LOGIN /* = new LOGIN() */,
    /*public static final*/ MAIN /* = new MAIN() */;
    
    StartDestination() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.jrkg.jrkgbites.viewmodel.StartDestination> getEntries() {
        return null;
    }
}