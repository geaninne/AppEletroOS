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

import brainapps.com.br.eletroos.Model.Usuario;
import brainapps.com.br.eletroos.config.ConfiguracaoFirebase;

public class Cadastro extends AppCompatActivity {
    private AppCompatButton btnCadastrar;
    private AppCompatEditText nome;
    private AppCompatEditText sobrenome;
    private AppCompatEditText email;
    private AppCompatEditText senha;
    private Usuario usuario;

    private FirebaseAuth autenticacao;

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
                cadastrarUsuario();

            }
        });

    }

    private void cadastrarUsuario() {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(Cadastro.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){
                    Toast.makeText(Cadastro.this, "Sucesso ao cadastrar usuário", Toast.LENGTH_LONG).show();

                    FirebaseUser usuarioFirebase = task.getResult().getUser();
                    usuario.setId( usuarioFirebase.getUid() );
                    usuario.salvarUsuario();

                    autenticacao.signOut(); //ao cadastrar automaticamente fica logado, logo deve deslogar ele para ele logar novamente
                    finish();
                }else
                {
                    String erroExececao = "";
                    try{
                        throw task.getException();

                    } catch (FirebaseAuthWeakPasswordException e) {
                        erroExececao = "Digite uma senha mais forte! 6 caracteres no mínimo.";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExececao = "O e-mail digitado é inválido, digite um novo e-mail.";
                    }catch (FirebaseAuthUserCollisionException e) {
                        erroExececao = "O e-mail digitado é já está cadastrado, digite um novo e-mail.";
                    }catch (Exception e) {
                        erroExececao = "Erro ao cadastrar usuário.";
                        e.printStackTrace();
                    }
                    Toast.makeText(Cadastro.this,"Erro:" + erroExececao, Toast.LENGTH_LONG).show();

                }
            }
        });
    }

}
