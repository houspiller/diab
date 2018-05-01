package br.com.fcschelb.diab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import br.com.fcschelb.diab.dao.DiaBDAO;
import br.com.fcschelb.diab.modelo.Alimento;

public class ListaAlimentosActivity extends AppCompatActivity {

    private ListView listaAlimentos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alimentos);

        listaAlimentos = (ListView) findViewById(R.id.lista_menu);
        Button btInserirAlimento = (Button) findViewById(R.id.lista_alimentos_adicionar);

        btInserirAlimento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itFormInsereAlimento = new Intent(ListaAlimentosActivity.this,InsereAlimentoActivity.class);
                startActivity(itFormInsereAlimento);
            }
        });

        listaAlimentos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> lista, View item, int position, long id) {
                Alimento alimento = (Alimento) listaAlimentos.getItemAtPosition(position);

                Intent itVaiEditarAlimento = new Intent(ListaAlimentosActivity.this, InsereAlimentoActivity.class);
                itVaiEditarAlimento.putExtra("alimento", alimento);
                startActivity(itVaiEditarAlimento);
            }
        });

        registerForContextMenu(listaAlimentos);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem item = menu.add("Deletar");

        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                DiaBDAO dao = new DiaBDAO(ListaAlimentosActivity.this);
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;

                Alimento alimento = (Alimento) listaAlimentos.getItemAtPosition(info.position);

                dao.deletaAlimento(alimento);
                dao.close();

                carregaLista();

                return false;
            }
        });

    }

    private void carregaLista() {
        DiaBDAO dao = new DiaBDAO(this);
        List<Alimento> alimentoList = dao.buscaAlimentos();
        dao.close();

        ArrayAdapter<Alimento> adapterLista = new ArrayAdapter<Alimento>(this,android.R.layout.simple_list_item_1, alimentoList);

        listaAlimentos.setAdapter(adapterLista);
    }
}
