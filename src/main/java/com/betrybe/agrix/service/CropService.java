package com.betrybe.agrix.service;

import com.betrybe.agrix.dto.CropCreationDto;
import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FarmRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Crop service.
 */
@Service
public class CropService {

  private final CropRepository cropRepository;
  private final FarmRepository farmRepository;

  /**
   * Instantiates a new Crop service.
   *
   * @param cropRepository the crop repository
   * @param farmRepository the farm repository
   */
  @Autowired
  public CropService(CropRepository cropRepository, FarmRepository farmRepository) {
    this.cropRepository = cropRepository;
    this.farmRepository = farmRepository;
  }

  /**
   * Create crop optional.
   *
   * @param farmId          the farm id
   * @param cropCreationDto the crop creation dto
   * @return the optional
   */
  public Optional<CropDto> createCrop(Long farmId, CropCreationDto cropCreationDto) {
    Optional<Farm> farmOptional = farmRepository.findById(farmId);
    if (farmOptional.isPresent()) {
      Crop crop = new Crop();
      crop.setName(cropCreationDto.name());
      crop.setPlantedArea(cropCreationDto.plantedArea());
      crop.setFarm(farmOptional.get());
      Crop savedCrop = cropRepository.save(crop);
      return Optional.of(
          new CropDto(savedCrop.getId(), savedCrop.getName(), savedCrop.getPlantedArea(), farmId)
      );
    } else {
      return Optional.empty();
    }
  }
}
