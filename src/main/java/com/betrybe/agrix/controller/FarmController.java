package com.betrybe.agrix.controller;

import com.betrybe.agrix.dto.FarmDto;
import com.betrybe.agrix.service.FarmService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 * The type Farm controller.
 */
@RestController
@RequestMapping("/farms")
@Tag(name = "Farm Controller", description = "APIs relacionadas às fazendas")
public class FarmController {

  private FarmService farmService;

  /**
   * Instantiates a new Farm controller.
   *
   * @param farmService the farm service
   */
  @Autowired
  public FarmController(FarmService farmService) {
    this.farmService = farmService;
  }

  /**
   * Create farm response entity.
   *
   * @param farmDto the farm dto
   * @return the response entity
   */
  @PostMapping
  @Operation(summary = "Cria uma nova fazenda")
  public ResponseEntity<FarmDto> createFarm(@RequestBody FarmDto farmDto) {
    FarmDto savedFarm = farmService.createFarm(farmDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedFarm);
  }

  /**
   * Get all farms response entity.
   *
   * @return the response entity
   */
  @GetMapping
  @Operation(summary = "Retorna todas as fazendas")
  public ResponseEntity<List<FarmDto>> getAllFarms() {
    List<FarmDto> farms = farmService.getAllFarms();
    return ResponseEntity.ok(farms);
  }

  /**
   * Get farm by id response entity.
   *
   * @param id the id
   * @return the response entity
   */
  @GetMapping("/{id}")
  @Operation(summary = "Retorna uma fazenda pelo ID")
  public ResponseEntity<Object> getFarmById(@PathVariable Long id) {
    Optional<FarmDto> farm = farmService.getFarmById(id);
    return farm.<ResponseEntity<Object>>map(ResponseEntity::ok).orElseGet(
        () -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Fazenda não encontrada!"));
  }
}
