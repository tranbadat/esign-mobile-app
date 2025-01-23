package vn.com.dattb.config;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * OkHttpClientConfig
 * <p>
 * Author: Tran Ba Dat - <a href="https://esign.dattb.com">Live demo</a>
 * Created: 1/23/2025
 * Version: 1.0.0
 * <p>
 * Description: OkHttpClientConfig class for OkHttpClient configuration
 */
public class OkHttpClientConfig {
    public static OkHttpClient getOkHttpClient() {
        // Create a logging interceptor
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Build the OkHttpClient
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .build();
    }
}
