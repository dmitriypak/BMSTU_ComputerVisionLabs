package ru.bmstu.computervision.objects;

import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.webflow.execution.RequestContext;

@Component
public class HogDetectorService {
	public ModelAndView detect(UploadedFile file, RequestContext context) throws IOException {
		ModelAndView mvn = new ModelAndView();
		mvn.setViewName("success");
		return mvn;
		
	}
}
