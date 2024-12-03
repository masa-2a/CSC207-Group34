package use_case.map2d;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Map2DUseCaseInteractorTest {
    private Map2DUseCaseInteractor map2DUseCaseInteractor;

    @BeforeEach
    void setUp() {
        map2DUseCaseInteractor = new Map2DUseCaseInteractor();
    }

    @Test
    void mapNoMarker() throws IOException {
        Map2DInputData inputData = new Map2DInputData(600, 400, 0, 0, 1, 0, 0, 0, 0, false, false);
        map2DUseCaseInteractor.execute(inputData);

        File originalFile = new File("src/test/java/use_case/map2d/static_map_original.png");
        BufferedImage originalImage = ImageIO.read(originalFile);

        File generatedFile = new File("src/main/resources/static_map.png");
        BufferedImage generatedImage = ImageIO.read(generatedFile);

        assertEquals(originalImage.getWidth(), generatedImage.getWidth(), "Image widths are not the same");
        assertEquals(originalImage.getHeight(), generatedImage.getHeight(), "Image heights are not the same");

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int originalPixel = originalImage.getRGB(x, y);
                int generatedPixel = generatedImage.getRGB(x, y);
                assertEquals(originalPixel, generatedPixel, String.format("Pixels at (%d, %d) are not the same", x, y));
            }
        }
    }

    @Test
    void testOneMarker() throws IOException {
        Map2DInputData inputData = new Map2DInputData(600, 400, 0, 0, 1, 0, 0, 0, 0, true, false);
        map2DUseCaseInteractor.execute(inputData);

        File originalFile = new File("src/test/java/use_case/map2d/static_map_one_marker.png");
        BufferedImage originalImage = ImageIO.read(originalFile);

        File generatedFile = new File("src/main/resources/static_map.png");
        BufferedImage generatedImage = ImageIO.read(generatedFile);

        assertEquals(originalImage.getWidth(), generatedImage.getWidth(), "Image widths are not the same");
        assertEquals(originalImage.getHeight(), generatedImage.getHeight(), "Image heights are not the same");

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int originalPixel = originalImage.getRGB(x, y);
                int generatedPixel = generatedImage.getRGB(x, y);
                assertEquals(originalPixel, generatedPixel, String.format("Pixels at (%d, %d) are not the same", x, y));
            }
        }
    }

    @Test
    void mapGuessAnswer() throws IOException {
        Map2DInputData inputData = new Map2DInputData(600, 400, 0, 0, 3, 0, 0, 10, 10, true, true);
        map2DUseCaseInteractor.execute(inputData);

        File originalFile = new File("src/test/java/use_case/map2d/static_map_answer.png");
        BufferedImage originalImage = ImageIO.read(originalFile);

        File generatedFile = new File("src/main/resources/static_map.png");
        BufferedImage generatedImage = ImageIO.read(generatedFile);

        assertEquals(originalImage.getWidth(), generatedImage.getWidth(), "Image widths are not the same");
        assertEquals(originalImage.getHeight(), generatedImage.getHeight(), "Image heights are not the same");

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int originalPixel = originalImage.getRGB(x, y);
                int generatedPixel = generatedImage.getRGB(x, y);
                assertEquals(originalPixel, generatedPixel, String.format("Pixels at (%d, %d) are not the same", x, y));
            }
        }
    }
}