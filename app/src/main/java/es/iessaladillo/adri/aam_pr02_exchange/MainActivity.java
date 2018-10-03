package es.iessaladillo.adri.aam_pr02_exchange;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import es.iessaladillo.adri.aam_pr02_exchange.utils.ToastUtils;

public class MainActivity extends AppCompatActivity{
    private EditText txtAmount;
    private RadioGroup rgTo;
    private RadioGroup rgFrom;
    private RadioButton rbToEuro;
    private RadioButton rbToDollar;
    private RadioButton rbToPound;
    private RadioButton rbFromEuro;
    private RadioButton rbFromDollar;
    private RadioButton rbFromPound;
    private ImageView imgFrom;
    private ImageView imgTo;
    // TE RECOMIENDO QUE HAGAS Analyze -> Inspect Code...
    // TE AYUDARÁ A MEJORAR TU FORMA DE CODIFICAR. POR EJEMPLO, ESTA VARIABLE
    // PUEDE SER DEFINIDA COMO LOCAL AL MÉTODO EN EL QUE SE USA.
    private Button btnExchange;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        txtAmount = findViewById(R.id.txtAmount);
        rgTo = findViewById(R.id.rgTo);
        rgFrom = findViewById(R.id.rgFrom);
        rgTo.setOnCheckedChangeListener((group, checkedId) -> OnCheckedCurrencyTo(checkedId));
        rgFrom.setOnCheckedChangeListener((group, checkedId) -> OnCheckedCurrencyFrom(checkedId));
        rbToEuro = findViewById(R.id.rbToEuro);
        rbToDollar = findViewById(R.id.rbToDollar);
        rbToPound = findViewById(R.id.rbToPound);
        rbFromEuro = findViewById(R.id.rbFromEuro);
        rbFromDollar = findViewById(R.id.rbFromDollar);
        rbFromPound = findViewById(R.id.rbFromPound);
        // ESTAS DOS VISTAS NO SE LLAMAN IGUAL QUE EN LOS TESTS. FÍJATE BIEN.
        imgFrom = findViewById(R.id.imgFrom);
        imgTo = findViewById(R.id.imgTo);
        btnExchange = findViewById(R.id.btnExchange);
        btnExchange.setOnClickListener(v -> exchange());

