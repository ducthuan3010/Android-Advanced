package edu.eiu.tourist_app;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.lifecycle.LiveData;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.HashMap;
import java.util.List;

import edu.eiu.tourist_app.datamodel.QueryResponse;
import edu.eiu.tourist_app.datamodel.WikipediaPage;
import edu.eiu.tourist_app.datamodel.WikipediaResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlacesViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Test
    public void testGetTouristSites() {
        WikipediaService mockWikipediaService = new MockWikipediaService();

        // create a REAL PlacesViewModel to perform the test on
        PlacesRepository placesRepository = new PlacesRepository(mockWikipediaService);
        PlacesViewModel.PlacesViewModelFactory factory = new PlacesViewModel.PlacesViewModelFactory(placesRepository);
        PlacesViewModel model = factory.create(PlacesViewModel.class);

        // perform the testable action
        LiveData<List<WikipediaPage>> placesLiveData = model.getTouristSites();
        // knowing "Statue" is at position one mock data, assert tha the code gave us that in the LiveData
        Assert.assertEquals("Statue", placesLiveData.getValue().get(0).getTitle());

    }
}
