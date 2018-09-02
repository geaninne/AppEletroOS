package brainapps.com.br.eletroos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import brainapps.com.br.eletroos.Model.M_Cadastrar;
import brainapps.com.br.eletroos.Model.M_Login;
import brainapps.com.br.eletroos.Model.Usuario;
import brainapps.com.br.eletroos.config.ConfiguracaoFirebase;

public class Cadastro extends AppCompatActivity {
    private AppCompatButton btnCadastrar;
    private AppCompatEditText nome;
    private AppCompatEditText sobrenome;
    private AppCompatEditText email;
    private AppCompatEditText senha;
    private Usuario usuario;


    private M_Cadastrar MCadastrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro);

        nome = (AppCompatEditText) findViewById(R.id.tf_nome);
        sobrenome = (AppCompatEditText) findViewById(R.id.tf_sobrenome);
        email = (AppCompatEditText) findViewById(R.id.tf_email);
        senha = (AppCompatEditText) findViewById(R.id.tf_senha);

        btnCadastrar = (AppCompatButton) findViewById (R.id.btn_cadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setSobrenome(sobrenome.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                MCadastrar = new M_Cadastrar();
                if( MCadastrar.cadastrarUsuario(usuario, getApplicationContext())){
                    finish();
                }

            }
        });

    }



}
