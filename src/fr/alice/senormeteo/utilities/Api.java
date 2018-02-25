package fr.alice.senormeteo.utilities;

/**
 * Refactoring : url de base et api key originiellement
 * présentes dans la classe MainFrame
 */

public class Api {

	private static final String FORECAST_API_BASE_URL = "https://api.darksky.net/forecast/";
	private static final String FORECAST_API_KEY = "0a3fefca58c6279de4fbfc6a38f9fa3f";
	
	public static String getForecastUrl(double latitude, double longitude) {
		return FORECAST_API_BASE_URL + FORECAST_API_KEY + "/" + latitude + "," + longitude;
	}
}
