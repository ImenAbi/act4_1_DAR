import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientUDP {


        public static void main(String[] args) {
            final String SERVEUR_IP = "localhost";
            final int SERVEUR_PORT = 1234;

            try (DatagramSocket clientSocket = new DatagramSocket()) {
                // Saisir le prénom et le nom
                String prenom = "John"; // Remplacez par votre prénom
                String nom = "Doe"; // Remplacez par votre nom

                // Concaténer le prénom et le nom
                String message = prenom + " " + nom;

                // Envoyer le message au serveur
                DatagramPacket envoiPacket = new DatagramPacket(message.getBytes(), message.length(), InetAddress.getByName(SERVEUR_IP), SERVEUR_PORT);
                clientSocket.send(envoiPacket);

                // Recevoir la réponse du serveur
                byte[] buffer = new byte[1024];
                DatagramPacket receptionPacket = new DatagramPacket(buffer, buffer.length);
                clientSocket.receive(receptionPacket);

                // Extraire la réponse du paquet
                String reponse = new String(receptionPacket.getData(), 0, receptionPacket.getLength());

                // Afficher la réponse du serveur
                System.out.println(reponse);

                // Afficher l'adresse et le numéro de port du serveur
                System.out.println("Adresse du serveur : " + receptionPacket.getAddress());
                System.out.println("Numéro de port du serveur : " + receptionPacket.getPort());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
