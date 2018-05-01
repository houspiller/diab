package br.com.fcschelb.diab;

import android.widget.EditText;

import br.com.fcschelb.diab.modelo.ProfissionalSaude;

public class InsereProfissionalSaudeHelper {


    private EditText campoNome;
    private EditText campoEspecialidade;
    private EditText campoNomeClinica;
    private EditText campoTelefone;
    private EditText campoCelular;
    private EditText campoNumeroConselho;
    private EditText campoSite;

    private ProfissionalSaude profissionalSaude;

    public InsereProfissionalSaudeHelper(InsereProfissionaisSaudeActivity activity) {

        campoNome = activity.findViewById(R.id.insere_profissional_nome);
        campoEspecialidade = activity.findViewById(R.id.insere_profissional_especialidade);
        campoNomeClinica = activity.findViewById(R.id.insere_profissional_nome_clinica);
        campoTelefone = activity.findViewById(R.id.insere_profissional_telefone);
        campoCelular = activity.findViewById(R.id.insere_profissional_celular);
        campoNumeroConselho = activity.findViewById(R.id.insere_profissional_numero_conselho);
        campoSite = activity.findViewById(R.id.insere_profissional_site);

        campoTelefone.addTextChangedListener(Mask.mask(campoTelefone,Mask.TELEFONE_MASK));
        campoCelular.addTextChangedListener(Mask.mask(campoCelular,Mask.TELEFONE_MASK));

        profissionalSaude = new ProfissionalSaude();
    }

    public ProfissionalSaude pegaProfissionalSaude() {

        profissionalSaude.setNome(campoNome.getText().toString());
        profissionalSaude.setEspecialidade(campoEspecialidade.getText().toString());
        profissionalSaude.setNomeClinica(campoNomeClinica.getText().toString());
        profissionalSaude.setTelefone(campoTelefone.getText().toString());
        profissionalSaude.setCelular(campoCelular.getText().toString());
        profissionalSaude.setNumeroConselho(campoNumeroConselho.getText().toString());
        profissionalSaude.setSite(campoSite.getText().toString());

        return profissionalSaude;
    }

    public void preencheCampos(ProfissionalSaude profissionalSaude) {

        campoNome.setText(profissionalSaude.getNome());
        campoEspecialidade.setText(profissionalSaude.getEspecialidade());
        campoNomeClinica.setText(profissionalSaude.getNomeClinica());
        campoTelefone.setText(profissionalSaude.getTelefone());
        campoCelular.setText(profissionalSaude.getCelular());
        campoNumeroConselho.setText(profissionalSaude.getNumeroConselho());
        campoSite.setText(profissionalSaude.getSite());

        this.profissionalSaude = profissionalSaude;
    }

}
