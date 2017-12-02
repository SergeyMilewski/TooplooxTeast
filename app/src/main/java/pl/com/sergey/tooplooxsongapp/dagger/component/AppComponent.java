package pl.com.sergey.tooplooxsongapp.dagger.component;



import javax.inject.Singleton;

import dagger.Component;
import pl.com.sergey.tooplooxsongapp.dagger.modules.AppModule;
import pl.com.sergey.tooplooxsongapp.dagger.modules.NetworkModule;
import pl.com.sergey.tooplooxsongapp.search.SearchActivity;

/**
 * Created by sergey on 27.11.17.
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    void inject(SearchActivity searchActivity);
}
