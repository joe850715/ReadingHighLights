package cc.openhome.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MessageDAOJdbcImpl implements MessageDAO {
    private DataSource dataSource;
    
    @Autowired
    public MessageDAOJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Message> messagesBy(String username) {
        try(var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement("SELECT * FROM t_message WHERE name = ?")) {
            stmt.setString(1, username);
            var rs = stmt.executeQuery();
            
            var messages = new ArrayList<Message>();
            while(rs.next()) {
                messages.add(new Message(
                    rs.getString(1),
                    rs.getLong(2),
                    rs.getString(3))
                );
            }
            return messages;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void createMessage(Message message) {
        try(var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement("INSERT INTO t_message(name, time, blabla) VALUES(?, ?, ?)")) {
            stmt.setString(1, message.getUsername());
            stmt.setLong(2, message.getMillis());
            stmt.setString(3, message.getBlabla());
            stmt.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteMessageBy(String username, String millis) {
        try(var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement("DELETE FROM t_message WHERE name = ? AND time = ?")) {
                stmt.setString(1, username);
                stmt.setLong(2, Long.parseLong(millis));
                stmt.executeUpdate();
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Message> newestMessages(int n) {
        try(var conn = dataSource.getConnection();
            var stmt = conn.prepareStatement(
                 "SELECT * FROM t_message ORDER BY time DESC LIMIT ?")) {
            stmt.setInt(1, n);
            var rs = stmt.executeQuery();
            
            var messages = new ArrayList<Message>();
            while(rs.next()) {
                messages.add(new Message(
                    rs.getString(1),
                    rs.getLong(2),
                    rs.getString(3))
                );
            }
            return messages;
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
