package pl.com.sergey.tooplooxsongapp.search.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;
import pl.com.sergey.tooplooxsongapp.dto.SongDto;
import pl.com.sergey.tooplooxsongapp.facade.DataFacade;
import pl.com.sergey.tooplooxsongapp.facade.SourceType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sergey on 02.12.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class SearchPresenterTest {

    SearchPresenter presenter;

    @Mock
    SearchPresenter.MainActivityUI ui;

    @Mock
    DataFacade dataFacade;


    Scheduler scheduler;


    @Before
    public void setUp() {
        scheduler = new TestScheduler();
        presenter = new SearchPresenter(dataFacade, scheduler, scheduler);
        presenter.attacheUI(ui);
        when(dataFacade.getListSongs(any(CharSequence.class))).thenReturn(Flowable.just(getSongDto()));
    }


    @Test
    public void should_set_latest_source() {
        //given
        presenter.setLastSearch("song".subSequence(0, 3));
        //when
        presenter.setSource(SourceType.BOTH);
        //then
        verify(dataFacade, atLeastOnce()).setSource(SourceType.BOTH);
    }

    @Test
    public void should_show_details_activity() {
        //given

        //when
        presenter.starDetailsActivity(getSongDto());
        //then
        verify(ui).showDetails(any(SongDto.class));
    }

    @Test
    public void should_show_dialog_source() {
        //given

        //when
        presenter.clickSource();

        //then
        verify(ui).showSourceDialog();
    }



    private SongDto getSongDto() {
        return new SongDto("song", "name", "1999", "url", "Local");
    }
}