        // AÑADO ESTAS DOS LÍNEAS PARA QUE PASE DOS DE LOS TESTS QUE FALLABAN.
        imgFrom.setTag(R.drawable.ic_euro);
        imgTo.setTag(R.drawable.ic_dollar);
    }

    private void exchange() {
        if(txtAmount.getText().toString().equals(".")){
            txtAmount.setText("0.00");
            return;
        }
        // POR QUÉ ESTÁ REPETIDO ESTE CÓDIGO ?
        if(txtAmount.getText().toString().equals("")){
            txtAmount.setText("0.00");
            return;
        }
        // NO DEFINAS VARIABLES CON LA PRIMERA LETRA EN MAYÚSCULA. ESO ESTÁ RESERVADO
        // PARA CLASES E INTERFACES.
        double Amount = Double.parseDouble(txtAmount.getText().toString());
        String message = "";
        // TE RECOMIENDO QUE REFORMATEES TU CÓDIGO CON Code -> Reformat Code

        // NORMALMENTE EL CAMBIO SE TIENE DE TODAS LAS MONEDAS RESPECTO A UN ÚNICA MONEDA
        // DE REFERENCIA, NORMALMENTE EL DÓLAR.
        if(rgFrom.getCheckedRadioButtonId()==R.id.rbFromEuro && rgTo.getCheckedRadioButtonId()==R.id.rbToDollar){
            // UTILIZA UN RECURSO DE CADENA CON PARÁMETROS. POR EJEMPLO:
            // %1$.2f € = %2$.2f £
            // TE CAMBIO A 1.17 PARA QUE CUMPLA EL TEST.
            // NO PASA EL TEST PORQUE Amount NO MUESTRA DOS DECIMALES.
            //message = Amount + " € = " + (Amount*1.17)+" $";
            message = String.format("%.2f € = %.2f $", Amount, Amount * 1.17);
            ToastUtils.ToastMessage(this,message );
        }
        if(rgFrom.getCheckedRadioButtonId()==R.id.rbFromEuro && rgTo.getCheckedRadioButtonId()==R.id.rbToPound){
            message = Amount + " € = " + (Amount*0.88)+" £";
            ToastUtils.ToastMessage(this,message );
        }
        if(rgFrom.getCheckedRadioButtonId()==R.id.rbFromDollar && rgTo.getCheckedRadioButtonId()==R.id.rbToEuro){
            message = Amount + " $ = " + (Amount*0.86)+" €";
            ToastUtils.ToastMessage(this,message );
        }
        if(rgFrom.getCheckedRadioButtonId()==R.id.rbFromDollar && rgTo.getCheckedRadioButtonId()==R.id.rbToPound){
            message = Amount + " $ = " + (Amount*0.76)+" £";
            ToastUtils.ToastMessage(this,message );
        }
        if(rgFrom.getCheckedRadioButtonId()==R.id.rbFromPound && rgTo.getCheckedRadioButtonId()==R.id.rbToEuro){
            message = Amount + " £ = " + (Amount*1.12)+" €";
            ToastUtils.ToastMessage(this,message );
        }
        if(rgFrom.getCheckedRadioButtonId()==R.id.rbFromPound && rgTo.getCheckedRadioButtonId()==R.id.rbToDollar){
            message = Amount + " £ = " + (Amount*1.30)+" $";
            ToastUtils.ToastMessage(this,message );
        }


    }

    private void OnCheckedCurrencyTo(int checkedId) {

        if(checkedId == R.id.rbToEuro){
            rbFromEuro.setEnabled(false);
            rbFromDollar.setEnabled(true);
            rbFromPound.setEnabled(true);
            imgTo.setImageResource(R.drawable.ic_euro);
            // AÑADO ESTA LÍNEA PARA QUE PASE LOS TESTS. FÍJATE BIEN.
            imgTo.setTag(R.drawable.ic_euro);
        }
        if(checkedId == R.id.rbToDollar){
            rbFromDollar.setEnabled(false);
            rbFromEuro.setEnabled(true);
            rbFromPound.setEnabled(true);
            imgTo.setImageResource(R.drawable.ic_dollar);
            // AÑADO ESTA LÍNEA PARA QUE PASE LOS TESTS. FÍJATE BIEN.
            imgTo.setTag(R.drawable.ic_dollar);
        }
        if(checkedId == R.id.rbToPound){
            rbFromPound.setEnabled(false);
            rbFromDollar.setEnabled(true);
            rbFromEuro.setEnabled(true);
            imgTo.setImageResource(R.drawable.ic_pound);
            // AÑADO ESTA LÍNEA PARA QUE PASE LOS TESTS. FÍJATE BIEN.
            imgTo.setTag(R.drawable.ic_pound);
        }
    }
    private void OnCheckedCurrencyFrom(int checkedId) {

        // TIENES CAMBIADOS LOS NOMBRES DE LOS IMAGEVIEW. EL imgFrom DEBERÍA
        // LLAMARSE imgFrom Y EL imgTo DEBERÍA LLAMARSE imgTo.
        // LOS HE CAMBIADO PARA QUE PASEN LOS TEST.

        if(checkedId == R.id.rbFromEuro){
            rbToEuro.setEnabled(false);
            rbToDollar.setEnabled(true);
            rbToPound.setEnabled(true);
            imgFrom.setImageResource(R.drawable.ic_euro);
            // AÑADO ESTA LÍNEA PARA QUE PASE LOS TESTS. FÍJATE BIEN.
            imgFrom.setTag(R.drawable.ic_euro);
        }
        if(checkedId == R.id.rbFromDollar){
            rbToDollar.setEnabled(false);
            rbToEuro.setEnabled(true);
            rbToPound.setEnabled(true);
            imgFrom.setImageResource(R.drawable.ic_dollar);
            // AÑADO ESTA LÍNEA PARA QUE PASE LOS TESTS. FÍJATE BIEN.
            imgFrom.setTag(R.drawable.ic_dollar);
        }
        if(checkedId == R.id.rbFromPound){
            rbToPound.setEnabled(false);
            rbToDollar.setEnabled(true);
            rbToEuro.setEnabled(true);
            imgFrom.setImageResource(R.drawable.ic_pound);
            // AÑADO ESTA LÍNEA PARA QUE PASE LOS TESTS. FÍJATE BIEN.
            imgFrom.setTag(R.drawable.ic_pound);
        }
    }


}
