package brainapps.com.br.eletroos;

import android.graphics.drawable.DrawableWrapper;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Inicial extends AppCompatActivity{

    private Toolbar toolbar;

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicial);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){

        getMenuInflater().inflate(R.menu.menu_toolbar,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){

        switch (menuItem.getItemId()){

            case R.id.os_menu:{
                Toast.makeText(this,"Minhas OS", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.despesas_menu:{
                Toast.makeText(this,"Despesas", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.clientes_menu:{
                Toast.makeText(this,"Clientes", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.materiais_menu:{
                Toast.makeText(this,"Materiais", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.item4_menu:{
                Toast.makeText(this,"item4", Toast.LENGTH_SHORT).show();
                break;
            }

        }
        return super.onOptionsItemSelected(menuItem);
    }*/
}
