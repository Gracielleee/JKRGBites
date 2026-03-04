package com.jrkg.jrkgbites.data.api;

@kotlin.Metadata(mv = {2, 2, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00042\b\b\u0001\u0010\u0007\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\t\u00a8\u0006\n\u00c0\u0006\u0003"}, d2 = {"Lcom/jrkg/jrkgbites/data/api/UserApiService;", "", "getUsers", "", "Lcom/jrkg/jrkgbites/data/api/UserDto;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUserById", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface UserApiService {
    
    @retrofit2.http.GET(value = "users")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUsers(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.jrkg.jrkgbites.data.api.UserDto>> $completion);
    
    @retrofit2.http.GET(value = "users/{id}")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getUserById(@retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.jrkg.jrkgbites.data.api.UserDto> $completion);
}