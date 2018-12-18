#include "jni.h"
#include "com_HelloWorld.h"
#include<stdio.h>
#include<cstring>

JNIEXPORT void JNICALL Java_com_HelloWorld_sayHello
  (JNIEnv * env, jclass clazz, jstring string) {
    string hello = "Hello,World";
  	return env->NewStringUTF(hello.c_str());
  }
