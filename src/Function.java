import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Function {

    public static String hashPassword( String password ) {
        byte [] bytesOfPassword = password . getBytes ( StandardCharsets.UTF_8) ;
        byte [] md5Digest = new byte[0];
        try {
            md5Digest = MessageDigest.getInstance("MD5").digest( bytesOfPassword) ;
        } catch( NoSuchAlgorithmException e ) {
            return null ;
        }
        return Base64.getEncoder( ).encodeToString( md5Digest ) ;
    }
    public static void ChangeTxt(ReadingBackUp list) throws IOException {
        FileWriter monitoring = new FileWriter("assets\\data\\backup.dat");
        for (User user : list.user){
            monitoring.write("user\t"+user.username+"\t"+user.password+"\t"+user.club_member+"\t"+user.admin+"\n");
        }
        for (Film films : list.film){
            monitoring.write("film\t"+films.getFilm_name()+"\t"+films.getTrailer_path()+"\t"+films.getDuration()+"\n");
            for(Hall halls : films.halls){
                monitoring.write("hall\t"+halls.film_name+"\t"+halls.hall_name+"\t"+halls.price_per_seat+"\t"+halls.row+"\t"+halls.column+"\n");
                for(Seat seats : halls.seat_list){
                    monitoring.write("seat\t"+seats.film_name+"\t"+seats.hall_name+"\t"+seats.row_of_seat+"\t"+seats.column_of_seat+"\t"+seats.owner_name+"\t"+seats.price_that_it_has_been_bought+"\n");
                }
            }
        }
        monitoring.close();
    }
}
