    var geocoder;
    var map;
    var startMarker;
    var endMarker;
    var start;
    var end;
    var directionsDisplay;
    var directionsService = new google.maps.DirectionsService();
    var parraCoordinates = new google.maps.LatLng(-33.815154, 151.006109);
    var markerArray = [];
    var tempParkSpaceArray = [];
    var compassDegrees;


    //CONSTANTS;
    var sMarkLat = '#slatitude';
    var sMarkLng = '#slongitude';
    var eMarkLat = '#elatitude';
    var eMarkLng = '#elongitude';
    var roadWidth = 7.4338356804708745;
    var parkingSpaceLength = 0.006;

    /* How does this script work ?
    listener -> onMarkerChange -> calcRoute - > processResult 


    So processResult() creates a bunch of new parkingSpace objects and adds it to the array. After which the
    method displayParkingSpaces is called and displays the parking lines.

    new parkingSpace(mathLibLatLngParkStart,parkSpaceAngle);
    tempParkSpaceArray.push(tmpParkSpace);
    function displayParkingSpaces()



     //Note Lon refers to MatLib and Lng to google maps


    */


    function isStartEndNotEmpty(){
     var slat = $("#slatitude").val();
     var slng = $("#slongitude").val();
     var elat = $("#elatitude").val();
     var elng = $("#elongitude").val();
     if(slat && slng && elat && elng){
      return true;
    }
    return false;
  }

  function onMarkerChange(){
    if(isStartEndNotEmpty()){
      updateDistance();
      updateBearing();
      calcRoute();
    }
  }

  function areAdditionalParkingSpacesRequired(){
    var spaceRequired = $("#carspaces").val()
    var currentParkingSpace = tempParkSpaceArray[0];
    var startMatLibLatLon = currentParkingSpace.startLatLon;
    var startLoc = startMatLibLatLon.toGoogleLatLng();
    var endMatLibLatLon = startMatLibLatLon.destPoint(currentParkingSpace.bearing,parkingSpaceLength);
    var endLoc = endMatLibLatLon.toGoogleLatLng();
    addRowToTable(currentParkingSpace);

      for (var i=1; i<spaceRequired;i++) {

          var startOfNextParkSpaceMatLibLatLon = endMatLibLatLon;
          startLoc = startOfNextParkSpaceMatLibLatLon.toGoogleLatLng();
          endMatLibLatLon = startOfNextParkSpaceMatLibLatLon.destPoint(currentParkingSpace.bearing,parkingSpaceLength);
          endLoc = endMatLibLatLon.toGoogleLatLng();
          var parkingAngle = currentParkingSpace.bearing;

          var tmpParkSpace = new parkingSpace(startOfNextParkSpaceMatLibLatLon,parkingAngle);
          tempParkSpaceArray.push(tmpParkSpace);


          addRowToTable(tmpParkSpace);
          
        }


  }


  function afterOptimizeChange(){
    if(isStartEndNotEmpty()){
      updateDistance();
      updateBearing();
    }

  }

  function distanceFieldNotEmpty(){
    var distance = ("#distance").val();
    if(distance){
      return true;
    }
    return false;
  }

  function updateDistance(){
   start = new google.maps.LatLng($("#slatitude").val(), $("#slongitude").val());
   end = new google.maps.LatLng($("#elatitude").val(), $("#elongitude").val());
   var distance = getDistance('Total Distance',start,end);
   $("#distance").val(distance);
 }

 function updateBearing(){
  if(distanceFieldNotEmpty){
    var initBearing = LatLon.bearing(start.lat(),start.lng(),end.lat(),end.lng());
    $("#bearing").val(initBearing.toDMS());
  }
}

