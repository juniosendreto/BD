package bancodados.vistoria.Util;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import bancodados.vistoria.R;

/**
 * Created by junio on 03/11/16.
 */

public class FragmentPicture extends Fragment {


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        byte[] imagemMedia = getArguments().getByteArray("fotoVistoriaImagemMedia");
        final View layoutInflater = inflater.inflate(R.layout.fragment_tab_picture, container, false);
        ImageView imgV = (ImageView) layoutInflater.findViewById(R.id.fragmentImagem);
        ImageButton imgB = (ImageButton) layoutInflater.findViewById(R.id.fragmentExcluirImagem);
        imgV.setImageBitmap(BitmapFactory.decodeByteArray(imagemMedia, 0, imagemMedia.length));

        imgB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return layoutInflater;
    }

}
