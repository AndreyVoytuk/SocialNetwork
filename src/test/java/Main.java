import JdbcDatasource.exceptions.DbFailException;
import JdbcDatasource.implementDao.UserDaoImpl;
import bean.Post;
import bean.User;

public class Main {
    public static void main(String[] args) {
        UserDaoImpl userDao = new UserDaoImpl ();
        User user = new User ("Shahtar", "roxOXE", "Xerox@ukr.net", "active");
        Post post = new Post ();

        try {
            userDao.addUser (user);
        } catch (DbFailException e) {
            e.printStackTrace ();
        }
        System.out.println ("All!!!");

    }
}
