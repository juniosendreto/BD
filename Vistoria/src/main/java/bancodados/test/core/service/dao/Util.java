package bancodados.test.core.service.dao;

import android.os.Environment;

import java.io.File;

/**
 * Created by junio on 24/05/16.
 */
public class Util {
    public static File getDirectory(String directory) {
        if (directory==null) return null;
        String path = "";
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            path = Environment.getExternalStorageDirectory().toString();
        }else{
            path = Environment.getDataDirectory().getAbsolutePath();
        }
        File file = new File(path);
        if(file!=null && file.canWrite()) {
            path += directory.startsWith("/") ? "" : "/";
            path += directory.endsWith("/") ? directory : directory + "/";
            file = new File(path);
            file.mkdirs();
            if (file.isDirectory())
                return file;
        }
        return null;
    }
}
