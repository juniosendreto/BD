package bancodados.vistoria.core.service.dao;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bancodados.vistoria.model.FotoVistoria;
import bancodados.vistoria.model.Vistoria;

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
    public void saveAllImage(List<FotoVistoria> fotoVistorias, Vistoria vistoria, List<Bitmap> bitmaps) throws IOException {
        File directory = new File(Environment.getExternalStorageDirectory() + File.separator +
                "Vistorias/" +  "V_" + vistoria.getId());
        directory.mkdirs();


            for(int i = 0;  i < bitmaps.size(); i++){
                File file = new File(directory.getPath() + "/" + fotoVistorias.get(i).getDescricao());
                OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmaps.get(i).compress(Bitmap.CompressFormat.PNG, 100, os);
                fotoVistorias.get(i).setImagemGrande(stream.toByteArray());
                os.close();
            }

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