function calcRoute() {
  var request  = {
    origin:start,
    destination:end,
    travelMode: google.maps.TravelMode.DRIVING,
    optimizeWaypoints: true

  };

  directionsService.route(request, function(result, status) {
    if ( status === google.maps.DirectionsStatus.OK) {
          processResult(result);   
        }
      });
}

  //Used to process optimized directional service results
  function processResult(result){

   var dirRoute = result.routes[0];
   var fullRoute = result.routes[0].overview_path;
   var routePath = result.routes[0].overview_path.length;
   console.log('Length of routes: ' + result.routes[0].overview_path.length);
   var parkStart;
   var parkEnd;
   
   for ( var i=0; i < fullRoute.length; i++ ) {
    var latlng = fullRoute[i];
    console.log(latlng.toString());
    addMarker(fullRoute[i],'test');

    var totes =  $("#bearing").val();


    if(i===0){
      setValueForDom(sMarkLat,latlng.lat());
      setValueForDom(sMarkLng,latlng.lng());
      var mathLibLatLngStart = new LatLon(latlng.lat(),latlng.lng());
      var mathLibLatLngParkStart = mathLibLatLngStart.destPoint(perpendicularAngle(totes),(roadWidth/2000))
      var parkStart = new google.maps.LatLng(mathLibLatLngParkStart.lat, mathLibLatLngParkStart.lon);
      addMarker(parkStart,'ParkStart');
    }

    if(i===1){
      setValueForDom(eMarkLat,latlng.lat());
      setValueForDom(eMarkLng,latlng.lng());
      var mathLibLatLngEnd = new LatLon(latlng.lat(),latlng.lng());
      // divided by 2 because midway of road ..1000 m = 1km
      var mathLibLatLngParkEnd = mathLibLatLngEnd.destPoint(perpendicularAngle(totes),(roadWidth/2000))
      var parkEnd = new google.maps.LatLng(mathLibLatLngParkEnd.lat, mathLibLatLngParkEnd.lon);
      addMarker(mathLibLatLngParkEnd.toGoogleLatLng(),'ParkStart');
    }

   }

  var parkSpaceAngle = LatLon.bearing(
     mathLibLatLngParkStart.lat,
     mathLibLatLngParkStart.lon,
     mathLibLatLngParkEnd.lat,
     mathLibLatLngParkEnd.lon);

  var tmpParkSpace = new parkingSpace(mathLibLatLngParkStart,parkSpaceAngle);
  tempParkSpaceArray.push(tmpParkSpace);

  hideMarkersOverlays();
  afterOptimizeChange();
  areAdditionalParkingSpacesRequired();
  displayParkingSpaces();

 }



