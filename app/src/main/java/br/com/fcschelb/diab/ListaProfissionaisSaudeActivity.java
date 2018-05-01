package br.com.fcschelb.diab;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.fcschelb.diab.dao.DiaBDAO;
import br.com.fcschelb.diab.modelo.Alimento;
import br.com.fcschelb.diab.modelo.ProfissionalSaude;

public class ListaProfissionaisSaudeActivity extends AppCompatActivity {

    final private int REQUEST_CODE_CALL = 100;
    private ListView listaProfissionais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_profissionais_saude);

        listaProfissionais = (ListView) findViewById(R.id.lista_profissionais_saude);
        Button btInserirProfissionalSaude = (Button) findViewById(R.id.lista_profissionais_saude_adicionar);

        btInserirProfissionalSaude.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itFormInsereProfissionaisSaude = new Intent(ListaProfissionaisSaudeActivity.this, InsereProfissionaisSaudeActivity.class);
                startActivity(itFormInsereProfissionaisSaude);
            }
        });

        listaProfissionais.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                ProfissionalSaude profissionalSaude = (ProfissionalSaude) listaProfissionais.getItemAtPosition(position);

                Intent itVaiEditarProfissionalSaude = new Intent(ListaProfissionaisSaudeActivity.this, InsereProfissionaisSaudeActivity.class);
                itVaiEditarProfissionalSaude.putExtra("prof_saude", profissionalSaude);
                startActivity(itVaiEditarProfissionalSaude);
            }
        });

        registerForContextMenu(listaProfissionais);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final ProfissionalSaude profissionalSaude = (ProfissionalSaude) listaProfissionais.getItemAtPosition(info.position);

        criaMenuVisitarPagina(menu, profissionalSaude);
        criaMenuDeletar(menu, profissionalSaude);
        criaMenuEfetuaLigacao(menu, profissionalSaude);

    }

    private void criaMenuEfetuaLigacao(ContextMenu menu, final ProfissionalSaude profissionalSaude) {
        MenuItem itemEfetuarLigacao = menu.add("Ligar");

        itemEfetuarLigacao.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (ActivityCompat.checkSelfPermission(ListaProfissionaisSaudeActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(ListaProfissionaisSaudeActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_CALL);
                }else{
                    Intent itEfetuarLigação = new Intent(Intent.ACTION_CALL);
                    itEfetuarLigação.setData(Uri.parse("tel:" + profissionalSaude.getTelefone()));
                    startActivity(itEfetuarLigação);
                }

                return false;
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case REQUEST_CODE_CALL:
                Toast.makeText(ListaProfissionaisSaudeActivity.this,"Permissão para ligações concedida",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void criaMenuVisitarPagina(ContextMenu menu, ProfissionalSaude profissionalSaude){
        //visitar o site
        MenuItem visitarUrl = menu.add("Ir para a página");
        Intent itIrParaPagina = new Intent(Intent.ACTION_VIEW);
        String url = profissionalSaude.getSite();
        if(!url.startsWith("http://")){
            url = "http://"+url;
        }
        itIrParaPagina.setData(Uri.parse(url));
        visitarUrl.setIntent(itIrParaPagina);
    }

    private  void criaMenuDeletar(ContextMenu menu, final ProfissionalSaude profissionalSaude){

        MenuItem itemDeletar = menu.add("Deletar");

        itemDeletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                DiaBDAO dao = new DiaBDAO(ListaProfissionaisSaudeActivity.this);

                dao.deletaProfissionalSaude(profissionalSaude);
                dao.close();

                carregaLista();

                return false;
            }
        });
    }

    private void carregaLista() {
        DiaBDAO dao = new DiaBDAO(this);
        List<ProfissionalSaude> profissionalSaudeList = dao.buscaProfissionais();
        dao.close();

        ArrayAdapter<ProfissionalSaude> adapterLista = new ArrayAdapter<ProfissionalSaude>(this,android.R.layout.simple_list_item_1, profissionalSaudeList);

        listaProfissionais.setAdapter(adapterLista);
    }
}