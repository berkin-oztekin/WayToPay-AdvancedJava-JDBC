import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

public class WaytoPayTest {

    @Test
    public void test() throws Exception {
        LoginPage loginPage = new LoginPage();
            //afterClass();

    }

    @After
    public void afterClass() {
        SqlQuery sqlQuery = new SqlQuery();
        LoginPage loginPage = new LoginPage();
        if (sqlQuery.queryEmail.equals(loginPage.textFieldEmail.getText())) { //We need the solve this problem in runtime
            System.out.println("These are equals");
        } else {
            System.out.println("NOT EQUAL WARNING");
        }


    }


}

