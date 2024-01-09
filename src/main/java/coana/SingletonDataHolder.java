package coana;

import java.util.List;

public class SingletonDataHolder {
    private static SingletonDataHolder instance = null;
    private List<String> appClasses;
    private SingletonDataHolder() {
    }
    public static SingletonDataHolder getInstance() {
        if (instance == null) {
            instance = new SingletonDataHolder();
        }
        return instance;
    }

    public void setAppClasses(List<String> appClasses) {
        this.appClasses = appClasses;
    }

    public List<String> getAppClasses() {
        return appClasses;
    }
}
