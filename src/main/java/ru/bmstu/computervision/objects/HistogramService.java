package ru.bmstu.computervision.objects;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfInt;
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
public class HistogramService {
	final static int max_BINARY_value = 255;
	public ModelAndView calcHistogram(UploadedFile file, RequestContext context) throws IOException {
		ModelAndView mvn = new ModelAndView();
		
		ServletExternalContext externalContext = (ServletExternalContext) context.getExternalContext();
		HttpServletRequest request = (HttpServletRequest) externalContext.getNativeRequest();
		
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		String serverPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
		String dir = rootPath +"resources\\";

		String filePath = serverPath +"\\resources\\";
//		BufferedImage image = ImageIO.read(file.getGreyScaleFile());
//		Mat matGreyScale = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC1);
//		String greyScaleFileName = file.getGreyScaleFile().getName();
//		Mat matBW = new Mat(); // Binary
//		String binaryFileName = greyScaleFileName.substring(0, greyScaleFileName.lastIndexOf(".")) + "_binary"
//				+ greyScaleFileName.substring(greyScaleFileName.lastIndexOf("."), greyScaleFileName.length());
//		Imgproc.threshold(matGreyScale, matBW, file.getBINARY_value(), max_BINARY_value, 0);		
//		Imgcodecs.imwrite(dir+ binaryFileName, matBW);
//		File binaryOuptut = new File(dir+ binaryFileName);
//		file.setBinaryFile(binaryOuptut);

		File histogramFile = getHistogram(file.getBinaryFile(),dir);
		file.setHistogram(histogramFile);
		file.setHistogramPath(filePath+histogramFile.getName());
		
		
		mvn.setViewName("success");
		return mvn;
	}
	private File getHistogram(File file,String dir) throws IOException {
		MatOfInt channels = new MatOfInt(0);
		MatOfInt histSize = new MatOfInt(256);
		 MatOfFloat ranges=new MatOfFloat(0.0f,255.0f);
		int hist_w = 500;
		int hist_h = 500;
		int bin_w = (int) Math.round(hist_w / histSize.get(0, 0)[0]);
		Mat hist_b = new Mat();
		//ArrayList<Mat> imagesList=new ArrayList<Mat>();
        
		hist_b = Imgcodecs.imread(file.getAbsolutePath());
		Mat histImage = new Mat(hist_h, hist_w, CvType.CV_8UC3, new Scalar(0, 0, 0));
		
		Core.normalize(hist_b, hist_b, 0, histImage.rows(), Core.NORM_MINMAX, -1, new Mat());
		//imagesList.add(hist_b);
		//Imgproc.calcHist(imagesList, channels ,new Mat(), histImage, histSize, ranges);
		for (int i = 1; i < histSize.get(0, 0)[0]; i++){
			Imgproc.line(histImage, new Point(bin_w * (i - 1), hist_h - Math.round(hist_b.get(i - 1, 0)[0])),
			new Point(bin_w * (i), hist_h - Math.round(hist_b.get(i,0)[0])), new Scalar(255, 0, 0), 2, 8, 0);
		}
			
		
//		byte[] data1 = new	byte[histImage.rows()*histImage.cols()*(int)(histImage.elemSize())];
//		histImage.get(0, 0, data1);
//		BufferedImage image1=new BufferedImage(histImage.cols(),histImage.rows(),BufferedImage.TYPE_BYTE_GRAY);
//				image1.getRaster().setDataElements(0,0,histImage.cols(),histImage.rows(),data1);
//				File ouptut = new File("grayscale.jpg");
//				ImageIO.write(image1, "jpg", ouptut);
		
		String fileName = file.getName();
		BufferedImage histo = convertMatToBufferedImage(histImage);
		String histogramFileName = fileName.substring(0, fileName.lastIndexOf(".")) + "_histogram"
				+ fileName.substring(fileName.lastIndexOf("."), fileName.length());
		String histogram = "histogram.jpg";
		File outputfile = new File(dir+histogram);
		ImageIO.write(histo, "jpg", outputfile);
		return outputfile;

	}	
	
	
	private static BufferedImage convertMatToBufferedImage(Mat mat) {  
        byte[] data = new byte[mat.rows() * mat.cols() * (int)mat.elemSize()];  
        System.out.println("######" + mat.rows() + "/" + mat.cols());
        int type;  
        mat.get(0,0,data);  
  
        switch (mat.channels()) {    
            case 1:    
                type = BufferedImage.TYPE_BYTE_GRAY;    
                break;    
            case 3:    
                type = BufferedImage.TYPE_3BYTE_BGR;    
                // bgr to rgb    
                byte b;    
                for(int i=0; i<data.length; i=i+3) {    
                    b = data[i];    
                    data[i] = data[i+2];    
                    data[i+2] = b;    
                }    
                break;    
            default:    
                throw new IllegalStateException("Unsupported number of channels");  
        }    
          
        BufferedImage out = new BufferedImage(mat.width(), mat.height(), type);  
  
        out.getRaster().setDataElements(0, 0, mat.width(), mat.height(), data);  
          
        return out;  
    }  
}
