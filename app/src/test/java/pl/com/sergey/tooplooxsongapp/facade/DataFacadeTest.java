package pl.com.sergey.tooplooxsongapp.facade;

import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import pl.com.sergey.tooplooxsongapp.api.ItunsApi;
import pl.com.sergey.tooplooxsongapp.api.LocalApi;
import pl.com.sergey.tooplooxsongapp.pojo.LocalSong;
import pl.com.sergey.tooplooxsongapp.pojo.ResultResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sergey on 02.12.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class DataFacadeTest {

    @Mock
    ItunsApi itunsApi;
    @Mock
    LocalApi localApi;
    @Mock
    Context context;

    DataFacade dataFacade;


    @Before
    public void setUp() {
        dataFacade = new DataFacadeImpl(itunsApi, localApi, context);
        when(localApi.getLocalSongs()).thenReturn(Flowable.just(new LocalSong.Builder().build()));
        when(itunsApi.searchSongs(anyString(), anyString(), anyString())).thenReturn(Observable.just(new ResultResponse()));
    }

    @Test
    public void should_search_locally() {
        //given
        dataFacade.setSource(SourceType.LOCAL);
        //when
        dataFacade.getListSongs("song".subSequence(0, 3));
        //then
        verify(localApi, atLeastOnce()).getLocalSongs();
    }

    @Test
    public void should_search_remotely() {
        //given
        dataFacade.setSource(SourceType.REMOTE);
        //when
        dataFacade.getListSongs("song".subSequence(0, 3));

        //then
        verify(itunsApi, atLeastOnce()).searchSongs(anyString(), anyString(), anyString());
    }

    @Test
    public void should_search_both() {
        //given
        dataFacade.setSource(SourceType.BOTH);
        //when
        dataFacade.getListSongs("song".subSequence(0, 3));

        //then
        verify(itunsApi, atLeastOnce()).searchSongs(anyString(), anyString(), anyString());
        verify(localApi, atLeastOnce()).getLocalSongs();
    }


}
