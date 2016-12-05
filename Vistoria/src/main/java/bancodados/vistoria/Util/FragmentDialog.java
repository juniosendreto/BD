package bancodados.vistoria.Util;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import bancodados.vistoria.R;
import bancodados.vistoria.model.FotoVistoria;
import bancodados.vistoria.model.Vistoria;

/**
 * Created by junio on 03/11/16.
 */

public class FragmentDialog extends DialogFragment {

    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;
    private BDFacade bdFacade;

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container);
        List<FotoVistoria> fotoVistorias = new ArrayList<>();

        /*if(!getArguments().getBoolean("vistoriaNova")){
            bdFacade = new BDFacade(getActivity());
            long chaveVistoria = getArguments().getLong("chaveVistoria");
            Vistoria vistoria = (Vistoria) bdFacade.getVistoriaDao().findById(Vistoria.class, chaveVistoria);
            fotoVistorias  = bdFacade.getFotoVistoriaDao().querryImagemPequena(vistoria);
        }else if(getArguments().getBoolean("vistoriaNova")){*/
            File tempFile = FileUtil.tempDirectory();
            if(tempFile.exists()){
                for(File f: tempFile.listFiles()){
                    if(f.getName().length() == 25){
                        FotoVistoria fotoVistoria = new FotoVistoria();
                        fotoVistoria.setImagemMedia(FileUtil.fileToByteArray(f.getAbsolutePath()));
                        fotoVistorias.add(fotoVistoria);
                    }
                }
            }
        //}

        // tab slider
        sectionsPagerAdapter = new SectionsPagerAdapter(getChildFragmentManager(), fotoVistorias);

        // Set up the ViewPager with the sections adapter.
        viewPager = (ViewPager) view.findViewById(R.id.imagensViewPager);
        viewPager.setAdapter(sectionsPagerAdapter);

        return view;
    }


    @Override
    public void onResume() {
        int width = getResources().getDimensionPixelOffset(R.dimen.popup_width);
        int height = getResources().getDimensionPixelOffset(R.dimen.popup_height);
        getDialog().getWindow().setLayout(width, height);

        super.onResume();
    }
}
