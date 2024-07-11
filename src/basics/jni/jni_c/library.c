#include "library.h"

#include <stdio.h>

void hello(void) {
    printf("Hello, World!\n");
}

void JNICALL Java_com_batchsight_demo_test_JNI_displayHelloWorld(JNIEnv *env, jobject obj) {
    printf("Helloworld jni!\n");
}