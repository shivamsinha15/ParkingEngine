package com.parkingengine.domain.entities;

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

	@Column(name = "pes_start_lat")
	private Long startLat;

	@Column(name = "pes_start_lng")
	private Long startLng;

	@Column(name = "pes_end_lat")
	private Long endLat;

	@Column(name = "pes_end_lng")
	private Long endLng;

	@Column(name = "pes_bearing")
	private Long bearing;

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

	public Long getStartLat() {
		return startLat;
	}

	public void setStartLat(Long startLat) {
		this.startLat = startLat;
	}

	public Long getStartLng() {
		return startLng;
	}

	public void setStartLng(Long startLng) {
		this.startLng = startLng;
	}

	public Long getEndLat() {
		return endLat;
	}

	public void setEndLat(Long endLat) {
		this.endLat = endLat;
	}

	public Long getEndLng() {
		return endLng;
	}

	public void setEndLng(Long endLng) {
		this.endLng = endLng;
	}

	public Long getBearing() {
		return bearing;
	}

	public void setBearing(Long bearing) {
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
