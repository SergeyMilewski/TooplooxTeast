package pl.com.sergey.tooplooxsongapp;

/**
 * Created by sergey on 27.11.17.
 */

public abstract class BasePresenter<T extends UI> {

    protected T ui;

    public void attacheUI(T ui) {
        this.ui = ui;
    }

    public void detachUI() {
        ui = null;
    }

}
