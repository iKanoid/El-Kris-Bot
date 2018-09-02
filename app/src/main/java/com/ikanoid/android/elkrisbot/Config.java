package com.ikanoid.android.elkrisbot;

public abstract class Config {
    // copy this keys from your developer dashboard
    public static final String ACCESS_TOKEN = "Put the access token here";

    public static final LanguageConfig[] languages = new LanguageConfig[]{
            new LanguageConfig("en", "Put the English token here"),
            new LanguageConfig("fr", "Put the French token here"),
            new LanguageConfig("es", "Put the Spanish token here"),
    };

    public static final String[] events = new String[]{
            "name",
    };
}
