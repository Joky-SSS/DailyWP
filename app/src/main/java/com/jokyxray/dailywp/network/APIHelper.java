package com.jokyxray.dailywp.network;


public class APIHelper {
    private static BingApi api = RetrofitProvider.getInstance().create(BingApi.class);

    public static BingApi getApi() {
        return api;
    }
}
