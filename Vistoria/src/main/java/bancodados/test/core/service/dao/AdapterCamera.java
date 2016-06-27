package bancodados.test.core.service.dao;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.File;

/**
 * Created by junio on 27/06/16.
 */
public class AdapterCamera {

    public static File file;

    public File gerarPastaPrincipal(){
        if(file == null){
            File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "Vistorias");
            directory.mkdirs();
        }
        return file;
    }

    public Intent chamarCamera(File file){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file2 = new File(file, "teste");
        Uri uri = Uri.fromFile(file2);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        return intent;

    }



}
