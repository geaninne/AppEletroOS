package brainapps.com.br.eletroos;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.drawable.DrawableWrapper;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
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
    private NavigationView navigationView;

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

        navigationView = (NavigationView) findViewById(R.id.navView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.os_menu:{
                       // Toast.makeText(this,"Minhas OS", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.despesas_menu:{
                        //Toast.makeText(this,"Despesas", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.clientes_menu:{
                        Intent it = new Intent(Inicial.this, CadastroCliente.class);
                        startActivity(it);

                        //Toast.makeText(this,"Clientes", Toast.LENGTH_SHORT).show();
                        //telaCadastroCliente();
                        break;
                    }

                    case R.id.materiais_menu:{
                        //Toast.makeText(this,"Materiais", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.submenu_config:{
                        //Toast.makeText(this,"Configurações", Toast.LENGTH_SHORT).show();
                        break;
                    }

                    case R.id.submenu_sobre:{
                        //Toast.makeText(this,"Sobre", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            }
        });
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
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
