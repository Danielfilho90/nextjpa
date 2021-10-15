package school.cesar;

import java.sql.*;

public class JDBCTest {
    private static final String MYSQL_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/next_database?user=root&password=root1234";
    private static final String INSERT = "INSERT INTO next_database.next_aluno(nome, cpf) VALUES (?, ?)";
    private static final String UPDATE = "UPDATE next_database.next_aluno SET nome=? WHERE id=?";
    private static final String DELETE = "DELETE FROM next_database.next_aluno WHERE id=?";
    private static final String SELECT = "SELECT * from next_database.next_aluno";

    public static void main(String... args) {
        Integer returnedId = null;

        try {
            Class.forName(MYSQL_DRIVER);

            try (Connection connection = DriverManager.getConnection(CONNECTION_STRING)) {
                JDBCTest.printTable(connection);

                try (PreparedStatement statement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, "Aluno Muito Calminho");
                    statement.setString(2, "11122233344");

                    statement.execute();

                    ResultSet resultSet = statement.getGeneratedKeys();
                    while (resultSet.next()) {
                        returnedId = resultSet.getInt(1);
                    }

                    System.out.println("Inserção realizada com sucesso!");
                }

                JDBCTest.printTable(connection);

                try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
                    statement.setString(1, "Aluno Bem Bravo");
                    statement.setInt(2, returnedId);

                    statement.execute();
                    System.out.println("Atualização executada com sucesso!");
                }

                JDBCTest.printTable(connection);

                try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
                    statement.setInt(1, returnedId);

                    statement.execute();
                    System.out.println("Deleção realizada com sucesso!");
                }

                JDBCTest.printTable(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void printTable(Connection connection) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            System.out.println("========================================================");

            ResultSet resultSet = statement.executeQuery(SELECT);

            while (resultSet.next()) {
                int idAluno = resultSet.getInt("id");
                String nomeAluno = resultSet.getString("nome");
                String cpfAluno = resultSet.getString("cpf");
                System.out.println(idAluno + ", " + nomeAluno + ", " + cpfAluno);
            }

            System.out.println("========================================================");
        }
    }
}
