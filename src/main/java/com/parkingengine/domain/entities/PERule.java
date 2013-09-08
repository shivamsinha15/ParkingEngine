package com.parkingengine.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.hibernate.annotations.Type;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.parkingengine.json.JodaLocalTimeSerializer;
import com.parkingengine.json.PERuleSerializer;

@Entity
@Table(name = PERule.TABLE_NAME)
public class PERule {

  public static final String TABLE_NAME = "pe_rule";

  @Id
  @Column(name = "per_id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(name = "per_from_day")
  @Enumerated(EnumType.STRING)
  private DaysOfTheWeek fromDay;

  @Column(name = "per_to_day")
  @Enumerated(EnumType.STRING)
  private DaysOfTheWeek toDay;

  @Column(name = "per_cost")
  private double cost;

  @Column(nullable = false, name = "per_from_time")
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
  @JsonSerialize(using = JodaLocalTimeSerializer.class)
  private LocalTime fromTime;

  @Column(name = "per_to_time")
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
  @JsonSerialize(using = JodaLocalTimeSerializer.class)
  private LocalTime toTime;

  /*
   * @Column(name = "per_time_limit")
   * 
   * @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentPeriodAsString") private Period
   * timeLimit;
   */

  @Column(name = "per_time_limit")
  @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalTime")
  @JsonSerialize(using = JodaLocalTimeSerializer.class)
  private LocalTime timeLimit;

  @Transient
  public static DateTimeFormatter hoursMinFormatter = DateTimeFormat.forPattern("HH:mm");

  public enum DaysOfTheWeek {
    Monday("Monday"), Tuesday("Tuesday"), Wednesday("Wednesday"), Thursday("Thursday"), Friday(
        "Friday"), Saturday("Saturday"), Sunday("Sunday");

    private String description;

    private DaysOfTheWeek(final String description) {
      this.description = description;
    }

    public String getString() {
      return description;
    }
  }

  public PERule() {

  }

  public PERule(PERule peRule) {
    this.id = peRule.id;
    this.cost = peRule.cost;
    this.fromDay = peRule.fromDay;
    this.fromTime = peRule.fromTime;
    this.timeLimit = peRule.timeLimit;
    this.toDay = peRule.toDay;
    this.toTime = peRule.toTime;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public DaysOfTheWeek getFromDay() {
    return fromDay;
  }

  public void setFromDay(DaysOfTheWeek fromDay) {
    this.fromDay = fromDay;
  }

  public DaysOfTheWeek getToDay() {
    return toDay;
  }

  public void setToDay(DaysOfTheWeek toDay) {
    this.toDay = toDay;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public LocalTime getFromTime() {
    return fromTime;
  }

  public void setFromTime(LocalTime fromTime) {
    this.fromTime = fromTime;
  }

  public LocalTime getToTime() {
    return toTime;
  }

  public void setToTime(LocalTime toTime) {
    this.toTime = toTime;
  }

  public LocalTime getTimeLimit() {
    return timeLimit;
  }

  public void setTimeLimit(LocalTime timeLimit) {
    this.timeLimit = timeLimit;
  }

}
