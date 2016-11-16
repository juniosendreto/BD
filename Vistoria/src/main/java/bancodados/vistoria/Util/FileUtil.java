package bancodados.vistoria.Util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import bancodados.vistoria.core.service.dao.FotoVistoriaDaoImpl;
import bancodados.vistoria.model.FotoVistoria;
import bancodados.vistoria.model.Vistoria;

/**
 * Created by junio on 06/09/16.
 */
public class FileUtil {

    /// PATH = storage/emulated/0/
    public static final String PATH = Environment.getExternalStorageDirectory() + File.separator;
    public static final String IMAGEM_MEDIA_SIGLA = "M";
    public static final String THUMBNAIL_SIGLA = "_TN";
    public static final String JPEG = ".jpeg";

    private Context context;
    private static File mFile;

    public FileUtil(){}

    public FileUtil(Context context){
        this.context = context;
    }


    public static File mainDirectory(){
        mFile = new File(PATH + "vistoria");
        if(!(mFile.exists()))
            mFile.mkdirs();
        return mFile;
    }

    public static File bDDirectory(){
        mFile = new File(PATH + "vistoria/bd");
        if(!(mFile.exists()))
            mFile.mkdirs();
        return mFile;
    }

    public static File vistoriasDirectory(){
        mFile = new File(PATH + "vistoria/vistorias");
        if(!(mFile.exists()))
            mFile.mkdirs();
        return mFile;
    }

    public static File tempDirectory(){
        mFile = new File(PATH + "vistoria/vistorias/temp");
        if(!(mFile.exists()))
            mFile.mkdirs();
        return mFile;
    }

    public static void removeAllFile(File file){
        if(file != null){
            file.delete();
            if(file.exists()){
                File[] fileList = file.listFiles();
                for(File f: fileList){
                    f.delete();
                }
                file.delete();
            }
        }
    }

    public static File tempToVistoria(File file, Long id){
        if(file.exists())
            file.renameTo(new File(PATH + "vistoria/vistorias/v_" + id));
        return mFile;
    }

    public static void saveAllPhotosDB(Context context, Map<Integer, List<String>> map, Vistoria vistoria){
        FotoVistoriaDaoImpl fotoVistoriaDao = new FotoVistoriaDaoImpl(context);

        try {
            for(Map.Entry<Integer, List<String>> entry: map.entrySet()){
                for(String path: entry.getValue()){
                    fotoVistoriaDao.save(FotoVistoria.class,
                            new FotoVistoria(vistoria, vistoria.getNomeCameras().get(entry.getKey()),
                                    fileToByteArray(path + JPEG),
                                    fileToByteArray(path + IMAGEM_MEDIA_SIGLA + JPEG),
                                    fileToByteArray(path + THUMBNAIL_SIGLA + JPEG)));
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public static byte[] fileToByteArray(String pathPhoto) {
        File file;
        try {
            file = new File(pathPhoto);
            byte[] data = FileUtils.readFileToByteArray(file);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void copy(File sourceLocation, File targetLocation){
        if(sourceLocation.isDirectory()){
            copyDirectory(sourceLocation, targetLocation);
        }else{
            copyFile(sourceLocation, targetLocation);
        }
    }

    public static void copyDirectory(File source, File target){
        if(!target.exists())
            target.mkdirs();

        for(String f: source.list())
            copy(new File(source, f), new File(target, f));
    }

    public static void copyFile(File source, File target){
        try {
            InputStream in = new FileInputStream(source);
            OutputStream out = new FileOutputStream(target);

            byte[] buf = new byte[1024];
            int length;
            while((length = in.read(buf)) > 0)
                out.write(buf, 0, length);
            in.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Bitmap getResizeBitmap(Bitmap bm, int newWidth, int newHeight){
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

}
