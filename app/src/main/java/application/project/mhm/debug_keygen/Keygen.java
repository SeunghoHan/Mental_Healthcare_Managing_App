package application.project.mhm.debug_keygen;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Base64;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Seungho Han on 2017-05-08.
 */

public class Keygen {

    public static final String getKeyHash(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String keyHash = Base64.encodeToString(md.digest(), Base64.DEFAULT);
                Log.d("TAG" + "KeyHash:%s", keyHash);
                return keyHash;
            }

        } catch (PackageManager.NameNotFoundException e) {
            Log.d("TAG"+ "getKeyHash Error:%s", e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            Log.d("TAG"+ "getKeyHash Error:%s", e.getMessage());
        }
        return "";
    }
}
