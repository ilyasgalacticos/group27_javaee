package kz.bitlab.group27.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {

    String text = "Ilyas";

    private static Connection connection;

    static {
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_group_27?serverTimezone=UTC&useUnicode=true", "root", "");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static boolean addUser(Users user){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "INSERT INTO users (email, password, full_name, picture, city_id) " +
                    "VALUES (?, ?, ?, ?, ?)");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setString(4, user.getPicture());
            statement.setLong(5, user.getCity().getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static boolean updateUserPicture(Users user){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET picture = ? WHERE id = ?");

            statement.setString(1, user.getPicture());
            statement.setLong(2, user.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static boolean updateUserProfile(Users user){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET full_name = ? WHERE id = ?");

            statement.setString(1, user.getFullName());
            statement.setLong(2, user.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static boolean updateUserPassword(Users user){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "UPDATE users SET password = ? WHERE id = ?");

            statement.setString(1, user.getPassword());
            statement.setLong(2, user.getId());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static Users getUserByEmail(String email){

        Users user = null;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT u.id, u.email, u.password, u.full_name, u.picture, u.city_id, c.name as cityName, c.country_id, co.name AS countryName, co.code " +
                    "FROM users u " +
                    "INNER JOIN cities c ON c.id = u.city_id " +
                    "INNER JOIN countries co on c.country_id = co.id " +
                    "WHERE u.email = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                user = new Users(
                        resultSet.getLong("id"),
                        resultSet.getString("email"),
                        resultSet.getString("password"),
                        resultSet.getString("full_name"),
                        resultSet.getString("picture"),
                        new Cities(
                                resultSet.getLong("city_id"),
                                resultSet.getString("cityName"),
                                new Countries(
                                        resultSet.getLong("country_id"),
                                        resultSet.getString("countryName"),
                                        resultSet.getString("code")
                                )
                        )
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return user;
    }

    public static boolean addHotel(Hotels hotel){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement("INSERT INTO hotels (id, name, author_id, stars, description, added_date, price) " +
                    "VALUES (NULL, ?, ?, ?, ?, NOW(), ?)");

            statement.setString(1, hotel.getName());
            statement.setLong(2, hotel.getAuthor().getId());
            statement.setInt(3, hotel.getStars());
            statement.setString(4, hotel.getDescription());
            statement.setInt(5, hotel.getPrice());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static ArrayList<Hotels> getAllHotels(){

        ArrayList<Hotels> hotels = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT h.id, h.name, h.description, h.added_date, h.price, h.stars, h.author_id, h.likes, u.full_name, u.picture " +
                    "FROM hotels h " +
                    "INNER JOIN users u ON u.id = h.author_id " +
                    "ORDER BY h.price ASC");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){

                hotels.add(
                        new Hotels(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                new Users(
                                        resultSet.getLong("author_id"),
                                        null, null,
                                        resultSet.getString("full_name"),
                                        resultSet.getString("picture"),
                                        null
                                ),
                                resultSet.getString("description"),
                                resultSet.getInt("stars"),
                                resultSet.getInt("price"),
                                resultSet.getTimestamp("added_date"),
                                resultSet.getInt("likes")
                        )
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return hotels;
    }

    public static Hotels getHotel(Long id){

        Hotels hotel = null;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT h.id, h.name, h.description, h.added_date, h.price, h.stars, h.author_id, h.likes, u.full_name, u.picture " +
                    "FROM hotels h " +
                    "INNER JOIN users u ON u.id = h.author_id " +
                    "WHERE h.id = ? ");

            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                  hotel = new Hotels(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            new Users(
                                    resultSet.getLong("author_id"),
                                    null, null,
                                    resultSet.getString("full_name"),
                                    resultSet.getString("picture"),
                                    null
                            ),
                            resultSet.getString("description"),
                            resultSet.getInt("stars"),
                            resultSet.getInt("price"),
                            resultSet.getTimestamp("added_date"),
                            resultSet.getInt("likes")
                  );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return hotel;
    }

    public static boolean addComment(Comments comment){
        int rows = 0;
        try{

            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO comments (hotel_id, user_id, comment, added_date) " +
                            "VALUES (?, ?, ?, NOW())"
            );

            statement.setLong(1, comment.getHotel().getId());
            statement.setLong(2, comment.getUser().getId());
            statement.setString(3, comment.getComment());

            rows = statement.executeUpdate();
            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return rows>0;
    }

    public static ArrayList<Comments> getAllComments(Long hotelId){

        ArrayList<Comments> comments = new ArrayList<>();
        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.user_id, c.hotel_id, c.comment, c.added_date, u.full_name, u.picture " +
                    "FROM comments c " +
                    "INNER JOIN users u ON u.id = c.user_id " +
                    "WHERE c.hotel_id = ? " +
                    "ORDER BY c.added_date DESC ");

            statement.setLong(1, hotelId);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                comments.add(new Comments(
                        resultSet.getLong("id"),
                        new Hotels(
                                resultSet.getLong("hotel_id"),
                                null,null, null, 0,0, null
                        ),
                        new Users(
                                resultSet.getLong("user_id"),
                                null, null, resultSet.getString("full_name"), resultSet.getString("picture"), null
                        ),
                        resultSet.getString("comment"),
                        resultSet.getTimestamp("added_date")
                ));
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return comments;

    }

    public static ArrayList<Countries> getAllCountries(){

        ArrayList<Countries> countries = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM countries");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                countries.add(new Countries(
                        resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("code")
                ));
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return countries;
    }

    public static ArrayList<Cities> getCitiesByCountryId(Long id){

        ArrayList<Cities> cities = new ArrayList<>();

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.name, c.country_id, co.name AS countryName, co.code " +
                    "FROM cities c " +
                    "INNER JOIN countries co ON co.id = c.country_id " +
                    "WHERE c.country_id = ? ");

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                cities.add(
                    new Cities(
                            resultSet.getLong("id"),
                            resultSet.getString("name"),
                            new Countries(
                                    resultSet.getLong("country_id"),
                                    resultSet.getString("countryName"),
                                    resultSet.getString("code")
                            )
                    )
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return cities;
    }

    public static Cities getCityById(Long id){

        Cities city = null;

        try{

            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT c.id, c.name, c.country_id, co.name AS countryName, co.code " +
                    "FROM cities c " +
                    "INNER JOIN countries co ON co.id = c.country_id " +
                    "WHERE c.id = ? ");

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                city = new Cities(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    new Countries(
                            resultSet.getLong("country_id"),
                            resultSet.getString("countryName"),
                            resultSet.getString("code")
                    )
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return city;
    }

    public static ArrayList<Hotels> searchHotels(String name, int priceFrom, int priceTo, int starsFrom, int starsTo){

        ArrayList<Hotels> hotels = new ArrayList<>();

        try{

            String priceFromQuery = "";
            String priceToQuery = "";
            String starsFromQuery = "";
            String starsToQuery = "";

            if(priceFrom>=0){
                priceFromQuery = " AND h.price >= " + priceFrom;
            }

            if(priceTo>=0){
                priceToQuery = " AND h.price <= " + priceTo;
            }

            if(starsFrom>=0){
                starsFromQuery = " AND h.stars >= " + starsFrom;
            }

            if(starsTo>=0){
                starsToQuery = " AND h.stars <= " + starsTo;
            }

            String sqlQuery = "SELECT h.id, h.name, h.description, h.added_date, h.price, h.stars, h.author_id, u.full_name, u.picture " +
                    "FROM hotels h " +
                    "INNER JOIN users u ON u.id = h.author_id " +
                    "WHERE h.name LIKE ? " + priceFromQuery + priceToQuery + starsFromQuery + starsToQuery + " "+
                    "ORDER BY h.price ASC ";

            PreparedStatement statement = connection.prepareStatement(sqlQuery);
            statement.setString(1, "%"+name+"%");

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()){
                hotels.add(
                        new Hotels(
                                resultSet.getLong("id"),
                                resultSet.getString("name"),
                                new Users(
                                        resultSet.getLong("author_id"),
                                        null, null,
                                        resultSet.getString("full_name"),
                                        resultSet.getString("picture"),
                                        null
                                ),
                                resultSet.getString("description"),
                                resultSet.getInt("stars"),
                                resultSet.getInt("price"),
                                resultSet.getTimestamp("added_date")
                        )
                );
            }

            statement.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return hotels;
    }

    public static int toLikeHotel(Long hotelId, Long userId){

        int likes = 0;
        boolean exists = false;

        try{

            PreparedStatement statement1 = connection.prepareStatement("" +
                    "SELECT * FROM likes WHERE hotel_id = ? AND user_id = ?");

            statement1.setLong(1, hotelId);
            statement1.setLong(2, userId);

            ResultSet resultSet1 = statement1.executeQuery();

            if(resultSet1.next()){
                exists = true;
            }

            statement1.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        try {

            String sqlText = "INSERT INTO likes(id, hotel_id, user_id) VALUES(NULL, ?, ?)";

            if(exists) {
                sqlText = "DELETE FROM likes WHERE hotel_id = ? AND user_id = ?";
            }

            PreparedStatement statement2 = connection.prepareStatement(sqlText);

            statement2.setLong(1, hotelId);
            statement2.setLong(2, userId);

            statement2.executeUpdate();
            statement2.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        try{

            PreparedStatement statement3 = connection.prepareStatement("" +
                    "SELECT COUNT(*) likeCount FROM likes WHERE hotel_id = ?");

            statement3.setLong(1, hotelId);
            ResultSet resultSet3 = statement3.executeQuery();

            if(resultSet3.next()){
                likes = resultSet3.getInt("likeCount");
            }

            statement3.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        try{

            PreparedStatement statement4 = connection.prepareStatement("" +
                    "UPDATE hotels SET likes = ? WHERE id = ?");
            statement4.setInt(1, likes);
            statement4.setLong(2, hotelId);

            statement4.executeUpdate();
            statement4.close();

        }catch (Exception e){
            e.printStackTrace();
        }

        return likes;

    }

}
