package ru.sberbank.springrest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.sberbank.springrest.dao.ClientDAO;
import ru.sberbank.springrest.model.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class SpringRestApplicationTests {

    Connection conn = DriverManager.getConnection("jdbc:h2:/Users/u19215114/Downloads/SpringRest;MV_STORE=false");
    Statement st = conn.createStatement();
    @Autowired
    private ClientDAO cdao;

    @Autowired
    private MockMvc mockMvc;

    public SpringRestApplicationTests() throws SQLException {
    }


    @Test
    public void findByIDTest() throws Exception {
        int idTest = 1;
        String fullName = "Антон Бажин";
        String phone = "89129823323";
        String ban = "1";
        ResultSet rs = st.executeQuery("select ID, FULL_NAME, PHONE, BANK_ACCOUNT_NUMBER from CLIENTS_TEST where id = 1");
        Assert.assertEquals(idTest, rs.findColumn("ID"));
//        Assert.assertEquals(fullName, rs.findColumn("FULL_NAME"));
//        Assert.assertEquals(phone, rs.findColumn("PHONE"));
//        Assert.assertEquals(ban, rs.findColumn("BANK_ACCOUNT_NUMBER"));
        rs.close();
    }

    @Test
    public void saveTest() throws Exception {
        st.executeUpdate("INSERT INTO CLIENTS_TEST  (id, full_name, phone, bank_account_number) VALUES (6, 'Виктор Сыркин', '89129823323', '6')");
        ResultSet countRs = st.executeQuery("select id = COUNT(*) from CLIENTS_TEST");
        Assert.assertEquals(6, countRs.toString());
    }

    @Test
    public void showCardsTest() throws Exception {
        System.out.println(cdao.showCards(1));
    }
}
