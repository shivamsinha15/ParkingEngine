package com.parkingengine.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingengine.domain.entities.PEMeter;
import com.parkingengine.service.PEMeterService;

@Controller
public class PEMeterController {

  @Inject
  PEMeterService peMeterServiceImpl;

  @RequestMapping("/PEMeter/all")
  public @ResponseBody
  List<PEMeter> getAll() {
    return peMeterServiceImpl.getAllPEMeter();
  }

  @RequestMapping("/PEMeter/save/pointLat/{pointLat}/pointLng/{pointLng}")
  public @ResponseBody
  long mapPESpaceToPERules(@PathVariable String pointLat, @PathVariable String pointLng) {

    BigDecimal pointLatBig = new BigDecimal(pointLat);
    pointLatBig.setScale(15);

    BigDecimal pointLngBig = new BigDecimal(pointLng);
    pointLngBig.setScale(15);

    PEMeter peMeter = new PEMeter();
    peMeter.setPointLat(pointLatBig);
    peMeter.setPointLng(pointLngBig);

    return peMeterServiceImpl.save(peMeter);
  }

  @RequestMapping("/PEMeter/map/meterId/{meterId}/ruleId/{ruleId}")
  public @ResponseBody
  long mapPEMeterToPERules(@PathVariable long meterId, @PathVariable long ruleId) {
    return peMeterServiceImpl.mapPEMeterToPERules(meterId, ruleId);
  }

}
