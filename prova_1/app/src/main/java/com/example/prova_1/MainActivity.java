package


    @Override com.example.prova_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.View;
import android.widget.Button;

    public class MainActivity extends AppCompatActivity {

        Button botaoCadastro = (Button) findViewById(R.id.cadastro);
        Button botaoExtrato = (Button) findViewById(R.id.extrato);
        Button botaoSair = (Button) findViewById(R.id.sair);

        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            SQLiteDatabase db = openOrCreateDatabase("contacts.db", MODE_PRIVATE, null);
            db.execSQL("CREATE TABLE IF NOT EXISTS contacts(name VARCHAR, age INT(3))");

            db.execSQL("INSERT INTO contacts(name, age) VALUES ('Maferibe',23)");
            db.execSQL("INSERT INTO contacts(name, age) VALUES ('Erika',21)");


            Cursor cursor = db.rawQuery("SELECT name,age FROM contacts", null);
            int nameIndex = cursor.getColumnIndex("name");
            int ageIndex = cursor.getColumnIndex("age");

            cursor.moveToFirst();
            while(cursor != null){
                Log.i("RESULTADO - nome: ", cursor.getString(nameIndex));
                Log.i("RESULTADO - idade: ", cursor.getString(ageIndex));
                cursor.moveToNext();
            }

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void irCadastro (){
        Intent it = new Intent(this,CadastroActivity.class);
        startActivity(it);
        System.out.println("ir cadastro");
    }
    public void irExtrato (){
        Intent it = new Intent(this,ExtratoActivity.class);
        startActivity(it);
        System.out.println("ir cadastro");
    }

    public void irSair (){
        finishAndRemoveTask();
    }


}




