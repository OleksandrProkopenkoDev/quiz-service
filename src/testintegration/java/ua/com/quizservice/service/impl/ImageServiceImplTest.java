package ua.com.quizservice.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.com.quizservice.IntegrationTestBase;
import ua.com.quizservice.entity.quiz.Image;
import ua.com.quizservice.exception.ImageNotFoundException;
import ua.com.quizservice.service.ImageService;

/** Test class for image. */
class ImageServiceImplTest extends IntegrationTestBase {
  private final byte[] testData = "Test Image Data".getBytes();
  @Autowired private ImageService imageService;

  @Test
  @Order(1)
  void saveImageAndDeleteById() {
    final Image savedImage = imageService.saveImage(testData);

    Assertions.assertNotNull(imageService.getImageById(savedImage.getId()));
    imageService.deleteImageById(savedImage.getId());
    Assertions.assertThrows(
        ImageNotFoundException.class, () -> imageService.getImageById(savedImage.getId()));
  }

  @Test
  @Order(2)
  void saveImageAndRetrieveById() {

    final Image savedImage = imageService.saveImage(testData);

    Assertions.assertNotNull(savedImage.getId());
    Assertions.assertArrayEquals(testData, savedImage.getData());

    final Image retrievedImage = imageService.getImageById(savedImage.getId());
    Assertions.assertEquals(savedImage.getId(), retrievedImage.getId());
    Assertions.assertArrayEquals(savedImage.getData(), retrievedImage.getData());
  }
}
