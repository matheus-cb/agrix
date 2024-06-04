package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.CropCreationDto;
import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.service.CropService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Crop controller.
 */
@RestController
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
  @PostMapping("/farms/{farmId}/crops")
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

  /**
   * Gets crops by farm id.
   *
   * @param farmId the farm id
   * @return the crops by farm id
   */
  @GetMapping("/farms/{farmId}/crops")
  public ResponseEntity<Object> getCropsByFarmId(@PathVariable Long farmId) {
    Optional<List<CropDto>> cropsOptional = cropService.getCropsByFarmId(farmId);
    return cropsOptional.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(
        () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!"));
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  @GetMapping("/crops")
  public ResponseEntity<List<CropDto>> getAllCrops() {
    List<CropDto> crops = cropService.getAllCrops();
    return ResponseEntity.ok(crops);
  }
}
