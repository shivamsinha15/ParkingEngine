  <script type="text/javascript" src="./sharedResources/plugin/bootstrap-timepicker/js/bootstrap-timepicker.min.js"/></script> 

   <script type="text/javascript">
   <!--http://jdewit.github.io/bootstrap-timepicker/ -->
        $(document).ready(function () { 

            $('#save').click(function(){
                $.ajax({url:buildSaveHref(),success:function(result){
                        $("#div1").html(result);
                }});
              });

             $('#timepickerFrom').timepicker({
                minuteStep: 1,
                template: 'dropdown',
                showSeconds: false,
                showMeridian: false,
                defaultTime: false
            });
             $('#timepickerTo').timepicker({
                minuteStep: 1,
                template: 'dropdown',
                showSeconds: false,
                showMeridian: false,
                defaultTime: false
            });text
             $('#timepickerLimit').timepicker({
                minuteStep: 1,
                template: 'dropdown',
                showSeconds: false,
                showMeridian: false,
                defaultTime: false
            });
        });

        function buildSaveHref(){
            var saveString = "NewParkingRule" +
                           "/save/?fromDay="+$("#dayFrom").val()+
                           "&toDay="+$("#dayTo").val()+
                           "&cost="+$("#cost").val()+
                           "&fromTime="+$("#timepickerFrom").val()+
                           "&toTime="+$("#timepickerTo").val()+
                           "&timeLimit="+$("#timepickerLimit").val();
            return saveString;          
        }
    </script>

<div class="main">
    <div class="container">
      <div class="row">
        <div class="span12">
            <div class="widget stacked">
                    
                <div class="widget-header">
                    <i class="icon-th-large"></i>
                    <h3>Create New Rule</h3>
                </div> <!-- /widget-header -->
                
<div class="widget-content">
<div class="container-fluid">
<div class="row-fluid">
    
<!--- ********************* COLUMN ONE ********************* -->    
<div class="span4"> 
    <form>
<!--- CONTROL GROUP ONE  -->            
        <div class="control-group">
            <h3>DAY</h3>
            <label class="control-label" for="dayFrom">From:</label>
                <div class="controls">
                     <select id="dayFrom">
                        <option>Monday</option>
                        <option>Tuesday</option>
                        <option>Wednesday</option>
                        <option>Thursday</option>
                        <option>Friday</option>  
                        <option>Saturday</option>
                        <option>Sunday</option>
                    </select>
                </div>
        </div>
<!----  CONTROL GROUP ONE END   --->
<!--- CONTROL GROUP Two  -->    
  
        <div class="control-group">
            <label class="control-label" for="dayTo">To:</label>
                <div class="controls">
                    <select id="dayTo">
                        <option>Monday</option>
                        <option>Tuesday</option>
                        <option>Wednesday</option>
                        <option>Thursday</option>
                        <option>Friday</option>  
                        <option>Saturday</option>
                        <option>Sunday</option>
                    </select>  
                </div>
        </div> 
  <!----  CONTROL GROUP Two END   -->       
    </form>
</div>
<!--- ********************* COLUMN TWO ********************* -->
<div class="span4"> 
    <form>
<!--- CONTROL GROUP ONE  -->            
        <div class="control-group">
            <h3>TIME</h3>
            <label class="control-label" for="dayFrom">From:</label>
                <div class="controls">
                     <div class="input-append bootstrap-timepicker">
                        <input id="timepickerFrom" type="text" class="input-small">
                        <span class="add-on"><i class="icon-time"></i></span>
                     </div>
                </div>
        </div>
<!----  CONTROL GROUP ONE END   --->
<!--- CONTROL GROUP Two  -->    
  
        <div class="control-group">
            <label class="control-label" for="dayTo">To:</label>
                <div class="controls">
                    <div class="input-append bootstrap-timepicker">
                         <input id="timepickerTo" type="text" class="input-small">
                        <span class="add-on"><i class="icon-time"></i></span>
                    </div>
                </div>
        </div> 
  <!----  CONTROL GROUP Two END   -->       
    </form>
</div>  
<!--- ********************* COLUMN THREE ********************* -->
<div class="span4"> 
    <form>
<!--- CONTROL GROUP ONE  -->            
        <div class="control-group">
            <h3>OTHER</h3>
            <label class="control-label" for="dayFrom">Cost:</label>
                <div class="controls">
                     <input id="cost" type="text" placeholder="$XXXX Per Hour">
                </div>
        </div>
<!----  CONTROL GROUP ONE END   --->
<!--- CONTROL GROUP Two  -->    
  
        <div class="control-group">
            <label class="control-label" for="timepickerLimit">Max Time Limit:</label>
                <div class="input-append bootstrap-timepicker">
                         <input id="timepickerLimit" type="text" class="input-small">
                        <span class="add-on"><i class="icon-time"></i></span>
                </div>
        </div> 
  <!----  CONTROL GROUP Two END   -->       
    </form>
</div>
<!---SAVE BUTTON START -->
<div class="row">
    <div class="control-group">
        <div class="span12">
            <hr>
        </div>  
        <label class="control-label" for="save"></label>
        <div class="span1">
            <button id ="save"type="button" class="btn btn-success">Save</button>
        </div>
     </div>        
 </div>
 <!---SAVE BUTTON END -->

<!--- ********************* COLUMN THREE END********************* -->

</div> <!-- container-fluid --> 
</div> <!-- row-fluid -->
                </div> <!-- /widget-content --> 
            </div> <!-- /widget -->                         
        </div> <!-- /span12 -->             
      </div> <!-- /row -->
    </div> <!-- /container --> 
</div> <!-- /main -->