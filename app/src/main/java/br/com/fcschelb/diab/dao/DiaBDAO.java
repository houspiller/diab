package br.com.fcschelb.diab.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import br.com.fcschelb.diab.modelo.Alimento;
import br.com.fcschelb.diab.modelo.ProfissionalSaude;

public class DiaBDAO extends SQLiteOpenHelper{


    public DiaBDAO(Context context) {
        super(context, "DiaBDB", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlTabAlimentos = "CREATE TABLE Alimentos (id INTEGER PRIMARY KEY, descricao TEXT NOT NULL, porcao TEXT NOT NULL, carboidratos TEXT NOT NULL, fibras TEXT);";
        String sqlTabProfSaude = "CREATE TABLE Profissionais (id INTEGER PRIMARY KEY, nome TEXT NOT NULL, especialidade TEXT NOT NULL, clinica TEXT, telefone TEXT, celular TEXT, numeroConselho TEXT, site TEXT);";
        db.execSQL(sqlTabProfSaude);
        db.execSQL(sqlTabAlimentos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS Alimentos";
        String sql_prof = "DROP TABLE IF EXISTS Profissionais";
        db.execSQL(sql);
        db.execSQL(sql_prof);
        onCreate(db);
    }

    public void insereAlimento(Alimento alimento) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("descricao",alimento.getDescricao());
        contentValues.put("porcao",alimento.getPorcao());
        contentValues.put("carboidratos",alimento.getCarboidratos());
        contentValues.put("fibras",alimento.getFibras());

        db.insert("Alimentos",null,contentValues);
    }

    public void insereProfissional(ProfissionalSaude profissionalSaude) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome",profissionalSaude.getNome());
        contentValues.put("especialidade",profissionalSaude.getEspecialidade());
        contentValues.put("clinica",profissionalSaude.getNomeClinica());
        contentValues.put("telefone",profissionalSaude.getTelefone());
        contentValues.put("celular",profissionalSaude.getCelular());
        contentValues.put("numeroConselho",profissionalSaude.getNumeroConselho());
        contentValues.put("site",profissionalSaude.getSite());

        db.insert("Profissionais",null,contentValues);
    }

    public List<Alimento> buscaAlimentos() {

        String sql = "SELECT * FROM Alimentos";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        List<Alimento> listAlimento = new ArrayList<Alimento>();

        while (cursor.moveToNext()){

            Alimento alimento = new Alimento();

            alimento.setId(cursor.getLong(cursor.getColumnIndex("id")));
            alimento.setDescricao(cursor.getString(cursor.getColumnIndex("descricao")));
            alimento.setPorcao(cursor.getString(cursor.getColumnIndex("porcao")));
            alimento.setCarboidratos(cursor.getString(cursor.getColumnIndex("carboidratos")));
            alimento.setFibras(cursor.getString(cursor.getColumnIndex("fibras")));

            listAlimento.add(alimento);
        }

        cursor.close();
        return listAlimento;
    }

    public List<ProfissionalSaude> buscaProfissionais() {

        String sql = "SELECT * FROM Profissionais";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        List<ProfissionalSaude> listProfissionais = new ArrayList<ProfissionalSaude>();

        while (cursor.moveToNext()){

            ProfissionalSaude profissional = new ProfissionalSaude();

            profissional.setId(cursor.getLong(cursor.getColumnIndex("id")));
            profissional.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            profissional.setEspecialidade(cursor.getString(cursor.getColumnIndex("especialidade")));
            profissional.setNomeClinica(cursor.getString(cursor.getColumnIndex("clinica")));
            profissional.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
            profissional.setCelular(cursor.getString(cursor.getColumnIndex("celular")));
            profissional.setNumeroConselho(cursor.getString(cursor.getColumnIndex("numeroConselho")));
            profissional.setSite(cursor.getString(cursor.getColumnIndex("site")));

            listProfissionais.add(profissional);
        }

        cursor.close();
        return listProfissionais;
    }

    public void deletaAlimento(Alimento alimento) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {alimento.getId().toString()};

        db.delete("Alimentos","id = ?", params);
    }

    public void deletaProfissionalSaude(ProfissionalSaude profissionalSaude) {
        SQLiteDatabase db = getWritableDatabase();

        String[] params = {profissionalSaude.getId().toString()};

        db.delete("Profissionais","id = ?", params);
    }

    public void alteraAlimento(Alimento alimento) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosAlimento(alimento);

        String[] params = {String.valueOf(alimento.getId())};

        db.update("Alimentos", dados, "id = ?", params);

    }

    public void alteraProfissional(ProfissionalSaude profissionalSaude) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues dados = pegaDadosProfissional(profissionalSaude);

        String[] params = {String.valueOf(profissionalSaude.getId())};

        db.update("Profissionais", dados, "id = ?", params);

    }

    @NonNull
    private ContentValues pegaDadosAlimento(Alimento alimento) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("descricao",alimento.getDescricao());
        contentValues.put("porcao",alimento.getPorcao());
        contentValues.put("carboidratos",alimento.getCarboidratos());
        contentValues.put("fibras",alimento.getFibras());
        return contentValues;
    }

    @NonNull
    private ContentValues pegaDadosProfissional(ProfissionalSaude profissionalSaude) {

        ContentValues contentValues = new ContentValues();
        contentValues.put("nome",profissionalSaude.getNome());
        contentValues.put("especialidade",profissionalSaude.getEspecialidade());
        contentValues.put("clinica",profissionalSaude.getNomeClinica());
        contentValues.put("telefone",profissionalSaude.getTelefone());
        contentValues.put("celular",profissionalSaude.getCelular());
        contentValues.put("numeroConselho",profissionalSaude.getNumeroConselho());
        contentValues.put("site",profissionalSaude.getSite());

        return contentValues;
    }

}
