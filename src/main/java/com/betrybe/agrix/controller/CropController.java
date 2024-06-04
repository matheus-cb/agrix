package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropCreationDto;
import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.service.CropService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
@RequestMapping("/farms/{farmId}/crops")
public class CropController {

  private final CropService cropService;

  /**
   * Instantiates a new Crop controller.
   *
   * @param cropService the crop service
   */
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;
  }

  /**
   * Create crop response entity.
   *
   * @param farmId          the farm id
   * @param cropCreationDto the crop creation dto
   * @return the response entity
   */
  @PostMapping
  public ResponseEntity<Object> createCrop(
      @PathVariable Long farmId, @RequestBody CropCreationDto cropCreationDto
  ) {
    Optional<CropDto> cropDtoOptional = cropService.createCrop(farmId, cropCreationDto);
    if (cropDtoOptional.isPresent()) {
      return ResponseEntity.status(HttpStatus.CREATED).body(cropDtoOptional.get());
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!");
    }
  }
}
