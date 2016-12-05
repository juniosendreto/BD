package bancodados.vistoria.Util;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import bancodados.vistoria.R;
import bancodados.vistoria.model.FotoVistoria;

/**
 * Created by junio on 03/11/16.
 */

public class FragmentPicture extends Fragment {

    ImageButton imgB;
    Fragment frag;
    ImageView imgV;

    Integer position;
    List<FotoVistoria> fotoVistorias;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        final byte[] imagemMedia = getArguments().getByteArray("fotoVistoriaImagemMedia");
        final View layoutInflater = inflater.inflate(R.layout.fragment_tab_picture, container, false);
        final ImageView imgV = (ImageView) layoutInflater.findViewById(R.id.fragmentImagem);
        imgB = (ImageButton) layoutInflater.findViewById(R.id.fragmentExcluirImagem);
        imgV.setImageBitmap(BitmapFactory.decodeByteArray(imagemMedia, 0, imagemMedia.length));
        final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setMessage("Você realmente deseja excluir essa imagem?");

        frag = this;
        position = getArguments().getInt("position");
        fotoVistorias = getArguments().getParcelableArrayList("fotoVistorias");
        final List<CharSequence> picturesName = getArguments().getCharSequenceArrayList("picturesName");

        imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        File tempFile = FileUtil.tempDirectory();
                        int aux = 0;
                        if(tempFile.exists()) {
                            for (File f : tempFile.listFiles()) {
                                if(f.getName().length() == 25){
                                    if(aux != 1){
                                        for(int i = 0; i < picturesName.size(); i++){
                                            if(Arrays.equals(FileUtil.fileToByteArray(f.getPath()), imagemMedia)){
                                                if(f.delete()){
                                                    aux = 1;

                                                    FragmentManager fm = getActivity().getSupportFragmentManager();
                                                    fm.beginTransaction().remove(getActivity().getSupportFragmentManager().findFragmentByTag("fragment")).commit();

                                                    FragmentDialog overlay = new FragmentDialog();
                                                    Bundle args;
                                                    args = new Bundle();
                                                    args.putBoolean("vistoriaNova", false);
                                                    //args.putLong("chaveVistoria", vistoriaCriada.getId());
                                                    overlay.setArguments(args);
                                                    FragmentTransaction fragmentTransaction = fm.beginTransaction();
                                                    fragmentTransaction.add(overlay, "fragment");
                                                    //Log.d("--------", fm.getFragments().size() +"");

                                                    fragmentTransaction.commit();
                                                    //overlay.show(fm, "FragmentDialog");
                                                    break;
                                                }
                                            }
                                        }
                                    }else{
                                        break;
                                    }
                                }
                            }
                        }
                        dialog.dismiss();
                    }
                }).setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();



                //imgV.setImageBitmap(null);
                /*FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.imagensViewPager, fm.findFragmentByTag(getTag()));
                ft.addToBackStack(null);
                //Fragment f = fm.findFragmentByTag(getTag());
                //ft.remove(f);
                ft.commit();

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                //if(fragmentManager.getBackStackEntryCount()>0)
                    fragmentManager.popBackStack();
                //fotoVistorias.remove(position);

                //getActivity().getSupportFragmentManager().beginTransaction().remove(fm.findFragmentByTag(getTag())).commit();
                getActivity().getSupportFragmentManager().popBackStack();*/

            }
        });

        return layoutInflater;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        imgB = (ImageButton) view.findViewById(R.id.fragmentExcluirImagem);
        imgV = (ImageView) view.findViewById(R.id.fragmentImagem);
        FragmentManager fm = getActivity().getSupportFragmentManager();

        Log.d("------", fm.getFragments().get(0)+"");

        //if(img)

        /*imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                //if(fragmentManager.getBackStackEntryCount()>0)
                    fragmentManager.popBackStack();
                //getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();

            }
        });*/

    }


    @Override
    public void onResume() {
        super.onResume();

    }

}
