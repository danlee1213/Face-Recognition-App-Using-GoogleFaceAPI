# Face-Recognition-App-Using-GoogleFaceAPI
Android based face recognition app using Google Face API

# Overview
This project builds the android app using Google Face API.

Built by Android Studio 4.1.3

## Face Detection in Google Vision API
The Face API finds human faces in photos, videos, or live streams. It also finds and tracks positions of facial landmarks such as the eyes, nose, and mouth.

Here are some of the terms that we use in discussing face detection and the various functionalities of the Mobile Vision API.

__Face recognition__ automatically determines if two faces are likely to correspond to the same person. Note that at this time, the Google Face API only provides functionality for face detection and not face recognition.


__Face tracking extends__ face detection to video sequences. Any face appearing in a video for any length of time can be tracked. That is, faces that are detected in consecutive video frames can be identified as being the same person. Note that this is not a form of face recognition; this mechanism just makes inferences based on the position and motion of the face(s) in a video sequence.

A __landmark__ is a point of interest within a face. The left eye, right eye, and nose base are all examples of landmarks. The Face API provides the ability to find landmarks on a detected face.

__Classification__ is determining whether a certain facial characteristic is present. For example, a face can be classified with regards to whether its eyes are open or closed. Another example is whether the face is smiling or not.

## Brief explanation for each class
__FaceActivity__: The main activity of the app which shows the camera preview. 

__FaceTracker__: Follows faces that are detected in images from the camera, and gathers their positions and landmarks. 

__FaceGraphic__: Draws the computer-generated images over faces in the camera images. 

__FaceData__: A data class for passing FaceTracker data to FaceGraphic. 

__EyePhysics__: Provided by Google in their Mobile Vision sample apps on GitHub, it’s a simple physics engine that will animate the AR irises as the faces they’re on move. 

__CameraSourcePreview__: Another class from Google. It displays the live image data from the camera in a view. 

__GraphicOverlay__: One more Google class; FaceGraphic subclasses it.


## Landmarks that the Face API can identify
<img src=https://user-images.githubusercontent.com/72503871/117692116-48b42e00-b1ef-11eb-94c6-04f96a67d622.jpg width="480">
This information will be saved in a FaceData object, instead of the provided Face object. For facial landmarks, “left” and “right” refer to the subject’s left and right. Viewed through the front camera, the subject’s right eye will be closer to the right side of the screen, but through the rear camera, it’ll be closer to the left.

## Expected Outputs
In FaceTracker class:
```Java
 //2
  @Override
  public void onUpdate(Detector.Detections<Face>detectionResults, Face face){
    mOverlay.add(mFaceGraphic);
    updatePreviousLandmarkPositions(face);

    //Get face dimensions
    mFaceData.setPosition(face.getPosition());
    mFaceData.setWidth(face.getWidth());
    mFaceData.setHeight(face.getHeight());

    //Get the positions of facial landmarks
    updatePreviousLandmarkPositions(face);
    mFaceData.setLeftEyePosition(getLandmarkPosition(face, Landmark.LEFT_EYE));
    mFaceData.setRightEyePosition(getLandmarkPosition(face, Landmark.RIGHT_EYE));
    mFaceData.setMouthBottomPosition(getLandmarkPosition(face, Landmark.LEFT_CHEEK));
    mFaceData.setMouthBottomPosition(getLandmarkPosition(face, Landmark.RIGHT_CHEEK));
    mFaceData.setNoseBasePosition(getLandmarkPosition(face, Landmark.NOSE_BASE));
    mFaceData.setMouthBottomPosition(getLandmarkPosition(face, Landmark.LEFT_EAR));
    mFaceData.setMouthBottomPosition(getLandmarkPosition(face, Landmark.LEFT_EAR_TIP));
    mFaceData.setMouthBottomPosition(getLandmarkPosition(face, Landmark.RIGHT_EAR));
    mFaceData.setMouthBottomPosition(getLandmarkPosition(face, Landmark.RIGHT_EAR_TIP));
    mFaceData.setMouthLeftPosition(getLandmarkPosition(face, Landmark.LEFT_MOUTH));
    mFaceData.setMouthBottomPosition(getLandmarkPosition(face, Landmark.BOTTOM_MOUTH));
    mFaceData.setMouthRightPosition(getLandmarkPosition(face, Landmark.RIGHT_MOUTH));

    //1
    final float EYE_CLOSED_THRESHOLD = 0.4f;
    float leftOpenScore = face.getIsLeftEyeOpenProbability();
    if(leftOpenScore == Face.UNCOMPUTED_PROBABILITY){
      mFaceData.setLeftEyeOpen(mPreviousIsLeftEyeOpen);
    }else{
      mFaceData.setLeftEyeOpen(leftOpenScore > EYE_CLOSED_THRESHOLD);
      mPreviousIsLeftEyeOpen = mFaceData.isLeftEyeOpen();
    }
    float rightOpenScore = face.getIsRightEyeOpenProbability();
    if(rightOpenScore == Face.UNCOMPUTED_PROBABILITY){
      mFaceData.setRightEyeOpen(mPreviousIsRightEyeOpen);
    }else{
      mFaceData.setRightEyeOpen(rightOpenScore > EYE_CLOSED_THRESHOLD);
      mPreviousIsRightEyeOpen = mFaceData.isRightEyeOpen();
    }

    //2
    //Determine if person is smiling.
    final float SMILING_THRESHOLD = 0.8f;
    mFaceData.setSmiling(face.getIsSmilingProbability() > SMILING_THRESHOLD);

    mFaceGraphic.update(mFaceData);
  }
```
1. FaceGraphic should be responsible simply for drawing graphics over faces, not determining whether an eye is open or closed based on the face detector’s probability assessments. This means that FaceTracker should do those calculations and provide FaceGraphic with ready-to-eat data in the form of a FaceData instance. These calculations take the results from getIsLeftEyeOpenProbability and getIsRightEyeOpenProbability and turn them into a simple true/false value. If the detector thinks that there’s a greater than 40% chance that an eye is open, it’s considered open.

2. The same is for smiling with getIsSmilingProbability, but more strictly. If the detector thinks that there’s a greater than 80% chance that the face is smiling, it’s considered to be smiling.



# Install the app
Install the debug app in the following path:
```
\SmileDetection\app\build\outputs\apk\debug\app-debug.apk
```
