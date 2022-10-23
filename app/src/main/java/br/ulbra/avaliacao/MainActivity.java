package br.ulbra.avaliacao;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   EditText edtFilhos, edtSalario, edtnome;
   Button btnCalcular;
  RadioButton rbM, rbF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtFilhos = (EditText) findViewById(R.id.edtFilhos);
        edtSalario = (EditText) findViewById(R.id.edtSalario);
        btnCalcular = (Button) findViewById(R.id.btnCalcular);
        rbM  = (RadioButton) findViewById(R.id.rbM);
        rbF = (RadioButton) findViewById(R.id.rbF);
        edtnome = (EditText) findViewById(R.id.edtnome);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double Sbruto = Double.parseDouble(edtSalario.getText().toString());
                int filhos = Integer.parseInt(edtFilhos.getText().toString());



                AlertDialog.Builder dialogo = new AlertDialog.Builder(MainActivity.this);
                dialogo.setTitle("Descontos do seu salario");
                dialogo.setMessage(seletorSexo()+ " Seu salario bruto é de:  " + String.valueOf(Sbruto) +
                        "\n Filhos: " + String.valueOf(filhos)+ " "+
                        "\n inss de R$ " + inss(Sbruto)+
                        "\n Trasnporte de R$ " + transporte(Sbruto)+
                        "\n Ir de R$ " + ir(Sbruto)+
                        "\n Salario familia " + familia(Sbruto, filhos)+
                        "\n salario R$ "
                        +(Sbruto - inss(Sbruto) - ir(Sbruto) - transporte(Sbruto) + familia (Sbruto, filhos))
                );
        dialogo.show();
            }

        });



    }

    public String seletorSexo() {
        String sexo = null;
        if (rbM.isChecked()) {
            sexo = "Meu Senhor  " + edtnome.getText().toString();
        } else {
            sexo = "Minha Senhora  " + edtnome.getText().toString();
        }
        return sexo;
    }

    public double transporte(double salario){
      double t = 0;
      if (salario > 0 ){
          t = (salario * 2.5)/100;
      }
return t;

    }

    public double familia(double salario, int filho){
        double f = 0;
        if (salario <= 1212){
            f = filho * 56.47;

        }
        return f;
    }


    public double inss(double salario) {
        double novosalario = 0;
        if (salario <= 1212) {
            novosalario = salario * 0.075;
        } else if (salario >= 1212.01 && salario <= 2427.35) {
            novosalario = salario * 0.09;
        } else if (salario >= 2427.36 && salario <= 3641.03) {
            novosalario = salario * 0.12;
        } else if (salario >= 3641.04) {
            novosalario = salario * 0.14;
        }
        return novosalario;
    }

    public double ir(double salario){
        double irr = 0;
        if (salario <= 1903.98) {
            irr = salario * 0;
        } else if (salario >= 1903.99 && salario <= 2826.65) {
            irr = salario * 0.075;
        } else if (salario >= 2826.66 && salario <= 3751.05) {
            irr = salario * 0.15;
        } else {
            irr = salario * 0.225;
        }
        return irr;

    }




}



