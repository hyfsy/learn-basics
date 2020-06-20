#include <iostream.h>
#include <machine_JniCall.h>

JNIEXPORT void JNICALL Java_machine_JniCall_sayBaBi(JNIEnv *, jclass){
	cout << "ba bi" << endl;
}
