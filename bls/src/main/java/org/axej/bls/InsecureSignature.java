/*
 * Copyright 2018 Axe Core Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * This file was generated by SWIG (http://www.swig.org) and modified.
 * Version 3.0.12
 */

package org.axej.bls;

public class InsecureSignature {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected InsecureSignature(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(InsecureSignature obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        JNI.delete_InsecureSignature(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public static InsecureSignature FromBytes(byte [] data) {
    return new InsecureSignature(JNI.InsecureSignature_FromBytes(data), true);
  }

  public static InsecureSignature FromG2(SWIGTYPE_p_g2_t element) {
    return new InsecureSignature(JNI.InsecureSignature_FromG2(SWIGTYPE_p_g2_t.getCPtr(element)), true);
  }

  public InsecureSignature(InsecureSignature signature) {
    this(JNI.new_InsecureSignature(InsecureSignature.getCPtr(signature), signature), true);
  }

  public boolean Verify(MessageHashVector hashes, PublicKeyVector pubKeys) {
    return JNI.InsecureSignature_Verify(swigCPtr, this, MessageHashVector.getCPtr(hashes), PublicKeyVector.getCPtr(pubKeys), pubKeys);
  }

  public boolean Verify(byte [] hash, PublicKey pubKey) {
    PublicKeyVector pubKeys = new PublicKeyVector();
    pubKeys.push_back(pubKey);
    MessageHashVector hashes = new MessageHashVector();
    hashes.push_back(hash);
    return Verify(hashes, pubKeys);
  }

  public static InsecureSignature Aggregate(InsecureSignatureVector sigs) {
    return new InsecureSignature(JNI.InsecureSignature_Aggregate(InsecureSignatureVector.getCPtr(sigs)), true);
  }

  public InsecureSignature DivideBy(InsecureSignatureVector sigs) {
    return new InsecureSignature(JNI.InsecureSignature_DivideBy(swigCPtr, this, InsecureSignatureVector.getCPtr(sigs)), true);
  }

  public void Serialize(byte[] buffer) {
    JNI.InsecureSignature_Serialize__SWIG_0(swigCPtr, this, buffer);
  }

  public SWIGTYPE_p_std__vectorT_unsigned_char_t Serialize() {
    return new SWIGTYPE_p_std__vectorT_unsigned_char_t(JNI.InsecureSignature_Serialize__SWIG_1(swigCPtr, this), true);
  }

  public final static long SIGNATURE_SIZE = JNI.InsecureSignature_SIGNATURE_SIZE_get();
}
