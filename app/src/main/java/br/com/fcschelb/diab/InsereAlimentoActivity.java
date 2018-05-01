package br.com.fcschelb.diab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.fcschelb.diab.dao.DiaBDAO;
import br.com.fcschelb.diab.modelo.Alimento;

public class InsereAlimentoActivity extends AppCompatActivity {

    InsereAlimentoHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insere_alimento);

        helper = new InsereAlimentoHelper(this);

        Intent intent = getIntent();
        Alimento alimento = (Alimento) intent.getSerializableExtra("alimento");

        if(alimento !=null){
            helper.preencheCampos(alimento);
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

                Alimento alimento = helper.pegaAlimento();

                DiaBDAO dao = new DiaBDAO(this);

                if(alimento.getId() == null){
                    dao.insereAlimento(alimento);
                }else {
                    dao.alteraAlimento(alimento);
                }
                    dao.close();

                Toast.makeText(InsereAlimentoActivity.this,"Alimento "+alimento.getDescricao()+" adicionado!!",Toast.LENGTH_LONG).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
