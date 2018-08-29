package brainapps.com.br.eletroos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Cadastro extends AppCompatActivity {
    private AppCompatButton btnTeste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        btnTeste = (AppCompatButton) findViewById (R.id.btn_teste);

        btnTeste.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                Intent it = new Intent(Cadastro.this, MainActivity.class);
                startActivity(it);

            }
        });

    }

}
