package com.parkingengine.json;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.io.IOContext;

public class CustomJsonFactory extends JsonFactory {

  /*-
   * If you want to configure add below to the root-context.xml
  <mvc:annotation-driven>
      <mvc:message-converters>
          <bean
              class="org.springframework.http.converter.json.MappingJacksonHttpMessageConverter">
              <property name="objectMapper" ref="customObjectMapper" />
          </bean>
      </mvc:message-converters>
  </mvc:annotation-driven>

  <bean id="customObjectMapper" class="org.codehaus.jackson.map.ObjectMapper">
      <constructor-arg>
          <bean class="com.parkingengine.json.CustomJsonFactory">
          </bean>
      </constructor-arg>
  </bean>
   */

  @Override
  public JsonGenerator createJsonGenerator(OutputStream out, JsonEncoding enc) throws IOException {
    // false -> we won't manage the stream unless explicitly directed to
    IOContext ctxt = _createContext(out, false);
    ctxt.setEncoding(enc);
    if (enc == JsonEncoding.UTF8) {
      // [JACKSON-512]: allow wrapping with _outputDecorator
      if (_outputDecorator != null) {
        out = _outputDecorator.decorate(ctxt, out);
      }
      return _createUTF8JsonGenerator(out, ctxt);
    }
    Writer w = _createWriter(out, enc, ctxt);
    // [JACKSON-512]: allow wrapping with _outputDecorator
    if (_outputDecorator != null) {
      w = _outputDecorator.decorate(ctxt, w);
    }
    return _createJsonGenerator(w, ctxt);
  }

  @Override
  protected JsonGenerator _createUTF8JsonGenerator(OutputStream out, IOContext ctxt)
      throws IOException {
    CustomUtf8Generator gen = new CustomUtf8Generator(ctxt, _generatorFeatures, _objectCodec, out);
    if (_characterEscapes != null) {
      gen.setCharacterEscapes(_characterEscapes);
    }
    return gen;
  }

}
