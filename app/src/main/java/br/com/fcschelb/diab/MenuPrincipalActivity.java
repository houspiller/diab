package br.com.fcschelb.diab;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MenuPrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_menu_principal,menu);

        //Criando as preferencias do usuário
        SharedPreferences primeiro_acesso_pref = getSharedPreferences("primeiro_acesso", MODE_PRIVATE);

        if (primeiro_acesso_pref.contains("ja_abriu_app")) {
            Toast.makeText(this,"Segundo acesso ou mais", Toast.LENGTH_SHORT).show();
        } else {
            adicionarPreferenceJaAbriu(primeiro_acesso_pref);
            Toast.makeText(this,"Primeiro acesso", Toast.LENGTH_SHORT).show();
        }

        return super.onCreateOptionsMenu(menu);
    }

    private void adicionarPreferenceJaAbriu(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("ja_abriu_app", true);
        editor.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //TODO Fazer as opções do menu

        switch (item.getItemId()) {

            case R.id.menu_menu_principal:
                Intent itPerfilDoUsuario = new Intent(MenuPrincipalActivity.this, PerfilDoUsuarioActivity.class);
                startActivity(itPerfilDoUsuario);
                break;

        }
            return super.onOptionsItemSelected(item);
    }

    //metodo que vai para a tela que lista os alimentos
    public void irListaAlimentos(View view) {
        Intent itIrListaAlimentos = new Intent(this, ListaAlimentosActivity.class);
        startActivity(itIrListaAlimentos);
    }

    public void irCalculadoraGlicêmica(View view) {
        //TODO Metodo que vai para a Tela da Calculadora Glicêmica
        Toast.makeText(this,"Ainda falta implementar essa tela!", Toast.LENGTH_SHORT).show();
    }

    public void irProfissionaisDaSaude(View view) {
        Intent itIrListaProfissionais = new Intent(this, ListaProfissionaisSaudeActivity.class);
        startActivity(itIrListaProfissionais);
    }
}
