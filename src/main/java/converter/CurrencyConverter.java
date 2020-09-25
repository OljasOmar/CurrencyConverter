package converter;

import model.Model;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Scanner;


public class CurrencyConverter {


    private static DecimalFormat df = new DecimalFormat("0.00");
    private double conversionRate;

    // convert currencies
    public static String showRateToKZT(Model model) throws IOException {
        // get request to API
        URL url = new URL("https://v6.exchangerate-api.com/v6/be280de698db0d210910b91d/latest/KZT");
        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";

        while (scanner.hasNext()) {
            result += scanner.nextLine();
        }

        // converting string to JSON object
        JSONObject object = new JSONObject(result);
        JSONObject convRates = object.getJSONObject("conversion_rates");

        model.setConversionRate(convRates.getDouble("USD"));
        double usdToKzt = 1.00/model.getConversionRate();

        model.setConversionRate(convRates.getDouble("EUR"));
        double usdToEur = 1.00/model.getConversionRate();

        model.setConversionRate(convRates.getDouble("RUB"));
        double usdToRub = 1.00/model.getConversionRate();

        return "Conversion rates for KZT:" +"\n\n" +
                "1 USD = " + df.format(usdToKzt) + " KZT"+ "\n" +
                "1 EUR = " + df.format(usdToEur) + " KZT" + "\n" +
                "1 RUB = " + df.format(usdToRub) + " KZT" + "\n"
                ;
    }


    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }
}
