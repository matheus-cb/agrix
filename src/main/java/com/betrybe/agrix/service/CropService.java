package com.betrybe.agrix.service;

import com.betrybe.agrix.dto.CropCreationDto;
import com.betrybe.agrix.dto.CropDto;
import com.betrybe.agrix.entity.Crop;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.CropRepository;
import com.betrybe.agrix.repository.FarmRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

  /**
   * Gets crops by farm id.
   *
   * @param farmId the farm id
   * @return the crops by farm id
   */
  public Optional<List<CropDto>> getCropsByFarmId(Long farmId) {
    Optional<Farm> farmOptional = farmRepository.findById(farmId);
    if (farmOptional.isPresent()) {
      List<CropDto> crops = cropRepository.findAllByFarmId(farmId)
          .stream()
          .map(crop -> new CropDto(crop.getId(), crop.getName(), crop.getPlantedArea(), farmId))
          .collect(Collectors.toList());
      return Optional.of(crops);
    } else {
      return Optional.empty();
    }
  }

  /**
   * Gets all crops.
   *
   * @return the all crops
   */
  public List<CropDto> getAllCrops() {
    return cropRepository.findAll()
        .stream()
        .map(crop -> new CropDto(
            crop.getId(), crop.getName(), crop.getPlantedArea(), crop.getFarm().getId()
        ))
        .collect(Collectors.toList());
  }
}
