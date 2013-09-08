package com.parkingengine.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.parkingengine.domain.entities.PERule;

/*
 * Currently Not Using This Class
 */

public class PERuleSerializer extends JsonSerializer<PERule> {

  private JsonGenerator jgen;

  @Override
  public void serialize(PERule value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {
    this.jgen = jgen;
    jgen.writeStartObject();
    writeNameValue("id", String.valueOf(value.getId()), true);
    writeNameValue("fromDay", String.valueOf(value.getFromDay()), true);
    writeNameValue("toDay", String.valueOf(value.getToDay()), true);
    writeNameValue("cost", String.valueOf(value.getCost()), true);
    writeNameValue("fromTime", String.valueOf(value.getFromTime()), true);
    writeNameValue("toTime", String.valueOf(value.getToTime()), true);
    writeNameValue("timeLimit", String.valueOf(value.getTimeLimit()), false);
    jgen.writeEndObject();

  }

  public void writeNameValue(String fieldName, String fieldValue, boolean addComma)
      throws JsonGenerationException, IOException {
    String name = "\"" + fieldName + "\"";
    String value = "\"" + fieldValue + "\"";
    jgen.writeRaw(name + ":" + value);
    if (addComma) {
      addComma();
    }
  }

  public void addComma() throws JsonGenerationException, IOException {
    jgen.writeRaw(",");
  }

  public void addStartBracket() throws JsonGenerationException, IOException {
    jgen.writeRaw("{");
  }

  public void addEndBracket(boolean addComma) throws JsonGenerationException, IOException {
    jgen.writeEndObject();
    if (addComma) {
      addComma();
    }
  }



}
