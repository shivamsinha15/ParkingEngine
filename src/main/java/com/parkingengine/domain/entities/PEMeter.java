package com.parkingengine.domain.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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

  @ManyToMany(targetEntity = com.parkingengine.domain.entities.PERule.class)
  @Cascade({CascadeType.ALL})
  @JoinTable(name = "pe_meter_rule", joinColumns = @JoinColumn(name = "pem_id"), inverseJoinColumns = @JoinColumn(name = "per_id"))
  @LazyCollection(LazyCollectionOption.FALSE)
  @JsonIgnore
  private List<PERule> parkingRules;

  @ManyToMany(targetEntity = com.parkingengine.domain.entities.PESpace.class)
  @Cascade({CascadeType.ALL})
  @JoinTable(name = "pe_space_meter", joinColumns = @JoinColumn(name = "pem_id"), inverseJoinColumns = @JoinColumn(name = "pes_id"))
  @LazyCollection(LazyCollectionOption.FALSE)
  @JsonIgnore
  private List<PESpace> parkingSpaces;

  @Transient
  private List<Long> parkingSpaceIds;

  @Transient
  private List<Long> parkingRuleIds;

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

  public List<Long> getParkingSpaceIds() {
    return parkingSpaceIds;
  }

  public void setParkingSpaceIds(List<Long> parkingSpaceIds) {
    this.parkingSpaceIds = parkingSpaceIds;
  }

  public List<Long> getParkingRuleIds() {
    return parkingRuleIds;
  }

  public void setParkingRuleIds(List<Long> parkingRuleIds) {
    this.parkingRuleIds = parkingRuleIds;
  }



}
