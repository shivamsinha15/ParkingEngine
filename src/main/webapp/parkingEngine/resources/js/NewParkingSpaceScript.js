  /* http://jdewit.github.io/bootstrap-timepicker/ 

   Two tables used from newparkingspace.jsp: 
           1. parkingTableBody    (main.areAdditionalParkingSpacesRequired, triggered by google listener
           2. parkingMeterTableBody (main.initialize(), triggered by google listener)

    The Main Flow:
    saveParkingMeter(rowIndex)
    [Save static parking Meter, which is retrieved using the rowIndex, content save into the the database "PEMeter/save/pointLat/"+savePointLat+"/pointLng/"+savePointLng and meterId is  returned] 
    -> saveAllParkingSpace(meterID)
    [Retrieves rows from the element table body "parkingTableBody" and calls the function below for the number of static rows]
    -> saveNewParkingSpace(rowIndex,meterId) 
    [Saves static parking spaces, used the row index to retrieve the required dom elements from a specific row, which is then saved and PESpace.Id is returned]
    -> mapSpaceToMeter(spaceId,meterId)
    [Finally this is used to map PESpace to PEMeter, "PESpace/map/spaceId/"+spaceId+"/pointLng/"+meterId;]


   */

    function saveParkingMeter(rowIndex,pointlat,pointlng){
        var savePointLat =  $("#"+ParkENG.pointlat+rowIndex).text();   
        var savePointLng = $("#"+ParkENG.pointlng+rowIndex).text();
        setModalFields(savePointLat,savePointLng);
        var saveMeterString = ("PEMeter/save/pointLat/"+savePointLat+"/pointLng/"+savePointLng+"/");
        $.ajax({url:saveMeterString,success:function(result){
                  saveAllParkingSpace(result);
            }});
    }

    function saveAllParkingSpace(meterId){

        var parkingTableBody = document.getElementById("parkingTableBody");
        for(var i = 0,row; row = parkingTableBody.rows[i]; i++) {
            saveNewParkingSpace(i,meterId);
         }
         createMeterToRulePopUp(meterId);
    }

    function saveNewParkingSpace(rowIndex,meterId){
    
        var saveAddress =  $("#"+ParkENG.address+rowIndex).text();   
        var saveStartLat = $("#"+ParkENG.startlat+rowIndex).text();
        var savestartlng = $("#"+ParkENG.startlng+rowIndex).text();
        var saveEndlat = $("#"+ParkENG.endlat+rowIndex).text();
        var saveEndlng = $("#"+ParkENG.endlng+rowIndex).text();
        var saveBearing = $("#"+ParkENG.bearing+rowIndex).text();
        var saveLenght = $("#"+ParkENG.lenght+rowIndex).text();

        var saveString = "NewParkingSpace" +
                           "/save/?"+ParkENG.address+"="+saveAddress+
                           "&"+ParkENG.startlat+"="+saveStartLat+
                           "&"+ParkENG.startlng+"="+savestartlng+
                           "&"+ParkENG.endlat+"="+saveEndlat+
                           "&"+ParkENG.endlng+"="+saveEndlng+
                           "&"+ParkENG.bearing+"="+saveBearing+
                           "&"+ParkENG.lenght+"="+saveLenght;

          $.ajax({url:saveString,async: false,success:function(result){

                        mapSpaceToMeter(result,meterId);
                        
                }});           
    }

    function mapSpaceToMeter(spaceId,meterId){
      var saveSpaceToMeterString = "PESpace/map/spaceId/"+spaceId+"/meterId/"+meterId;
         $.ajax({url:saveSpaceToMeterString,async: false,success:function(result){

                }});  
    }

    function mapMeterToRule(meterId,ruleId){
      var saveMeterToRuleString = "PEMeter/map/meterId/"+meterId+"/ruleId/"+ruleId;
      $.ajax({url:saveMeterToRuleString,success:function(result){
                
                }}); 
    }

    function createMeterToRulePopUp(meterId){
       $('#idm').text("Id: "+meterId);
       $('#myModal').modal('show');
       $.ajax({url:"PERule/all",success:function(result){
       for (var i = 0; i < result.length; i++) { 
                    addRowToParkingRuleTable(meterId,result[i]);
          }
                }}); 
    }

     function setModalFields(pointlat,pointlng){
       $('#pointlatm').text("Point Lat: "+pointlat);
       $('#pointlngm').text("Point Lng: "+pointlng);
     }


   