/**********************Initialization: ************************/
$(document).ready(function() { 

  initialize();

  $(function() {
    $("#address").autocomplete({
          //This bit uses the geocoder to fetch address values
          source: function(request, response) {
            geocoder.geocode( {'address': request.term }, function(results, status) {
              response($.map(results, function(item) {
                return {
                  label:  item.formatted_address,
                  value: item.formatted_address,
                  latitude: item.geometry.location.lat(),
                  longitude: item.geometry.location.lng()
                }
              }));
            })
          },
          //This bit is executed upon selection of an address
          select: function(event, ui) {
            $("#slatitude").val(ui.item.latitude);
            $("#slongitude").val(ui.item.longitude);
            var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
            startMarker.setPosition(location);
            map.setCenter(location);
          }
        });
  });

      //Add listener to startMarker for reverse geocoding
      google.maps.event.addListener(startMarker, 'drag', function() {
        geocoder.geocode({'latLng': startMarker.getPosition()}, function(results, status) {
          if (status == google.maps.GeocoderStatus.OK) {
            if (results[0]) {
              $('#address').val(results[0].formatted_address);
              $('#slatitude').val(startMarker.getPosition().lat());
              $('#slongitude').val(startMarker.getPosition().lng());
              onMarkerChange();
            }
          }
        }.debounce(10000,false));
      });

      //Add listener to startMarker for reverse geocoding
      google.maps.event.addListener(endMarker, 'drag', function() {
        geocoder.geocode({'latLng': endMarker.getPosition()}, function(results, status) {
          if (status == google.maps.GeocoderStatus.OK) {
            if (results[0]) {
              $('#elatitude').val(endMarker.getPosition().lat());
              $('#elongitude').val(endMarker.getPosition().lng());
              onMarkerChange();
            }
          }
        }.debounce(10000,false));
      });
    });

    function initialize(){
    //MAP

    var options = {
      zoom: 18,
      center: parraCoordinates,
      mapTypeId: google.maps.MapTypeId.ROADMAP
    };

       map = new google.maps.Map(document.getElementById("map_canvas"), options);

      bootstrapFixForMaps();

      //GEOCODER
      geocoder = new google.maps.Geocoder();

      initStartEndMarkers();



      //initializing directional service variables
      var lineOption = {
        strokeColor: "#FF0000"
      };

      var directionRendererOpt = {
        polylineOptions: lineOption,
        draggable: true
      };

      directionsDisplay = new google.maps.DirectionsRenderer(directionRendererOpt);
      directionsDisplay.setMap(map);

      compassDegrees = new CircularArray(4);
      compassDegrees.set(0,0);
      compassDegrees.set(1,90);
      compassDegrees.set(2,180);
      compassDegrees.set(3,270);

    }


    function bootstrapFixForMaps(){
      //https://github.com/twitter/bootstrap/issues/2475
      $(window).resize(function () {
        var h = $(window).height(),
        offsetTop = 190; // Calculate the top offset
      $('#map_canvas').css('height', (h - offsetTop));
      }).resize();
    }

    function initStartEndMarkers(){

      endMarker = new google.maps.Marker({
        map: map,
        draggable: true,
        position: parraCoordinates
      });



      startMarker = new google.maps.Marker({
        map: map,
        draggable: true,
        position: parraCoordinates
      });



      markerArray.push(startMarker);
      markerArray.push(endMarker);
    }



    /**********************Util methods: ************************/
 function addRowToTable(parkingSpace)
  {
    var table=document.getElementById("parkingTable");
    var row=table.insertRow(-1);
    var cell1=row.insertCell(0);
    var cell2=row.insertCell(1);
    var cell3=row.insertCell(2);
    var cell4=row.insertCell(3);
    var cell5=row.insertCell(4);
    var cell6=row.insertCell(5);
    var cell7=row.insertCell(6);
    var cell8=row.insertCell(7);
    cell1.innerHTML=$("#address").val();
    cell2.innerHTML= parkingSpace.startLatLon.lat;
    cell3.innerHTML= parkingSpace.startLatLon.lon;
    cell4.innerHTML= parkingSpace.endLatLon.lat;
    cell5.innerHTML= parkingSpace.endLatLon.lat;
    cell6.innerHTML= parkingSpace.bearing;
    cell7.innerHTML= parkingSpace.parkLength;
    cell8.innerHTML= saveDeleteAction;
  }

    function setValueForDom(dom,value){
      //dom e.g "#slatitude"
      $(dom).val(value);
    }

    function addMarker(latLng,title,excludeFromArray){
      var pinMarker = new google.maps.Marker({
        map: map,
        position: latLng,
        title: title
      });

      google.maps.event.addListener(pinMarker, 'click', function(){
        onItemClick(event, pinMarker);
      });
      if(excludeFromArray){
        return;
      }
      markerArray.push(pinMarker);
    }

    function removeMarker(marker){
      marker.setMap(null);
    }

    function showOverlays() {
      if (markersArray) {
        for (i in markersArray) {
          markersArray[i].setMap(map);
        }
      }
    }

    function hideMarkersOverlays() {
      if (markerArray) {
        for (i in markerArray) {
          markerArray[i].setMap(null);
        }
      }
    }

    function createPolyLine(arrayofLatLng,parkingSpace){
  /* arrayofLatLng example:
  var flightPlanCoordinates = [
      new google.maps.LatLng(37.772323, -122.214897),
      new google.maps.LatLng(21.291982, -157.821856), */

      var path = new google.maps.Polyline({
        path: arrayofLatLng,
        strokeColor: "#000000",//Red"#FF0000",
        strokeOpacity: 1.0,
        strokeWeight: 2
      });

      path.setMap(map);

      if(parkingSpace){
        parkingSpace.path = path;
      }


    }

    function displayParkingSpaces(){


      if (tempParkSpaceArray) {
        for (i in tempParkSpaceArray) {

          var parkingSpace = tempParkSpaceArray[i];
          parkingSpace.showOnMap();
          
          /*
          Can delete replaced by parkingSpace.prototype.showOnMap  
          var startMatLibLatLon = parkingSpace.startLatLon;
          var startLoc = startMatLibLatLon.toGoogleLatLng();
          var endMatLibLatLon = startMatLibLatLon.destPoint(parkingSpace.bearing,(parkingSpaceLength-0.002));
          var endLoc = endMatLibLatLon.toGoogleLatLng();

          var snappedLine = [
          startLoc,
          endLoc
          ]

          createPolyLine(snappedLine,parkingSpace); */
        }
      }
    }

