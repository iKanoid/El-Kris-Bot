package com.ikanoid.android.elkrisbot;

public abstract class Config {
    // copy this keys from your developer dashboard
    public static final String ACCESS_TOKEN = "d236db904adb4f2db36e2ee7a7726079";

    public static final LanguageConfig[] languages = new LanguageConfig[]{
            new LanguageConfig("en", "a11ea1d839e3446d84e402cb97cdadfb"),
            new LanguageConfig("fr", "62161233bc094a75b3acfe16aeeed203"),
            new LanguageConfig("es", "49be4c10b6a543dfb41d49d88731bd49"),
    };

    public static final String[] events = new String[]{
            "name",
    };
}
