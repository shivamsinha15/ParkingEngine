package com.parkingengine.domain.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Type;

@Entity
@Table(name = PEMeter.TABLE_NAME)
public class PEMeter {

  public static final String TABLE_NAME = "pe_meter";

  @Id
  @Column(name = "pem_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "pes_point_lat", scale = 14, precision = 19)
  @Type(type = "org.hibernate.type.BigDecimalType")
  private BigDecimal pointLat;

  @Column(name = "pem_point_lng", scale = 14, precision = 19)
  @Type(type = "org.hibernate.type.BigDecimalType")
  private BigDecimal pointLng;

  @ManyToMany(targetEntity = com.parkingengine.domain.entities.PERule.class, fetch = FetchType.LAZY)
  @Cascade({CascadeType.ALL})
  @JoinTable(name = "pe_meter_rule", joinColumns = @JoinColumn(name = "pem_id"), inverseJoinColumns = @JoinColumn(name = "per_id"))
  private List<PERule> parkingRules;


  @ManyToMany(targetEntity = com.parkingengine.domain.entities.PESpace.class, fetch = FetchType.LAZY)
  @Cascade({CascadeType.ALL})
  @JoinTable(name = "pe_space_meter", joinColumns = @JoinColumn(name = "pem_id"), inverseJoinColumns = @JoinColumn(name = "pes_id"))
  private List<PESpace> parkingSpaces;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getPointLat() {
    return pointLat;
  }

  public void setPointLat(BigDecimal pointLat) {
    this.pointLat = pointLat;
  }

  public BigDecimal getPointLng() {
    return pointLng;
  }

  public void setPointLng(BigDecimal pointLng) {
    this.pointLng = pointLng;
  }

  public List<PERule> getParkingRules() {
    return parkingRules;
  }

  public void setParkingRules(List<PERule> parkingRules) {
    this.parkingRules = parkingRules;
  }

  public List<PESpace> getParkingSpaces() {
    return parkingSpaces;
  }

  public void setParkingSpaces(List<PESpace> parkingSpaces) {
    this.parkingSpaces = parkingSpaces;
  }

  public void addParkingSpaces(List<PESpace> parkingSpaces) {
    this.parkingSpaces.addAll(parkingSpaces);
  }
  
  public void addParkingRules(List<PERule> parkingRules) {
    this.parkingRules.addAll(parkingRules);
  }

}
