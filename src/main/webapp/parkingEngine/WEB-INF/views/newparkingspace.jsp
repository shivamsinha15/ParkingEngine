<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="./sharedResources/js/geomath.js"></script>
<script type="text/javascript" src="./sharedResources/js/main.js"></script>
<script type="text/javascript" src="./sharedResources/js/NewParkingSpaceScript.js"></script>


<script type="text/javascript">


</script>

<div class="main">

    <div class="container">

      <div class="row">
        
        <div class="span6">
            
            <div class="widget stacked">
                    
                <div class="widget-header">
                    <i class="icon-road"></i>
                    <h3>New Parking Space</h3>
                </div> <!-- /widget-header -->
                
                <div class="widget-content">

                    <br>
                    
                    <form action="/" id="validation-form" class="form-horizontal" novalidate="novalidate">
                       

                            <label>Address: </label>
                            <input id="address" type="text" style="width: 350px;"/>
                         </br>
                            <label>Start Lat: </label>
                            <input id="slatitude" type="text" style="width: 250px;"/>
                         </br>
                            <label>Start Lng: </label>
                            <input id="slongitude" type="text" style="width: 250px;"/>
                         </br>
                             <label>End Lat: </label>
                             <input id="elatitude" type="text" style="width: 250px;"/>
                         </br>
                            <label>End Lng: </label>
                            <input id="elongitude" type="text" style="width: 250px;"/>
                         </br>
                            <label>Bearing: </label>
                            <input id="bearing" type="text" style="width: 250px;"/>
                         </br>
                            <label>Distance: </label>
                            <input id="distance" type="text" style="width: 250px;"/>
                         </br>
                            <label>CarSpaces: </label>
                            <input id="carspaces" type="text" style="width: 250px;"/>
                        </br>              
                            <label class="title">SnapTo</label>
                        <select id="snap" name="field15">
                            <option value="UPRIGHT">
                                UPRIGHT
                            </option>
                        <option value="UPLEFT">
                            UPLEFT
                        </option>
                        <option value="DOWNRIGHT">
                            DOWNRIGHT
                        </option>
                        <option value="DOWNLEFT">
                            DOWNLEFT
                        </option>
                     </select>

                  

                        </form>
                    
                </div> <!-- /widget-content -->
                    
            </div> <!-- /widget -->                 
            
        </div> <!-- /span6 -->    

        <div class="span6"> 
            <div class="widget stacked">
                    
                <div class="widget-header">
                    <i class="icon-map-marker"></i>
                    <h3>Map</h3>
                </div> <!-- /widget-header -->    
                <div class="widget-content">    
                          <div id="content">
                                 <div id="map_canvas"></div><br/>
                         </div>
                </div> <!-- /widget-content 2nd -->     
            </div> <!-- /widget 2nd -->   
        </div> <!-- /span6 2nd -->  
      </div> <!-- /row -->

      <!-------------------------^END OF FIRST ROW ------>


                <div class="row">
          <div class="span12">
            <div class="widget stacked">
             <div class="widget-header">
                    <i class="icon-inbox"></i>
                    <h3>New Parking Meter</h3>
             </div> <!-- /widget-header -->
            <div class="widget-content">
              <table id="parkingMeterTable" class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Point Lat</th>
                                <th>Point Lng</th>
                                <th class="td-actions"> Save/Delete </th>
                            </tr>
                        </thead>
                        <tbody id="parkingMeterTableBody">
                            <!--
                            <tr>
                                <td>Trident</td>
                                <td>Internet
                                     Explorer 4.0</td>
                                     <td>Trident</td>
                                     <td>Trident</td>
                                     <td>Trident</td>
                                     <td>Trident</td>
                                     <td>Trident</td>

                                <td class="td-actions">
                                    <a href="javascript:;" class="btn btn-small btn-warning">
                                        <i class="btn-icon-only icon-ok"></i>                                       
                                    </a>
                                    
                                    <a href="javascript:;" class="btn btn-small">
                                        <i class="btn-icon-only icon-remove"></i>                                       
                                    </a>
                                </td>
                            </tr> -->
                            </tbody>
                        </table>
                </div> <!-- /widget-content -->  
            </div> <!-- /widget -->                 
         </div> <!-- /span6 -->    
        </div> <!-- /row -->

      <!-------------------------^ END OF Second ROW ------>

    <div class="row">
          <div class="span12">
            <div class="widget stacked">
             <div class="widget-header">
                    <i class="icon-road"></i>
                    <h3>New Parking Space</h3>
             </div> <!-- /widget-header -->
            <div class="widget-content">
              <table id="parkingTable" class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Address</th>
                                <th>Start Lat</th>
                                <th>Start Lng</th>
                                <th>End Lat</th>
                                <th>End Lng</th>
                                <th>Bearing</th>
                                <th>Length</th>
                                <th class="td-actions"> Save/Delete </th>
                            </tr>
                        </thead>
                        <tbody id="parkingTableBody">
                            <!--
                            <tr>
                                <td>Trident</td>
                                <td>Internet
                                     Explorer 4.0</td>
                                     <td>Trident</td>
                                     <td>Trident</td>
                                     <td>Trident</td>
                                     <td>Trident</td>
                                     <td>Trident</td>

                                <td class="td-actions">
                                    <a href="javascript:;" class="btn btn-small btn-warning">
                                        <i class="btn-icon-only icon-ok"></i>                                       
                                    </a>
                                    
                                    <a href="javascript:;" class="btn btn-small">
                                        <i class="btn-icon-only icon-remove"></i>                                       
                                    </a>
                                </td>
                            </tr> -->
                            </tbody>
                        </table>
                </div> <!-- /widget-content -->  
            </div> <!-- /widget -->                 
         </div> <!-- /span6 -->    
        </div> <!-- /row -->

      <!-------------------------^ END OF Third ROW ------>



      <!-- Button trigger modal -->
  <a data-toggle="modal" href="#myModal" class="btn btn-primary btn-lg">Launch demo modal</a>

  <!-- Modal -->
  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
          <h3 class="modal-title">Add Rules To Meters</h3>
        </div>
        <div class="modal-body">
      

      <div class="row">
        
        <div class="span2">
            
            <div class="widget stacked">
                    
                <div class="widget-header">
                    <i class="icon-road"></i>
                    <h3>Saved Meter</h3>
                </div> <!-- /widget-header -->
                
                <div class="widget-content">

                    <br>
                    
                    <form action="/" id="validation-formm" class="form-horizontal" novalidate="novalidate">
                        <label id="idm">Id: </label>
                         </br>
                            <label id="pointlatm">Point Lat: </label>
                         </br>
                            <label id="pointlngm">Point Lng: </label>
                         </br>   
                        </form>
                    
                </div> <!-- /widget-content -->
                    
            </div> <!-- /widget -->                 
            
        </div> <!-- /span6 -->    

        <div class="span7"> 
            <div class="widget stacked">
                    
                <div class="widget-header">
                    <i class="icon-book"></i>
                    <h3>Parking Rules</h3>
                </div> <!-- /widget-header -->    
                <div class="widget-content">    
                           <table id="parkingRuleTablem" class="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>DayFrom</th>
                                <th>DayTo</th>
                                <th>TimeFrom</th>
                                <th>TimeTo</th>
                                <th>Cost</th>
                                <th>MaxTimeLimit</th>
                            <th class="td-actions"> Add </th>
                            </tr>
                        </thead>
                            <tbody id="parkingRuleTableBodym">

                            </tbody>
                        </table>
                </div> <!-- /widget-content 2nd -->     
            </div> <!-- /widget 2nd -->   
        </div> <!-- /span7 2nd -->  
      </div> <!-- /row -->
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary">Save changes</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
  </div><!-- /.modal -->


    </div> <!-- /container -->
</div> <!-- /main -->