/*------------- PARKING SPACE -----------------*/
   var parkingSpace = function (latLon,bearing){
    //Note Lon refers to MatLib and Lng to google maps
    //Parking Space needs the following attributes address,sLat,sLng,eLat,eLng,bearing,distance
      this.startLatLon = latLon;
      this.bearing = bearing;
      this.address = $('address').val();
      //0.002 (so its visually appealing)
      this.parkLength = parkingSpaceLength-0.002;
      this.endLatLon = latLon.destPoint(bearing,this.parkLength);
  }

  parkingSpace.prototype.showOnMap = function () {
      var startLoc = this.startLatLon.toGoogleLatLng();
      var endLoc = this.endLatLon.toGoogleLatLng();

       var snappedLine = [
          startLoc,
          endLoc
          ]

      createPolyLine(snappedLine,parkingSpace);   

  }

  /*------------- PARKING SPACE -----------------*/

  function perpendicularAngle(totalBearing){

    console.log(totalBearing);


    var angleInDeg;
    var quad = getQuad(totalBearing);
    var quadIndexForPerpendicularAngle = getQuadIndex();

    angleInDeg = (compassDegrees.get(quadIndexForPerpendicularAngle) 
      + getActualBearing(totalBearing,quad));

    console.log(angleInDeg.toDMS());     
    console.log(angleInDeg);
    return angleInDeg;
  }

  function getQuadIndex(){
    /*
    Returns the index reference for qualitatively described 
    quads
    */
    var snapValue = $("#snap").val();
    if(snapValue==="UPRIGHT"){
      return 0;
    } else if (snapValue==="UPLEFT"){
      return 3;
    } else if (snapValue==="DOWNRIGHT"){
      return 1;
    } else if (snapValue==="DOWNLEFT"){
      return 2;         
    } 

  }


  function getQuad(bearing){
    /*
    Returns the quad the bearing is located in.
    */
    var bearingAsNumber = getBearingAsNumber(bearing); 
    if( bearingAsNumber > 0 && bearingAsNumber <= 90){
      return 1;
    } else if ( bearingAsNumber > 90 && bearingAsNumber <= 180){
      return 2;
    } else if ( bearingAsNumber > 180 && bearingAsNumber <= 270){
      return 3;
    } else if ( bearingAsNumber > 270 && bearingAsNumber <= 360){
      return 4;
    }
  }

/*An alternative way of implementing this is to use the prototype Number.parseDeg
defined in geomath.js*/
function getBearingAsNumber(bearing){
     /*
    The format of the bearing is 000°00'00'
    Its only the first 3 or 2 numbers we are interested in.  
    */  
    var bearingAsNumber  = bearing.substring(0,3);  
    if(bearingAsNumber.indexOf(0)===0){
      bearingAsNumber = bearingAsNumber.substring(1,3);
    };
    return bearingAsNumber;
  }

  function getActualBearing(totalBearing, quad){
    /*
    Returns the bearing of the angle from the last 90 degrees
    e.g. totalbearing of 185 will return an actual bearing for (185-180) = 5;
    */

    return Math.abs(compassDegrees.get(quad - 1) - totalBearing.parseDeg()); 
  }



  function CircularArray(n){
    this._array = new Array(n);
    this.length = n;
  }

  CircularArray.prototype.get = function(i){
    i = this.getIndex(i);
    return this._array[i];
  }

  CircularArray.prototype.getIndex = function(i){
    if ( i < 0){
      while( i < 0 ){
        i = this.length + i;
      }
    }


    if( i > (this.length - 1) ) {
     while(i >= this.length){
      i = Math.abs(this.length - i);
    }
  }
  return i;
}

CircularArray.prototype.set = function(i,v){
  this._array[this.getIndex(i)] = v;
}



var saveDeleteAction =  '<a href=\'javascript:alert(\"test\")\'; class="btn btn-small btn-warning"><i class="btn-icon-only icon-ok"></i></a>      <a href="javascript:;" class="btn btn-small"><i class="btn-icon-only icon-remove"></i></a>';






