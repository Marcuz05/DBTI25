package fhwedel.JDBC;

import java.sql.*;

public class Hello {

    final static private String CONNECTION_STRING = "jdbc:mariadb://localhost:3306/firma";
    final static private String USER = "root";
    final static private String PASSWORD = "password";
    static private Connection dbConnection;

    public static void main(String[] args) {
        System.out.println("Start Main...");
        System.out.println("Connecting to DB...");

        try {
            dbConnection = connectToDB(CONNECTION_STRING, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("ERROR: Connection unsuccesfull!");
            return;
        }

        System.out.println("Connection established!");

        executeQuery(dbConnection, "INSERT INTO personal (pnr, name, vorname, geh_stufe, abt_nr, krankenkasse) VALUES (417, 'Krause', 'Henrik', 'it1', (SELECT abt_nr FROM abteilung WHERE name = 'Produktion'), 'tkk');");

        System.out.print(convertResultSet(selectQuery(dbConnection, "*", "personal")));
    }

    private static String convertResultSet(ResultSet resultSet) {
        if (resultSet == null) {
            throw new IllegalArgumentException("ResultSet is null!");
        }
        
        StringBuilder sb = new StringBuilder();

        try {
            ResultSetMetaData rsmd = resultSet.getMetaData();

            resultSet.last();
            int rowC = resultSet.getRow() + 1;
            resultSet.first();

            int colC = rsmd.getColumnCount();

            String[][] rsA = new String[rowC + 1][colC];
            int[] lenA = new int[colC];

            String curValue;

            for (int i = 0; i < colC; i++) {
                curValue = rsmd.getColumnName(i + 1);
                rsA[0][i] = curValue;
                lenA[i] = curValue.length();
            }

            for (int row = 1; row < rowC; row++) {
                for (int col = 0; col < colC; col++) {
                    curValue = resultSet.getString(col + 1);
                    rsA[row][col] = curValue;

                    if (curValue.length() > lenA[col]) {
                        lenA[col] = curValue.length();
                    }
                }
                resultSet.next();
            }

            sb.append("+");
            for (int i : lenA) {
                sb.append("-".repeat((i + 2))).append("+");
            }
            sb.append("\n");

            sb.append("|");
            for (int i = 0; i < colC; i++) {
                sb.append(String.format(" %-" + lenA[i] + "s |", rsA[0][i]));
            }
            sb.append("\n");

            sb.append("+");
            for (int i : lenA) {
                sb.append("-".repeat((i + 2))).append("+");
            }
            sb.append("\n");

            for (int row = 1; row < rowC; row++) {
                sb.append("|");
                for (int col = 0; col < colC; col++) {
                    sb.append(String.format(" %-" + lenA[col] + "s |", rsA[row][col]));
                }
                sb.append("\n");
            }

            sb.append("+");
            for (int i : lenA) {
                sb.append("-".repeat((i + 2))).append("+");
            }
            sb.append("\n");
        } catch (SQLException e) {
            System.out.printf("ERROR[%d]: Unable to write ResultSet: %s\n", e.getErrorCode(), e.getMessage());
        }
    

        return sb.toString();
    }

    private static ResultSet selectQuery(Connection con, String args, String tableName) {
        ResultSet result = null;
            
        try {
            Statement stmnt = con.createStatement();
            result = stmnt.executeQuery(String.format("SELECT %s FROM %s;", args, tableName));
        } catch (SQLException e) {
            System.out.printf("ERROR[%d]: Unable to fetch the requestet data: %s\n", e.getErrorCode(), e.getMessage());
        }

        return result;
    } 

    private static ResultSet executeQuery(Connection con, String query) {
        ResultSet result = null;
            
        try {
            Statement stmnt = con.createStatement();
            result = stmnt.executeQuery(query);
        } catch (SQLException e) {
            System.out.printf("ERROR[%d]: Unable to execute query: %s\n", e.getErrorCode(), e.getMessage());
        }

        return result;
    }

    public static Connection connectToDB(String connectionString, String user, String password) throws SQLException {
        return DriverManager.getConnection(connectionString, user, password);
    }

}
