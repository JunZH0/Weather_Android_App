package com.example.consultatiempo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.consultatiempo.retrofitdata.WeatherResult;
import com.example.consultatiempo.retrofitutils.RetrofitClient;
import com.example.consultatiempo.retrofitutils.APIRestService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class WeatherActivity extends AppCompatActivity {

    TextView tvRegion;
    TextView tvTemperatura;
    TextView tvHumedad;
    TextView tvLluvia;
    TextView tvHora;
    TextView tvResumen;

    ImageView ivIcono;

    public static String exclude = "minutely,hourly,daily,alerts,flags";
    public static String lang = "es";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tvRegion = findViewById(R.id.txtRegion);
        tvTemperatura = findViewById(R.id.txtTiempo);
        tvHumedad = findViewById(R.id.txtPorcentaje);
        tvLluvia = findViewById(R.id.txtPorcentajeLluvia);
        tvHora = findViewById(R.id.txtHora);
        tvResumen = findViewById(R.id.txtTextoTiempo);
        ivIcono = findViewById(R.id.imgAstro);


        Retrofit retrofit = RetrofitClient.getClient(APIRestService.BASE_URL);
        APIRestService ars = retrofit.create(APIRestService.class);
        Call<WeatherResult> call = ars.getWeather(
                getIntent().getDoubleExtra("latitud",0),
                getIntent().getDoubleExtra("longitud", 0),
                exclude, lang);
        call.enqueue(new Callback<WeatherResult>() {
            @Override
            public void onResponse(Call<WeatherResult> call, Response<WeatherResult> response) {
                if (response.isSuccessful()) {
                    cargarDatos((WeatherResult) response.body());
                } else {
                    Log.e("ERROR_WS", response.code() + " - " + response.message());
                }
            }

            @Override
            public void onFailure(Call<WeatherResult> call, Throwable t) {
                Log.e("ERROR_WS", t.getMessage());
            }
        });

    }

    private void cargarDatos(WeatherResult weatherResult) {

        tvRegion.setText(weatherResult.getTimezone());
        tvHumedad.setText(String.valueOf(Math.round(weatherResult.getCurrently().getHumidity() * 100)) + "%");
        tvTemperatura.setText(String.valueOf(Math.round(weatherResult.getCurrently().getTemperature())));
        tvLluvia.setText(String.format("%s%%", weatherResult.getCurrently().getProbabilityPrecip() * 100) + "%");


        // Usando Timezone para convertir la hora UTC a la hora local
        TimeZone timeZone = TimeZone.getTimeZone(weatherResult.getTimezone());
        int timezoneOffset = timeZone.getRawOffset() + (timeZone.inDaylightTime(new Date()) ? timeZone.getDSTSavings() : 0);
        long time = weatherResult.getCurrently().getTime() + timezoneOffset;
        tvHora.setText(String.format("%s:%s %s",
                new SimpleDateFormat("h").format(new Date(time)),
                new SimpleDateFormat("mm").format(new Date(time)),
                new SimpleDateFormat("a").format(new Date(time))));


        tvResumen.setText(weatherResult.getCurrently().getSummary());

        String icon = weatherResult.getCurrently().getIcon();
        int iconResId = 0;

        switch (icon) {
            case "clear-day":
                iconResId = R.drawable.clear_day;
                break;
            case "clear-night":
                iconResId = R.drawable.clear_night;
                break;
            case "partly-cloudy-day":
                iconResId = R.drawable.partly_cloudy;
                break;
            case "partly-cloudy-night":
                iconResId = R.drawable.cloudy_night;
                break;
            case "cloudy":
                iconResId = R.drawable.cloudy;
                break;
            case "rain":
                iconResId = R.drawable.rain;
                break;
            case "sleet":
                iconResId = R.drawable.sleet;
                break;
            case "snow":
                iconResId = R.drawable.snow;
                break;
            case "wind":
                iconResId = R.drawable.wind;
                break;
            case "fog":
                iconResId = R.drawable.fog;
                break;
        }

        if (iconResId != 0) {
            ivIcono.setImageResource(iconResId);
        }


    }

}
