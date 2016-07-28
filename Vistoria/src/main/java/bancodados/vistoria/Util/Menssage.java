package bancodados.vistoria.Util;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by junio on 20/07/16.
 */
public class Menssage {

    public static ProgressDialog startProgressDialog(Context context, String waitingMsg) {
        final ProgressDialog progressDialog = new MarkerProgressView(context);
        progressDialog.setMessage(waitingMsg);
        progressDialog.show();
        return progressDialog;
    }

    private static class MarkerProgressView extends ProgressDialog {

        public MarkerProgressView(Context context) {
            super(context);
            this.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            this.setIndeterminate(true);
            this.setCanceledOnTouchOutside(false);
        }
    }

}
