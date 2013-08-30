package com.parkingengine.controllers;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.parkingengine.domain.entities.PESpace;
import com.parkingengine.service.PEMeterService;
import com.parkingengine.service.PESpaceService;

@Controller
public class PESpaceController {

  @Inject
  PESpaceService peSpaceServiceImpl;

  @Inject
  PEMeterService peMeterServiceImpl;

  private static final String ADDRESS = "address";
  private static final String STARTLAT = "startlat";
  private static final String STARTLNG = "startlng";
  private static final String ENDLAT = "endlat";
  private static final String ENDLNG = "endlng";
  private static final String BEARING = "bearing";

  // private static final String LENGTH = "length";

  @RequestMapping("/NewParkingSpace/save")
  public @ResponseBody
  long withParamGroup(HttpServletRequest request) {
    PESpace peSpace = new PESpace();
    peSpace.setAddress(request.getParameter(ADDRESS));
    peSpace.setStartLat(BigDecimal.valueOf(Double.parseDouble(request.getParameter(STARTLAT))));

    peSpace.setStartLng(new BigDecimal(request.getParameter(STARTLNG)));
    peSpace.setEndLat(new BigDecimal(request.getParameter(ENDLAT)));
    peSpace.setEndLng(new BigDecimal(request.getParameter(ENDLNG)));
    peSpace.setBearing(new BigDecimal(request.getParameter(BEARING)));

    // peSpace.setLength();
    return peSpaceServiceImpl.save(peSpace);
  }

  @RequestMapping("/PESpace/all")
  public @ResponseBody
  List<PESpace> getAllPESpaces() {
    return peSpaceServiceImpl.getAllPESpaces();
  }

  /*-
   @RequestMapping("/PESpace/map/spaceId/{peSpaceId}/ruleId/{peRuleId}")
   public @ResponseBody
   long mapPESpaceToPERules(@PathVariable long peSpaceId, @PathVariable long peRuleId) {
   return peSpaceServiceImpl.mapPESpaceToRule(peSpaceId, peRuleId);
   }
   */
  @RequestMapping("/PESpace/map/spaceId/{peSpaceId}/meterId/{meterId}")
  public @ResponseBody
  long mapPESpaceToPEMeters(@PathVariable long peSpaceId, @PathVariable long meterId) {
    return peMeterServiceImpl.mapPESpaceToMeter(peSpaceId, meterId);
  }


  /*-
    NewParkingSpace.html
    What happens when you click the TICK Icon, basic example of prompting a Text Alert:
    var saveDeleteAction = '<a href=\'javascript:alert(\"test\")\';class="btn btn-small btn-warning"><i class="btn-icon-only icon-ok"></i></a> <a href="javascript:;" class="btn btn-small"><i class="btn-icon-only icon-remove"></i></a>'; 
    <table class="table table-striped table-bordered" id="parkingTable">
        <thead>
            <tr>
                <th>Address</th>
                <th>Start Lat</th>
                <th>Start Lng</th>
                <th>End Lat</th>
                <th>End Lng</th>
                <th>Bearing</th>
                <th>Length</th>
                <th class="td-actions">Save/Delete</th>
            </tr>
        </thead>

        <tbody>
            <tr>
                <td id="address2">18-20 Smith Street, Parramatta NSW 2150,Australia</td>
                <td id="startlat2">-33.81466372278844</td>
                <td id="startlng2">151.0061970496952</td>
                <td id="endlat2">-33.81469839070781</td>
                <td id="endlng2">-33.81469839070781</td>
                <td id="bearing2">195.47988738645842</td>
                <td id="lenght2">0.004</td>
                <td>
                    <a class="btn btn-small btn-warning" href="javascript:saveNewParkingSpace(2);">
                        <em class="btn-icon-only icon-ok"></em>
                    </a> 
                    <a class="btn btn-small" href="javascript:;">
                        <em class="btn-icon-only icon-remove"></em>
                    </a>
                </td>
            </tr>  
         </tbody>
     </table> 
     
      <script type="text/javascript">
   <!--http://jdewit.github.io/bootstrap-timepicker/ -->

        function saveNewParkingSpace(rowIndex){

        var saveString = "NewParkingSpace" +
                           "/save/?"+ParkENG.address+"="+$("#"+ParkENG.address+rowIndex).text()+
                           "&"+ParkENG.startlat+"="+$("#"+ParkENG.startlat+rowIndex).text()+
                           "&"+ParkENG.startlng+"="+$("#"+ParkENG.startlng+rowIndex).text()+
                           "&"+ParkENG.endlat+"="+$("#"+ParkENG.endlat+rowIndex).text()+
                           "&"+ParkENG.endlng+"="+$("#"+ParkENG.endlng+rowIndex).text()+
                           "&"+ParkENG.bearing+"="+$("#"+ParkENG.bearing+rowIndex).text()+
                           "&"+ParkENG.lenght+"="+$("#"+ParkENG.lenght+rowIndex).text();

        $.ajax({url:saveString,success:function(result){
                        $("#address").html(result);
                }});                   
                      
        }
    </script>
    
    main.js:
    function saveDeleteAction(rowIndex) {
      return '<a href="javascript:saveNewParkingSpace(' + rowIndex + ');" class="btn btn-small btn-warning"><i class="btn-icon-only icon-ok"></i></a>      <a href="javascript:;" class="btn btn-small"><i class="btn-icon-only icon-remove"></i></a>';
  }
   */



}
