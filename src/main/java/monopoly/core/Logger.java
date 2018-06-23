package monopoly.core;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import monopoly.model.Player;

public class Logger {
	
	private BufferedWriter writer;

	private SimpleDateFormat date = new SimpleDateFormat("HH:mm:ss");
	
	public static final Logger ActionLogger = new Logger("Actions.csv");
	
	public static final String CARD = "CARD";
	
	public static final String STREET = "STREET";
	
	public static final String LANDING = "LANDING";
	
	public static final String PASSING = "PASSING";
	
	public static final String PLAYER = "PLAYER";
	
	public static final String MESSAGE = "MESSAGE";
	
	public static final String GAME = "GAME";
	
	public static long GAME_STARTED_AT = System.currentTimeMillis();
	
	protected Logger(String name){
		try {
			writer = new BufferedWriter(new FileWriter(new File(".").getAbsolutePath() + File.separator + name));
			write("Time", "Game Duration", "Player", "Category", "Action", "Details");
		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
	
	public void log(Player player, String action, String comment) {
		log(player, "", action, comment);
	}
	
	public void log(Player player, String category, String action, String comment) {
		try {
			write(date.format(new Date()), String.format("%.1fs", ((System.currentTimeMillis() - GAME_STARTED_AT) / 1000.0)), player != null ? player.getName() : "", action, comment);			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	private void write(String... data) throws IOException {
		for (String d : data) {
			writer.write(d);
			writer.write(";");
		}
		writer.newLine();
		writer.flush();		
	}
	
}
