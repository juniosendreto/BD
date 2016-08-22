package bancodados.vistoria.core.service.dao;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by junio on 27/06/16.
 */
public class AdapterCamera {

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static File file;
    private Context context;

    public AdapterCamera(Context context){
        this.context = context;
        if(file == null){
            File directory = new File(Environment.getExternalStorageDirectory() + File.separator + "Vistorias");
            directory.mkdirs();
        }

    }
    public void saveAllImage(){

    }

    public Intent callCamera(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri uri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        if(intent.resolveActivity(context.getPackageManager()) != null) {
            return intent;
        }
        return null;

    }

    private static Uri getOutputMediaFileUri(int type){
        return Uri.fromFile(getOutputMediaFile(type));
    }


    private static File getOutputMediaFile(int type){
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + File.separator + "Vistorias");

        if (! mediaStorageDir.exists()){
            if (! mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE){
            mediaFile = new File(Environment.getExternalStorageDirectory() + File.separator + "Vistorias/"+
                    "IMG_"+ timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }










}
