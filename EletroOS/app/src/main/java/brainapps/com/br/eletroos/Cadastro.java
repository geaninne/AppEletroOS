package brainapps.com.br.eletroos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Cadastro extends AppCompatActivity {
    private AppCompatButton btnTeste;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();

        //cadastro
        firebaseAuth.createUserWithEmailAndPassword("robertotubino@hotmail.com","1234abcd")
                .addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful() ){
                            Log.i("createUser", "Sucesso!");
                            AuthResult result = task.getResult();
                        }else{
                            Log.i("createUser", "Erro!" + task.getException().getMessage());
                        }
                    }
                });

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
