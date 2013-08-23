package com.parkingengine.domain.entities;

import java.math.BigDecimal;
import java.util.Collection;

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

import org.hibernate.annotations.Type;
import org.joda.time.LocalTime;

@Entity
@Table(name = PESpace.TABLE_NAME)
public class PESpace {

  public static final String TABLE_NAME = "pe_space";

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "pes_id")
  private Long id;

  @Column(name = "pes_start_lat", scale = 14, precision = 19)
  @Type(type = "org.hibernate.type.BigDecimalType")
  private BigDecimal startLat;

  @Column(name = "pes_start_lng", scale = 14, precision = 19)
  private BigDecimal startLng;

  @Column(name = "pes_end_lat", scale = 14, precision = 19)
  private BigDecimal endLat;

  @Column(name = "pes_end_lng", scale = 14, precision = 19)
  private BigDecimal endLng;

  @Column(name = "pes_bearing", scale = 14, precision = 19)
  private BigDecimal bearing;

  @Column(name = "pes_address")
  private String address;

  @Column(name = "pes_occupied")
  private boolean occupied;

  @Column(name = "pes_occupied_time")
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
  private LocalTime occupiedTime;

  // I believe this automatically adds the joining table
  @ManyToMany(targetEntity = com.parkingengine.domain.entities.PERule.class, fetch = FetchType.EAGER)
  @JoinTable(name = "pe_space_rule", joinColumns = @JoinColumn(name = "pes_id"), inverseJoinColumns = @JoinColumn(name = "per_id"))
  private Collection<PERule> parkingEngineRules;

  public Long getId() {
    return id;
  }

  public Collection<PERule> getParkingEngineRules() {
    return parkingEngineRules;
  }

  public void setParkingEngineRules(Collection<PERule> parkingEngineRules) {
    this.parkingEngineRules = parkingEngineRules;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public BigDecimal getStartLat() {
    return startLat;
  }

  public void setStartLat(BigDecimal startLat) {
    this.startLat = startLat;
  }

  public BigDecimal getStartLng() {
    return startLng;
  }

  public void setStartLng(BigDecimal startLng) {
    this.startLng = startLng;
  }

  public BigDecimal getEndLat() {
    return endLat;
  }

  public void setEndLat(BigDecimal endLat) {
    this.endLat = endLat;
  }

  public BigDecimal getEndLng() {
    return endLng;
  }

  public void setEndLng(BigDecimal endLng) {
    this.endLng = endLng;
  }

  public BigDecimal getBearing() {
    return bearing;
  }

  public void setBearing(BigDecimal bearing) {
    this.bearing = bearing;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public boolean isOccupied() {
    return occupied;
  }

  public void setOccupied(boolean occupied) {
    this.occupied = occupied;
  }

  public LocalTime getOccupiedTime() {
    return occupiedTime;
  }

  public void setOccupiedTime(LocalTime occupiedTime) {
    this.occupiedTime = occupiedTime;
  }



}
