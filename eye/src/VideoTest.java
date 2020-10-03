////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////                                  ______ _     _ __   __ _______ _______                            //////
//////                                 |______ |_____|   \_/   |_____| |  |  |                            //////
//////                                 ______| |     |    |    |     | |  |  |                            //////
//////                                                                                                    //////
////////////////////////////////////////////////////////////////////////////////////////////////////////////////

import javax.swing.JOptionPane;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;
import org.opencv.videoio.Videoio;

//import opencvImpl.view.ImageViewer;
//import opencvImpl.view.VideoViewer;

public class VideoTest {
    
 static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
public static void main(String args[]){
    int i=0;
VideoViewer videoViewer=new VideoViewer();
 videoViewer.createJFrame("Webcam");
 Mat matImg=new Mat();
 VideoCapture videoCapture=new VideoCapture(0);
 videoCapture.set(Videoio.CAP_PROP_FRAME_WIDTH, 1120);
 videoCapture.set(Videoio.CAP_PROP_FRAME_HEIGHT, 840);
 if(videoCapture.isOpened()){
  CascadeClassifier facedectector=new CascadeClassifier("F:/sgi/opencv/sources/data/haarcascades/haarcascade_frontalface_alt.xml"); // change the path
  if ( facedectector.empty() ) {
    //handler error here
      System.out.println("naaaaaa");
}
  CascadeClassifier eyedetecter=new CascadeClassifier("F:/sgi/opencv/sources/data/haarcascades/haarcascade_eye.xml");    // change the path
  CascadeClassifier smiledetecter=new CascadeClassifier("F:/sgi/opencv/sources/data/haarcascades/haarcascade_smile.xml");  // change the path
  MatOfRect faceDetections = new MatOfRect();
  MatOfRect eyeDetections = new MatOfRect();
  MatOfRect smileDetections = new MatOfRect();
  while(true){
   videoCapture.read(matImg);
   facedectector.detectMultiScale(matImg, faceDetections);
   for(Rect rect:faceDetections.toArray()){
   Imgproc.rectangle(matImg, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
      
   }
   eyedetecter.detectMultiScale(matImg, eyeDetections);
   for(Rect rect:eyeDetections.toArray()){
    Imgproc.rectangle(matImg, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 0, 255));
       
    System.out.println("eye"+i++);
    if(i==10)
    {
        System.out.println("10k giyaa");
        i=0;
    }
    }
//   smiledetecter.detectMultiScale(matImg, smileDetections);
//   for(Rect rect:smileDetections.toArray()){
//    Imgproc.rectangle(matImg, new Point(rect.x, rect.y), new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
//       
//    }
   videoViewer.show(matImg);
   
   
  }
 }else {
  System.out.println("Err in CAMERA IO");
 }
}
}