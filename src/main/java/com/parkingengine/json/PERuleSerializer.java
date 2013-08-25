package com.parkingengine.json;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

import com.parkingengine.domain.entities.PERule;

/*
 * Currently Not Using This Class
 */

public class PERuleSerializer extends JsonSerializer<PERule> {

  @Override
  public void serialize(PERule value, JsonGenerator jgen, SerializerProvider provider)
      throws IOException, JsonProcessingException {
    jgen.writeStartObject();
    jgen.writeNumber(value.getId());
    jgen.writeNumber(value.getCost());
    jgen.writeFieldName(value.getFromDay().toString());
    jgen.writeFieldName(value.getToDay().toString());
    jgen.writeFieldName(value.getFromTime().toString());
    jgen.writeFieldName(value.getToTime().toString());
    jgen.writeFieldName(value.getTimeLimit().toString());
    jgen.writeEndObject();
  }

}
