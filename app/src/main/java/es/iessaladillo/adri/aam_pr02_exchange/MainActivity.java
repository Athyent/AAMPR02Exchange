package es.iessaladillo.adri.aam_pr02_exchange;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

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
    private ImageView imageTo;
    private ImageView imageFrom;
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
        imageTo = findViewById(R.id.imageTo);
        imageFrom = findViewById(R.id.imageFrom);
        btnExchange = findViewById(R.id.btnExchange);
        btnExchange.setOnClickListener(v -> exchange());
    }

    private void exchange() {
        if(txtAmount.getText().toString().equals(".")){
            txtAmount.setText("0.00");
            return;
        }
        if(txtAmount.getText().toString().equals("")){
            txtAmount.setText("0.00");
            return;
        }
        double Amount = Double.parseDouble(txtAmount.getText().toString());
        String message = "";
        if(rgFrom.getCheckedRadioButtonId()==R.id.rbFromEuro && rgTo.getCheckedRadioButtonId()==R.id.rbToDollar){
            message = Amount + " € = " + (Amount*1.15)+" $";
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
            imageFrom.setImageResource(R.drawable.ic_euro);
        }
        if(checkedId == R.id.rbToDollar){
            rbFromDollar.setEnabled(false);
            rbFromEuro.setEnabled(true);
            rbFromPound.setEnabled(true);
            imageFrom.setImageResource(R.drawable.ic_dollar);
        }
        if(checkedId == R.id.rbToPound){
            rbFromPound.setEnabled(false);
            rbFromDollar.setEnabled(true);
            rbFromEuro.setEnabled(true);
            imageFrom.setImageResource(R.drawable.ic_pound);
        }
    }
    private void OnCheckedCurrencyFrom(int checkedId) {

        if(checkedId == R.id.rbFromEuro){
            rbToEuro.setEnabled(false);
            rbToDollar.setEnabled(true);
            rbToPound.setEnabled(true);
            imageTo.setImageResource(R.drawable.ic_euro);
        }
        if(checkedId == R.id.rbFromDollar){
            rbToDollar.setEnabled(false);
            rbToEuro.setEnabled(true);
            rbToPound.setEnabled(true);
            imageTo.setImageResource(R.drawable.ic_dollar);
        }
        if(checkedId == R.id.rbFromPound){
            rbToPound.setEnabled(false);
            rbToDollar.setEnabled(true);
            rbToEuro.setEnabled(true);
            imageTo.setImageResource(R.drawable.ic_pound);
        }
    }


}
