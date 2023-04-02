package telran.chat.client.task;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import telran.chat.model.Message;

public class Receiver implements Runnable {
	Socket socket;

	public Receiver(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (Socket socket = this.socket) {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			while(true) {
				Message message = (Message) ois.readObject();
				System.out.println(message.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Connection to server: " + socket.getInetAddress() + ":" + socket.getPort() + " closed");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
