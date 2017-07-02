package application.project.mhm.debug_keygen;

import android.os.Debug;
import android.util.Log;

/**
 * Created by Seungho Han on 2017-05-20.
 */

public class MemoryManager {

    public static void showMemoryStatusLog() {
        double maxMemory = Runtime.getRuntime().maxMemory() / (1024.0f);
        double allocateMemory = Debug.getNativeHeapAllocatedSize() / (1024.0f);

        Log.e("!!!", "최대 메모리 : " + maxMemory + "KB ");
        Log.e("!!!", "사용 메모리 : " + allocateMemory + "KB ");
    }
}
