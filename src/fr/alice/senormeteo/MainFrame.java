package fr.alice.senormeteo;

import java.awt.Dimension;
import java.io.IOException;

import javax.swing.JFrame;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import fr.alice.senormeteo.utilities.Alert;
import fr.alice.senormeteo.utilities.Api;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 3376491977303099275L;
	
	private static final String GENERIC_ERROR_MESSAGE = "Oups, une erreur est survenue";
	private static final String INTERNET_CONNECTIVITY_ERROR_MESSAGE = "Veuillez v�rifier votre connexion internet";

	public MainFrame(String title) {
		super(title);
		
		double latitude = 37.8267;
		double longitude = -122.4233;
		
		//System.out.println("Avant ma requ�te");
		
		OkHttpClient Client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(Api.getForecastUrl(latitude, longitude))
				.build();
		Call call = Client.newCall(request);
		
		/**
		 * OkHttpClient a sa propre m�thode get asynchrone (d'o� l'inutilit� de SwingWorker)
		 * On ajoute la requ�te dans une file d'attente (FIFO) :
		 */
		call.enqueue(new Callback() {

			/**
			 * (non-Javadoc)
			 * @see okhttp3.Callback#onFailure(okhttp3.Call, java.io.IOException)
			 */
			@Override
			public void onFailure(Call call, IOException e) {
				Alert.error(MainFrame.this, INTERNET_CONNECTIVITY_ERROR_MESSAGE);
			}
			
			/**
			 * La partie graphique ne se g�re pas ici
			 * contrairement � SwingWorker, o� �a se g�re dans la m�thode done
			 */
			@Override
			public void onResponse(Call call, Response response) {
				
				/**
				 * Try with ressources
				 * Il s'agit d'utiliser la classe ResponseBody pr�sente dans okttp
				 * qui fait appel � Closeable et qui ferme automatiquement body
				 */
				try(ResponseBody body = response.body()) {
					//System.out.println(Thread.currentThread().getName());
					if(response.isSuccessful()) {
						
						String jsonData = body.string();
						//String jsonData = response.body().string(); /*"{toto : ";*/
						
						/**
						 * Cr�ation d'un objet forecast qui prend en compte notre requ�te
						 * requ�te qui doit �tre cast�e en JSONObject
						 */
						JSONObject forecast = (JSONObject) JSONValue.parseWithException(jsonData);
						System.out.println(forecast.get("timezone"));
						//System.out.println(forecast.get("currently"));
						JSONObject currently = (JSONObject) forecast.get("currently");
						System.out.println(currently.get("temperature"));
					
					} else {
						Alert.error(MainFrame.this, GENERIC_ERROR_MESSAGE);
						//Alert.info(MainFrame.this, "Oups, une erreur est survenue");
					}
			/**
			 * Multi catch / ParseException pour parseWithException
			 * et IOException pour la m�thode string
			 */
			} catch (ParseException | IOException e) {
				Alert.error(MainFrame.this, GENERIC_ERROR_MESSAGE);
				//e.printStackTrace();
			}
		  }
		});
		
		/**
		 * teste le fait que doInBackground est une m�thode asynchrone
		 */
		//System.out.println("Apr�s ma requ�te");
		
	}
	
	public Dimension getPreferredSize() {
		return new Dimension(500, 500);
	}
	
	@Override
	public Dimension getMinimumSize() {
		return getPreferredSize();
	}
	
	@Override
	public Dimension getMaximumSize() {
		return getPreferredSize();
	}
}
