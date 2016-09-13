package bancodados.vistoria.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageButton;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import bancodados.vistoria.core.service.dao.AdapterCamera;
import bancodados.vistoria.core.service.dao.FotoVistoriaDaoImpl;
import bancodados.vistoria.model.FotoVistoria;

/**
 * Created by junio on 05/09/16.
 */
public class CameraActivity extends Activity {


    public static final int PICK_PHOTO_REQUEST = 100;
    private List<FotoVistoria> fotoVistorias;
    private List<Bitmap> bitmaps;
    private File mTempFile;
    private AdapterCamera adapterCamera;
    private FotoVistoriaDaoImpl fotoVistoriaDao;
    private Map<Integer, List<String>> mPathPhotos;
    ImageButton teste;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTempFile = new File(Environment.getExternalStorageDirectory() + File.separator + "Vistorias/temp");



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri uri = null;
        File file;
        OutputStream os;

        String photo = mTempFile.getPath() + "/" + "IMG_" +
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpeg";
        String thumbNail = mTempFile.getPath() + "/" +  "IMG_" +
                new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_TN" + ".jpeg";

        if (requestCode == PICK_PHOTO_REQUEST && resultCode == Activity.RESULT_OK && data != null &&
                data.getData() != null) {
            try {
                if(!mTempFile.exists()){
                    mTempFile.mkdirs();
                }

                uri = data.getData();
                file = new File(photo);
                Bitmap imageReal = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                os = new BufferedOutputStream(new FileOutputStream(file));
                // ByteArrayOutputStream stream = new ByteArrayOutputStream();

                imageReal.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.close();


                //FotoVistoria fotoVistoria = new FotoVistoria();
                //fotoVistoria.setDescricao("IMG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + ".jpeg");
                //fotoVistoria.setImagemGrande(adapterCamera.bitmapToByteArray(getApplicationContext(), imageReal));

                /* ----*/
                file = new File(thumbNail);
                Bundle extras = data.getExtras();
                Bitmap imageThumbNail =  (Bitmap) extras.get("data");
                os = new BufferedOutputStream(new FileOutputStream(file));
                imageThumbNail.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.close();

                //fotoVistoria.setImagemPequena(adapterCamera.bitmapToByteArray(getApplicationContext(), imageThumbNail));

                //fotoVistorias.add(fotoVistoria);

                /// 2131493058
                /// 2131493071



                //ByteArrayOutputStream stream = new ByteArrayOutputStream();

                //imageThumbNail.compress(Bitmap.CompressFormat.JPEG, 100, stream);
                //fotoVistoria.setImagemPequena(stream.toByteArray());

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("-------", e.getMessage());
            }finally {
                getContentResolver().delete(uri, null, null);

            }
        }

    }


    public void teste(){

    }


}
