package brainapps.com.br.eletroos.Model;

import android.app.AlertDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;

import brainapps.com.br.eletroos.MainActivity;
import brainapps.com.br.eletroos.config.ConfiguracaoFirebase;

public class M_Login extends MainActivity {
    private DatabaseReference referenciaFirebase;
    private FirebaseAuth autenticacao;
    public boolean confirma = false;

    public boolean Validalogin(String email, String senha, final Context context){

        referenciaFirebase = ConfiguracaoFirebase.getFirebase();
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(email,
                senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if( task.isSuccessful() ){
                    String erroExececao = "Login Confirmado";

                    Toast.makeText(context,"" + erroExececao, Toast.LENGTH_LONG).show();

                }else
                {
                    String  erroExececao = "";
                    try{
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        erroExececao = "E-mail Inválido";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExececao = "Senha Inválida";
                    } catch (Exception e) {
                        erroExececao = "Erro ao autenticar usuário.";
                        e.printStackTrace();
                    }
                    Toast.makeText(context,"Erro:" + erroExececao, Toast.LENGTH_LONG).show();

                }
            }
        });
        if(autenticacao.getUid() == null){
            confirma = false;
        }else{
            confirma = true;
        }
        return confirma;
    }

}
