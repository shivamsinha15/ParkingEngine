package org.springframework.samples.mvc.simple;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SimpleController {
	/*
    	<li>
    		<a id="simpleLink" class="textLink" href="<c:url value="/simple" />">GET /simple</a>
  		</li>
   */
	@RequestMapping("/simple")
	public @ResponseBody String simple() {
		return "Hello Shiv!";
	}

}
