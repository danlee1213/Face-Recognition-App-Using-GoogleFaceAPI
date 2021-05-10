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




Install app \SmileDetection\app\build\outputs\apk\debug\app-debug.apk
