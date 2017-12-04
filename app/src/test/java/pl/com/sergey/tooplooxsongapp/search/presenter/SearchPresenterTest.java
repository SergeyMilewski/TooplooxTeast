package pl.com.sergey.tooplooxsongapp.search.presenter;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.schedulers.TestScheduler;
import pl.com.sergey.tooplooxsongapp.dto.SongDto;
import pl.com.sergey.tooplooxsongapp.facade.DataFacade;
import pl.com.sergey.tooplooxsongapp.facade.SourceType;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
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

    @Captor
    ArgumentCaptor<List<SongDto>> argumentCaptorSongs;

    TestScheduler scheduler;


    @Before
    public void setUp() {
        scheduler = new TestScheduler();
        MockitoAnnotations.initMocks(this);
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

    @Test
    public void should_show_search_result() {
        //given
        presenter.setSource(SourceType.BOTH);
        presenter.setSortedBy(0);
        //when
        presenter.searchSong("song".subSequence(0, 3));
        scheduler.triggerActions();

        //then

        verify(ui).setData(argumentCaptorSongs.capture());
        Assert.assertFalse(argumentCaptorSongs.getValue().isEmpty());
        SongDto song = argumentCaptorSongs.getValue().get(0);
        Assert.assertEquals("song", song.getSong());
        Assert.assertEquals("name", song.getNameSinger());
        Assert.assertEquals("1999", song.getReleaseDate());
        Assert.assertEquals("url", song.getUrlImg());
        Assert.assertEquals("Local", song.getSource());

    }


    @Test
    public void should_filtered_result_of_search() {
        //given
        presenter.setSource(SourceType.BOTH);
        presenter.setSortedBy(1);
        //when
        presenter.searchSong("song".subSequence(0, 3));
        scheduler.triggerActions();
        //then
        verify(ui).setData(argumentCaptorSongs.capture());
        Assert.assertTrue(argumentCaptorSongs.getValue().isEmpty());
    }

    private SongDto getSongDto() {
        return new SongDto("song", "name", "1999", "url", "Local");
    }
}
