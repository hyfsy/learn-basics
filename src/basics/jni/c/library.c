#include "library.h"

#include <stdio.h>

void hello(void) {
    printf("Hello, World!\n");
}

void JNICALL Java_basics_jni_java_JNI_displayHelloWorld(JNIEnv *env, jobject obj) {
    printf("Helloworld jni!\n");
}