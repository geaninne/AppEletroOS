package brainapps.com.br.eletroos.Model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import brainapps.com.br.eletroos.Cadastro;
import brainapps.com.br.eletroos.MainActivity;
import brainapps.com.br.eletroos.config.ConfiguracaoFirebase;

public class M_Cadastrar extends MainActivity {
    private FirebaseAuth autenticacao;
    public boolean confirma = false;
    public boolean cadastrarUsuario(final Usuario usuario, final Context context) {
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(M_Cadastrar.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if( task.isSuccessful() ){
                    Toast.makeText(context, "Sucesso ao cadastrar usuário", Toast.LENGTH_LONG).show();

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
                    Toast.makeText(context,"Erro:" + erroExececao, Toast.LENGTH_LONG).show();

                }
            }
        });

        if(autenticacao.getUid() == null){
            confirma = true;
            autenticacao.signOut(); //ao cadastrar automaticamente fica logado, logo deve deslogar ele para ele logar novamente
        }else
        {
            confirma = false;


        }
        return confirma;
    }
}
