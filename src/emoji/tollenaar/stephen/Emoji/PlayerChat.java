package emoji.tollenaar.stephen.Emoji;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChat implements Listener {

	private EMain plugin;

	public PlayerChat(EMain plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onPlayerChat(PlayerChatEvent event) {
		String[] message = event.getMessage().split(" ");
		String builder = "";
		for (String word : message) {
			System.out.println(word + " " + (word.startsWith(":") && word.endsWith(":")));
			if (word.startsWith(":") && word.endsWith(":")) {
				// do check if this is a valid emoji
				word = getUnicode(word);
			}
			builder += word;
		}
		event.setMessage(builder);
	}

	private String getUnicode(String name) {
		try {
			// url of the website
			URL url = new URL("https://unicode-search.net/unicode-namesearch.pl?term=" + name + "&.submit=Search");

			// connection stuff
			URLConnection con = url.openConnection();
			InputStream is = con.getInputStream();

			Scanner in = new Scanner(is);
			boolean found = false;
			while (in.hasNextLine()) {
				String line = in.nextLine();
				System.out.println(line);
				if(line.contains("tr class=\"resod\"")){
					found = true;
				}
				
				if (found && line.contains("wbr")) {
					line = line.split(">")[2].split("<")[0];
					int value  = Integer.parseInt(line, 16);
					in.close();
					return Character.toString((char)value);
				}
			}
			in.close();

			return name;
		} catch (IOException | NumberFormatException ex) {
			return name;
		}

	}

}
