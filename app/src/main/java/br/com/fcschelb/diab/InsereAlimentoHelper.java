package br.com.fcschelb.diab;

import android.widget.EditText;

import br.com.fcschelb.diab.modelo.Alimento;

public class InsereAlimentoHelper {

    private EditText campoDescricao;
    private EditText campoPorcao;
    private EditText campoCarboidrato;
    private EditText campoFibra;

    private Alimento alimento;

    public InsereAlimentoHelper(InsereAlimentoActivity activity) {

        campoDescricao = activity.findViewById(R.id.insere_alimento_descricao);
        campoPorcao = activity.findViewById(R.id.insere_alimento_porcao);
        campoCarboidrato = activity.findViewById(R.id.insere_alimento_carboidrato);
        campoFibra = activity.findViewById(R.id.insere_alimento_fibras);

        alimento = new Alimento();
    }

    public Alimento pegaAlimento() {

        alimento.setDescricao(campoDescricao.getText().toString());
        alimento.setPorcao(campoPorcao.getText().toString());
        alimento.setCarboidratos(campoCarboidrato.getText().toString());
        alimento.setFibras(campoFibra.getText().toString());

        return alimento;
    }

    public void preencheCampos(Alimento alimento) {

        campoDescricao.setText(alimento.getDescricao());
        campoPorcao.setText(alimento.getPorcao());
        campoCarboidrato.setText(alimento.getCarboidratos());
        campoFibra.setText(alimento.getFibras());

        this.alimento = alimento;
    }
}
