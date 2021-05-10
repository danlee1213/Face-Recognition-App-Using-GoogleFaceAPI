/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ece.course.lab7;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;

import com.google.android.gms.vision.face.Face;
import ece.course.lab7.GraphicOverlay;

class FaceGraphic extends GraphicOverlay.Graphic {

  private static final String TAG = "FaceGraphic";

  private static final float DOT_RADIUS = 3.0f;
  private static final float TEXT_OFFSET_Y = -30.0f;

  private boolean mIsFrontFacing;

  // This variable may be written to by one of many threads. By declaring it as volatile,
  // we guarantee that when we read its contents, we're reading the most recent "write"
  // by any thread.
  private volatile FaceData mFaceData;

  private Paint mHintTextPaint;
  private Paint mHintOutlinePaint;
  private Paint mEyeWhitePaint;
  private Paint mIrisPaint;
  private Paint mEyeOutlinePaint;
  private Paint mEyelidPaint;

  private Drawable mPigNoseGraphic;
  private Drawable mMustacheGraphic;
  private Drawable mHappyStarGraphic;
  private Drawable mHatGraphic;


  FaceGraphic(GraphicOverlay overlay, Context context, boolean isFrontFacing) {
    super(overlay);
    mIsFrontFacing = isFrontFacing;
    Resources resources = context.getResources();
    initializePaints(resources);
    initializeGraphics(resources);
  }

  private void initializeGraphics(Resources resources) {
    mPigNoseGraphic = resources.getDrawable(R.drawable.pig_nose_emoji);
    mMustacheGraphic = resources.getDrawable(R.drawable.mustache);
    mHappyStarGraphic = resources.getDrawable(R.drawable.happy_star);
    mHatGraphic = resources.getDrawable(R.drawable.red_hat);
  }

  private void initializePaints(Resources resources) {
    mHintTextPaint = new Paint();
    mHintTextPaint.setColor(resources.getColor(R.color.overlayHint));
    mHintTextPaint.setTextSize(resources.getDimension(R.dimen.textSize));

    mHintOutlinePaint = new Paint();
    mHintOutlinePaint.setColor(resources.getColor(R.color.overlayHint));
    mHintOutlinePaint.setStyle(Paint.Style.STROKE);
    mHintOutlinePaint.setStrokeWidth(resources.getDimension(R.dimen.hintStroke));

    mEyeWhitePaint = new Paint();
    mEyeWhitePaint.setColor(resources.getColor(R.color.eyeWhite));
    mEyeWhitePaint.setStyle(Paint.Style.FILL);

    mIrisPaint = new Paint();
    mIrisPaint.setColor(resources.getColor(R.color.iris));
    mIrisPaint.setStyle(Paint.Style.FILL);

    mEyeOutlinePaint = new Paint();
    mEyeOutlinePaint.setColor(resources.getColor(R.color.eyeOutline));
    mEyeOutlinePaint.setStyle(Paint.Style.STROKE);
    mEyeOutlinePaint.setStrokeWidth(resources.getDimension(R.dimen.eyeOutlineStroke));

    mEyelidPaint = new Paint();
    mEyelidPaint.setColor(resources.getColor(R.color.eyelid));
    mEyelidPaint.setStyle(Paint.Style.FILL);
  }

  void update(FaceData faceData){
    mFaceData = faceData ;
    postInvalidate(); //Trigger a redraw of the graphic(i.e. cause draw() to be called)
  }

