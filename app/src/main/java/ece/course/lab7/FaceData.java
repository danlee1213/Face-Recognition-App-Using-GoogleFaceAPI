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

import android.graphics.PointF;



public class FaceData {

  private static final String TAG = "FaceData";

  private int mId;

  // Face dimensions
  private PointF mPosition;
  private float mWidth;
  private float mHeight;

  // Head orientation
  private float mEulerY;
  private float mEulerZ;

  // Facial states
  private boolean mLeftEyeOpen;
  private boolean mRightEyeOpen;
  private boolean mSmiling;

  // Facial landmarks
  private PointF mLeftEyePosition;
  private PointF mRightEyePosition;
  private PointF mLeftCheekPosition;
  private PointF mRightCheekPosition;
  private PointF mNoseBasePosition;
  private PointF mLeftEarPosition;
  private PointF mLeftEarTipPosition;
  private PointF mRightEarPosition;
  private PointF mRightEarTipPosition;
  private PointF mMouthLeftPosition;
  private PointF mMouthBottomPosition;
  private PointF mMouthRightPosition;


  public int getId() {
    return mId;
  }

  public void setId(int id) {
    mId = id;
  }

  public PointF getPosition() {
    return mPosition;
  }

  public void setPosition(PointF position) {
    mPosition = position;
  }

  public float getWidth() {
    return mWidth;
  }

  public void setWidth(float width) {
    mWidth = width;
  }

  public float getHeight() {
    return mHeight;
  }

  public void setHeight(float height) {
    mHeight = height;
  }

  public float getEulerY() {
    return mEulerY;
  }

  public void setEulerY(float eulerY) {
    mEulerY = eulerY;
  }

  public float getEulerZ() {
    return mEulerZ;
  }

  public void setEulerZ(float eulerZ) {
    mEulerZ = eulerZ;
  }

  public boolean isLeftEyeOpen() {
    return mLeftEyeOpen;
  }

  public void setLeftEyeOpen(boolean leftEyeOpen) {
    this.mLeftEyeOpen = leftEyeOpen;
  }

  public boolean isRightEyeOpen() {
    return mRightEyeOpen;
  }

  public void setRightEyeOpen(boolean rightEyeOpen) {
    this.mRightEyeOpen = rightEyeOpen;
  }

  public boolean isSmiling() {
    return mSmiling;
  }

  public void setSmiling(boolean smiling) {
    this.mSmiling = smiling;
  }

  public PointF getLeftEyePosition() {
    return mLeftEyePosition;
  }

  public void setLeftEyePosition(PointF leftEyePosition) {
    this.mLeftEyePosition = leftEyePosition;
  }

  public PointF getRightEyePosition() {
    return mRightEyePosition;
  }

  public void setRightEyePosition(PointF rightEyePosition) {
    this.mRightEyePosition = rightEyePosition;
  }

  public PointF getLeftCheekPosition() {
    return mLeftCheekPosition;
  }

  public void setLeftCheekPosition(PointF leftCheekPosition) {
    mLeftCheekPosition = leftCheekPosition;
  }

  public PointF getRightCheekPosition() {
    return mRightCheekPosition;
  }

  public void setRightCheekPosition(PointF rightCheekPosition) {
    mRightCheekPosition = rightCheekPosition;
  }

  public PointF getNoseBasePosition() {
    return mNoseBasePosition;
  }

  public void setNoseBasePosition(PointF noseBasePosition) {
    this.mNoseBasePosition = noseBasePosition;
  }

  public PointF getLeftEarPosition() {
    return mLeftEarPosition;
  }

  public void setLeftEarPosition(PointF leftEarPosition) {
    mLeftEarPosition = leftEarPosition;
  }

  public PointF getLeftEarTipPosition() {
    return mLeftEarTipPosition;
  }

  public void setLeftEarTipPosition(PointF leftEarTipPosition) {
    mLeftEarTipPosition = leftEarTipPosition;
  }

  public PointF getRightEarPosition() {
    return mRightEarPosition;
  }

  public void setRightEarPosition(PointF rightEarPosition) {
    mRightEarPosition = rightEarPosition;
  }

  public PointF getRightEarTipPosition() {
    return mRightEarTipPosition;
  }

  public void setRightEarTipPosition(PointF rightEarTipPosition) {
    mRightEarTipPosition = rightEarTipPosition;
  }

  public PointF getMouthLeftPosition() {
    return mMouthLeftPosition;
  }

  public void setMouthLeftPosition(PointF mouthLeftPosition) {
    this.mMouthLeftPosition = mouthLeftPosition;
  }

  public PointF getMouthBottomPosition() {
    return mMouthBottomPosition;
  }

  public void setMouthBottomPosition(PointF mouthBottomPosition) {
    this.mMouthBottomPosition = mouthBottomPosition;
  }

  public PointF getMouthRightPosition() {
    return mMouthRightPosition;
  }

  public void setMouthRightPosition(PointF mouthRightPosition) {
    this.mMouthRightPosition = mouthRightPosition;
  }

}
