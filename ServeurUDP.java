import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServeurUDP {




        public static void main(String[] args) {
            final int PORT = 1234;

            try (DatagramSocket serveurSocket = new DatagramSocket(PORT)) {
                System.out.println("Le serveur est en écoute sur le port " + PORT);

                while (true) {
                    byte[] buffer = new byte[1024];

                    // Attendre un message du client
                    DatagramPacket receptionPacket = new DatagramPacket(buffer, buffer.length);
                    serveurSocket.receive(receptionPacket);

                    // Extraire le message du paquet
                    String message = new String(receptionPacket.getData(), 0, receptionPacket.getLength());

                    // Extraire le prénom et le nom du message
                    String[] parts = message.split(" ");
                    String prenom = parts[0];
                    String nom = parts[1];

                    // Construire la réponse
                    String reponse = "Bienvenue " + prenom + " " + nom;

                    // Envoyer la réponse au client
                    DatagramPacket envoiPacket = new DatagramPacket(reponse.getBytes(), reponse.length(), receptionPacket.getAddress(), receptionPacket.getPort());
                    serveurSocket.send(envoiPacket);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


