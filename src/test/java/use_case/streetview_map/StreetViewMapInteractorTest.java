package use_case.streetview_map;


import entity.map.Map;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class StreetViewMapInteractorTest {

    private StreetViewMapInteractor.PlatformRunner platformRunnerMock;
    private Map mapMock;
    private StreetViewMapInteractor interactor;

    @BeforeEach
    void setUp() {
        platformRunnerMock = mock(StreetViewMapInteractor.PlatformRunner.class);
        mapMock = mock(Map.class);
        interactor = new StreetViewMapInteractor(mapMock, platformRunnerMock);
    }

    @Test
    void testConstructor_Default() {
        StreetViewMapInteractor interactor = new StreetViewMapInteractor();
        assertNotNull(interactor); // Ensure the object is instantiated.
    }

    @Test
    void testConstructor_Custom() {
        StreetViewMapInteractor interactor = new StreetViewMapInteractor(mapMock, platformRunnerMock);
        assertNotNull(interactor); // Ensure the object is instantiated.
    }

    @Test
    public void testExecute() {
        // Prepare test input
        final double testLatitude = 40.7128;
        final double testLongitude = -74.0060;
        StreetViewMapInputData inputData = new StreetViewMapInputData(testLatitude, testLongitude);

        // Execute the method
        interactor.execute(inputData);

        new JFXPanel();

        Platform.runLater(() -> {
            // Capture the Runnable passed to platformRunner.run
            ArgumentCaptor<Runnable> runnableCaptor = ArgumentCaptor.forClass(Runnable.class);
            verify(platformRunnerMock, times(1)).run(runnableCaptor.capture());

            // Manually execute the captured Runnable to simulate the behavior of Platform.runLater
            runnableCaptor.getValue().run();

            // Verify interactions with the mocked map
            verify(mapMock).giveCoords(testLatitude, testLongitude);
        });
    }

    @Test
    void testGuessSubmit() {
        // Setup mock map behavior
        when(mapMock.getUserLatitude()).thenReturn(51.5074);
        when(mapMock.getUserLongitude()).thenReturn(-0.1278);

        StreetViewMapOutputData outputData = interactor.guessSubmit();

        // Verify the output data contains the correct latitude and longitude
        assertEquals(51.5074, outputData.getUserLatitude());
        assertEquals(-0.1278, outputData.getUserLongitude());
    }

    @Test
    void testGuessSubmit_withEdgeValues() {
        // Setup map to return edge coordinates
        when(mapMock.getUserLatitude()).thenReturn(Double.MAX_VALUE);
        when(mapMock.getUserLongitude()).thenReturn(Double.MIN_VALUE);

        StreetViewMapOutputData outputData = interactor.guessSubmit();

        // Verify edge values in output
        assertEquals(Double.MAX_VALUE, outputData.getUserLatitude());
        assertEquals(Double.MIN_VALUE, outputData.getUserLongitude());
    }
}
