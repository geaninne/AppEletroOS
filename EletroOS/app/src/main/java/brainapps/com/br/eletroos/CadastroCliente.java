package brainapps.com.br.eletroos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class CadastroCliente extends AppCompatActivity {

    private AppCompatButton btnCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_cliente);
        btnCadastro = (AppCompatButton) findViewById (R.id.btn_cadastro);


        btnCadastro.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent it = new Intent(CadastroCliente.this, Inicial.class);
                startActivity(it);

            }
        });

    }



}



