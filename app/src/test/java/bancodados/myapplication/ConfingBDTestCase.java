package bancodados.myapplication;

import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;
import android.util.Log;

import org.junit.After;
import org.junit.Before;

import bancodados.myapplication.bd.DataBaseHelperFactory;

/**
 * Created by junio on 04/04/16.
 */
public class ConfingBDTestCase extends AndroidTestCase{

    private RenamingDelegatingContext context = null;

    @Before
    public void setUp(){
        try {
            context = new RenamingDelegatingContext(getContext(), "sys_vistoria");
            DataBaseHelperFactory.getIntanceConnection(context);

        }catch (Exception e){
            Log.d("-----------", "Erro ConfigBDTestCase " + e.getMessage());
        }
    }

    @After
    public void setDown(){
        DataBaseHelperFactory.getIntanceConnection(context).close();
    }

}
