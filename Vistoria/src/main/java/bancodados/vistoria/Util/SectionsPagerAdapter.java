package bancodados.vistoria.Util;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;

import java.util.List;

import bancodados.vistoria.R;
import bancodados.vistoria.model.FotoVistoria;

/**
 * Created by junio on 03/11/16.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<FotoVistoria> fotoVistorias;
    private FragmentPicture fp;

    public SectionsPagerAdapter(FragmentManager fm, List<FotoVistoria> fotoVistorias) {
        super(fm);
        this.fotoVistorias = fotoVistorias;
    }

    @Override
    public Fragment getItem(int position) {
        fp = new FragmentPicture();
        Bundle args = new Bundle();
        args.putByteArray("fotoVistoriaImagemMedia", fotoVistorias.get(position).getImagemMedia());
        fp.setArguments(args);
        return fp;
    }

    @Override
    public int getCount() {
        return fotoVistorias.size();
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return fotoVistorias.get(position).getDescricao();
    }

}
