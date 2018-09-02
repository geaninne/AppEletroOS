package brainapps.com.br.eletroos;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;

import brainapps.com.br.eletroos.Model.Login;
import brainapps.com.br.eletroos.Model.Usuario;
import brainapps.com.br.eletroos.config.ConfiguracaoFirebase;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton btnLogin;
    private AppCompatButton btnCadastro;

    private AppCompatEditText editTextEmail;
    private AppCompatEditText editTextSenha;

    private TextInputLayout textLayoutEmail;
    private TextInputLayout textLayoutSenha;

    private Usuario usuario;
    private Login login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.login);



        editTextEmail = (AppCompatEditText) findViewById(R.id.tf_email);
        editTextSenha = (AppCompatEditText) findViewById(R.id.tf_senha);

        textLayoutEmail = (TextInputLayout) findViewById(R.id.tl_email);
        textLayoutSenha = (TextInputLayout) findViewById(R.id.tl_senha);

        btnLogin = (AppCompatButton) findViewById(R.id.btn_login);
        btnCadastro = (AppCompatButton) findViewById(R.id.btn_cadastro);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){
                    usuario = new Usuario();
                    usuario.setEmail(editTextEmail.getText().toString());
                    usuario.setSenha(editTextSenha.getText().toString());
                    login = new Login();

                    login.Validalogin(usuario.getEmail(), usuario.getSenha());
                    //telaInicial();





                };
            }
        });

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                telaCadastro(v);

            }
        });


    }

    public void telaCadastro(View view){

        Intent it = new Intent(MainActivity.this, Cadastro.class);

        startActivity(it);

    }

    public void telaInicial(){

        Intent it = new Intent(MainActivity.this, Inicial.class);

        startActivity(it);
        finish();

    }

    public void Mensagem (String mensagem){
        Toast.makeText(MainActivity.this, "Erro: " + mensagem, Toast.LENGTH_LONG).show();
    }



    private boolean validateForm(){
        if(editTextEmail.getText().toString().isEmpty()){
            textLayoutEmail.setErrorEnabled(true);
            textLayoutEmail.setError("Preencha com seu email");
            return false;
        } else{
            textLayoutEmail.setErrorEnabled(false);

        }

        if(editTextSenha.getText().toString().isEmpty()){
            textLayoutSenha.setErrorEnabled(true);
            textLayoutSenha.setError("Preencha com a sua senha");
            return false;
        } else{
            textLayoutSenha.setErrorEnabled(false);
        }

        return true;
    }


}
