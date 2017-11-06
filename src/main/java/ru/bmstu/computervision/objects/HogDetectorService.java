package ru.bmstu.computervision.objects;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.HOGDescriptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.execution.RequestContext;

@Component
public class HogDetectorService {
	
	public ModelAndView detect(UploadedFile file, RequestContext context) throws IOException {
		ModelAndView mvn = new ModelAndView();
		
		ServletExternalContext externalContext = (ServletExternalContext) context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getNativeRequest();
		
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String dir = rootPath +"/resources/";

		String pathGreyScaleFile = file.getGreyScaleFile().getAbsolutePath();
		String pathBinaryFile = file.getBinaryFile().getAbsolutePath();
		System.out.println("$$$ path" + pathGreyScaleFile);
		
		String detectPeopleName = file.getGreyScaleFile().getName();
		String detectPeopleBinaryName = file.getBinaryFile().getName();
		String detectPeopleFilePath = serverPath +"/resources/";
		File greyScaleDetectFile = createDetectPeopleFile(pathGreyScaleFile,detectPeopleName,dir);
		file.setDetectPeopleFile(greyScaleDetectFile);
		file.setDetectPeopleFilePath(detectPeopleFilePath+greyScaleDetectFile.getName());
		
		File binaryFile = createDetectPeopleFile(pathBinaryFile,detectPeopleBinaryName,dir);
		file.setDetectPeopleFile(greyScaleDetectFile);
		file.setDetectPeopleFilePath(detectPeopleFilePath+greyScaleDetectFile.getName());
		file.setDetectPeopleFile(binaryFile);
		file.setDetectPeopleBinaryFilePath(detectPeopleFilePath+binaryFile.getName());
		
		
		mvn.setViewName("success");
		return mvn;
	}
	private File createDetectPeopleFile(String path, String fileName, String dir) {
		HOGDescriptor hog = new HOGDescriptor();
		hog.setSVMDetector(HOGDescriptor.getDefaultPeopleDetector());
		Mat matrix = Imgcodecs.imread(path);
		File detectPeopleFile = null;
		MatOfRect found = new MatOfRect();
		MatOfDouble weight = new MatOfDouble();
		hog.detectMultiScale(matrix, found, weight, 0, new Size(8, 8), new Size(32, 32), 1.05, 2, false);

		Rect[] rects = found.toArray();
		if (rects.length > 0) {
			for (Rect rect : rects) {
				Imgproc.rectangle(matrix, // where to draw the box
						new Point(rect.x, rect.y), // bottom left
						new Point(rect.x + rect.width, rect.y + rect.height), // top right
						new Scalar(0, 0, 255),
						3); // RGB colour
			}
			
			// Writing the image
			String detectPeopleFileName = fileName.substring(0,fileName.lastIndexOf("."))+"_detect_people"+fileName.substring(fileName.lastIndexOf("."),
					fileName.length());
			Imgcodecs.imwrite(dir+detectPeopleFileName, matrix);	
			detectPeopleFile = new File(dir+detectPeopleFileName);		
			
		}
		return detectPeopleFile;
	}	
}
