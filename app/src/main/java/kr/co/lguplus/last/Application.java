package kr.co.lguplus.last;

public final class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "DEFAULT", "1234.ttf");
        FontsOverride.setDefaultFont(this, "MONOSPACE", "1234.ttf");
    }
}