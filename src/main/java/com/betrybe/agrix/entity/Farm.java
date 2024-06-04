package com.betrybe.agrix.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * The type Farm.
 */
@Entity
public class Farm {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private double size;

  /**
   * Instantiates a new Farm.
   */
  public Farm() {}

  /**
   * Gets id.
   *
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * Sets id.
   *
   * @param id the id
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * Gets name.
   *
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * Sets name.
   *
   * @param name the name
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets size.
   *
   * @return the size
   */
  public double getSize() {
    return size;
  }

  /**
   * Sets size.
   *
   * @param size the size
   */
  public void setSize(double size) {
    this.size = size;
  }
}