  @Override
  public void draw(Canvas canvas) {
    final float DOT_RADIUS = 3.0f;
    final float TEXT_OFFSET_Y = -30.0f;

    //Confirm that the face and its features are still visible before drawing any graphics over it
    if(mFaceData == null){
      return;
    }
    //1
    PointF detectPosition = mFaceData.getPosition();
    PointF detectLeftEyePosition = mFaceData.getLeftEyePosition();
    PointF detectRightEyePosition = mFaceData.getRightEyePosition();
    PointF detectNoseBasePosition = mFaceData.getNoseBasePosition();
    PointF detectMouthLeftPosition = mFaceData.getMouthLeftPosition();
    PointF detectMouthBottomPosition = mFaceData.getMouthBottomPosition();
    PointF detectMouthRightPosition = mFaceData.getMouthRightPosition();
    if((detectPosition == null || detectLeftEyePosition == null || detectRightEyePosition == null || detectNoseBasePosition == null
    || detectMouthLeftPosition == null || detectMouthBottomPosition == null || detectMouthRightPosition == null)){
      return;
    }
    //2
    //Face position and dimensions
    PointF position = new PointF(translateX(detectPosition.x), translateY(detectPosition.y));
    float width = scaleX(mFaceData.getWidth());
    float height = scaleY(mFaceData.getHeight());

    //3
    //Eye coordinates
    PointF leftEyePosition = new PointF(translateX(detectLeftEyePosition.x), translateY(detectLeftEyePosition.y));
    PointF rightEyePosition = new PointF(translateX(detectRightEyePosition.x), translateY(detectRightEyePosition.y));

    //Eye state
    boolean leftEyeOpen = mFaceData.isLeftEyeOpen();
    boolean rightEyeOpen = mFaceData.isRightEyeOpen();

    //Nose coordinates
    PointF noseBasePosition = new PointF(translateX(detectNoseBasePosition.x), translateY(detectNoseBasePosition.y));

    //Mouth coordinates
    PointF mouthLeftPosition = new PointF(translateX(detectMouthLeftPosition.x), translateY(detectMouthLeftPosition.y));
    PointF mouthRightPosition = new PointF(translateX(detectMouthRightPosition.x), translateY(detectMouthRightPosition.y));
    PointF mouthBottomPosition = new PointF(translateX(detectMouthBottomPosition.x), translateY(detectMouthBottomPosition.y));

    //Smile state
    boolean smiling = mFaceData.isSmiling();

    //Calculate the distance between the eyes using Pythagoras' formula
    //Use that distance to set the size of the eyes and irises
    final float EYE_RADIUS_PROPORTION = 0.45f;
    final float IRIS_RADIUS_PROPORTION = EYE_RADIUS_PROPORTION / 2.0f;
    float distance = (float) Math.sqrt((rightEyePosition.x - leftEyePosition.x) * (rightEyePosition.x - leftEyePosition.x)
    + (rightEyePosition.y - leftEyePosition.y) * (rightEyePosition.y - leftEyePosition.y));
    float eyeRadius = EYE_RADIUS_PROPORTION * distance;
    float irisRadius = IRIS_RADIUS_PROPORTION * distance;

    //Draw the eyes
    drawEye(canvas, leftEyePosition, eyeRadius, leftEyePosition, irisRadius, leftEyeOpen, smiling);
    drawEye(canvas, rightEyePosition, eyeRadius, rightEyePosition, irisRadius, rightEyeOpen, smiling);

    //Draw the nose
    drawNose(canvas, noseBasePosition, leftEyePosition, rightEyePosition, width);

    //Draw the mustache
    drawMustache(canvas, noseBasePosition, mouthLeftPosition, mouthRightPosition);
  }

  private void drawEye(Canvas canvas, PointF eyePosition, float eyeRadius, PointF irisPosition, float irisRadius, boolean eyeOpen, boolean smiling){
    if(eyeOpen){
      canvas.drawCircle(eyePosition.x, eyePosition.y, eyeRadius, mEyeWhitePaint);
      if(smiling){
        mHappyStarGraphic.setBounds((int) (irisPosition.x - irisRadius), (int) (irisPosition.y - irisRadius),
                (int) (irisPosition.x + irisRadius), (int) (irisPosition.y + irisRadius));
        mHappyStarGraphic.draw(canvas);
      }else{
        canvas.drawCircle(irisPosition.x, irisPosition.y, irisRadius, mIrisPaint);
      }
    }else{
      canvas.drawCircle(eyePosition.x, eyePosition.y, eyeRadius, mEyelidPaint);
      float y = eyePosition.y;
      float start = eyePosition.x - eyeRadius;
      float end = eyePosition.x + eyeRadius;
      canvas.drawLine(start, y, end, y, mEyeOutlinePaint);
    }
    canvas.drawCircle(eyePosition.x, eyePosition.y, eyeRadius, mEyeOutlinePaint);
  }

  private void drawNose(Canvas canvas, PointF noseBasePosition, PointF leftEyePosition, PointF rightEyePosition, float faceWidth){
    final float NOSE_FACE_WIDTH_RATIO = (float) (1/5.0);
    float noseWidth = faceWidth * NOSE_FACE_WIDTH_RATIO;
    int left = (int)(noseBasePosition.x - (noseWidth/2));
    int right = (int)(noseBasePosition.x + (noseWidth/2));
    int top = (int)(leftEyePosition.y + rightEyePosition.y) / 2;
    int bottom = (int)noseBasePosition.y;
    mPigNoseGraphic.setBounds(left, top, right, bottom);
    mPigNoseGraphic.draw(canvas);
  }

  private void drawMustache(Canvas canvas, PointF noseBasePosition, PointF mouthLeftPosition, PointF mouthRightPosition){
    int left = (int)mouthLeftPosition.x;
    int top = (int)noseBasePosition.y;
    int right = (int)mouthRightPosition.x;
    int bottom = (int)Math.min(mouthLeftPosition.y, mouthRightPosition.y);

    if(mIsFrontFacing){
      mMustacheGraphic.setBounds(left, top, right, bottom);
    }else{
      mMustacheGraphic.setBounds(right, top, left, bottom);
    }
    mMustacheGraphic.draw(canvas);
  }

}
