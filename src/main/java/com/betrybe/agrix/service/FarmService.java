package com.betrybe.agrix.service;

import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.entity.Farm;
import com.betrybe.agrix.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Farm service.
 */
@Service
public class FarmService {

  private FarmRepository farmRepository;

  /**
   * Instantiates a new Farm service.
   *
   * @param farmRepository the farm repository
   */
  @Autowired
  public FarmService(FarmRepository farmRepository) {
    this.farmRepository = farmRepository;
  }

  /**
   * Create farm farm dto.
   *
   * @param farmDto the farm dto
   * @return the farm dto
   */
  public FarmDto createFarm(FarmDto farmDto) {
    Farm farm = new Farm();
    farm.setName(farmDto.name());
    farm.setSize(farmDto.size());
    Farm savedFarm = farmRepository.save(farm);
    return new FarmDto(savedFarm.getId(), savedFarm.getName(), savedFarm.getSize());
  }
}
