package com.betrybe.agrix.dto;

/**
 * The type Crop dto.
 */
public record CropDto(Long id, String name, double plantedArea, Long farmId) {}
