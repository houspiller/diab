package br.com.fcschelb.diab;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.fcschelb.diab.dao.DiaBDAO;
import br.com.fcschelb.diab.modelo.ProfissionalSaude;

public class InsereProfissionaisSaudeActivity extends AppCompatActivity {

    InsereProfissionalSaudeHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_profissionais_saude);


        helper = new InsereProfissionalSaudeHelper(this);

        Intent intent = getIntent();
        ProfissionalSaude profissionalSaude = (ProfissionalSaude) intent.getSerializableExtra("prof_saude");

        if(profissionalSaude !=null){
            helper.preencheCampos(profissionalSaude);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_insere,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_insere:

                ProfissionalSaude profissionalSaude = helper.pegaProfissionalSaude();

                DiaBDAO dao = new DiaBDAO(this);

                if(profissionalSaude.getId() == null){
                    dao.insereProfissional(profissionalSaude);
                }else {
                    dao.alteraProfissional(profissionalSaude);
                }
                dao.close();

                Toast.makeText(InsereProfissionaisSaudeActivity.this,"Profissional "+profissionalSaude.getNome()+" adicionado!!",Toast.LENGTH_LONG).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
